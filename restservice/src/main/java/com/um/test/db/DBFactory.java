package com.um.test.db;

import com.mongodb.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;

public class DBFactory {
    private final MongoDbFactory mongo;

    @Autowired
    public DBFactory(MongoDbFactory mongo) {
        this.mongo = mongo;
    }

    public void example() {
        DB db = mongo.getDb();

    }

}