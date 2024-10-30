package com.microservice.todo.portfolio.list.dto;

import com.microservice.todo.portfolio.list.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatedUserDTO {
    private String email;
    private String name;
    private String password;
    private Role role;
}
