package com.niveksys.mvcrest.service;

import java.util.List;
import java.util.stream.Collectors;

import com.niveksys.mvcrest.controller.VendorController;
import com.niveksys.mvcrest.dto.VendorDto;
import com.niveksys.mvcrest.dto.VendorListDto;
import com.niveksys.mvcrest.mapper.VendorMapper;
import com.niveksys.mvcrest.model.Vendor;
import com.niveksys.mvcrest.repository.VendorRepository;

import org.springframework.stereotype.Service;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public VendorListDto getVendorList() {
        List<VendorDto> vendorDtoList = this.vendorRepository.findAll().stream().map(vendor -> {
            VendorDto vendorDto = this.vendorMapper.vendorToVendorDto(vendor);
            vendorDto.setVendorUrl(this.getVendorUrl(vendor.getId()));
            return vendorDto;
        }).collect(Collectors.toList());
        return new VendorListDto(vendorDtoList);
    }

    @Override
    public VendorDto getVendorById(Long id) {
        return this.vendorRepository.findById(id).map(this.vendorMapper::vendorToVendorDto).map(vendorDto -> {
            vendorDto.setVendorUrl(getVendorUrl(id));
            return vendorDto;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDto createVendor(VendorDto vendorDto) {
        return this.saveAndReturnDto(this.vendorMapper.vendorDtoToVendor(vendorDto));
    }

    @Override
    public VendorDto updateVendor(Long id, VendorDto vendorDto) {
        Vendor vendor = this.vendorMapper.vendorDtoToVendor(vendorDto);
        vendor.setId(id);
        return this.saveAndReturnDto(vendor);
    }

    @Override
    public VendorDto patchVendor(Long id, VendorDto vendorDto) {
        return this.vendorRepository.findById(id).map(vendor -> {
            // todo if more properties, add more if statements
            if (vendorDto.getName() != null) {
                vendor.setName(vendorDto.getName());
            }
            return saveAndReturnDto(vendor);
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendor(Long id) {
        this.vendorRepository.deleteById(id);
    }

    private String getVendorUrl(Long id) {
        return VendorController.BASE_URL + "/" + id;
    }

    private VendorDto saveAndReturnDto(Vendor vendor) {
        Vendor savedVendor = this.vendorRepository.save(vendor);
        VendorDto returnDto = this.vendorMapper.vendorToVendorDto(savedVendor);
        returnDto.setVendorUrl(getVendorUrl(savedVendor.getId()));
        return returnDto;
    }
}
