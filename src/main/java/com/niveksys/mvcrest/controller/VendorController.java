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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "vendors", description = "Vendor API")
@Slf4j
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "GET all the vendors.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDto getAllVendors() {
        log.debug("GET all the Vendors.");
        return this.vendorService.getAllVendors();
    }

    @ApiOperation(value = "GET a vendor by id.")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDto getVendorById(@PathVariable Long id) {
        log.debug("GET a vendor by id: " + id);
        return this.vendorService.getVendorById(id);
    }

    @ApiOperation(value = "CREATE a new vendor.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDto createNewVendor(@RequestBody VendorDto vendorDto) {
        log.debug("CREATE a new vendor.");
        return this.vendorService.createVendor(vendorDto);
    }

    @ApiOperation(value = "UPDATE a vendor by id.", notes = "UPDATE (replace) a vendor by id.")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDto updateVendor(@PathVariable Long id, @RequestBody VendorDto vendorDto) {
        log.debug("UPDATE a vendor by id: " + id);
        return this.vendorService.updateVendor(id, vendorDto);
    }

    @ApiOperation(value = "PATCH a vendor by id.", notes = "PATCH (partial update) a vendor by id.")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDto patchVendor(@PathVariable Long id, @RequestBody VendorDto vendorDto) {
        log.debug("PATCH a vendor by id: " + id);
        return this.vendorService.patchVendor(id, vendorDto);
    }

    @ApiOperation(value = "DELETE a vendor by id.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id) {
        log.debug("DELETE a vendor by id: " + id);
        this.vendorService.deleteVendor(id);
    }
}