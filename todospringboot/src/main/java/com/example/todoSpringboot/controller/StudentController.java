package com.example.todoSpringboot.controller;

import com.example.todoSpringboot.entity.Student;
import com.example.todoSpringboot.service.AccountService;
import com.example.todoSpringboot.service.StudentLessonService;
import com.example.todoSpringboot.service.StudentService;
import com.example.todoSpringboot.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentLessonService studentLessonService;
    @GetMapping("/")
    public CommonResult<?> getAllStudents(){
        try{
            List<Student> res=studentService.getAllStudents();
            CommonResult< List<Student>> result = CommonResult.success(res);
            return result;
        } catch (Exception e){
            return CommonResult.error(401,"查询失败");
        }
    }

    @GetMapping("/{id}")
    public CommonResult<?> getStudentById(@PathVariable int id){
        try{
            Student res=studentService.getStudentById(id);
            CommonResult<Student> result = CommonResult.success(res);
            return result;
        } catch (Exception e){
            return CommonResult.error(401,"查询失败");
        }
    }

    @PostMapping("/")
    public CommonResult<?> addStudent(@RequestBody Student student) {
        try {
            studentService.addStudent(student);
            return CommonResult.success("添加成功");
        } catch (Exception e) {
            // 处理或记录错误
            return CommonResult.error(401,"添加失败");
        }
    }
//    public void addStudent(@RequestBody Student student) {
//        studentService.addStudent(student);
//    }

    @PutMapping("/")
    public CommonResult<?> updateStudent(@RequestBody Student student) {
        try {
            studentService.updateStudent(student);
            return CommonResult.success("更新成功");
        } catch (Exception e) {
            // 处理或记录错误
            return CommonResult.error(401,"更新失败");
        }
    }

//    public void updateStudent(@RequestBody Student student) {
//        studentService.updateStudent(student);
//    }
    @DeleteMapping("/{student_id}")
    public CommonResult<?> deleteStudent(@PathVariable int student_id) {
        try {
            accountService.deleteAccountById(student_id);
            studentLessonService.deleteStudentLessons(student_id);
            studentService.deleteStudent(student_id);
            return CommonResult.success("删除成功");
        } catch (Exception e) {
            // 处理或记录错误
            return CommonResult.error(401,"删除失败");
        }
    }
//    public void deleteStudent(@PathVariable int student_id) {
//        studentService.deleteStudent(student_id);
//        studentLessonService.deleteStudentLessons(student_id);
//    }


}
