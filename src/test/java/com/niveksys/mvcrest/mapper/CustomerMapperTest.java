package com.niveksys.mvcrest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.niveksys.mvcrest.dto.CustomerDto;
import com.niveksys.mvcrest.model.Customer;

import org.junit.jupiter.api.Test;

public class CustomerMapperTest {

    public static final String FIRSTNAME = "Jimmy";
    public static final String LASTNAME = "Fallon";

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDto() throws Exception {
        // given
        Customer customer = new Customer();
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        // when
        CustomerDto customerDto = this.customerMapper.customerToCustomerDto(customer);

        // then
        assertEquals(FIRSTNAME, customerDto.getFirstname());
        assertEquals(LASTNAME, customerDto.getLastname());

    }
}
