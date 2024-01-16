<template>
  <div class="login-container">
      <div class="login-box">
      <div class="login-logo">
          <a href="#">
          <img src="../assets/logo.png" alt="logo">
          </a>
      </div>
      <div class="login-form">
          <h3>用户登录</h3>
          <el-form ref="form" :model="loginForm" :rules="rules" label-position="left" label-width="0">
          <el-form-item prop="username">
              <el-input v-model="loginForm.username" placeholder="请输入用户名"></el-input>
          </el-form-item>
          <el-form-item prop="password">
              <el-input type="password" v-model="loginForm.password" placeholder="请输入密码"></el-input>
          </el-form-item>

          <el-form-item label="" prop="type">
                <el-radio-group v-model="loginForm.type">
                  <el-radio label="student" value="student">学生</el-radio>
                  <el-radio label="admin" value="admin">admin</el-radio>
                </el-radio-group>
              </el-form-item>
          <el-form-item>
              <el-button type="primary" @click="do_login" :loading="loading">登录</el-button>
          </el-form-item>              
          </el-form>
      <div>admin账号：admin，密码：123</div>
      <div>学生账号：user01，密码：123</div>          
      </div>

      </div>
  </div>
  </template>
  
  <script>
  import { login, getInfo} from '@/api/login.js'
  import { setToken, setRefreshToken } from '@/utils/auth'
  import axios from 'axios'
  export default {
  mounted(){
      localStorage.clear()
  },
  data() {
      return {
      loginForm: {
          username: '',
          password: '',
          type:''
      },
      rules: {
          username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
          password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
          type: [{ required: true, message: "请选择", trigger: "change" }],
      },
      loading: false
      }
  },
  methods: {
      do_login() {
      this.$refs.form.validate(valid => {
          if (valid) {
          this.loading = true
              // 登录处理逻辑中增加网络请求
            let data = {"username":this.loginForm.username,"password":this.loginForm.password,"type":this.loginForm.type};
            console.log(data);
            axios.post('/proxy/accounts/login', data)
            .then(res =>{
                res=res.data;
                console.log(res);
                this.loading = false
                if(res.code === 200){
                    setToken(res.data.accessToken)
                    setRefreshToken(res.data.refreshToken)
                    getInfo().then(res =>{
                    localStorage.setItem("userInfo",JSON.stringify(res.data))
                    // sessionStorage.setItem("token", "true");
                    sessionStorage.setItem("type", this.loginForm.type);
                    sessionStorage.setItem("name", this.loginForm.username);
                //   sessionStorage.setItem("sid", resp.data.sid);
                    //   this.$router.push({ path: '/student' })
                    console.log(this.loginForm);
                    })
                if(this.loginForm.type === 'student'){
                    this.$router.push({ path: '/student/studenthome' })                    
                }                  
                if(this.loginForm.type === 'admin'){
                    this.$router.push({ path: '/admin' })                    
                }
                }   
                else
                    this.$message.error(res.msg)
                console.log("登录成功！");
              }).catch(() => {
                // 登录失败，显示错误提示
                window.alert('登陆失败')
                console.log("登录失败！");
                this.loading = false
              })
          }
      })
      }
  }
  }
  </script>
  
  <style lang="scss">
  .login-container {
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  
  .login-box {
      width: 400px;
      height: 500px;
      border-radius: 5px;
      box-shadow: 0px 0px 10px #ccc;
  
      .login-logo {
      height: 100px;
      display: flex;
      justify-content: center;
      align-items: center;
  
      img {
          height: 80%;
      }
      }
  
      .login-form {
      padding: 20px;
  
      h3 {
          font-size: 24px;
          margin-bottom: 20px;
          text-align: center;
      }
      }
  }
  }
  </style>
    