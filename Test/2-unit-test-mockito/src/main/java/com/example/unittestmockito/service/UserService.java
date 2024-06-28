package com.example.unittestmockito.service;

import com.example.unittestmockito.exception.EntityNotFoundException;
import com.example.unittestmockito.exception.InvalidEmailException;
import com.example.unittestmockito.model.User;
import com.example.unittestmockito.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository){
    this.userRepository = userRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        if (!user.getEmail().contains("@")) {
            throw new InvalidEmailException("Invalid format email");
        }
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        if (userRepository.findById(id).isPresent()) {
            user.setId(id);
            return userRepository.save(user);
        }else {
            throw new EntityNotFoundException("User not found");
        }
    }

    public void deleteUserById(Long id){
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("User not found");
        }


    }

}
