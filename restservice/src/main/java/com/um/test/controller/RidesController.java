package com.um.test.controller;

import com.um.test.repository.RideRepository;
import com.um.test.taxiservice.api.RidesApi;
import com.um.test.taxiservice.model.RideDefinition;
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
 * Implementation of the ride-related REST endpoints. Provides methods for adding, updating and searching for
 * ride entities (executable/executed tasks).
 */
@Controller
public class RidesController implements RidesApi {

    private static final Logger LOG = LoggerFactory.getLogger(RidesController.class);

    RideRepository repository;

    @Autowired
    public RidesController(RideRepository repository) {
        this.repository = repository;
    }

    /**
     * Schedule a new ride (task) for execution in the near future.
     */
    public ResponseEntity<String> submitRide(@RequestBody RideDefinition ride) {

        LOG.info("submitRide:" + ride);

        RideDefinition retunredRide = repository.insert(ride);
        LOG.info("Returned ID:" + retunredRide.getId());

        String id = "\"" + retunredRide.getId() + "\"";
        return new ResponseEntity<String>(id, HttpStatus.CREATED);
    }

    /**
     * Delete information about an existing ride.
     */
    public ResponseEntity<Void> deleteRideById(@PathVariable("id") String id) {

        LOG.info("Delete RideByID:" + id);

        repository.delete(id);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    /**
     * Obtain information about an existing ride.
     */
    public ResponseEntity<RideDefinition> getRideById(@PathVariable("id") String id) {
        LOG.info("getRideByID:" + id);

        RideDefinition ride = repository.findOne(id);

        return new ResponseEntity<RideDefinition>(ride, HttpStatus.OK);
    }

    /**
     * Obtain information about existing rides matching a query-expression.
     */
    public ResponseEntity<List<RideDefinition>> getRides() {

        LOG.info("Get rides");

        List ridesList = repository.findAll();

        return new ResponseEntity<>(ridesList, HttpStatus.OK);
    }

    public ResponseEntity<Void> updateRideById(@PathVariable("id") String id, @RequestBody RideDefinition ride) {

        ride.setId(id);

        repository.save(ride);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Void> updateRideAttributesById(@PathVariable("id") String id, @RequestBody RideDefinition ride) {
        // custom code for mock (only return code changed)
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}