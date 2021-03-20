package com.niveksys.mvcrest.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import com.niveksys.mvcrest.dto.CustomerDto;
import com.niveksys.mvcrest.service.CustomerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTests extends AbstractRestControllerTest {

    public static final Long ID1 = 1L;
    public static final String FIRSTNAME1 = "Michale";
    public static final String LASTNAME1 = "Weston";

    public static final Long ID2 = 2L;
    public static final String FIRSTNAME2 = "Sam";
    public static final String LASTNAME2 = "Axe";

    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void list() throws Exception {
        // given
        CustomerDto customer1 = new CustomerDto();
        customer1.setFirstname(FIRSTNAME1);
        customer1.setLastname(LASTNAME1);
        customer1.setCustomerUrl("/api/customers/" + ID1);

        CustomerDto customer2 = new CustomerDto();
        customer2.setFirstname(FIRSTNAME2);
        customer2.setLastname(LASTNAME2);
        customer2.setCustomerUrl("/api/customers/" + ID2);

        when(this.customerService.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        // when & then
        this.mockMvc.perform(get("/api/customers/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void show() throws Exception {
        // given
        CustomerDto customer = new CustomerDto();
        customer.setFirstname(FIRSTNAME1);
        customer.setLastname(LASTNAME1);
        customer.setCustomerUrl("/api/customers/" + ID1);

        when(this.customerService.findById(anyLong())).thenReturn(customer);

        // when & then
        this.mockMvc.perform(get("/api/customers/" + ID1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.firstname", equalTo(FIRSTNAME1)));
    }

    @Test
    public void create() throws Exception {
        // given
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstname(FIRSTNAME1);
        customerDto.setLastname(LASTNAME1);

        CustomerDto returnDto = new CustomerDto();
        returnDto.setFirstname(customerDto.getFirstname());
        returnDto.setLastname(customerDto.getLastname());
        returnDto.setCustomerUrl("/api/customers/" + ID1);

        when(this.customerService.create(customerDto)).thenReturn(returnDto);

        // when & then
        this.mockMvc
                .perform(post("/api/customers/").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerDto)))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.firstname", equalTo(FIRSTNAME1)))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/customers/" + ID1)));
    }

    @Test
    public void update() throws Exception {
        // given
        CustomerDto customer = new CustomerDto();
        customer.setFirstname(FIRSTNAME1);
        customer.setLastname(LASTNAME1);

        CustomerDto returnDto = new CustomerDto();
        returnDto.setFirstname(customer.getFirstname());
        returnDto.setLastname(customer.getLastname());
        returnDto.setCustomerUrl("/api/customers/" + ID1);

        when(this.customerService.update(anyLong(), any(CustomerDto.class))).thenReturn(returnDto);

        // when & then
        mockMvc.perform(
                put("/api/customers/" + ID1).contentType(MediaType.APPLICATION_JSON).content(asJsonString(customer)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.firstname", equalTo(FIRSTNAME1)))
                .andExpect(jsonPath("$.lastname", equalTo(LASTNAME1)))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/customers/" + ID1)));
    }

    @Test
    public void testPatchCustomer() throws Exception {
        // given
        CustomerDto customer = new CustomerDto();
        customer.setFirstname(FIRSTNAME1);

        CustomerDto returnDto = new CustomerDto();
        returnDto.setFirstname(customer.getFirstname());
        returnDto.setLastname(LASTNAME1);
        returnDto.setCustomerUrl("/api/customers/1");

        when(this.customerService.patch(anyLong(), any(CustomerDto.class))).thenReturn(returnDto);

        // when & then
        mockMvc.perform(
                patch("/api/customers/" + ID1).contentType(MediaType.APPLICATION_JSON).content(asJsonString(customer)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.firstname", equalTo(FIRSTNAME1)))
                .andExpect(jsonPath("$.lastname", equalTo(LASTNAME1)))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/customers/1")));
    }
}
