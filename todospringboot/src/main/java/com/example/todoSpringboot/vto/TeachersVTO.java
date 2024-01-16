package com.example.todoSpringboot.vto;

import com.example.todoSpringboot.entity.Teachers;
import lombok.Data;

/**
 * ClassName: TeachersVTO
 * Package: com.example.todoSpringboot.vto
 * Description:
 * User: 1953287730
 *
 * @Author 栗飞龙
 * @Create 2023/12/25 14:59
 * @Version 1.0
 */
@Data
public class TeachersVTO extends Teachers {
    private String majorName;
}
