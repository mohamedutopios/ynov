package com.example.thymeleafform.service.impl;

import com.example.thymeleafform.model.Category;
import com.example.thymeleafform.model.Product;
import com.example.thymeleafform.repository.CategoryRepository;
import com.example.thymeleafform.service.CategoryService;
import com.example.thymeleafform.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
