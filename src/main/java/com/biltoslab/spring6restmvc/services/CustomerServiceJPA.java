package com.biltoslab.spring6restmvc.services;

import com.biltoslab.spring6restmvc.mappers.CustomerMapper;
import com.biltoslab.spring6restmvc.model.CustomerDTO;
import com.biltoslab.spring6restmvc.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;




    @Override
    public List<CustomerDTO> listCustomers() {

        return customerRepository.findAll().stream().map(customerMapper::CustomerToCustomerDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.ofNullable(customerMapper.CustomerToCustomerDTO(customerRepository.findById(id).orElse(null)));
    }

    @Override
    public CustomerDTO NewCustomer(CustomerDTO customer) {
        return customerMapper.CustomerToCustomerDTO(customerRepository.save(customerMapper.CustomerDTOToCustomer(customer)));
    }

    @Override
    public Optional<CustomerDTO> updateCustomer(UUID id, CustomerDTO customer) {
        AtomicReference<Optional<CustomerDTO>> atomicReference = new AtomicReference<>();
        customerRepository.findById(id).ifPresentOrElse(foundCustomer -> {
            foundCustomer.setCustomerName(customer.getCustomerName());
            atomicReference.set(Optional.of(customerMapper.CustomerToCustomerDTO(customerRepository.save(foundCustomer))));
        },() -> atomicReference.set(Optional.empty()));
        return atomicReference.get();
    }

    @Override
    public Boolean deleteById(UUID id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<CustomerDTO> PatchCustomerById(UUID id, CustomerDTO customer) {
        AtomicReference<Optional<CustomerDTO>> atomicReference = new AtomicReference<>();
        customerRepository.findById(id).ifPresentOrElse(foundCustomer -> {
            if (StringUtils.hasText(customer.getCustomerName())) {
                foundCustomer.setCustomerName(customer.getCustomerName());
            }
            atomicReference.set(Optional.of(customerMapper.CustomerToCustomerDTO(customerRepository.save(foundCustomer))));
        },() -> atomicReference.set(Optional.empty()));
        return atomicReference.get();
    }

}
