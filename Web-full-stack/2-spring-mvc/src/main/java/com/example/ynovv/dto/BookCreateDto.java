package com.example.ynovv.dto;

import lombok.Data;

import java.util.Set;

@Data
public class BookCreateDto {

    private String title;
    private Long authorId;
    private Long publishedId;
    private Set<Long> genreIds;

}
