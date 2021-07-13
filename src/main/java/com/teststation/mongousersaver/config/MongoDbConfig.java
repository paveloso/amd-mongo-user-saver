package com.teststation.mongousersaver.config;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = {"com.teststation.mongousersaver.dao"})
public class MongoDbConfig {

}
