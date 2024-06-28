package com.example.ynovv.controller;


import com.example.ynovv.dto.BookCreateDto;
import com.example.ynovv.entity.Author;
import com.example.ynovv.entity.Book;
import com.example.ynovv.entity.Genre;
import com.example.ynovv.entity.Publisher;
import com.example.ynovv.service.AuthorService;
import com.example.ynovv.service.BookService;
import com.example.ynovv.service.GenreService;
import com.example.ynovv.service.PublisherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;


    @Autowired
    GenreService genreService;

    @Autowired
    PublisherService publisherService;

    @GetMapping
    public List<Book> getAllBook() {
        return bookService.findAll();
    }


    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody BookCreateDto bookCreateDto) {

        try {

            Author author = authorService.findById(bookCreateDto.getAuthorId());

            Publisher publisher = publisherService.findById(bookCreateDto.getPublishedId());

            Set<Genre> genreSet = new HashSet<>();

            for (Long id : bookCreateDto.getGenreIds()) {
                Genre genre = genreService.findById(id);
                if (genre != null) {
                    genreSet.add(genre);
                }
            }
            Book book = new Book();
            book.setTitle(bookCreateDto.getTitle());
            book.setAuthor(author);
            book.setPublisher(publisher);
            book.setGenres(genreSet);

            return ResponseEntity.ok(bookService.save(book));

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }


    }


}
