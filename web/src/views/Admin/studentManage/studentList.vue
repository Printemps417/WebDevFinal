<template>
  <div>
    <el-table
        :data="tableData"
        border
        style="width: 100%">
      <el-table-column
          fixed
          prop="studentId"
          label="学生id"
          width="150">
      </el-table-column>
      <el-table-column
          prop="studentName"
          label="姓名"
          width="120">
      </el-table-column>
      <el-table-column
          prop="studentSex"
          label="性别"
          width="120">
      </el-table-column>
      <el-table-column
          prop="majorId"
          label="专业"
          width="120">
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
              @confirm="deleteStudent(scope.row)"
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
import { getstudentlist,deletestudent } from "@/api/student";
export default {
  methods: {
    deleteStudent(row) {
      const that = this
      deletestudent(row.studentId).then(function (resp) {

        if (resp.data === "删除成功") {
          const index = that.tableData.findIndex(item => item.studentId === row.studentId);
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
          //   that.$router.push('/queryStudent')
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
    // changePage(page) {
    //   page = page - 1
    //   if (this.tmpList === null) {
    //     const that = this
    //     axios.get('http://localhost:10086/student/findByPage/' + page + '/' + that.pageSize).then(function (resp) {
    //       that.tableData = resp.data
    //     })
    //   }
    //   else {
    //     let that = this
    //     let start = page * that.pageSize, end = that.pageSize * (page + 1)
    //     let length = that.tmpList.length
    //     let ans = end < length ? end : length
    //     that.tableData = that.tmpList.slice(start, ans)
    //   }
    // },
    editor(row) {
      this.$router.push({
        path: '/editorStudent',
        query: {
          studentId: row.studentId
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
      pageSize: 7,
      total: null,
      ruleForm: null,
      tmpList: null
    }
  },

  // created() {
  //   if (this.tmpList !== null)
  //     this.tmpList = null
  //   const that = this
  //   // 是否从查询页跳转
  //   this.ruleForm = this.$route.query.ruleForm
  //   if (this.$route.query.ruleForm === undefined || (this.ruleForm.sid === null && this.ruleForm.sname === null)) {
  //     axios.get('http://localhost:10086/student/getLength').then(function (resp) {
  //       console.log("获取列表总长度: " + resp.data)
  //       that.total = resp.data
  //     })

  //     axios.get('http://localhost:10086/student/findByPage/0/' + that.pageSize).then(function (resp) {
  //       that.tableData = resp.data
  //     })
  //   }
  //   else {
  //     // 从查询页跳转并且含查询
  //     console.log('正在查询跳转数据')
  //     console.log(this.ruleForm)
  //     axios.post('http://localhost:10086/student/findBySearch', this.ruleForm).then(function (resp) {
  //       console.log('获取查询数据：')
  //       that.tmpList = resp.data
  //       that.total = resp.data.length
  //       console.log(that.tmpList)
  //       let start = 0, end = that.pageSize
  //       let length = that.tmpList.length
  //       let ans = end < length ? end : length
  //       that.tableData = that.tmpList.slice(start, ans)
  //     })
  //   }
  // }
  mounted(){
        getstudentlist().then((res)=>{
          
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
}
</script>