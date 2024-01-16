package com.example.todoSpringboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: Majors
 * Package: com.example.todoSpringboot.entity
 * Description:
 * User: 1953287730
 *
 * @Author 栗飞龙
 * @Create 2023/12/24 15:52
 * @Version 1.0
 */
@Data
public class Majors implements Serializable {

    @TableId(value = "major_id",type = IdType.AUTO)
    private Long majorId;
    private String majorName;
}
