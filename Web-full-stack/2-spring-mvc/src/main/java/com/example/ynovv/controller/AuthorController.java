package com.example.ynovv.controller;


import com.example.ynovv.entity.Author;
import com.example.ynovv.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Author> getAuthorById(@PathVariable("idAuthor") Long id){
        Author author = authorService.findById(id);
        if(author != null){
            return ResponseEntity.status(HttpStatus.OK).body(author);
        }else{
           return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable Long id,@Valid @RequestBody Author author, BindingResult bindingResult){
        ResponseEntity<Object> errors = getObjectResponseEntity(bindingResult);
        if (errors != null) return errors;

        return ResponseEntity.ok(authorService.update(id,author));
    }



    @PostMapping("")
    public ResponseEntity<Object> createAuthor(@Valid @RequestBody Author author, BindingResult bindingResult) {
        ResponseEntity<Object> errors = getObjectResponseEntity(bindingResult);
        if (errors != null) return errors;
        return ResponseEntity.ok(authorService.save(author));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id){
        authorService.deleteById(id);
        return ResponseEntity.ok().build();
    }


    private ResponseEntity<Object> getObjectResponseEntity(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

}
