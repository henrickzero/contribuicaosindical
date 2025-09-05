package com.brcodigo.app.task.config;

import com.mongodb.client.MongoCollection;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.mongo.MongoLockProvider;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class ScheduleConfig {

    @Value("${task.database:task}")
    private String databaseName;

    @Bean
    public LockProvider lockProvider(MongoTemplate template) {
        MongoCollection<Document> mongo = template.getCollection(databaseName);
        return new MongoLockProvider(mongo);
    }
}
