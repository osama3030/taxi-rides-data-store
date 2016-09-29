package com.um.test.controller;

import com.um.test.repository.DriverRepository;
import com.um.test.taxiservice.api.DriversApi;
import com.um.test.taxiservice.model.DriverDefinition;
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
 * Implementation of the driver-related REST endpoints. Provides methods for adding, updating and searching for
 * driver entities (executable/executed tasks).
 */
@Controller
public class DriverController implements DriversApi {

    private static final Logger LOG = LoggerFactory.getLogger(DriverController.class);

    DriverRepository repository;

    @Autowired
    public DriverController(DriverRepository repository) {
        this.repository = repository;
    }

    /**
     * Schedule a new driver (task) for execution in the near future.
     */
    public ResponseEntity<String> submitDriver(@RequestBody DriverDefinition driver) {

        LOG.info("submitDriver:" + driver);

        DriverDefinition returnedDriver = repository.insert(driver);
        LOG.info("Returned ID:" + returnedDriver.getId());

        String id = "\"" + returnedDriver.getId() + "\"";
        return new ResponseEntity<String>(id, HttpStatus.CREATED);
    }

    /**
     * Delete information about an existing driver.
     */
    public ResponseEntity<Void> deleteDriverById(@PathVariable("id") String id) {

        LOG.info("Delete DriverByID:" + id);

        repository.delete(id);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    /**
     * Obtain information about an existing driver.
     */
    public ResponseEntity<DriverDefinition> getDriverById(@PathVariable("id") String id) {
        LOG.info("getDriverByID:" + id);

        DriverDefinition driver = repository.findOne(id);

        return new ResponseEntity<DriverDefinition>(driver, HttpStatus.OK);
    }

    /**
     * Obtain information about existing drivers matching a query-expression.
     */
    public ResponseEntity<List<DriverDefinition>> getDrivers() {

        LOG.info("Get drivers");

        List driversList = repository.findAll();

        return new ResponseEntity<>(driversList, HttpStatus.OK);
    }

    public ResponseEntity<Void> updateDriverById(@PathVariable("id") String id, @RequestBody DriverDefinition driver) {

        driver.setId(id);

        repository.save(driver);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Void> updateDriverAttributesById(@PathVariable("id") String id, @RequestBody DriverDefinition driver) {
        // custom code for mock (only return code changed)
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}