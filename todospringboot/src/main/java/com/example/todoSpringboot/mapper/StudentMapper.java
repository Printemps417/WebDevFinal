package com.example.todoSpringboot.mapper;

import com.example.todoSpringboot.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {

    Student getStudentById(int id);
    List<Student> getAllStudents();
    void addStudent(Student student);

    void deleteStudent(int student_id);
    void updateStudent(Student student);

}
