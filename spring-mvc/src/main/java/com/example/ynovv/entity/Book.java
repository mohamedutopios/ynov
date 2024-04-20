package com.example.ynovv.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le livre doit avoir un titre")
    @Size(max = 200, message = "Titre doit être inférieur à 200")
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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
               // ", genres=" + genres +
                '}';
    }
}
