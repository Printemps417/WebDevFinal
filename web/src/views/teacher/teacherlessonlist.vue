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
            prop="lessonChossenNum"
            label="已选人数"
            width="100%">
        </el-table-column>
        <el-table-column
            prop="lessonMaxNum"
            label="最大人数"
            width="100%">
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
  import {selectlesson,getselectlist} from '@/api/lesson.js'
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
        tableData: {
        "lessonName": "心里艺术",
        "majorId": "1",
        "lessonLocation": "教室1202",
        "lessonTime": "晚上1-2阶",
        "lessonChoosenNum": "0",
        "lessonMaxNum": "20"
        },
        pageSize: 4,
        total: null,
        tmpList: null,
        // type: sessionStorage.getItem('type')
      }
    },
    // props: {
    //   ruleForm: Object
    // },
 
  }
  </script>