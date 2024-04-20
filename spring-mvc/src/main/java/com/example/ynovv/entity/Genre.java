package com.example.ynovv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 15, message = "Le nom ne doit pas être supeieur à 15")
    @NotBlank(message =" Le genre doit avoir un nom")
    @Size(min = 3,message = "Le nom doit être plus grand que 3")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "genres")
    private Set<Book> books = new HashSet<>();



}
