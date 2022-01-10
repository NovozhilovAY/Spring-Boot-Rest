package com.company.controllers;

import com.company.exceptions.ResourceNotFoundException;
import com.company.models.Customer;
import com.company.models.task3.LastNameAndDiscounts;
import com.company.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CustomerController {
    @Autowired
    CustomerRepository repository;
    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return repository.findAll();
    }
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Customer customer = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Customer not found for id: "+id));
        return ResponseEntity.ok().body(customer);
    }
    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer) {
        return repository.save(customer);
    }

    @DeleteMapping("/customers/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Customer customer = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Contact not found for id: " + id));
        repository.delete(customer);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",true);
        return response;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer) throws ResourceNotFoundException {
        repository.findById(customer.getId()).orElseThrow(()-> new ResourceNotFoundException("Contact not found for id: " + customer.getId()));
        return repository.save(customer);
    }

    @GetMapping("/customers/districts")
    public List<String> getUniqDistricts(){
        return repository.getUniqDistricts();
    }

    @GetMapping("/customers/by-district")
    public List<LastNameAndDiscounts> getLastNameAndDiscount(){
        return repository.findCustomersByDistrict("Нижегородский");
    }
}
