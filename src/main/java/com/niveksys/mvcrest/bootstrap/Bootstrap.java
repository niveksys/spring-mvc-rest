package com.niveksys.mvcrest.bootstrap;

import com.niveksys.mvcrest.model.Category;
import com.niveksys.mvcrest.model.Customer;
import com.niveksys.mvcrest.model.Vendor;
import com.niveksys.mvcrest.repository.CategoryRepository;
import com.niveksys.mvcrest.repository.CustomerRepository;
import com.niveksys.mvcrest.repository.VendorRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository,
            VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.loadCategories();
        this.loadCustomers();
        this.loadVendors();
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        this.categoryRepository.save(fruits);
        this.categoryRepository.save(dried);
        this.categoryRepository.save(fresh);
        this.categoryRepository.save(exotic);
        this.categoryRepository.save(nuts);

        log.debug("LOADED Categories with count: " + this.categoryRepository.count());
    }

    private void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setId(1l);
        customer1.setFirstname("Michale");
        customer1.setLastname("Weston");
        this.customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setId(2l);
        customer2.setFirstname("Sam");
        customer2.setLastname("Axe");
        this.customerRepository.save(customer2);

        log.debug("LOADED Customers with count: " + this.customerRepository.count());
    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor 1");
        vendorRepository.save(vendor1);

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor 2");
        vendorRepository.save(vendor2);

        log.debug("LOADED Vendors with count: " + this.vendorRepository.count());
    }
}
