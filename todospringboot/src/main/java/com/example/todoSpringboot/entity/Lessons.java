package com.example.todoSpringboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: Lessons
 * Package: com.example.todoSpringboot.entity
 * Description:
 * User: 1953287730
 *
 * @Author 栗飞龙
 * @Create 2023/12/24 12:46
 * @Version 1.0
 */
@Data
public class Lessons implements Serializable {
    @TableId(value = "lesson_id",type = IdType.AUTO)
    private Long lessonId;
    private String lessonName;
    private Long teacherId,majorId;
    private String lessonLocation,lessonTime;
    private Long lessonChoosenNum,lessonMaxNum;
}
