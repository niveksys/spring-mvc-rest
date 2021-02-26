package com.niveksys.mvcrest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.niveksys.mvcrest.dto.CustomerDto;
import com.niveksys.mvcrest.mapper.CustomerMapper;
import com.niveksys.mvcrest.model.Customer;
import com.niveksys.mvcrest.repository.CustomerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTests {

    public static final Long ID1 = 1L;
    public static final String FIRSTNAME1 = "Michale";
    public static final String LASTNAME1 = "Weston";

    public static final Long ID2 = 2L;
    public static final String FIRSTNAME2 = "Sam";
    public static final String LASTNAME2 = "Axe";

    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;

    @BeforeEach
    public void setUp() throws Exception {
        this.customerService = new CustomerServiceImpl(this.customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void findAll() throws Exception {
        // given
        Customer customer1 = new Customer();
        customer1.setId(ID1);
        customer1.setFirstname(FIRSTNAME1);
        customer1.setLastname(LASTNAME1);

        Customer customer2 = new Customer();
        customer2.setId(ID2);
        customer2.setFirstname(FIRSTNAME2);
        customer2.setLastname(LASTNAME2);

        when(this.customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        // when
        List<CustomerDto> customerDtoList = this.customerService.findAll();

        // then
        assertEquals(2, customerDtoList.size());
    }

    @Test
    public void findById() throws Exception {
        // given
        Customer customer = new Customer();
        customer.setId(ID1);
        customer.setFirstname(FIRSTNAME1);
        customer.setLastname(FIRSTNAME2);

        when(this.customerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(customer));

        // when
        CustomerDto customerDto = this.customerService.findById(ID1);

        // then
        assertEquals(FIRSTNAME1, customerDto.getFirstname());
    }
}
