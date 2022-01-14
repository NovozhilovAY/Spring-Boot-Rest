package com.company.controllers;

import com.company.exceptions.ResourceNotFoundException;
import com.company.models.Purchase;
import com.company.models.task4.LastNameAndShopName;
import com.company.repositories.PurchaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PurchaseController {
    @Autowired
    PurchaseRepository repository;

    @GetMapping("/purchases")
    public List<Purchase> getAllPurchases(){
        return repository.findAll();
    }

    @GetMapping("/purchases/{id}")
    public ResponseEntity<Purchase> getPurchasesById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Purchase purchase = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Purchase not found for id: "+id));
        return ResponseEntity.ok().body(purchase);
    }
    @PostMapping("/purchases")
    public Purchase createPurchase(@RequestBody Purchase purchase) {
        return repository.save(purchase);
    }

    @DeleteMapping("/purchases/{id}")
    public Map<String, Boolean> deletePurchase(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Purchase purchase = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Purchase not found for id: " + id));
        repository.delete(purchase);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",true);
        return response;
    }

    @PutMapping("/purchases")
    public Purchase updatePurchase(@RequestBody Purchase purchase) throws ResourceNotFoundException {
        repository.findById(purchase.getId()).orElseThrow(()-> new ResourceNotFoundException("Purchase not found for id: " + purchase.getId()));
        return repository.save(purchase);
    }

    @GetMapping("/purchases/uniq-months")
    public List<Double> getUniqMonths(){
        return repository.uniqMonth();
    }

    @GetMapping("/purchases/task4A")
    public List<LastNameAndShopName> task4A(){
        return repository.task4A();
    }

    @GetMapping("/purchases/task4B")
    public Collection<Object> task4B(){
        return repository.task4B();
    }

    @GetMapping("/purchases/task5A")
    public Collection<Object> task5A(){
        return repository.task5A();
    }

    @GetMapping("/purchases/task5B")
    public Collection<Object> task5B(){
        return repository.task5B();
    }

    @GetMapping("/purchases/task5D")
    public Collection<Object> task5D(){
        return repository.task5D();
    }

}
