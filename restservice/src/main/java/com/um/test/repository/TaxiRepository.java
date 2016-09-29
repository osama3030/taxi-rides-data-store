package com.um.test.repository;


import com.um.test.taxiservice.model.TaxiDefinition;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaxiRepository extends MongoRepository<TaxiDefinition, String> {


}