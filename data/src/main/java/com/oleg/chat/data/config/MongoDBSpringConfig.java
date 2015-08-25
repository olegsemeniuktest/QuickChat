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
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.net.UnknownHostException;

/**
 * Created by Oleg Semeniuk.
 */
@Configuration
@PropertySource("classpath:properties/jdbc.properties")
@Profile(DataProfiles.MONGO_DB)
public class MongoDBSpringConfig {

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

}
