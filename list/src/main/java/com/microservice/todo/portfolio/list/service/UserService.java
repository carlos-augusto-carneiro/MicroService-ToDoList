package com.microservice.todo.portfolio.list.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservice.todo.portfolio.list.dto.CreatedUserDTO;
import com.microservice.todo.portfolio.list.model.User;
import com.microservice.todo.portfolio.list.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User createdUser(CreatedUserDTO createdUserDTO) {
        Optional<User> userOptional = userRepository.findByEmail(createdUserDTO.getEmail());
        if (userOptional.isPresent()) {
            throw new RuntimeException("User ja existe");
        }
        User newUser = new User();
        newUser.setEmail(createdUserDTO.getEmail());
        newUser.setName(createdUserDTO.getName());
        newUser.setRole(createdUserDTO.getRole());
        newUser.setPassword(passwordEncoder.encode(createdUserDTO.getPassword()));
        return userRepository.save(newUser);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
