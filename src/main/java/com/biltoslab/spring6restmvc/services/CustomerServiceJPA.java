package com.biltoslab.spring6restmvc.services;

import com.biltoslab.spring6restmvc.mappers.CustomerMapper;
import com.biltoslab.spring6restmvc.model.CustomerDTO;
import com.biltoslab.spring6restmvc.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;




    @Override
    public List<CustomerDTO> listCustomers() {

        return List.of();
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.empty();
    }

    @Override
    public CustomerDTO NewCustomer(CustomerDTO customer) {
        return null;
    }

    @Override
    public void updateCustomer(UUID id, CustomerDTO customer) {

    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public void PatchCustomerById(UUID id, CustomerDTO customer) {

    }
}
