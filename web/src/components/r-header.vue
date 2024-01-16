<template>
  <div>
      <el-button type="text" style="color: #333333; font-size: 18px" @click="logout()">
        logout
      </el-button>
      <el-divider direction="vertical" style="color: #333333"></el-divider>
      <i class="el-icon-paperclip" style="margin-right: 18px"></i>
      <span>2023年秋</span>
      <el-divider direction="vertical"></el-divider>
      <i class="el-icon-user" style="margin-right: 18px"></i>
      <span>{{ name }}</span>
  </div>
</template>

<script>
    import { removeToken, removeRefreshToken } from '@/utils/auth'
export default {
  name: "r-header",
  data() {
    return {
      name: null,
      // currentTerm: null
    }
  },
  created() {
    this.name = sessionStorage.getItem("name")
    // this.currentTerm = sessionStorage.getItem("currentTerm")
  },
  methods: {
    logout() {
            this.$confirm('确定注销并退出系统吗？', '提示').then(() => {
                removeToken()
                removeRefreshToken()
                sessionStorage.clear()
                localStorage.clear()
                this.$router.push({ path: '/login' }) // 重定向到登录页面
            }).catch(() => {});
            },
  }
}
</script>

<style scoped>

</style>