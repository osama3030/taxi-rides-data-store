package com.um.test.controller;

import com.um.test.repository.TaxiRepository;
import com.um.test.taxiservice.api.TaxisApi;
import com.um.test.taxiservice.model.TaxiDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Implementation of the taxi-related REST endpoints. Provides methods for adding, updating and searching for
 * taxi entities (executable/executed tasks).
 */
@Controller
public class TaxisController implements TaxisApi {

    private static final Logger LOG = LoggerFactory.getLogger(TaxisController.class);

    TaxiRepository repository;

    @Autowired
    public TaxisController(TaxiRepository repository) {
        this.repository = repository;
    }

    /**
     * Schedule a new taxi (task) for execution in the near future.
     */
    public ResponseEntity<String> submitTaxi(@RequestBody TaxiDefinition taxi) {

        LOG.info("submitTaxi:" + taxi);

        TaxiDefinition retunredTaxi = repository.insert(taxi);
        LOG.info("Returned ID:" + retunredTaxi.getId());

        String id = "\"" + retunredTaxi.getId() + "\"";
        return new ResponseEntity<String>(id, HttpStatus.CREATED);
    }

    /**
     * Delete information about an existing taxi.
     */
    public ResponseEntity<Void> deleteTaxiById(@PathVariable("id") String id) {

        LOG.info("Delete TaxiByID:" + id);

        repository.delete(id);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    /**
     * Obtain information about an existing taxi.
     */
    public ResponseEntity<TaxiDefinition> getTaxiById(@PathVariable("id") String id) {
        LOG.info("getTaxiByID:" + id);

        TaxiDefinition taxi = repository.findOne(id);

        return new ResponseEntity<TaxiDefinition>(taxi, HttpStatus.OK);
    }

    /**
     * Obtain information about existing taxis matching a query-expression.
     */
    public ResponseEntity<List<TaxiDefinition>> getTaxis() {

        LOG.info("Get taxis");

        List taxisList = repository.findAll();

        return new ResponseEntity<>(taxisList, HttpStatus.OK);
    }

    public ResponseEntity<Void> updateTaxiById(@PathVariable("id") String id, @RequestBody TaxiDefinition taxi) {

        taxi.setId(id);

        repository.save(taxi);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Void> updateTaxiAttributesById(@PathVariable("id") String id, @RequestBody TaxiDefinition taxi) {
        // custom code for mock (only return code changed)
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}