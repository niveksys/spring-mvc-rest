package com.niveksys.mvcrest.repository;

import com.niveksys.mvcrest.model.Vendor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
