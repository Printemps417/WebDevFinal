package com.example.todoSpringboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.todoSpringboot.common.CommonResult;
import com.example.todoSpringboot.entity.Lessons;

/**
 * ClassName: LessonsService
 * Package: com.example.todoSpringboot.service
 * Description:
 * User: 1953287730
 *
 * @Author 栗飞龙
 * @Create 2023/12/24 12:49
 * @Version 1.0
 */
public interface LessonsService extends IService<Lessons> {
    CommonResult<?> deleteByTeacherId(long id);
}
