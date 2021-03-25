package com.niveksys.mvcrest.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.niveksys.mvcrest.dto.VendorDto;
import com.niveksys.mvcrest.dto.VendorListDto;
import com.niveksys.mvcrest.mapper.VendorMapper;
import com.niveksys.mvcrest.model.Vendor;
import com.niveksys.mvcrest.repository.VendorRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VendorServiceImplTests {

    public static final long ID_1 = 1L;
    public static final String NAME_1 = "Vendor 1";
    public static final long ID_2 = 1L;
    public static final String NAME_2 = "Vendor 2";

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @BeforeEach
    public void setUp() throws Exception {
        this.vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    public void getVendorList() throws Exception {
        // given
        List<Vendor> vendors = Arrays.asList(this.getVendor1(), this.getVendor2());
        given(this.vendorRepository.findAll()).willReturn(vendors);

        // when
        VendorListDto vendorListDto = this.vendorService.getVendorList();

        // then
        then(this.vendorRepository).should(times(1)).findAll();
        assertThat(vendorListDto.getVendors().size(), is(equalTo(2)));
    }

    @Test
    public void getVendorById() throws Exception {
        // given
        Vendor vendor = this.getVendor1();
        // mockito BDD syntax
        given(this.vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));

        // when
        VendorDto vendorDto = this.vendorService.getVendorById(1L);

        // then
        then(this.vendorRepository).should(times(1)).findById(anyLong());
        // JUnit Assert that with matchers
        assertThat(vendorDto.getName(), is(equalTo(NAME_1)));
    }

    @Test
    public void getVendorByIdNotFound() throws Exception {
        // given
        // mockito BBD syntax since mockito 1.10.0
        given(this.vendorRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            // when
            VendorDto vendorDto = this.vendorService.getVendorById(1L);

            // then
            then(this.vendorRepository).should(times(1)).findById(anyLong());
            assertNull(vendorDto);
        });

    }

    @Test
    public void createVendor() throws Exception {
        // given
        VendorDto vendorDto = new VendorDto();
        vendorDto.setName(NAME_1);

        Vendor vendor = this.getVendor1();

        given(this.vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        // when
        VendorDto savedVendorDto = this.vendorService.createVendor(vendorDto);

        // then
        // 'should' defaults to times = 1
        then(this.vendorRepository).should().save(any(Vendor.class));
        assertThat(savedVendorDto.getVendorUrl(), containsString(String.valueOf(ID_1)));

    }

    @Test
    public void updateVendor() throws Exception {

        // given
        VendorDto vendorDto = new VendorDto();
        vendorDto.setName(NAME_1);

        Vendor vendor = this.getVendor1();

        given(this.vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        // when
        VendorDto savedVendorDto = vendorService.updateVendor(ID_1, vendorDto);

        // then
        // 'should' defaults to times = 1
        then(this.vendorRepository).should().save(any(Vendor.class));
        assertThat(savedVendorDto.getVendorUrl(), containsString(String.valueOf(ID_1)));
    }

    @Test
    public void patchVendor() throws Exception {
        // given
        VendorDto vendorDto = new VendorDto();
        vendorDto.setName(NAME_1);

        Vendor vendor = getVendor1();

        given(this.vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));
        given(this.vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        // when

        VendorDto savedVendorDto = this.vendorService.patchVendor(ID_1, vendorDto);

        // then
        // 'should' defaults to times = 1
        then(this.vendorRepository).should().save(any(Vendor.class));
        then(this.vendorRepository).should(times(1)).findById(anyLong());
        assertThat(savedVendorDto.getVendorUrl(), containsString(String.valueOf(ID_1)));
    }

    @Test
    public void deleteVendor() throws Exception {
        // when
        this.vendorService.deleteVendor(ID_1);

        // then
        then(this.vendorRepository).should().deleteById(anyLong());
    }

    private Vendor getVendor1() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME_1);
        vendor.setId(ID_1);
        return vendor;
    }

    private Vendor getVendor2() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME_2);
        vendor.setId(ID_2);
        return vendor;
    }
}
