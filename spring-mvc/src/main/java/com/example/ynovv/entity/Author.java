package com.example.ynovv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 15, message = "Le nom ne doit pas être supeieur à 15")
    private String name;

    @NotBlank(message = "L'email ne peut être vide")
    @Email
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "author",fetch = FetchType.LAZY)
    private Set<Book> books = new HashSet<>();
/*
    @OneToMany(mappedBy = "author",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Book> books = new HashSet<>();*/

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
