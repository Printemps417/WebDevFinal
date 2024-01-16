package com.example.todoSpringboot.mapper;

import com.example.todoSpringboot.entity.StudentLesson;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentLessonMapper {

    List<StudentLesson> getStudentLessonById(int student_id);
    List<StudentLesson> getAllStudentLessons();
    void addStudentLesson(StudentLesson studentlesson);
    void updateStudentLesson(StudentLesson studentlesson);

    void deleteStudentLesson(int student_id,int lesson_id);
    void deleteStudentLessons(int student_id);

}
