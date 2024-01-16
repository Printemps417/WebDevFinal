package com.example.todoSpringboot.entity;

import lombok.Data;

@Data
public class Account {
    private String account;
    private String password;
    private Integer studentId;
    private String type;
}
