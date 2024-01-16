package com.example.todoSpringboot.consumer;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.todoSpringboot.entity.Lessons;
import com.example.todoSpringboot.entity.StudentsLessons;
import com.example.todoSpringboot.service.LessonsService;
import com.example.todoSpringboot.service.StudentsLessonsService;
import com.example.todoSpringboot.utils.LockUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Queue;

/**
 * ClassName: LessonsConsumer
 * Package: com.example.todoSpringboot.consumer
 * Description:
 * User: 1953287730
 *
 * @Author 栗飞龙
 * @Create 2023/12/28 12:01
 * @Version 1.0
 */
@Component
public class LessonsConsumer {
    @Autowired
    private LockUtils lockUtils;
    @Autowired
    private StudentsLessonsService service;
    @Autowired
    private LessonsService lessonsService;

    String lessonLockKey = "lessonLockKey";//给整张大表上锁
    @RabbitListener(queues = "select.queue")
    @Transactional
    public void select(long[] ma) throws InterruptedException {
        StudentsLessons studentsLessons = new StudentsLessons();
        studentsLessons.setStudentId(ma[0]);
        studentsLessons.setLessonId(ma[1]);
        lockUtils.lock(lessonLockKey,30);
        try {
            boolean save = service.save(studentsLessons);
            UpdateWrapper<Lessons> updateWrapper = new UpdateWrapper<>();
            updateWrapper.setSql("lesson_choosen_num = lesson_choosen_num + 1")
                    .eq("lesson_id",ma[1]);
            boolean update = lessonsService.update(updateWrapper);
            if(save == true && update == true)
                System.out.println("加入成功！");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lockUtils.unlock(lessonLockKey);
        }
    }
    @RabbitListener(queues = "remove.queue")
    @Transactional
    public void cancel(long[] ma) throws InterruptedException {
        StudentsLessons studentsLessons = new StudentsLessons();
        studentsLessons.setStudentId(ma[0]);
        studentsLessons.setLessonId(ma[1]);
        lockUtils.lock(lessonLockKey,30);
        try {
            boolean remove = service.remove(new QueryWrapper<>(studentsLessons));
            UpdateWrapper<Lessons> updateWrapper = new UpdateWrapper<>();
            updateWrapper.setSql("lesson_choosen_num = lesson_choosen_num - 1")
                    .eq("lesson_id",ma[1]);
            boolean update = lessonsService.update(updateWrapper);
            if(remove == true && update == true)
                System.out.println("退课成功！");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lockUtils.unlock(lessonLockKey);
        }
    }
}
