package com.codeway.adapter.persistence.configuration;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.codeway.adapter.persistence.repository")
public class MongoConfig {

    @Value("${spring.data.mongodb.port}")
    public String port;
    @Value("${spring.data.mongodb.host}")
    public String host;
    @Value("${spring.data.mongodb.database}")
    public String database;

    @Bean
    public MongoClient mongo() {
        return new MongoClient(host, Integer.valueOf(port));
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), database);
    }


}
