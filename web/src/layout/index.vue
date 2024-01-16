<template>
    <div>
        <div class="side-bar">
            <el-menu class="el-menu-vertical-demo" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
                <el-menu-item index="/" @click="$router.push('/')">
                    <i class="el-icon-house"></i>
                    <span slot="title">首页</span>
                </el-menu-item>
                <el-menu-item index="/rbac/user/list" v-if = "viewUserPermission" @click="$router.push('/rbac/user/list')" >
                    <i class="el-icon-menu"></i>
                    <span slot="title">用户列表</span>
                </el-menu-item>
                <el-menu-item index="/rbac/user/add" v-if = "editUserPermission" @click="$router.push('/rbac/user/add')">
                    <i class="el-icon-setting"></i>
                    <span slot="title">添加用户</span>
                </el-menu-item>
                <el-menu-item index="/rbac/role/list" v-if = "viewRolePermission" @click="$router.push('/rbac/role/list')">
                    <i class="el-icon-menu"></i>
                    <span slot="title">角色列表</span>
                </el-menu-item>
                <el-menu-item index="/rbac/role/add" v-if = "editRolePermission" @click="$router.push('/rbac/role/add')">
                    <i class="el-icon-setting"></i>
                    <span slot="title">添加角色</span>
                </el-menu-item>
                <el-menu-item index="/rbac/permission/list" v-if = "viewPermissonPermission" @click="$router.push('/rbac/permission/list')">
                    <i class="el-icon-menu"></i>
                    <span slot="title">权限列表</span>
                </el-menu-item>
                <el-menu-item index="/logout" @click="logout">
                    <i class="el-icon-setting"></i>
                    <span slot="title">退出</span>
                </el-menu-item>
    
            </el-menu>
    
            <div class="main-content">
                <router-view/>
            </div>
        </div>
    </div>
    </template>
    
    <script>
    import { removeToken, removeRefreshToken } from '@/utils/auth'
    import { getUserPermission } from '@/api/PermissionAdmin';
    import { getInfo } from '@/api/login';
    export default {
        mounted(){
            var permission = getUserPermission()
            if(permission.includes("查看用户"))this.viewUserPermission = true
            if(permission.includes("查看角色"))this.viewRolePermission = true
            if(permission.includes("查看权限"))this.viewPermissonPermission = true
        },
        data(){
            return{
                viewUserPermission : false,
                viewRolePermission : false,
                viewPermissonPermission : false,
            }
        },
        methods: {
            logout() {
            this.$confirm('确定注销并退出系统吗？', '提示').then(() => {
                removeToken()
                removeRefreshToken()
                this.$router.push({ path: '/login' }) // 重定向到登录页面
            }).catch(() => {});
            },
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
      