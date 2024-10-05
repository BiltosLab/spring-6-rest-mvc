package com.biltoslab.spring6restmvc.controller;


import com.biltoslab.spring6restmvc.model.Customer;
import com.biltoslab.spring6restmvc.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CustomerController {
    public static final String CUSTOMERS_PATH = "/api/v1/customer";
    public static final String CUSTOMERS_PATH_ID = CUSTOMERS_PATH + "/{customerId}";

    private final CustomerService customerService;

    @PatchMapping(CUSTOMERS_PATH_ID)
    public ResponseEntity<Customer> PatchCustomerById(@PathVariable("customerId") UUID Id, @RequestBody Customer customer) {
        customerService.PatchCustomerById(Id,customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping(CUSTOMERS_PATH_ID)
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("customerId") UUID Id) {
        customerService.deleteById(Id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping(CUSTOMERS_PATH_ID)
    public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") UUID Id, @RequestBody Customer customer) {
        customerService.updateCustomer(Id, customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping(CUSTOMERS_PATH)
    public ResponseEntity<Customer> NewCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.NewCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/customer/"+savedCustomer.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @GetMapping(CUSTOMERS_PATH)
    public List<Customer> getAllCustomers() {
        return customerService.listCustomers();
    }
    @GetMapping(CUSTOMERS_PATH_ID)
    public Customer getCustomerById(@PathVariable("customerId") UUID customerId) {
        return customerService.getCustomerById(customerId).orElseThrow(NotFoundException::new);
    }


}
