<template>
  <div>
    <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <!-- <el-form-item label="教师id" prop="teacherId">
        <el-input v-model="ruleForm.teacherId" :value="ruleForm.teacherId"></el-input>
      </el-form-item> -->
      <el-form-item label="教师姓名" prop="teacherName">
        <el-input v-model="ruleForm.teacherName" :value="ruleForm.teacherName"></el-input>
      </el-form-item>
      <el-form-item label="教师性别" prop="teacherSex">
        <el-input v-model="ruleForm.teacherSex" :value="ruleForm.teacherSex"></el-input>
      </el-form-item>
      <el-form-item label="教师专业id" prop="majorId">
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
import { addteacher } from "@/api/teacher";
export default {
  data() {
    return {
      ruleForm: {
        // teacherId: null,
        teacherName: null,
        teacherSex: null,
        majorId:null
      },
      rules: {
        // teacherId:[
        //   {required: true, message: '请输入教师id', trigger: 'blur'},
        //   // { type: 'number', message: 'id必须为数字值'}
        // ],

        teacherName: [
          { required: true, message: '请输入名称', trigger: 'blur' },
          { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
        ],
        teacherSex: [
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
          addteacher(this.ruleForm).then(function (resp) {
            if (resp.data === "新增教师成功") {
              that.$message({
                showClose: true,
                message: '插入成功',
                type: 'success'
              });
            }
            else {
              that.$message.error('插入失败，请检查数据库');
            }
            that.$router.push("/queryTeacher")
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