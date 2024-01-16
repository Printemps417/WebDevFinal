package com.example.todoSpringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.todoSpringboot.entity.Teachers;
import org.apache.ibatis.annotations.Mapper;

import java.util.stream.BaseStream;

/**
 * ClassName: TeachersMapper
 * Package: com.example.todoSpringboot.mapper
 * Description:
 * User: 1953287730
 *
 * @Author 栗飞龙
 * @Create 2023/12/25 14:45
 * @Version 1.0
 */
@Mapper
public interface TeachersMapper extends BaseMapper<Teachers> {
}
