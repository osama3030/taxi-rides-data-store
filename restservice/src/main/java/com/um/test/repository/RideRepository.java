package com.um.test.repository;


import com.um.test.taxiservice.model.RideDefinition;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RideRepository extends MongoRepository<RideDefinition, String> {


}