package com.example.todoSpringboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.todoSpringboot.common.CommonResult;
import com.example.todoSpringboot.entity.Majors;
import com.example.todoSpringboot.service.MajorsService;
import com.example.todoSpringboot.utils.LockUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: MajorsController
 * Package: com.example.todoSpringboot.controller
 * Description:
 * User: 1953287730
 *
 * @Author 栗飞龙
 * @Create 2023/12/24 15:58
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/majors")
public class MajorsController {

    @Autowired
    private MajorsService majorsService;
    @Autowired
    private LockUtils lockUtils;
    @Autowired
    private RedisTemplate redisTemplate;

    private String majorKey = "majorKey";
    String majorLockKey = "majorLockKey";




    @PostMapping("/update")
    public CommonResult<?> update(@RequestBody Majors major){
        if(major == null || major.getMajorId() == null){
            return CommonResult.error(4040,"输入错误，更改失败！");
        }
        LambdaQueryWrapper<Majors> queryWrapper = new LambdaQueryWrapper<>();
        boolean updateById  = false;
        lockUtils.lock(majorLockKey,30);
        try{
            updateById = majorsService.updateById(major);
            if(!updateById)
                return CommonResult.error(4040,"更新失败");
            redisTemplate.opsForValue().set("major_" + major.getMajorId(),major);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lockUtils.unlock(majorLockKey);
        }
        return CommonResult.success("更新成功！");
    }



    @PostMapping("/add")
    public CommonResult<?> add(@RequestBody Majors major){
        if(major == null){
            return CommonResult.error(4040,"添加的数据为空！");
        }
        lockUtils.lock(majorLockKey,30);
        boolean isSave = majorsService.save(major);
        if(!isSave){
            lockUtils.unlock(majorKey);
            return CommonResult.error(4040,"保存失败");
        }
        Object o = redisTemplate.opsForValue().get(majorKey);
        List<String> list = (List<String>)o;
        list.add("major_" + major.getMajorId());
        redisTemplate.opsForValue().set(majorKey,list);
        redisTemplate.opsForValue().set("major_" + major.getMajorId(),major);
        lockUtils.unlock(majorLockKey);
        return CommonResult.success("添加专业成功！");
    }
    @GetMapping("/list")
    public CommonResult<?> list(){
        Object o = redisTemplate.opsForValue().get(majorKey);
        if(o != null){
            List<String> collect = (List<String>) o;
            List<Object> collect1 = collect.stream().map(item -> {
                return redisTemplate.opsForValue().get(item);
            }).collect(Collectors.toList());
            return CommonResult.success(collect1);
        }
        lockUtils.lock(majorLockKey,30);
        List<Majors> list =null;
        try {
             list = majorsService.list();
        }catch (Exception e){
            lockUtils.unlock(majorLockKey);
            return CommonResult.error(4040,"查询不到字段");
        }
        if(list == null){
            lockUtils.unlock(majorLockKey);
            return CommonResult.error(3030,"major为空");
        }
        List<String> collect = list.stream().map((item) -> {
            redisTemplate.opsForValue().set("major_" + item.getMajorId(), item);
            return "major_" + item.getMajorId();
        }).collect(Collectors.toList());
        redisTemplate.opsForValue().set(majorKey,collect);
        lockUtils.unlock(majorLockKey);
        return CommonResult.success(list);
    }
}
