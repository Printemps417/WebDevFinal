package com.example.todoSpringboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: Teachers
 * Package: com.example.todoSpringboot.entity
 * Description:
 * User: 1953287730
 *
 * @Author 栗飞龙
 * @Create 2023/12/25 14:44
 * @Version 1.0
 */
@Data
public class Teachers implements Serializable {
    @TableId(value = "teacher_id",type = IdType.AUTO)
    private Long teacherId;
    private String teacherName,teacherSex;
    private Long majorId;
}
