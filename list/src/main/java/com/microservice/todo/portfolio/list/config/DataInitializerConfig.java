package com.microservice.todo.portfolio.list.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.microservice.todo.portfolio.list.dto.CreatedUserDTO;
import com.microservice.todo.portfolio.list.enums.Role;
import com.microservice.todo.portfolio.list.service.UserService;

@Component
public class DataInitializerConfig implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        CreatedUserDTO admin = new CreatedUserDTO();
        admin.setName("admin");
        admin.setEmail("admin@hotmail.com");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRole(Role.Administrador);
        boolean adminExists = userService.findByEmail(admin.getEmail()) != null;
        if (adminExists == false) {
            userService.createdUser(admin);
            System.out.println("Administrador iniciado com sucesso!");
        } else {
            System.out.println("Administrador já existe.");
        }
    }
}
