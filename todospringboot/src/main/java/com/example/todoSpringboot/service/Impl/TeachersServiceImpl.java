package com.example.todoSpringboot.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.todoSpringboot.entity.Teachers;
import com.example.todoSpringboot.mapper.TeachersMapper;
import com.example.todoSpringboot.service.TeachersService;
import org.springframework.stereotype.Service;

/**
 * ClassName: TeachersServiceImpl
 * Package: com.example.todoSpringboot.service.Impl
 * Description:
 * User: 1953287730
 *
 * @Author 栗飞龙
 * @Create 2023/12/25 14:47
 * @Version 1.0
 */
@Service
public class TeachersServiceImpl extends ServiceImpl<TeachersMapper, Teachers> implements TeachersService {
}
