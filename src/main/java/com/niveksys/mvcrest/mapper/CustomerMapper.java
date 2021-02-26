package com.niveksys.mvcrest.mapper;

import com.niveksys.mvcrest.dto.CustomerDto;
import com.niveksys.mvcrest.model.Customer;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDto customerDto);

}
