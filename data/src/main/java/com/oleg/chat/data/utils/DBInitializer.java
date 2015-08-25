package com.oleg.chat.data.utils;

import com.google.common.reflect.ClassPath;
import com.oleg.chat.data.dao.IChatDao;
import com.oleg.chat.data.dao.ISequenceDao;
import com.oleg.chat.data.entities.impl.Sequence;
import com.oleg.chat.data.services.IChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class DBInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private Logger log = LoggerFactory.getLogger(DBInitializer.class);

    private final String scanPackage;

    public DBInitializer(String scanPackage) {
        this.scanPackage = scanPackage;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        ApplicationContext applicationContext = event.getApplicationContext();
        ISequenceDao sequenceDao = applicationContext.getBean(ISequenceDao.class);
        test(applicationContext);

        for (Document docAnnotation : findAnnotatedEntities()) {
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

    private Collection<Document> findAnnotatedEntities() {
        List<Document> documents = new ArrayList<>();
        ClassPath classPath;
        try {
            classPath = ClassPath.from(DBInitializer.class.getClassLoader());
        } catch (IOException e) {
            log.warn("Error during initialize DB: {}", e.getLocalizedMessage());
            return documents;
        }

        Set<ClassPath.ClassInfo> classesInfo = classPath.getTopLevelClassesRecursive(scanPackage);
        for (ClassPath.ClassInfo clazzInfo : classesInfo) {
            Class clazz = clazzInfo.load();

            if (!clazz.isAssignableFrom(Sequence.class) && clazz.isAnnotationPresent(Document.class)) {
                documents.add((Document) clazz.getAnnotation(Document.class));
            }
        }
        return documents;
    }

    private void test(ApplicationContext applicationContext) {
        IChatDao chatDao = applicationContext.getBean(IChatDao.class);
        IChatService chatService = applicationContext.getBean(IChatService.class);
        chatService.getAll();
        int point = 5;
    }

}