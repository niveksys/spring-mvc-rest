package com.niveksys.mvcrest.service;

import com.niveksys.mvcrest.dto.VendorDto;
import com.niveksys.mvcrest.dto.VendorListDto;

public interface VendorService {

    VendorListDto getAllVendors();

    VendorDto getVendorById(Long id);

    VendorDto createVendor(VendorDto vendorDto);

    VendorDto updateVendor(Long id, VendorDto vendorDto);

    VendorDto patchVendor(Long id, VendorDto vendorDto);

    void deleteVendor(Long id);

}
