package com.oleg.chat.data.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.oleg.chat.data.utils.MongoDBInitializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.net.UnknownHostException;
import java.util.Collection;

/**
 * Created by Oleg Semeniuk on 18.02.2015.
 */
@Configuration
@ComponentScan("com.oleg.chat.data")
@PropertySource("classpath:properties/jdbc.properties")
public class DataModuleSpringConfig {

    @Value("${mongodb.server}")
    private String mongoServer;
    @Value("${mongodb.port}")
    private int mongoPort;
    @Value("${mongodb.dbName}")
    private String dbName;
    @Value("${mongodb.username}")
    private String username;
    @Value("${mongodb.password}")
    private String password;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Mongo mongo() throws UnknownHostException {
        return new MongoClient(mongoServer, mongoPort);
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        UserCredentials userCredentials = new UserCredentials(username, password);
        return new SimpleMongoDbFactory(mongo(), dbName, userCredentials);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, new MongoMappingContext());
//        converter.setTypeMapper(new DefaultMongoTypeMapper());
        converter.afterPropertiesSet();


        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory(), converter);

        return mongoTemplate;
    }

    @Bean
    public ApplicationListener mongoDBInitializer() {
        MongoDBInitializer mongoDBInitializer = new MongoDBInitializer("com.oleg.chat.data.domain");
        return mongoDBInitializer;
    }

    private class CustomMongoTemplate extends MongoTemplate {

        public CustomMongoTemplate(Mongo mongo, String databaseName) {
            super(mongo, databaseName);
        }

        public CustomMongoTemplate(Mongo mongo, String databaseName, UserCredentials userCredentials) {
            super(mongo, databaseName, userCredentials);
        }

        public CustomMongoTemplate(MongoDbFactory mongoDbFactory) {
            super(mongoDbFactory);
        }

        public CustomMongoTemplate(MongoDbFactory mongoDbFactory, MongoConverter mongoConverter) {
            super(mongoDbFactory, mongoConverter);
        }

        @Override
        public void insert(Object objectToSave, String collectionName) {
            super.insert(objectToSave, collectionName);
        }

        @Override
        public void insert(Collection<? extends Object> batchToSave, String collectionName) {
            super.insert(batchToSave, collectionName);
        }

        @Override
        public void save(Object objectToSave, String collectionName) {
            super.save(objectToSave, collectionName);
        }
    }
}
