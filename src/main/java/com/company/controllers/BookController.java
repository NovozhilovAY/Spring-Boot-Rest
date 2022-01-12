package com.company.controllers;

import com.company.exceptions.ResourceNotFoundException;
import com.company.models.Book;
import com.company.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    BookRepository repository;

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return repository.findAll();
    }
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Book book = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Book not found for id: "+id));
        return ResponseEntity.ok().body(book);
    }
    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) {
        return repository.save(book);
    }

    @DeleteMapping("/books/{id}")
    public Map<String, Boolean> deleteBook(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Book book = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Book not found for id: " + id));
        repository.delete(book);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",true);
        return response;
    }

    @PutMapping("/books")
    public Book updateBook(@RequestBody Book book) throws ResourceNotFoundException {
        repository.findById(book.getId()).orElseThrow(()-> new ResourceNotFoundException("Book not found for id: " + book.getId()));
        return repository.save(book);
    }
}
