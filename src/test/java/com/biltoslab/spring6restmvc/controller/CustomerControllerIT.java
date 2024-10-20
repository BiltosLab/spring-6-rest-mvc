package com.biltoslab.spring6restmvc.controller;

import com.biltoslab.spring6restmvc.entities.Customer;
import com.biltoslab.spring6restmvc.entities.Drink;
import com.biltoslab.spring6restmvc.mappers.CustomerMapper;
import com.biltoslab.spring6restmvc.model.CustomerDTO;
import com.biltoslab.spring6restmvc.model.DrinkDTO;
import com.biltoslab.spring6restmvc.model.DrinkStyle;
import com.biltoslab.spring6restmvc.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerControllerIT {
    @Autowired
    CustomerController customerController;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;
    @Rollback
    @Transactional
    @Test
    void PatchCustomerNotFound(){
        assertThrows(NotFoundException.class,() ->
                customerController.PatchCustomerById(UUID.randomUUID(), CustomerDTO.builder().build()));
    }


    @Rollback
    @Transactional
    @Test
    void PatchCustomer() {
        Customer customer= customerRepository.findAll().getFirst();
        CustomerDTO customerDTO = customerMapper.CustomerToCustomerDTO(customer);
        final String name = "Khalid";
        customerDTO.setId(null);
        customerDTO.setVersion(null);
        customerDTO.setCustomerName(name);

        ResponseEntity<CustomerDTO> response = customerController.PatchCustomerById(customer.getId(), customerDTO);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.valueOf(204));

        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();
        assertThat(updatedCustomer.getCustomerName()).isEqualTo(name);


    }
    @Test
    void DeleteCustomerNotFound(){
        assertThrows(NotFoundException.class, () -> {
            customerController.deleteCustomer(UUID.randomUUID());
        });
    }

    @Rollback
    @Transactional
    @Test
    void DeleteCustomer(){
        Customer customer = customerRepository.findAll().getFirst();
        ResponseEntity<CustomerDTO> response = customerController.deleteCustomer(customer.getId());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.valueOf(204));
        assertThat(customerRepository.findById(customer.getId()).isEmpty());
    }


    @Rollback
    @Transactional
    @Test
    void UpdateCustomerNotFound(){
        assertThrows(NotFoundException.class,() ->
                customerController.updateCustomer(UUID.randomUUID(), CustomerDTO.builder().build()));
    }


    @Rollback
    @Transactional
    @Test
    void UpdateCustomer() {
        Customer customer= customerRepository.findAll().getFirst();
        CustomerDTO customerDTO = customerMapper.CustomerToCustomerDTO(customer);
        final String name = "Khalid";
        customerDTO.setId(null);
        customerDTO.setVersion(null);
        customerDTO.setCustomerName(name);

        ResponseEntity<CustomerDTO> response = customerController.updateCustomer(customer.getId(), customerDTO);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.valueOf(204));

        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();
        assertThat(updatedCustomer.getCustomerName()).isEqualTo(name);


    }

    @Rollback
    @Transactional
    @Test
    void saveNewCustomer() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .customerName("Ossama")
                .build();
        ResponseEntity<CustomerDTO> responseEntity= customerController.NewCustomer(customerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();
        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[4]);
        System.out.println(savedUUID.toString());
        Customer savedCustomer = customerRepository.findById(savedUUID).get();
        assertThat(savedCustomer).isNotNull();
    }


    @Test
    void TestListAllCustomers() {
        List<CustomerDTO> dtos = customerController.getAllCustomers();
        System.out.println("DTOS SIZE" + dtos.size());
        assertThat(dtos.size()).isEqualTo(3);
    }

    @Rollback
    @Transactional
    @Test
    void TestEmptyListAllCustomers() {
        customerRepository.deleteAll();
        List<CustomerDTO> dtos = customerController.getAllCustomers();
        assertThat(dtos.size()).isEqualTo(0);
    }
    @Test
    void testGetCustomerById() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO customerDTO = customerController.getCustomerById(customer.getId());
        assertThat(customerDTO).isNotNull();
    }

    @Test
    void testGetCustomerByIdNotFound() {
        assertThrows(NotFoundException.class,() -> {
           customerController.getCustomerById(UUID.randomUUID());
        });
    }


}