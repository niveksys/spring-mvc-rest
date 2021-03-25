package com.niveksys.mvcrest.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import com.niveksys.mvcrest.dto.CustomerDto;
import com.niveksys.mvcrest.service.CustomerService;
import com.niveksys.mvcrest.service.ResourceNotFoundException;

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
    public void getCustomerList() throws Exception {
        // given
        CustomerDto customer1 = new CustomerDto();
        customer1.setFirstname(FIRSTNAME1);
        customer1.setLastname(LASTNAME1);
        customer1.setCustomerUrl(CustomerController.BASE_URL + "/" + ID1);

        CustomerDto customer2 = new CustomerDto();
        customer2.setFirstname(FIRSTNAME2);
        customer2.setLastname(LASTNAME2);
        customer2.setCustomerUrl(CustomerController.BASE_URL + "/" + ID2);

        when(this.customerService.getCustomerList()).thenReturn(Arrays.asList(customer1, customer2));

        // when & then
        this.mockMvc.perform(get(CustomerController.BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void showCustomer() throws Exception {
        // given
        CustomerDto customer = new CustomerDto();
        customer.setFirstname(FIRSTNAME1);
        customer.setLastname(LASTNAME1);
        customer.setCustomerUrl(CustomerController.BASE_URL + "/" + ID1);

        when(this.customerService.getCustomerById(anyLong())).thenReturn(customer);

        // when & then
        this.mockMvc.perform(get(CustomerController.BASE_URL + "/" + ID1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.firstname", equalTo(FIRSTNAME1)));
    }

    @Test
    public void createCustomer() throws Exception {
        // given
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstname(FIRSTNAME1);
        customerDto.setLastname(LASTNAME1);

        CustomerDto returnDto = new CustomerDto();
        returnDto.setFirstname(customerDto.getFirstname());
        returnDto.setLastname(customerDto.getLastname());
        returnDto.setCustomerUrl(CustomerController.BASE_URL + "/" + ID1);

        when(this.customerService.createCustomer(customerDto)).thenReturn(returnDto);

        // when & then
        this.mockMvc
                .perform(post(CustomerController.BASE_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerDto)))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.firstname", equalTo(FIRSTNAME1)))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/" + ID1)));
    }

    @Test
    public void updateCustomer() throws Exception {
        // given
        CustomerDto customer = new CustomerDto();
        customer.setFirstname(FIRSTNAME1);
        customer.setLastname(LASTNAME1);

        CustomerDto returnDto = new CustomerDto();
        returnDto.setFirstname(customer.getFirstname());
        returnDto.setLastname(customer.getLastname());
        returnDto.setCustomerUrl(CustomerController.BASE_URL + "/" + ID1);

        when(this.customerService.updateCustomer(anyLong(), any(CustomerDto.class))).thenReturn(returnDto);

        // when & then
        mockMvc.perform(put(CustomerController.BASE_URL + "/" + ID1).contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer))).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(FIRSTNAME1)))
                .andExpect(jsonPath("$.lastname", equalTo(LASTNAME1)))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/" + ID1)));
    }

    @Test
    public void patchCustomer() throws Exception {
        // given
        CustomerDto customer = new CustomerDto();
        customer.setFirstname(FIRSTNAME1);

        CustomerDto returnDto = new CustomerDto();
        returnDto.setFirstname(customer.getFirstname());
        returnDto.setLastname(LASTNAME1);
        returnDto.setCustomerUrl(CustomerController.BASE_URL + "/" + ID1);

        when(this.customerService.patchCustomer(anyLong(), any(CustomerDto.class))).thenReturn(returnDto);

        // when & then
        mockMvc.perform(patch(CustomerController.BASE_URL + "/" + ID1).contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer))).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(FIRSTNAME1)))
                .andExpect(jsonPath("$.lastname", equalTo(LASTNAME1)))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/" + ID1)));
    }

    @Test
    public void deleteCustomer() throws Exception {
        mockMvc.perform(delete(CustomerController.BASE_URL + "/" + ID1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(customerService).deleteCustomer(anyLong());
    }

    @Test
    public void getCustomerNotFound() throws Exception {
        // given
        when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);
        // when
        mockMvc.perform(get(CustomerController.BASE_URL + "/222").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
