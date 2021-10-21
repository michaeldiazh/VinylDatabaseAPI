package com.diazmic.VinylDatabaseAPI.db.repo.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {
    public @Bean MongoClient mongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }
}
