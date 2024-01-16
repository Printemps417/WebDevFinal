package com.example.todoSpringboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.todoSpringboot.common.CommonResult;
import com.example.todoSpringboot.entity.Lessons;
import com.example.todoSpringboot.entity.StudentsLessons;
import com.example.todoSpringboot.mapper.LessonsMapper;
import com.example.todoSpringboot.service.LessonsService;
import com.example.todoSpringboot.service.StudentLessonService;
import com.example.todoSpringboot.service.StudentsLessonsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: LessonsServiceImpl
 * Package: com.example.todoSpringboot.service.Impl
 * Description:
 * User: 1953287730
 *
 * @Author 栗飞龙
 * @Create 2023/12/24 12:50
 * @Version 1.0
 */
@Slf4j
@Service
public class LessonsServiceImpl extends ServiceImpl<LessonsMapper, Lessons> implements LessonsService {
    @Autowired
    private StudentsLessonsServiceImpl studentsLessonsService;

    @Override
    public CommonResult<?> deleteByTeacherId(long id) {
        LambdaQueryWrapper<Lessons> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Lessons::getTeacherId,id);//找到所有该教师教授的课程成
        List<Lessons> list = list(queryWrapper);//所有教授的课程
        List<Long> collect = list.stream().map((item) -> {
            return item.getLessonId();
        }).collect(Collectors.toList());//得到所有教授课程的id
        LambdaQueryWrapper<StudentsLessons> studentsLessonsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentsLessonsLambdaQueryWrapper.in(StudentsLessons::getLessonId,collect);
        boolean remove = studentsLessonsService.remove(studentsLessonsLambdaQueryWrapper);
        log.info("deleteByTeacherId中remove的值为：" + remove);
        boolean remove1 = remove(queryWrapper);
        log.info("deleteByTeacherId中remove1的值为：" + remove1);
        return CommonResult.success("删除成功");
    }
}
