package com.niveksys.mvcrest.service;

import java.util.List;

import com.niveksys.mvcrest.dto.CustomerDto;

public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Long id);

    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(Long id, CustomerDto customerDto);

    CustomerDto patchCustomer(Long id, CustomerDto customerDto);

    void deleteCustomer(Long id);
}
