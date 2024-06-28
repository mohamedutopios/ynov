package com.example.thymeleafform.service;

import com.example.thymeleafform.model.Category;
import com.example.thymeleafform.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    List<Product> findAll();
    Product findById(Long id);

    Product save(Product product);

    void deleteById(Long id);

}
