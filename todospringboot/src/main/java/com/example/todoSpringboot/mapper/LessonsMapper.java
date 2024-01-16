package com.example.todoSpringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.todoSpringboot.entity.Lessons;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: LessonMapper
 * Package: com.example.todoSpringboot.mapper
 * Description:
 * User: 1953287730
 *
 * @Author 栗飞龙
 * @Create 2023/12/24 12:49
 * @Version 1.0
 */
@Mapper
public interface LessonsMapper extends BaseMapper<Lessons> {
}
