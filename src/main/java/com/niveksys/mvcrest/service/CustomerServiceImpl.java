package com.niveksys.mvcrest.service;

import java.util.List;
import java.util.stream.Collectors;

import com.niveksys.mvcrest.dto.CustomerDto;
import com.niveksys.mvcrest.mapper.CustomerMapper;
import com.niveksys.mvcrest.model.Customer;
import com.niveksys.mvcrest.repository.CustomerRepository;

import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDto> findAll() {
        return this.customerRepository.findAll().stream().map(customer -> {
            CustomerDto customerDto = this.customerMapper.customerToCustomerDto(customer);
            customerDto.setCustomerUrl("/api/customers/" + customer.getId());
            return customerDto;
        }).collect(Collectors.toList());
    }

    @Override
    public CustomerDto findById(Long id) {
        return this.customerRepository.findById(id).map(customer -> {
            CustomerDto customerDto = this.customerMapper.customerToCustomerDto(customer);
            customerDto.setCustomerUrl("/api/customers/" + customer.getId());
            return customerDto;
        }).orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDto create(CustomerDto customerDto) {
        Customer customer = this.customerMapper.customerDtoToCustomer(customerDto);
        return this.saveAndReturnDto(customer);
    }

    @Override
    public CustomerDto update(Long id, CustomerDto customerDto) {
        Customer customer = this.customerMapper.customerDtoToCustomer(customerDto);
        customer.setId(id);
        return this.saveAndReturnDto(customer);
    }

    private CustomerDto saveAndReturnDto(Customer customer) {
        Customer savedCustomer = this.customerRepository.save(customer);
        CustomerDto returnDto = this.customerMapper.customerToCustomerDto(savedCustomer);
        returnDto.setCustomerUrl("/api/customers/" + savedCustomer.getId());
        return returnDto;
    }
}
