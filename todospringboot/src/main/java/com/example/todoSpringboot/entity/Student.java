package com.example.todoSpringboot.entity;

import lombok.Data;

@Data
public class Student {
    private Integer studentId;
    private String studentName;
    private String studentSex;
    private Integer majorId;
}
