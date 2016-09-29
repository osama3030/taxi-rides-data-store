package com.um.test.repository;


import com.um.test.taxiservice.model.DriverDefinition;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DriverRepository extends MongoRepository<DriverDefinition, String> {


}