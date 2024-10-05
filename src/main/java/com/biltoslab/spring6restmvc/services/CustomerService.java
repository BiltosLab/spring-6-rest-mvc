package com.biltoslab.spring6restmvc.services;

import com.biltoslab.spring6restmvc.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    List<Customer> listCustomers();
    Optional<Customer> getCustomerById(UUID id);
    Customer NewCustomer(Customer customer);
    void updateCustomer(UUID id, Customer customer);

    void deleteById(UUID id);

    void PatchCustomerById(UUID id, Customer customer);
}
