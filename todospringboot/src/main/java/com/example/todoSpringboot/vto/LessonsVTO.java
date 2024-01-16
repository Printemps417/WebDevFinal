package com.example.todoSpringboot.vto;

import com.example.todoSpringboot.entity.Lessons;
import lombok.Data;

/**
 * ClassName: LessonsVTO
 * Package: com.example.todoSpringboot.vto
 * Description:
 * User: 1953287730
 *
 * @Author 栗飞龙
 * @Create 2023/12/24 15:46
 * @Version 1.0
 */
@Data
public class LessonsVTO extends Lessons {
    private String name;
}
