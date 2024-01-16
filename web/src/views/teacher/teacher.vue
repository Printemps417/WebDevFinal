<template>
    <div>
        <div class="side-bar">
            <el-menu class="el-menu-vertical-demo" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
                <el-menu-item index="/" >
                    <i class="el-icon-house"></i>
                    <span slot="title">教师信息</span>
                </el-menu-item>
                <el-menu-item index="/rbac/user/list"  @click="$router.push('/teacherlessonlist')" >
                    <i class="el-icon-menu"></i>
                    <span slot="title">我开设的课程</span>
                </el-menu-item>
                <el-menu-item index="/rbac/user/add"  @click="$router.push('/querylist')">
                    <i class="el-icon-setting"></i>
                    <span slot="title">选课学生列表</span>
                </el-menu-item>
                <el-menu-item index="/logout" @click="logout">
                    <i class="el-icon-setting"></i>
                    <span slot="title">退出</span>
                </el-menu-item>
            </el-menu>
    
            <div class="main-content">
                <el-container>
                    <el-main>
                        <el-card>
                        <el-form :inline="true" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px" class="demo-ruleForm">
                            <el-form-item label="工号" prop="tid">
                            <el-input v-model.number="ruleForm.tid"></el-input>
                            </el-form-item>
                            <el-form-item label="教师名" prop="tname">
                            <el-input v-model.number="ruleForm.tname"></el-input>
                            </el-form-item>
                            <el-form-item label="教师模糊查询">
                            <el-switch v-model="ruleForm.tFuzzy"></el-switch>
                            </el-form-item>
                            <el-form-item label="课程号" prop="cid">
                            <el-input v-model.number="ruleForm.cid"></el-input>
                            </el-form-item>
                            <el-form-item label="课程名" prop="cname">
                            <el-input v-model.number="ruleForm.cname"></el-input>
                            </el-form-item>
                            <el-form-item label="课程模糊查询">
                            <el-switch v-model="ruleForm.cFuzzy"></el-switch>
                            </el-form-item>
                            <el-form-item>
                            <el-button type="primary" @click="resetForm('ruleForm')">重置</el-button>
                            </el-form-item>
                        </el-form>
                        </el-card>
                        <!-- <el-card style="margin-top: 10px">
                        <select-course-list :rule-form="ruleForm"></select-course-list>
                        </el-card> -->
                    </el-main>
                </el-container>
            <el-card>
                <router-view/>
            </el-card>
                
            </div>
        </div>
    </div>
    </template>
    
    <script>
    import {selectCourseList} from '@/views/student/selectcourselist.vue'
    import { removeToken, removeRefreshToken } from '@/utils/auth'
    import { getUserPermission } from '@/api/PermissionAdmin';
    import { getInfo } from '@/api/login';
    export default {
        components: {selectCourseList},        
        methods: {
            logout() {
            this.$confirm('确定注销并退出系统吗？', '提示').then(() => {
                removeToken()
                removeRefreshToken()
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
      