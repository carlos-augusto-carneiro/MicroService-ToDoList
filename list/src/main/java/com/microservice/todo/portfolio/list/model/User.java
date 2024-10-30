package com.microservice.todo.portfolio.list.model;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.microservice.todo.portfolio.list.dto.LoginDTORequest;
import com.microservice.todo.portfolio.list.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true)
    @Email
    @NotNull
    private String email;
    @NotNull
    @Size(min = 2, max = 100)
    private String name;
    @NotNull
    private String password;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    public boolean isLoginCorrect(LoginDTORequest loginDtoRequest, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginDtoRequest.password(), this.password);
    }
}
