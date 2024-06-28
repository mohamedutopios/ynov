package com.example.thymeleafform.controller;


import com.example.thymeleafform.model.Product;
import com.example.thymeleafform.service.CategoryService;
import com.example.thymeleafform.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String listProduct(Model model){
        List<Product> productList = productService.findAll();
        model.addAttribute("products",productList);
        return "product/list";
    }

    @GetMapping("/new")
    public String showForm(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("categories",categoryService.findAll());
        return "product/form";
    }

    @PostMapping()
    public String valideForm(@Valid @ModelAttribute Product product,
                             BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("categories",categoryService.findAll());
            return "product/form";
        }
        productService.save(product);
        return "redirect:/products";

    }






}
