package com.example.ynovv.controller;


import com.example.ynovv.entity.Author;
import com.example.ynovv.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors(){
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id){
        Author author = authorService.findById(id);
        if(author != null){
            return ResponseEntity.status(HttpStatus.OK).body(author);
        }else{
           return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author){
        Author author1 = authorService.update(id,author);
        return ResponseEntity.ok(author1);
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author author){
        return authorService.save(author);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id){
        authorService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
