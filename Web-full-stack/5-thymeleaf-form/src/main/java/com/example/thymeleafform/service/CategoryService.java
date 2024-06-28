package com.example.thymeleafform.service;

import com.example.thymeleafform.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    List<Category> findAll();
    Category findById(Long id);

    Category save(Category category);

    void deleteById(Long id);



}
