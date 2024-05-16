package com.example.thymeleaf.controller;

import com.example.thymeleaf.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/form")
    public String form(Model model){
        model.addAttribute("product",new Product());
        return "product/form";
    }


    @PostMapping("/submit")
    private ModelAndView submitForm(@ModelAttribute Product product){
        ModelAndView mav = new ModelAndView("redirect:/product/result");
        mav.addObject("name",product.getName());
        mav.addObject("price", product.getPrice());
        return mav;
    }

    @GetMapping("/result")
    public String result(@ModelAttribute("name") String name,
                         @ModelAttribute("price") double price,
                         Model model){
        model.addAttribute("name",name);
        model.addAttribute("price",price);

        return "product/result";

    }


}
