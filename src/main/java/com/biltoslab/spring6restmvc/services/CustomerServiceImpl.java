package com.biltoslab.spring6restmvc.services;

import com.biltoslab.spring6restmvc.model.CustomerDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, CustomerDTO> customers;

    public CustomerServiceImpl() {
        customers = new HashMap<>();

        CustomerDTO customer1 = CustomerDTO
                .builder()
                .id(UUID.randomUUID())
                .customerName("Mohammad")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        CustomerDTO customer2 = CustomerDTO
                .builder()
                .id(UUID.randomUUID())
                .customerName("Hamza")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        CustomerDTO customer3 = CustomerDTO
                .builder()
                .id(UUID.randomUUID())
                .customerName("Mustafa")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customers.put(customer1.getId(),customer1);
        customers.put(customer2.getId(),customer2);
        customers.put(customer3.getId(),customer3);

    }


    @Override
    public List<CustomerDTO> listCustomers() {
        return new ArrayList<CustomerDTO>(customers.values());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.of(customers.get(id));
    }

    @Override
    public CustomerDTO NewCustomer(CustomerDTO customer){
        CustomerDTO savedCustomer = CustomerDTO
                .builder()
                .id(UUID.randomUUID())
                .customerName(customer.getCustomerName())
                .version(customer.getVersion())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customers.put(savedCustomer.getId(),savedCustomer);
        return savedCustomer;
    }

    @Override
    public Optional<CustomerDTO> updateCustomer(UUID id, CustomerDTO customer) {
        CustomerDTO existingCustomer = customers.get(id);
        existingCustomer.setCustomerName(customer.getCustomerName());
        customers.put(id,existingCustomer);
        return Optional.of(existingCustomer);
    }

    @Override
    public Boolean deleteById(UUID id) {
        customers.remove(id);
        return true;
    }

    @Override
    public Optional<CustomerDTO> PatchCustomerById(UUID id, CustomerDTO customer) {
        CustomerDTO customerToPatch = customers.get(id);
        if (StringUtils.hasText(customer.getCustomerName())) {
            customerToPatch.setCustomerName(customer.getCustomerName());
        }
        customers.put(id,customerToPatch);


        return Optional.of(customerToPatch);
    }
}
