package com.example.thymeleaf.controller;


import com.example.thymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class UserController {

    @GetMapping("/user")
    public String user(Model model){
        User  user = new User("John Doe", 45);
        model.addAttribute("user", user);
        return "user";
    }


    @GetMapping("/users")
    public String users(Model model){
        List<User> users = Arrays.asList(
                new User("John Doe", 45),
                new User("Mathys Muse", 25),
                new User("Jane Do", 120),
                new User("Alain Deloin", 78)
                );

        model.addAttribute("users", users);
        return "liste";
    }

    @GetMapping("/condition")
    public String condition(Model model){
        List<User> users = Arrays.asList(
                new User("John Doe", 45, "active"),
                new User("Mathys Muse", 17,"active"),
                new User("Jane Do", 14,"banned"),
                new User("Alain Deloin", 78,"inactive")
        );

        model.addAttribute("users", users);
        return "conditional";
    }

    @GetMapping("/iteration")
    public String iteration(Model model){
        List<User> users = Arrays.asList(
                new User("John Doe", 45, "active"),
                new User("Mathys Muse", 17,"active"),
                new User("Jane Do", 14,"banned"),
                new User("Alain Deloin", 78,"inactive")
        );
        HashMap<String, Integer> map = new HashMap<>();

        users.stream().forEach(user -> {
            map.put(user.getName().split(" ")[0],user.getAge());
        });
        model.addAttribute("map",map);
        model.addAttribute("users", users);
        return "iteration";
    }


    @GetMapping("/append")
    public String append(Model model){
        List<User> users = Arrays.asList(
                new User("John Doe", 45, "active"),
                new User("Mathys Muse", 17,"active"),
                new User("Jane Do", 14,"banned"),
                new User("Alain Deloin", 78,"inactive")
        );

        model.addAttribute("message","Bonjour");
        model.addAttribute("success",true);
        model.addAttribute("users", users);
        return "conditional-append";
    }

    @GetMapping("/dynamique")
    public String dynamique(){
        return "dynamique";
    }



}
