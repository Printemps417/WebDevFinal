package com.example.todoSpringboot.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.todoSpringboot.entity.Majors;
import com.example.todoSpringboot.mapper.MajorsMapper;
import com.example.todoSpringboot.service.MajorsService;
import org.springframework.stereotype.Service;

/**
 * ClassName: MajorsServiceImpl
 * Package: com.example.todoSpringboot.service.Impl
 * Description:
 * User: 1953287730
 *
 * @Author 栗飞龙
 * @Create 2023/12/24 15:57
 * @Version 1.0
 */
@Service
public class MajorsServiceImpl extends ServiceImpl<MajorsMapper, Majors> implements MajorsService {
}
