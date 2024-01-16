package com.example.todoSpringboot.mapper;

import com.example.todoSpringboot.entity.Lessons;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LessonMapper {

    Lessons getLessonById(int id);

}
