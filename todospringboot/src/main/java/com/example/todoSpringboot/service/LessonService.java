package com.example.todoSpringboot.service;

import com.example.todoSpringboot.entity.Lessons;
import com.example.todoSpringboot.mapper.LessonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {
    @Autowired
    private LessonMapper lessonMapper;
    public Lessons getLessonById(int id) {
        return lessonMapper.getLessonById(id);
    }



}
