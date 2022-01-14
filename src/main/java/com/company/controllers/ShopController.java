package com.company.controllers;

import com.company.exceptions.ResourceNotFoundException;
import com.company.models.Shop;
import com.company.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ShopController {
    @Autowired
    ShopRepository repository;

    @GetMapping("/shops")
    public List<Shop> getAllShops(){
        return repository.findAll();
    }

    @GetMapping("/shops/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Shop shop = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Shop not found for id: "+id));
        return ResponseEntity.ok().body(shop);
    }
    @PostMapping("/shops")
    public Shop createShop(@RequestBody Shop shop) {
        return repository.save(shop);
    }

    @DeleteMapping("/shops/{id}")
    public Map<String, Boolean> deleteShop(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Shop shop = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Shop not found for id: " + id));
        repository.delete(shop);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",true);
        return response;
    }

    @PutMapping("/shops")
    public Shop updateShop(@RequestBody Shop shop) throws ResourceNotFoundException {
        repository.findById(shop.getId()).orElseThrow(()-> new ResourceNotFoundException("Shop not found for id: " + shop.getId()));
        return repository.save(shop);
    }

    @GetMapping("/shops/task3B")
    public List<String> task3B(){
        return repository.task3B();
    }

    @GetMapping("/shops/task5C")
    public List<Shop> task5C(){
        return repository.task5C();
    }
}
