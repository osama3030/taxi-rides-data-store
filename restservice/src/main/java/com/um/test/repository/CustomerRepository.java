package com.um.test.repository;


import com.um.test.taxiservice.model.CustomerDefinition;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<CustomerDefinition, String> {


}