package com.example.todoSpringboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.todoSpringboot.common.CommonResult;
import com.example.todoSpringboot.entity.Lessons;
import com.example.todoSpringboot.note.AuthToken;
import com.example.todoSpringboot.service.LessonService;
import com.example.todoSpringboot.service.LessonsService;
import com.example.todoSpringboot.utils.LockUtils;
import com.example.todoSpringboot.utils.ThreadLocalParaments;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.stream.Collectors;

/**
 * ClassName: LessonsController
 * Package: com.example.todoSpringboot.controller
 * Description:
 * User: 1953287730
 *
 * @Author 栗飞龙
 * @Create 2023/12/23 22:07
 * @Version 1.0
 */
@RestController
@AuthToken
@RequestMapping("/lessons")
@Slf4j
public class LessonsController {
    @Autowired
    private LessonsService lessonsService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private LockUtils lockUtils;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    String lessonKey = "lessonKey";
    String lessonLockKey = "lessonLockKey";//给整张大表上锁



    @AuthToken
    @GetMapping("/select/{id}")
    public CommonResult<?> select(@PathVariable long id) {
        Lessons o = (Lessons) redisTemplate.opsForValue().get("lesson_" + id);
        //todo 当课程不存在时会一直sleep，更改逻辑
        if (o == null) {
            list();//如果没有查到这个课程，说明还没有加载，此时调用list将课程表全部加载到redis中
            int cnt=0;
            while (o == null) {
                if(cnt>100){
//                    如果等待了一秒还没有课程，说明该课程不存在
                    return CommonResult.error(4040,"课程不存在！选课失败");
                }
                try {
                    Thread.sleep(10); // 等待10毫秒
                    cnt++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                o = (Lessons) redisTemplate.opsForValue().get("lesson_" + id);
            }
        }
        //在这里得到学生的id
        int stu_id=Integer.parseInt(ThreadLocalParaments.getStuId());
        long[] ma = new long[]{stu_id, id};//对应的是学生id，和课程id
        System.out.println("正在查询选课资格："+stu_id+" "+id);
        if (o.getLessonMaxNum() <= o.getLessonChoosenNum()) return CommonResult.error(4040, "人员已满");
        Set<Long> stuLessonsSet= redisTemplate.opsForSet().members("student_lessons_" + stu_id);
        //判定是否选过课程
        if(stuLessonsSet.contains(id)){
            log.info("已经选课");
            return CommonResult.error(4040,"你已选择该课程！选课失败");
        }
        //如果没有选过这们课程就会进行选课
        System.out.println("正在进行选课："+stu_id+" "+id);
        boolean lockA = lockUtils.lock(lessonKey + id, 30);//对于某一门课程上锁
        while (!lockA) {
            try {
                Thread.sleep(40); // 暂停1毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lockA = lockUtils.lock(lessonKey + id, 30);
        }
        o = (Lessons) redisTemplate.opsForValue().get("lesson_" + id);//这是准备要修改数据了，需要获取最新的课程数据
        //如果这是已经不满足选课条件了，就需要及时释放锁并且返回失败信息
        if (o.getLessonMaxNum() <= o.getLessonChoosenNum()) {
            lockUtils.unlock(lessonKey + id);
            return CommonResult.error(4040, "人员已满");
        }
        o.setLessonChoosenNum(o.getLessonChoosenNum() + 1);
        redisTemplate.opsForValue().set("lesson_" + id, o);//对于单门课程的选课人数需要进行确认
        redisTemplate.opsForSet().add("student_lessons_" + stu_id,id);//一个学生已经选择的课程进行添加
        lockUtils.unlock(lessonKey + id);//需要及时释放锁，消息队列随后加入
        rabbitTemplate.convertAndSend("select.queue", ma);
        return CommonResult.success("选课成功");
    }

    @AuthToken
    @DeleteMapping("/remove/{id}")
    public CommonResult<?> cancel(@PathVariable long id) {
        Lessons o = (Lessons) redisTemplate.opsForValue().get("lesson_" + id);
        //课程不存在的时候直接返回提示就行
        if (o == null) {
            return CommonResult.error(4040,"没有该课程");
        }
        //todo 这里学生经常获取不到信息
        if(ThreadLocalParaments.getStuId() == null){
            return CommonResult.error(4040,"未获得学生信息");
        }
        int stuId =Integer.parseInt(ThreadLocalParaments.getStuId());; // 获取学生ID
//        int stuId = 1;
        log.info("学生id"+stuId);
        long[] ma = new long[]{stuId, id};
        Set<Long> stuLessonsSet = redisTemplate.opsForSet().members("student_lessons_" + stuId); // 获取学生已选课程列表
        if (stuLessonsSet == null || !stuLessonsSet.contains(id)){
            return CommonResult.error(4040, "你未选择该课程！退课失败"); // 如果学生未选择该课程，返回错误信息
        }
        System.out.println("正在进行退课："+stuId+" "+id);
        boolean lockA = lockUtils.lock(lessonKey + id, 30); // 对课程上锁
        if (!lockA) {
            int cnt = 0;
            while (!lockA) {
                if (cnt > 100) {
                    return CommonResult.error(5000, "系统繁忙，请稍后再试"); // 如果无法获取锁，返回错误信息
                }
                try {
                    Thread.sleep(10); // 暂停10毫秒
                    cnt++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lockA = lockUtils.lock(lessonKey + id, 30);
            }
        }
        o = (Lessons) redisTemplate.opsForValue().get("lesson_" + id); // 获取最新的课程信息
        if (o.getLessonChoosenNum() <= 0) {
            lockUtils.unlock(lessonKey + id); // 如果选课人数为0，则释放锁并返回错误信息
            return CommonResult.error(5000, "选课人数异常，退课失败");
        }

        o.setLessonChoosenNum(o.getLessonChoosenNum() - 1); // 将选课人数减1
        redisTemplate.opsForValue().set("lesson_" + id, o); // 更新课程信息到缓存
        redisTemplate.opsForSet().remove("student_lessons_" + stuId,id); // 从学生已选课程列表中移除该课程

        lockUtils.unlock(lessonKey + id); // 释放锁
        rabbitTemplate.convertAndSend("remove.queue", ma);//发给mq进行数据库的操作
        return CommonResult.success("退课成功");
    }


    //根据专业名查询课程
    @GetMapping("/querybymajor/{id}")
    public CommonResult<?> queryByMajor(@PathVariable long id){
        LambdaQueryWrapper<Lessons> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Lessons::getMajorId,id);
        List<Lessons> list = lessonsService.list(queryWrapper);
        if(list == null){
            return CommonResult.error(4040,"没有查询到信息！");
        }
        return CommonResult.success(list);
    }
    @GetMapping("/getLessonById/{id}")
    public com.example.todoSpringboot.utils.CommonResult<?> getStudentById(@PathVariable int id){
        try{
            Lessons res=lessonService.getLessonById(id);
            com.example.todoSpringboot.utils.CommonResult<Lessons> result = com.example.todoSpringboot.utils.CommonResult.success(res);
            return result;
        } catch (Exception e){
            return com.example.todoSpringboot.utils.CommonResult.error(401,"查询失败");
        }
    }

    @DeleteMapping("/delete/{id}")
    public CommonResult<?> delete(@PathVariable long id) {
        boolean b = lessonsService.removeById(id);
        if (b == false) return CommonResult.error(4040, "删除失败");
        return CommonResult.success("删除成功!");
    }

    @PutMapping("/update")
    public CommonResult<?> update(@RequestBody Lessons lessons) {
        if (lessons == null || lessons.getLessonId() == null) return CommonResult.error(4040, "传入信息有误！");
        boolean b = lessonsService.updateById(lessons);
        if (b == false) return CommonResult.error(4040, "更新失败");
        return CommonResult.success("修改成功");
    }

    @PostMapping("/add")
    @Transactional
    public CommonResult<?> add(@RequestBody Lessons lesson) {
        if (lesson == null) return CommonResult.error(4040, "添加数据缺失");
        boolean b = lessonsService.save(lesson);
        if (b == false) return CommonResult.error(4040, "添加失败");
        return CommonResult.success("添加成功！");

    }

    //查询所有的课程，拥有课程的全部数据
    @GetMapping("/list")
    public CommonResult<?> list() {
        List<Object> lessons = getCachedLessons();
        if (lessons != null) {
            return CommonResult.success(lessons);
        }
        boolean lockAcquired = lockUtils.lock(lessonLockKey, 30);
        if (!lockAcquired) {
            lockAcquired = waitForLock(lessonLockKey);
            if (!lockAcquired) {
                lessons = getCachedLessons();
                if (lessons != null) {
                    return CommonResult.success(lessons);
                }
                return CommonResult.error(3030, "list为空");
            }
        }
        List<Lessons> list = lessonsService.list();
        if (list == null) {
            lockUtils.unlock(lessonLockKey);
            return CommonResult.error(3030, "list为空");
        }
        updateCache(list);
        lockUtils.unlock(lessonLockKey);
        return CommonResult.success(list);
    }

    private List<Object> getCachedLessons() {
        Object cachedData = redisTemplate.opsForValue().get(lessonKey);
        if (cachedData != null) {
            List<String> lessonKeys = (List<String>) cachedData;
            return redisTemplate.opsForValue().multiGet(lessonKeys);
        }
        return null;
    }

    private void updateCache(List<Lessons> list) {
        List<String> lessonKeys = new ArrayList<>();
        Map<String, Object> lessonMap = new HashMap<>();
        for (Lessons lesson : list) {
            String lessonKey = "lesson_" + lesson.getLessonId();
            lessonKeys.add(lessonKey);
            lessonMap.put(lessonKey, lesson);
        }
        redisTemplate.opsForValue().multiSet(lessonMap);
        redisTemplate.opsForValue().set(lessonKey, lessonKeys);
    }

    private boolean waitForLock(String lessonLockKey) {
        boolean lockAcquired;
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lockAcquired = lockUtils.lock(lessonLockKey, 30);
            if (lockAcquired) {
                break;
            }
        }
        return lockAcquired;
    }
}
