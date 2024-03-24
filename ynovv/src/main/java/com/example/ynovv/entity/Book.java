package com.example.ynovv.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;


    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;


    @ManyToMany()
    @JoinTable(name="book_genre",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name="genre_id"))
    private Set<Genre> genres = new HashSet<>();

}
