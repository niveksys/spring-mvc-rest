package com.niveksys.mvcrest.service;

import java.util.List;

import com.niveksys.mvcrest.dto.CustomerDto;

public interface CustomerService {

    List<CustomerDto> findAll();

    CustomerDto findById(Long id);

    CustomerDto create(CustomerDto customerDto);

    CustomerDto update(Long id, CustomerDto customerDto);

    CustomerDto patch(Long id, CustomerDto customerDto);
}
