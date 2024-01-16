package com.example.todoSpringboot.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.todoSpringboot.entity.*;
import com.example.todoSpringboot.note.AuthToken;
import com.example.todoSpringboot.service.*;
import com.example.todoSpringboot.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AuthToken
@RequestMapping("/studentlesson")
public class StudentLessonController {
    @Autowired
    private StudentLessonService studentLessonService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private MajorsService majorsService;
    @Autowired
    private LessonsService lessonsService;
    @GetMapping("/")
    public CommonResult<List<Pair<Integer, List<Lessons>>>> getAllStudents() {
        try {
            List<Pair<Integer, List<Lessons>>> result = new ArrayList<>();
            List<Student> students = studentService.getAllStudents();
            for (Student student : students) {
                int id = student.getStudentId();
                List<Lessons> lessonlist = new ArrayList<>();
                List<StudentLesson> studentLessonList = studentLessonService.getStudentLessonById(id);
                for (StudentLesson studentLesson : studentLessonList) {
                    Lessons tmp = lessonService.getLessonById(studentLesson.getLessonId());
                    lessonlist.add(tmp);
                }
                result.add(new Pair<>(id, lessonlist));
            }
            CommonResult<List<Pair<Integer, List<Lessons>>>> commonResult = CommonResult.success(result);
            return commonResult;
        } catch (Exception e) {
            return CommonResult.error(401, "查询失败");
        }
    }

    @GetMapping("/{student_id}")
    public  CommonResult<?> getStudentLessonById(@PathVariable int student_id) {
        try {
            List<Lessons> res = new ArrayList<>(); // 初始化列表
            List<StudentLesson> studentLessonList = studentLessonService.getStudentLessonById(student_id);
            for (StudentLesson studentLesson : studentLessonList) {
                Lessons tmp = lessonService.getLessonById(studentLesson.getLessonId()); // 定义 tmp 的类型
                Majors mj= majorsService.getById(tmp.getMajorId());
                res.add(tmp);
            }
            CommonResult<List<Lessons>> result = CommonResult.success(res);
            return result;
        } catch (Exception e) {
            return CommonResult.error(401, "查询失败");
        }
    }
    @PostMapping("/")
    public CommonResult<?> addStudentLesson(@RequestBody StudentLesson studentlesson) {
        try {
            studentLessonService.addStudentLesson(studentlesson);
            // 更新lessons表中的lesson_choosen_num字段
            UpdateWrapper<Lessons> updateWrapper = new UpdateWrapper<>();
            updateWrapper.setSql("lesson_choosen_num = lesson_choosen_num + 1")
                    .eq("lesson_id", studentlesson.getLessonId());
            boolean update = lessonsService.update(updateWrapper);
            if (!update) {
                throw new Exception("更新失败");
            }
            return CommonResult.success("添加成功");
        } catch (Exception e) {
            // 处理或记录错误
            return CommonResult.error(401, "添加失败：" + e.getMessage());
        }
    }


    @PutMapping("/")
    public void updateStudentLesson(@RequestBody StudentLesson studentlesson) {
        studentLessonService.updateStudentLesson(studentlesson);
    }
    @DeleteMapping("/{student_id}/{lesson_id}")
    public CommonResult<?> deleteStudentLesson(@PathVariable int student_id, @PathVariable int lesson_id) {
        try {
            studentLessonService.deleteStudentLesson(student_id, lesson_id);
            // 更新lessons表中的lesson_choosen_num字段
            UpdateWrapper<Lessons> updateWrapper = new UpdateWrapper<>();
            updateWrapper.setSql("lesson_choosen_num = lesson_choosen_num - 1")
                    .eq("lesson_id", lesson_id);
            boolean update = lessonsService.update(updateWrapper);
            if (!update) {
                throw new Exception("更新失败");
            }
            return CommonResult.success("删除成功");
        } catch (Exception e) {
            // 处理或记录错误
            return CommonResult.error(401, "删除失败：" + e.getMessage());
        }
    }

    @DeleteMapping("/{student_id}")
    public CommonResult<?> deleteStudentLessons(@PathVariable int student_id) {
        try {
            // 获取该学生选修的所有课程
            List<StudentLesson> studentLessonList = studentLessonService.getStudentLessonById(student_id);
            List<Integer> lessonIds = studentLessonList.stream().map(StudentLesson::getLessonId).collect(Collectors.toList());
            studentLessonService.deleteStudentLessons(student_id);
            // 批量更新lessons表中的lesson_choosen_num字段
            UpdateWrapper<Lessons> updateWrapper = new UpdateWrapper<>();
            updateWrapper.setSql("lesson_choosen_num = lesson_choosen_num - 1")
                    .in("lesson_id", lessonIds);
            boolean update = lessonsService.update(updateWrapper);
            if (!update) {
                throw new Exception("更新失败");
            }
            return CommonResult.success("删除成功");
        } catch (Exception e) {
            // 处理或记录错误
            return CommonResult.error(401, "删除失败：" + e.getMessage());
        }
    }
}

