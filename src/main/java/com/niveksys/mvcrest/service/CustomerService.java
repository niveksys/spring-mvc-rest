package com.niveksys.mvcrest.service;

import java.util.List;

import com.niveksys.mvcrest.dto.CustomerDto;

public interface CustomerService {

    List<CustomerDto> findAllCustomers();

    CustomerDto findCustomerById(Long id);

    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(Long id, CustomerDto customerDto);

    CustomerDto patchCustomer(Long id, CustomerDto customerDto);

    void deleteCustomerById(Long id);
}
