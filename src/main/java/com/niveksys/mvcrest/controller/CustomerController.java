package com.niveksys.mvcrest.controller;

import com.niveksys.mvcrest.dto.CustomerDto;
import com.niveksys.mvcrest.dto.CustomerListDto;
import com.niveksys.mvcrest.service.CustomerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping({ "", "/" })
    public ResponseEntity<CustomerListDto> list() {
        log.debug("LIST all customers.");
        return new ResponseEntity<CustomerListDto>(new CustomerListDto(this.customerService.findAll()), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> show(@PathVariable Long id) {
        log.debug("GET a customer by id: " + id);
        return new ResponseEntity<CustomerDto>(this.customerService.findById(id), HttpStatus.OK);
    }

    @PostMapping({ "", "/" })
    public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto customerDto) {
        log.debug("CREATE a new customer.");
        return new ResponseEntity<CustomerDto>(this.customerService.create(customerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        log.debug("UPDATE a customer with id: " + id);
        return new ResponseEntity<CustomerDto>(this.customerService.update(id, customerDto), HttpStatus.OK);
    }

    @PatchMapping({ "/{id}" })
    public ResponseEntity<CustomerDto> patchCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        return new ResponseEntity<CustomerDto>(this.customerService.patch(id, customerDto), HttpStatus.OK);
    }
}
