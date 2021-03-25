package com.niveksys.mvcrest.mapper;

import com.niveksys.mvcrest.dto.VendorDto;
import com.niveksys.mvcrest.model.Vendor;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDto vendorToVendorDto(Vendor vendor);

    Vendor vendorDtoToVendor(VendorDto vendorDto);

}
