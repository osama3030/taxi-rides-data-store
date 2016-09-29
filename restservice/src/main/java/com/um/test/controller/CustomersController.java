package com.um.test.controller;

import com.um.test.repository.CustomerRepository;
import com.um.test.taxiservice.api.CustomersApi;
import com.um.test.taxiservice.model.CustomerDefinition;
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
 * Implementation of the customer-related REST endpoints. Provides methods for adding, updating and searching for
 * customer entities (executable/executed tasks).
 */
@Controller
public class CustomersController implements CustomersApi {

    private static final Logger LOG = LoggerFactory.getLogger(CustomersController.class);

    CustomerRepository repository;

    @Autowired
    public CustomersController(CustomerRepository repository) {
        this.repository = repository;
    }

    /**
     * Schedule a new customer (task) for execution in the near future.
     */
    public ResponseEntity<String> submitCustomer(@RequestBody CustomerDefinition customer) {

        LOG.info("submitCustomer:" + customer);

        CustomerDefinition retunredCustomer = repository.insert(customer);
        LOG.info("Returned ID:" + retunredCustomer.getId());

        String id = "\"" + retunredCustomer.getId() + "\"";
        return new ResponseEntity<String>(id, HttpStatus.CREATED);
    }

    /**
     * Delete information about an existing customer.
     */
    public ResponseEntity<Void> deleteCustomerById(@PathVariable("id") String id) {

        LOG.info("Delete CustomerByID:" + id);

        repository.delete(id);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    /**
     * Obtain information about an existing customer.
     */
    public ResponseEntity<CustomerDefinition> getCustomerById(@PathVariable("id") String id) {
        LOG.info("getCustomerByID:" + id);

        CustomerDefinition customer = repository.findOne(id);

        return new ResponseEntity<CustomerDefinition>(customer, HttpStatus.OK);
    }

    /**
     * Obtain information about existing customers matching a query-expression.
     */
    public ResponseEntity<List<CustomerDefinition>> getCustomers() {

        LOG.info("Get customers");

        List customersList = repository.findAll();

        return new ResponseEntity<>(customersList, HttpStatus.OK);
    }

    public ResponseEntity<Void> updateCustomerById(@PathVariable("id") String id, @RequestBody CustomerDefinition customer) {

        customer.setId(id);

        repository.save(customer);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Void> updateCustomerAttributesById(@PathVariable("id") String id, @RequestBody CustomerDefinition customer) {
        // custom code for mock (only return code changed)
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}