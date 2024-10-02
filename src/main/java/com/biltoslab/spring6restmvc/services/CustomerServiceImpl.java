package com.biltoslab.spring6restmvc.services;

import com.biltoslab.spring6restmvc.model.Customer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID,Customer> customers;

    public CustomerServiceImpl() {
        customers = new HashMap<>();

        Customer customer1 = Customer
                .builder()
                .id(UUID.randomUUID())
                .customerName("Mohammad")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        Customer customer2 = Customer
                .builder()
                .id(UUID.randomUUID())
                .customerName("Hamza")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        Customer customer3 = Customer
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
    public List<Customer> listCustomers() {
        return new ArrayList<Customer>(customers.values());
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return customers.get(id);
    }

    @Override
    public Customer NewCustomer(Customer customer){
        Customer savedCustomer = Customer
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
    public void updateCustomer(UUID id, Customer customer) {
        Customer existingCustomer = customers.get(id);
        existingCustomer.setCustomerName(customer.getCustomerName());
        customers.put(id,existingCustomer);

    }
}
