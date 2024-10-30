package com.microservice.todo.portfolio.list.model;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microservice.todo.portfolio.list.enums.Role;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testUserId() {
        UUID id = UUID.randomUUID();
        user.setId(id);
        assertThat(user.getId()).isEqualTo(id);
    }

    @Test
    public void testUserEmail() {
        String email = "test@example.com";
        user.setEmail(email);
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    public void testUserName() {
        String name = "John Doe";
        user.setName(name);
        assertThat(user.getName()).isEqualTo(name);
    }

    @Test
    public void testUserPassword() {
        String password = "password123";
        user.setPassword(password);
        assertThat(user.getPassword()).isEqualTo(password);
    }

    @Test
    public void testUserRole() {
        Role role = Role.Usuario;
        user.setRole(role);
        assertThat(user.getRole()).isEqualTo(role);
    }

    @Test
    public void testUserAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        String email = "test@example.com";
        String name = "John Doe";
        String password = "password123";
        Role role = Role.Usuario;

        User user2 = new User(id, email, name, password, role);

        assertThat(user2.getId()).isEqualTo(id);
        assertThat(user2.getEmail()).isEqualTo(email);
        assertThat(user2.getName()).isEqualTo(name);
        assertThat(user2.getPassword()).isEqualTo(password);
        assertThat(user2.getRole()).isEqualTo(role);
    }

    @Test
    public void testUserNoArgsConstructor() {
        User newUser = new User();
        assertThat(newUser).isNotNull();
    }
}
