package com.example.todoSpringboot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * ClassName: StudentsLessons
 * Package: com.example.todoSpringboot.entity
 * Description:
 * User: 1953287730
 *
 * @Author 栗飞龙
 * @Create 2023/12/28 12:06
 * @Version 1.0
 */
@Data
public class StudentsLessons {
    //@TableId(value = "student_id")
    private Long studentId;
    //@TableId(value = "lesson_id")
    private Long lessonId;
}
