package com.niveksys.mvcrest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.niveksys.mvcrest.dto.VendorDto;
import com.niveksys.mvcrest.model.Vendor;

import org.junit.jupiter.api.Test;

public class VendorMapperTests {

    public static final String NAME = "Vendor 1";

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void vendorToVendorDto() throws Exception {
        // given
        Vendor vendor = new Vendor();
        vendor.setName(NAME);

        // when
        VendorDto vendorDto = this.vendorMapper.vendorToVendorDto(vendor);

        // then
        assertEquals(vendor.getName(), vendorDto.getName());
    }

    @Test
    public void vendorDtoToVendor() throws Exception {
        // given
        VendorDto vendorDto = new VendorDto();
        vendorDto.setName(NAME);

        // when
        Vendor vendor = this.vendorMapper.vendorDtoToVendor(vendorDto);

        // then
        assertEquals(vendorDto.getName(), vendor.getName());
    }

}
