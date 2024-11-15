package com.anderson.pruebaai.controller;

import com.anderson.pruebaai.model.Book;
import com.anderson.pruebaai.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BookController {

    private final IBookService service;

    @GetMapping
    public ResponseEntity<List<Book>> findAll() throws Exception{
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable("id") Integer id) throws Exception{
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Book>save(@RequestBody Book book) throws Exception{
        Book obj = service.save(book);
        return ResponseEntity.created(URI.create("https://localhost:8080/books/" +  obj.getIdBook())).body(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable("id") Integer id, @RequestBody Book book) throws Exception{
        return ResponseEntity.ok(service.update(book,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}