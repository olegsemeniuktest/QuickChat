package com.oleg.chat.data.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.oleg.chat.data.utils.DBInitializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.net.UnknownHostException;

/**
 * Created by Oleg Semeniuk.
 */
@Configuration
@ComponentScan("com.oleg.chat.data")
@Import(MongoDBSpringConfig.class)
public class DataModuleSpringConfig {

    @Bean
    public ApplicationListener DBInitializer() {
        DBInitializer DBInitializer = new DBInitializer("com.oleg.chat.data.entities");
        return DBInitializer;
    }

}
