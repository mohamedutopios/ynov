package com.example.ynovv.controller;


import com.example.ynovv.entity.Author;
import com.example.ynovv.entity.Genre;
import com.example.ynovv.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {


    @Autowired
    private GenreService genreService;


    @GetMapping
    public List<Genre> getAllGenres(){
        return genreService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable("id") Long id){
        Genre genre = genreService.findById(id);
        if(genre != null){
            return ResponseEntity.status(HttpStatus.OK).body(genre);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody Genre genre){
        try{
            Genre genre1 = genreService.update(id,genre);
            if(genre1!= null){
                return ResponseEntity.ok(genre1);
            }else {
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping
    public ResponseEntity<Genre> createGenre(@Valid @RequestBody Genre genre){
        try{
            Genre genre1 = genreService.save(genre);
            return ResponseEntity.ok(genre1);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id){
       try{
           genreService.deleteById(id);
           return ResponseEntity.ok().build();
       }catch (Exception e) {
           return ResponseEntity.badRequest().build();

       }

    }







}
