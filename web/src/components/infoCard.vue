<template>
  <el-card>
    <el-descriptions title="用户信息" border>
      <el-descriptions-item label="学生id">{{ data.studentId }}</el-descriptions-item>
      <el-descriptions-item label="学生名">{{ data.studentName }}</el-descriptions-item>
      <el-descriptions-item label="学生性别">{{ data.studentSex }}</el-descriptions-item>
      <el-descriptions-item label="专业">{{ data.majorId }}</el-descriptions-item>
      <el-descriptions-item label="用户类型">
        <el-tag size="small">{{ type }}</el-tag>
      </el-descriptions-item>
    </el-descriptions>
  </el-card>
</template>

<script>
import axios from "axios";
import { getInfo } from "@/api/login";
import {refreshToken,ReloadToken} from "../utils/auth"
export default {
  name: "infoCard",

  data() {
    return {
      data:null,
      tpye:'student'
    };
  },
  mounted(){
    getInfo().then((res) => {
      this.data = res.data;
      console.log("测试刷新中")
    })
    .catch((e)=>{
      console.log(e)
      console.log("读取失败")
    })
;
    this.type=sessionStorage.getItem("type")
    // console.log(localStorage.getItem("userInfo"))
    // const userInfo=JSON.parse(localStorage.getItem("userInfo"))
    // console.log(userInfo)
    // this.data=userInfo
    // console.log(this.data)

  }
};
</script>

<style scoped></style>
