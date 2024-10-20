package com.biltoslab.spring6restmvc.services;

import com.biltoslab.spring6restmvc.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    List<CustomerDTO> listCustomers();
    Optional<CustomerDTO> getCustomerById(UUID id);
    CustomerDTO NewCustomer(CustomerDTO customer);
    Optional<CustomerDTO> updateCustomer(UUID id, CustomerDTO customer);

    Boolean deleteById(UUID id);

    Optional<CustomerDTO> PatchCustomerById(UUID id, CustomerDTO customer);
}
