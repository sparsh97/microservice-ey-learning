package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.CustomerDTO;
import com.eazybytes.accounts.entity.Customer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer customerDtoToCustomer(CustomerDTO customerDTO);
}
