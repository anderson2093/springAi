package com.anderson.pruebaai.controller;

import com.anderson.pruebaai.model.Author;
import com.anderson.pruebaai.service.IAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthorController {

    private final IAuthorService service;

    @GetMapping
    public ResponseEntity<List<Author>>findAll() throws Exception{
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable("id") Integer id) throws Exception{
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Author>save(@RequestBody Author author) throws Exception{
        Author obj = service.save(author);
        return ResponseEntity.created(URI.create("https://localhost:8080/authors/" +  obj.getIdAuthor())).body(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable("id") Integer id, @RequestBody Author author) throws Exception{
        return ResponseEntity.ok(service.update(author,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
