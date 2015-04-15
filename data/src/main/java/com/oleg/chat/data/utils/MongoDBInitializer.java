package com.oleg.chat.data.utils;

import com.google.common.reflect.ClassPath;
import com.oleg.chat.data.dao.ChatDao;
import com.oleg.chat.data.dao.SequenceDao;
import com.oleg.chat.data.domain.Sequence;
import com.oleg.chat.data.services.IChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.IOException;
import java.util.Set;

public class MongoDBInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private Logger log = LoggerFactory.getLogger(MongoDBInitializer.class);

    private String scanPackage;

    public MongoDBInitializer(String scanPackage) {
        this.scanPackage = scanPackage;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ClassPath classPath;
        try {
            classPath = ClassPath.from(MongoDBInitializer.class.getClassLoader());
        } catch (IOException e) {
            log.warn("Error during initialize DB: {}", e.getLocalizedMessage());
            return;
        }

        ApplicationContext applicationContext = event.getApplicationContext();
        SequenceDao sequenceDao = applicationContext.getBean(SequenceDao.class);

        test(applicationContext);

        Set<ClassPath.ClassInfo> classesInfo = classPath.getTopLevelClassesRecursive(scanPackage);
        for (ClassPath.ClassInfo clazzInfo : classesInfo) {
            Class clazz = clazzInfo.load();

            if (!clazz.isAssignableFrom(Sequence.class) && clazz.isAnnotationPresent(Document.class)) {
                Document docAnnotation = (Document) clazz.getAnnotation(Document.class);
                Sequence sequence = new Sequence();
                sequence.setSequence(0L);
                sequence.setId(docAnnotation.collection());

                try {
                    sequenceDao.insert(sequence);
                } catch (DuplicateKeyException e) {
                    log.info("Sequence for '{}' is already exist", sequence.getId());
                }
            }
        }
    }

    private void test( ApplicationContext applicationContext){
        ChatDao chatDao = applicationContext.getBean(ChatDao.class);
        IChatService chatService = applicationContext.getBean(IChatService.class);
        chatService.getAll();
        int point = 5;
    }

}