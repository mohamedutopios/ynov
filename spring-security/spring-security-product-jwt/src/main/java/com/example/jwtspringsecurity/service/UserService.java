package com.example.jwtspringsecurity.service;

import com.example.jwtspringsecurity.model.User;
import com.example.jwtspringsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email " + email));
    }


    public boolean verifyUser(String email, String password) {

        return userRepository.findByEmail(email).map(user ->
                        passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with " + email));
    }


    public boolean checkUserNameExists(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }


}
