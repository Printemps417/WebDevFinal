package com.example.todoSpringboot.service;

import com.example.todoSpringboot.entity.StudentLesson;
import com.example.todoSpringboot.mapper.StudentLessonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentLessonService  {
    @Autowired
    private StudentLessonMapper studentLessonMapper;
    public void addStudentLesson(StudentLesson studentlesson) {
        studentLessonMapper.addStudentLesson(studentlesson);
    }

    public void updateStudentLesson(StudentLesson studentlesson) {
        studentLessonMapper.updateStudentLesson(studentlesson);
    }

    public List<StudentLesson> getStudentLessonById(int student_id) {
        return studentLessonMapper.getStudentLessonById(student_id);
    }

    public List<StudentLesson> getAllStudentLessons() {
        return studentLessonMapper.getAllStudentLessons();
    }
    public void deleteStudentLesson(int student_id, int lesson_id) {
        studentLessonMapper.deleteStudentLesson(student_id,lesson_id);
    }

    public void deleteStudentLessons(int student_id) {
        studentLessonMapper.deleteStudentLessons(student_id);
    }
}
