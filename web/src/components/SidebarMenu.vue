<template>
<div class = "sidediv">
    <el-menu
    default-active="1"
    class="el-menu-vertical-demo">
    <el-menu-item index="1" @click="$router.push('/userAdmin')">
        <i class="el-icon-user-solid"></i>
        <span slot="title">用户管理</span>
    </el-menu-item>
    <el-menu-item index="2" @click="$router.push('/roleAdmin')">
        <i class="el-icon-s-custom"></i>
        <span slot="title">角色管理</span>
    </el-menu-item>
    <el-menu-item index="3" @click="$router.push('/permissionAdmin')">
        <i class="el-icon-menu"></i>
        <span slot="title">权限管理</span>
    </el-menu-item>
    </el-menu>
</div>
</template>

<script>
export default {
name: 'SidebarMenu'
}
</script>

<style>
.el-menu{
    border: 0 !important;
}
.sidediv{
    position: relative;
    height: 100%;
    border-right: solid 1px #e6e6e6;
}
</style>
  