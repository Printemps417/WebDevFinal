<template>
  <div>
    <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <!-- <el-form-item label="学生id" prop="studentId">
        <el-input v-model="ruleForm.studentId" :value="ruleForm.studentId"></el-input>
      </el-form-item> -->
      <el-form-item label="学生姓名" prop="studentName">
        <el-input v-model="ruleForm.studentName" :value="ruleForm.studentName"></el-input>
      </el-form-item>
      <el-form-item label="学生性别" prop="studentSex">
        <el-input v-model="ruleForm.studentSex" :value="ruleForm.studentSex"></el-input>
      </el-form-item>
      <el-form-item label="学生专业id" prop="majorId">
        <el-input v-model="ruleForm.majorId" :value="ruleForm.majorId"></el-input>
      </el-form-item>  
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
        <el-button @click="test">test</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import { addstudent } from "@/api/student";
export default {
  data() {
    return {
      ruleForm: {
        // studentId: null,
        studentName: null,
        studentSex: null,
        majorId:null
      },
      rules: {
        // studentId:[
        //   {required: true, message: '请输入学生id', trigger: 'blur'},
        //   // { type: 'number', message: 'id必须为数字值'}
        // ],

        studentName: [
          { required: true, message: '请输入名称', trigger: 'blur' },
          { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
        ],
        studentSex: [
          { required: true, message: '请输入性别', trigger: 'blur' }
        ],
        majorId: [
          { required: true, message: '请输入专业id', trigger: 'blur' },
          // { type: 'number', message: 'id必须为数字值'}
        ], 
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 通过前端校验
          const that = this
          console.log(this.ruleForm)
          addstudent(this.ruleForm).then(function (resp) {
            if (resp.data === "添加成功") {
              that.$message({
                showClose: true,
                message: '插入成功',
                type: 'success'
              });
            }
            else {
              that.$message.error('插入失败，请检查数据库');
            }
            that.$router.push("/studentList")
          })
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    test() {
      console.log(this.ruleForm)
    }
  }
}
</script>