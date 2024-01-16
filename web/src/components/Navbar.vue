<template>
<div class = "navbarDiv" >
    <div class="logo" @click="$router.push('/');">RBAC后台管理系统</div>
    <div>
        <div class="navFuc">登录</div>
        <div class="navFuc">用户中心</div>
    </div>
</div>
</template>

<script>
export default {
name: 'Navbar',
methods:{

}
}
</script>

<style lang="scss">
.navbarDiv{
    position: relative;
    width: 100%;
    height: 100%;
    background-color:cornflowerblue;
    display: flex;
    align-items: center;
    justify-content: space-between;
    div{
        color: white;
        padding: 0 20px;
        display: flex;
        align-items: center;
    }
    .logo{
        font-weight: bold;
        font-size: 20px;
        cursor: pointer;
    }
}
</style>
  