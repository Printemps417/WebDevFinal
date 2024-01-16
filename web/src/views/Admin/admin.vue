<template>

    <div>
    <el-container>
      <el-header style="text-align: right; font-size: 18px; background-color: #994ae4; border-radius: 3px">
        <r-header></r-header>
      </el-header>
        <div class="side-bar">
    <el-menu :default-openeds="['3','4']"  
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b">

      <el-submenu index="1">
        <template slot="title"><i class="el-icon-message"></i>学生管理</template>
          <el-menu-item index="1-2" @click="$router.push('/studentList')">学生列表</el-menu-item>
          <el-menu-item index="1-1" @click="$router.push('/addStudent')">添加学生</el-menu-item>
          <el-menu-item index="1-3" @click="$router.push('/editorStudent')">编辑学生</el-menu-item>
      </el-submenu>
      <el-submenu index="2">
        <template slot="title"><i class="el-icon-menu"></i>教师管理</template>
          <el-menu-item index="2-2" @click="$router.push('/queryTeacher')">教师列表</el-menu-item>
          <el-menu-item index="2-1" @click="$router.push('/addTeacher')">添加教师</el-menu-item>
          <el-menu-item index="2-3" @click="$router.push('/editorTeacher')">编辑教师</el-menu-item>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title"><i class="el-icon-setting"></i>课程管理</template>
          <el-menu-item index="3-2" @click="$router.push('/courseList')">课程列表</el-menu-item>
          <el-menu-item index="3-1" @click="$router.push('/addCourse')">添加课程</el-menu-item>
          <el-menu-item index="3-3" @click="$router.push('/editorCourse')">编辑课程</el-menu-item>
      </el-submenu>    

      <el-submenu index="4">
        <template slot="title"><i class="el-icon-setting"></i>选课管理</template>
          <el-menu-item index="4-1" @click="$router.push('/selectlessonlist')">选课列表</el-menu-item>
          <!-- <el-menu-item index="4-2" @click="$router.push('/')">选项2</el-menu-item> -->
          <!-- <el-menu-item index="4-3" @click="$router.push('/')">选项3</el-menu-item> -->
      </el-submenu>
  
      <el-menu-item index="/logout" @click="logout">
            <i class="el-icon-setting"></i>
            <span slot="title">退出</span>
        </el-menu-item>
      
    </el-menu>
    
            <div class="main-content">

            <el-card>
                <router-view/>
            </el-card>
                
            </div>
        </div>
    </el-container>
    </div>
    </template>
    
    <script>
    import RHeader from "@/components/r-header";
    import {selectCourseList} from '@/views/student/selectcourselist.vue'
    import { removeToken, removeRefreshToken } from '@/utils/auth'
    import { getInfo } from '@/api/login';
    // import { ElContainer } from "element-ui/types/container";
    export default {
        name: "admin",
        components: { selectCourseList,
                        'r-header': RHeader},        
        methods: {
            logout() {
            this.$confirm('确定注销并退出系统吗？', '提示').then(() => {
                removeToken()
                removeRefreshToken()
                sessionStorage.clear()
                this.$router.push({ path: '/login' }) // 重定向到登录页面
            }).catch(() => {});
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        },
        data() {
            return {
                ruleForm: {
                    tid: null,
                    cid: null,
                    cname: null,
                    tname: null,
                    tFuzzy: true,
                    cFuzzy: true
                },
                rules: {
                    tid: [
                    { type: 'number', message: '必须是数字类型' }
                    ],
                    cid: [
                    { type: 'number', message: '必须是数字类型' }
                    ],
                }
            };
        },
    };
    </script>
    
    <style lang="scss" scoped>
    .side-bar {
    display: flex;
    height: 100vh;
    }
    
    .main-content {
        flex: 1;
        padding: 20px;
        overflow: auto;
    }
    
    </style>
      