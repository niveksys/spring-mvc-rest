package com.niveksys.mvcrest.controller;

import com.niveksys.mvcrest.dto.VendorDto;
import com.niveksys.mvcrest.dto.VendorListDto;
import com.niveksys.mvcrest.service.VendorService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDto getVendorList() {
        return this.vendorService.getVendorList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDto getVendorById(@PathVariable Long id) {
        return this.vendorService.getVendorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDto createNewVendor(@RequestBody VendorDto vendorDto) {
        return this.vendorService.createVendor(vendorDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDto updateVendor(@PathVariable Long id, @RequestBody VendorDto vendorDto) {
        return this.vendorService.updateVendor(id, vendorDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDto patchVendor(@PathVariable Long id, @RequestBody VendorDto vendorDto) {
        return this.vendorService.patchVendor(id, vendorDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id) {
        this.vendorService.deleteVendor(id);
    }
}