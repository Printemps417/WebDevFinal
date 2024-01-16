<template>
  <div>
    <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="课程id" prop="lessonId">
        <el-input v-model="ruleForm.lessonId" :value="ruleForm.lessonId"></el-input>
      </el-form-item>
      <el-form-item label="课程姓名" prop="lessonName">
        <el-input v-model="ruleForm.lessonName" :value="ruleForm.lessonName"></el-input>
      </el-form-item>
      <el-form-item label="课程性别" prop="teacherId">
        <el-input v-model="ruleForm.teacherId" :value="ruleForm.teacherId"></el-input>
      </el-form-item>
      <el-form-item label="课程专业id" prop="majorId">
        <el-input v-model="ruleForm.majorId" :value="ruleForm.majorId"></el-input>
      </el-form-item>  
      <el-form-item label="课程专业id" prop="lessonLocation">
        <el-input v-model="ruleForm.lessonLocation" :value="ruleForm.lessonLocation"></el-input>
      </el-form-item>  
      <el-form-item label="课程专业id" prop="lessonTime">
        <el-input v-model="ruleForm.lessonTime" :value="ruleForm.lessonTime"></el-input>
      </el-form-item>  
      <el-form-item label="课程专业id" prop="lessonMaxNum">
        <el-input v-model="ruleForm.lessonMaxNum" :value="ruleForm.lessonMaxNum"></el-input>
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
import { addlesson } from "@/api/lesson";
export default {
  data() {
    return {
      ruleForm: {
        lessonId: null,
        lessonName: null,
        teacherId:null ,
        majorId: null,
        lessonLocation: null,
        lessonTime: null,
        lessonChoosenNum: 0,
        lessonMaxNum: null
      },
      rules: {
        lessonId:[
          {required: true, message: '请输入课程id', trigger: 'blur'},
          // { type: 'number', message: 'id必须为数字值'}
        ],

        lessonName: [
          { required: true, message: '请输入课程名称', trigger: 'blur' },
          { min: 2, max: 10, message: '长度在 2 到 5 个字符', trigger: 'blur' }
        ],
        teacherId:[
          {required: true, message: '请输入教师id', trigger: 'blur'},
          // { type: 'number', message: 'id必须为数字值'}
        ],
        majorId:[
          {required: true, message: '请输入专业id', trigger: 'blur'},
          // { type: 'number', message: 'id必须为数字值'}
        ],
        lessonLocation: [
          { required: true, message: '请输入教室地点', trigger: 'blur' }
        ],
        lessonTime: [
          { required: true, message: '请输入上课时间', trigger: 'blur' },
          // { type: 'number', message: 'id必须为数字值'}
        ], 
        lessonMaxNum: [
          { required: true, message: '请输入最大人数', trigger: 'blur' },
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
          addlesson(this.ruleForm).then(function (resp) {
            if (resp.data === "添加成功！") {
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