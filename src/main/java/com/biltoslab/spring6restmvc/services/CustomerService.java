package com.biltoslab.spring6restmvc.services;

import com.biltoslab.spring6restmvc.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    List<CustomerDTO> listCustomers();
    Optional<CustomerDTO> getCustomerById(UUID id);
    CustomerDTO NewCustomer(CustomerDTO customer);
    void updateCustomer(UUID id, CustomerDTO customer);

    void deleteById(UUID id);

    void PatchCustomerById(UUID id, CustomerDTO customer);
}
