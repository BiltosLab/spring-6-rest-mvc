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
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PatchMapping("{customerId}")
    public ResponseEntity<Customer> PatchCustomerById(@PathVariable("customerId") UUID Id, @RequestBody Customer customer) {
        customerService.PatchCustomerById(Id,customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("{customerId}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("customerId") UUID Id) {
        customerService.deleteById(Id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") UUID Id, @RequestBody Customer customer) {
        customerService.updateCustomer(Id, customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping
    public ResponseEntity<Customer> NewCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.NewCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/customer/"+savedCustomer.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        return customerService.listCustomers();
    }
    @RequestMapping(value = "{customerId}",method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable("customerId") UUID customerId) {
        return customerService.getCustomerById(customerId);
    }


}
