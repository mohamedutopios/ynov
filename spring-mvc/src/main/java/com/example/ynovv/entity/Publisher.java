package com.example.ynovv.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @OneToOne(mappedBy = "publisher" )
    private Book book;

}
