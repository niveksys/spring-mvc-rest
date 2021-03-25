package com.niveksys.mvcrest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import com.niveksys.mvcrest.dto.VendorDto;
import com.niveksys.mvcrest.dto.VendorListDto;
import com.niveksys.mvcrest.service.VendorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(VendorController.class)
public class VendorControllerTests extends AbstractRestControllerTest {

    public static final long ID_1 = 1L;
    public static final String NAME_1 = "Vendor 1";
    public static final long ID_2 = 1L;
    public static final String NAME_2 = "Vendor 2";

    @MockBean // provided by Spring Context
    VendorService vendorService;

    @Autowired
    MockMvc mockMvc; // provided by Spring Context

    VendorDto vendorDto1;
    VendorDto vendorDto2;

    @BeforeEach
    public void setUp() throws Exception {
        this.vendorDto1 = new VendorDto(NAME_1, VendorController.BASE_URL + "/" + ID_1);
        this.vendorDto2 = new VendorDto(NAME_2, VendorController.BASE_URL + "/" + ID_2);
    }

    @Test
    public void getVendorList() throws Exception {
        // given
        VendorListDto vendorListDto = new VendorListDto(Arrays.asList(this.vendorDto1, this.vendorDto2));

        given(this.vendorService.getVendorList()).willReturn(vendorListDto);

        // when
        this.mockMvc.perform(get(VendorController.BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    public void getVendorById() throws Exception {
        // given
        given(this.vendorService.getVendorById(anyLong())).willReturn(this.vendorDto1);
        // when
        this.mockMvc.perform(get(VendorController.BASE_URL + "/" + ID_1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name", equalTo(this.vendorDto1.getName())));
    }

    @Test
    public void createVendor() throws Exception {
        // given
        given(this.vendorService.createVendor(this.vendorDto1)).willReturn(this.vendorDto1);
        // when
        this.mockMvc
                .perform(post(VendorController.BASE_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(this.vendorDto1)))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.name", equalTo(this.vendorDto1.getName())));
    }

    @Test
    public void updateVendor() throws Exception {
        // given
        given(this.vendorService.updateVendor(anyLong(), any(VendorDto.class))).willReturn(this.vendorDto1);
        // when
        this.mockMvc
                .perform(put(VendorController.BASE_URL + "/" + ID_1).contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(this.vendorDto1)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name", equalTo(this.vendorDto1.getName())));
    }

    @Test
    public void patchVendor() throws Exception {
        // given
        given(this.vendorService.patchVendor(anyLong(), any(VendorDto.class))).willReturn(this.vendorDto1);
        // when
        this.mockMvc
                .perform(patch(VendorController.BASE_URL + "/" + ID_1).contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(this.vendorDto1)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name", equalTo(this.vendorDto1.getName())));
    }

    @Test
    public void deleteVendor() throws Exception {
        // when
        this.mockMvc.perform(delete(VendorController.BASE_URL + "/" + ID_1)).andExpect(status().isOk());
        // then
        then(this.vendorService).should().deleteVendor(anyLong());

    }
}
