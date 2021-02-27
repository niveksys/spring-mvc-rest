package com.niveksys.mvcrest.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.niveksys.mvcrest.bootstrap.Bootstrap;
import com.niveksys.mvcrest.dto.CustomerDto;
import com.niveksys.mvcrest.mapper.CustomerMapper;
import com.niveksys.mvcrest.model.Customer;
import com.niveksys.mvcrest.repository.CategoryRepository;
import com.niveksys.mvcrest.repository.CustomerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerServiceImplIT {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CustomerRepository customerRepository;

    CustomerService customerService;

    @BeforeEach
    public void setUp() throws Exception {
        System.out.println("Loading Customer Data");
        System.out.println(customerRepository.findAll().size());

        // setup data for testing
        Bootstrap bootstrap = new Bootstrap(this.categoryRepository, this.customerRepository);
        bootstrap.run(); // load data

        customerService = new CustomerServiceImpl(this.customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void patchFirstname() throws Exception {
        // given
        Customer originalCustomer = this.customerRepository.findAll().get(0);
        assertNotNull(originalCustomer);

        long id = originalCustomer.getId();
        String originalFirstname = originalCustomer.getFirstname();
        String originalLastname = originalCustomer.getLastname();

        String expectedFirstname = "ExpectedFirstname";
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstname(expectedFirstname);

        // when
        this.customerService.patch(id, customerDto);

        // then
        Customer updatedCustomer = this.customerRepository.findById(id).get();
        assertNotNull(updatedCustomer);
        assertEquals(expectedFirstname, updatedCustomer.getFirstname());
        assertThat(originalFirstname, not(equalTo(updatedCustomer.getFirstname())));
        assertThat(originalLastname, equalTo(updatedCustomer.getLastname()));
    }

    @Test
    public void patchLastname() throws Exception {
        // given
        Customer originalCustomer = this.customerRepository.findAll().get(0);
        assertNotNull(originalCustomer);

        long id = originalCustomer.getId();
        String originalFirstname = originalCustomer.getFirstname();
        String originalLastname = originalCustomer.getLastname();

        String expectedLastname = "ExpectedLastname";
        CustomerDto customerDto = new CustomerDto();
        customerDto.setLastname(expectedLastname);

        // when
        this.customerService.patch(id, customerDto);

        // then
        Customer updatedCustomer = this.customerRepository.findById(id).get();
        assertNotNull(updatedCustomer);
        assertEquals(expectedLastname, updatedCustomer.getLastname());
        assertThat(originalFirstname, equalTo(updatedCustomer.getFirstname()));
        assertThat(originalLastname, not(equalTo(updatedCustomer.getLastname())));
    }

}
