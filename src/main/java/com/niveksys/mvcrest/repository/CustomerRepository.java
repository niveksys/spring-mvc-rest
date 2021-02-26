package com.niveksys.mvcrest.repository;

import com.niveksys.mvcrest.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
