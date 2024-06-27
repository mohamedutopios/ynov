package com.example.jwtspringsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.jwtspringsecurity.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);


}
