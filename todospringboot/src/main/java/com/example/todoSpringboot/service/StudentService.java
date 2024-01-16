package com.example.todoSpringboot.service;


import com.example.todoSpringboot.entity.Student;
import com.example.todoSpringboot.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;
    public void addStudent(Student student) {
        studentMapper.addStudent(student);
    }

    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    public Student getStudentById(int id) {
        return studentMapper.getStudentById(id);
    }

    public void deleteStudent(int student_id) {
        studentMapper.deleteStudent(student_id);
    }

    public List<Student> getAllStudents() {
        return studentMapper.getAllStudents();
    }
}
