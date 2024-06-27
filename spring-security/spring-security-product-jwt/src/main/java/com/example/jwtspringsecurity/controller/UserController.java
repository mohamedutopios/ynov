package com.example.jwtspringsecurity.controller;


import com.example.jwtspringsecurity.dto.BaseResponseDto;
import com.example.jwtspringsecurity.dto.UserLoginDto;
import com.example.jwtspringsecurity.model.User;
import com.example.jwtspringsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public BaseResponseDto registerUser(@RequestBody User user){

        if(userService.createUser(user)){
            return new BaseResponseDto("Success");
        }else{
            return new BaseResponseDto("Failed");
        }

    }


    @PostMapping("/login")
    public BaseResponseDto loginUser(@RequestBody UserLoginDto loginDto){
        if(userService.checkUserNameExists(loginDto.getEmail())){
            if(userService.verifyUser(loginDto.getEmail(), loginDto.getPassword())){
                Map<String, String> data = new HashMap<>();
                data.put("token", userService.generateToken(loginDto.getEmail(), loginDto.getPassword()));
                return new BaseResponseDto("Success",data);

            }else{
                return new BaseResponseDto("wrong password");
            }
        }else{
            return new BaseResponseDto("User do not exist");
        }

    }





}
