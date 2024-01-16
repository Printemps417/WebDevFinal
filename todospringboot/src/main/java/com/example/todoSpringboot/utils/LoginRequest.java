package com.example.todoSpringboot.utils;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
    private String type;
}