package com.microservice.todo.portfolio.list.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.microservice.todo.portfolio.list.dto.CreatedUserDTO;
import com.microservice.todo.portfolio.list.enums.Role;
import com.microservice.todo.portfolio.list.service.UserService;

@Component
public class DataInitializerConfig implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        String adminEmail = "admin@hotmail.com";
        if (userService.findByEmail(adminEmail) == null) {
            CreatedUserDTO admin = new CreatedUserDTO();
            admin.setName("admin");
            admin.setEmail(adminEmail);
            admin.setPassword("admin");
            admin.setRole(Role.Administrador);
            userService.createdUser(admin);
            System.out.println("Administrador iniciado com sucesso!");
        } else {
            System.out.println("Administrador já existe.");
        }
    }
}
