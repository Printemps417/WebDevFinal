package com.example.todoSpringboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.todoSpringboot.common.CommonResult;
import com.example.todoSpringboot.entity.Teachers;
import com.example.todoSpringboot.service.Impl.LessonsServiceImpl;
import com.example.todoSpringboot.service.MajorsService;
import com.example.todoSpringboot.service.TeachersService;
import com.example.todoSpringboot.utils.LockUtils;
import com.example.todoSpringboot.vto.TeachersVTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: TeachersController
 * Package: com.example.todoSpringboot.controller
 * Description:
 * User: 1953287730
 *
 * @Author 栗飞龙
 * @Create 2023/12/25 14:47
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/teachers")
public class TeachersController {
    @Autowired
    private LockUtils lockUtils;
    @Autowired
    private TeachersService teachersService;
    @Autowired
    private MajorsService majorsService;

    @Autowired
    private LessonsServiceImpl lessonsService;


    @GetMapping("/{id}")
    public CommonResult<?> getTeacherById(@PathVariable long id){
        try {
            Teachers byId = teachersService.getById(id);
            if(byId == null){
                return CommonResult.error(4040,"没有该教师");
            }
            return CommonResult.success(byId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



    @DeleteMapping("/delete/{id}")
    @Transactional
    public CommonResult<?> delete(@PathVariable Long id){
        CommonResult<?> commonResult = lessonsService.deleteByTeacherId(id);
        String msg = commonResult.getMsg();
        if(msg == null || msg.equals("删除失败")){
            return CommonResult.error(4040,"删除失败");
        }
        LambdaQueryWrapper<Teachers> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teachers::getTeacherId,id);
        boolean remove = teachersService.remove(queryWrapper);
        log.info("删除教师中remove:" + remove);
        if(!remove){
            return CommonResult.error(4040,"删除失败");
        }
        return CommonResult.success("删除成功");
    }

    @PutMapping("/update")
    public CommonResult<?> update(@RequestBody Teachers teachers){
        if(teachers == null){
            return CommonResult.error(4040, "传入信息错误");
        }
        boolean b = teachersService.updateById(teachers);
        if(b == false)
            return CommonResult.error(4040, "更新失败");
        return CommonResult.success("更新成功");
    }

    @PostMapping("/add")
    public CommonResult<?> add(@RequestBody Teachers teachers) {
        boolean save = teachersService.save(teachers);
        if(save == false)
            return CommonResult.error(4040,"新增教师失败");
        System.out.println(teachers.getTeacherId());
        return CommonResult.success("新增教师成功");
    }

    @GetMapping("/list")
    public CommonResult<?> list(String name){
        LambdaQueryWrapper<Teachers> queryWrapper  = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null,Teachers::getTeacherName,name);
        List<Teachers> teachersList = teachersService.list(queryWrapper);
        List<TeachersVTO> teachersVTOList  = null;
        teachersVTOList = teachersList.stream().map(item -> {
            TeachersVTO temp = new TeachersVTO();
            BeanUtils.copyProperties(item,temp);
            temp.setMajorName(majorsService.getById(item.getMajorId()).getMajorName());
            return temp;
        }).collect(Collectors.toList());
        return CommonResult.success(teachersVTOList);
    }
}
