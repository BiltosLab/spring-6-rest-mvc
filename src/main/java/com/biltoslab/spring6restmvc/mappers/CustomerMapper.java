package com.biltoslab.spring6restmvc.mappers;


import com.biltoslab.spring6restmvc.entities.Customer;
import com.biltoslab.spring6restmvc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer CustomerDTOToCustomer(CustomerDTO customerDTO);
    CustomerDTO CustomerToCustomerDTO(Customer customer);
}
