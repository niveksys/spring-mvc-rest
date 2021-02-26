package com.niveksys.mvcrest.controller;

import com.niveksys.mvcrest.dto.CustomerDto;
import com.niveksys.mvcrest.dto.CustomerListDto;
import com.niveksys.mvcrest.service.CustomerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        log.debug("LIST all the customers.");
        return new ResponseEntity<CustomerListDto>(new CustomerListDto(this.customerService.findAll()), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> show(@PathVariable Long id) {
        log.debug("SHOW a customer by id.");
        return new ResponseEntity<CustomerDto>(this.customerService.findById(id), HttpStatus.OK);
    }
}
