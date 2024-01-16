<template>
  <div>
    <el-table
        :data="tableData"
        border
        show-header
        stripe
        style="width: 100%">
      <el-table-column
          fixed
          prop="lessonId"
          label="课id"
          width="100%">
      </el-table-column>
      <el-table-column
          prop="lessonName"
          label="课程名称"
          width="100%">
      </el-table-column>
      <el-table-column
          prop="teacherId"
          label="教师号"
          width="100%">
      </el-table-column>
      <el-table-column
          prop="majorId"
          label="专业"
          width="100%">
      </el-table-column>
      <el-table-column
          prop="lessonLocation"
          label="教室"
          width="100%">
      </el-table-column>
      <el-table-column
          prop="lessonTime"
          label="时间"
          width="100%">
      </el-table-column>
      <el-table-column
          prop="lessonChoosenNum"
          label="已选人数"
          width="100%">
      </el-table-column>
      <el-table-column
          prop="lessonMaxNum"
          label="最大人数"
          width="100%">
      </el-table-column>
      <el-table-column
          label="操作"
          width="100">
        <template slot-scope="scope">
          <el-popconfirm
              confirm-button-text='删除'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="删除不可复原"
              @confirm="deletecourse(scope.row)"
          >
            <el-button slot="reference" type="text" size="small">删除</el-button>
          </el-popconfirm>
          <el-button @click="editor(scope.row)" type="text" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        background
        layout="prev, pager, next"
        :total="total"
        :page-size="pageSize"
        @current-change="changePage"
    >
    </el-pagination>
  </div>
</template>

<script>
// import {getselectlist} from '@/api/getlist.js'
import {selectlesson,getselectlist,deletelesson} from '@/api/lesson.js'
export default {
  mounted(){
      getselectlist().then((res)=>{
          this.tmpList = res.data
          this.total = res.data.length
          let start = 0, end = this.pageSize
          let length = this.tmpList.length
          let ans = (end < length) ? end : length
          this.tableData = this.tmpList.slice(start, ans)
      }).catch(error=>{
          console.log(error)
      })
  },
  methods: {
    deletecourse(row) {
      const that = this
      deletelesson(row.lessonId).then(function (resp) {
        if (resp.data === "删除成功!") {
          const index = that.tableData.findIndex(item => item.lessonId === row.lessonId);
            if (index !== -1) {
              that.tableData.splice(index, 1);
            }

          that.$message({
            showClose: true,
            message: '删除成功',
            type: 'success'
          });
          // console.log(that.tmpList === null)
          // if (that.tmpList === null) {
          //   window.location.reload()
          // }
          // else {
          //   that.$router.push('/querycourse')
          // }
        }
        else {
          that.$message({
            showClose: true,
            message: '删除出错，请查询数据库连接',
            type: 'error'
          });
        }
      }).catch(function (e) {
        that.$message({
          showClose: true,
          message: '删除出错，存在外键依赖',
          type: 'error'
        });
      })
    },
    editor(row) {
      this.$router.push({
        path: '/editorcourse',
        query: {
          courseId: row.courseId
        }
      })
    },

    changePage(page) {
      page = page - 1
      const that = this
      let start = page * that.pageSize, end = that.pageSize * (page + 1)
      let length = that.tmpList.length
      let ans = (end < length) ? end : length
      that.tableData = that.tmpList.slice(start, ans)
    },
  },

  data() {
    return {
      tableData: null,
      pageSize: 4,
      total: null,
      tmpList: null,
      // type: sessionStorage.getItem('type')
    }
  },
  // props: {
  //   ruleForm: Object
  // },

  // watch: {
  //     ruleForm: {
  //     handler(newRuleForm, oldRuleForm) {
  //         const that = this
  //         that.tmpList = null
  //         that.total = null
  //         that.tableData = null
  //         axios.post("http://localhost:10086/courseTeacher/findCourseTeacherInfo", newRuleForm).then(function (resp) {
  //         that.tmpList = resp.data
  //         that.total = resp.data.length
  //         let start = 0, end = that.pageSize
  //         let length = that.tmpList.length
  //         let ans = (end < length) ? end : length
  //         that.tableData = that.tmpList.slice(start, ans)
  //         })
          
  //     },
  //     deep: true,
  //     immediate: true
  //     }
  // },    
}
</script>