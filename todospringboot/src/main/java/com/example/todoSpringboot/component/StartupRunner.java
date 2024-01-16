package com.example.todoSpringboot.component;

import com.example.todoSpringboot.entity.Lessons;
import com.example.todoSpringboot.entity.StudentsLessons;
import com.example.todoSpringboot.service.LessonsService;
import com.example.todoSpringboot.service.StudentsLessonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.PreDestroy;

import javax.annotation.PreDestroy;
import java.util.List;

@Component
public class StartupRunner implements CommandLineRunner {
    @Autowired
    private LessonsService lessonService; // 假设这是您的服务类
    @Autowired
    private StudentsLessonsService studentsLessonsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        List<Lessons> lessons = lessonService.list();
        List<StudentsLessons> studentslessons=studentsLessonsService.list();
        for (Lessons lesson : lessons) {
            redisTemplate.opsForValue().set("lesson_"+lesson.getLessonId(), lesson);
        }
        for (StudentsLessons studentLesson : studentslessons) {
            String studentKey = "student_lessons_" + studentLesson.getStudentId();
//            视为集合
            redisTemplate.opsForSet().add(studentKey, studentLesson.getLessonId());
//            System.out.println(studentKey+":"+studentLesson.getLessonId());
        }
    }
}