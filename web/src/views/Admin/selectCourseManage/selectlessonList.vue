<template>
  <div>
    <el-card>
      <el-table
          :data="tableData"
          border
          style="width: 100%">
          <el-table-column
            fixed
            prop="lessonId"
            label="课号"
            width="150">
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
        <!-- <el-table-column
            label="操作"
            width="100">

          <template slot-scope="scope">
            <el-popconfirm
                confirm-button-text='退课'
                cancel-button-text='取消'
                icon="el-icon-info"
                title="确定退课？"
                @confirm="deleteSCT(scope.row)"
            >
              <el-button slot="reference" type="text" size="small">退课</el-button>
            </el-popconfirm>
          </template>
        </el-table-column> -->
      </el-table>
      <el-pagination
          background
          layout="prev, pager, next"
          :total="total"
          :page-size="pageSize"
          @current-change="changePage"
      >
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
import { getStudentLessonById,deleteStudentLesson,getAllStudentlessons } from "@/api/stules";
export default {
  methods: {
    deleteSCT(row) {
      const that = this;
      deleteStudentLesson(row.studentId,row.lessonId).then(function (resp) {
        if (resp.data === "删除成功") {
          const index = that.tableData.findIndex(item => item.lessonId === row.lessonId);
          if (index !== -1) {
            that.tableData.splice(index, 1);
          }
        }
      });
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
      pageSize: 10,
      total: null,
      tmpList: null,
      type: sessionStorage.getItem('type')
    }
  },
  created() {
    const that = this
    console.log('ffffffff')
    getAllStudentlessons().then(function (resp) {
      console.log(resp)
      that.tmpList = resp.data
      that.total = resp.data.length
      let start = 0, end = that.pageSize
      let length = that.tmpList.length
      let ans = (end < length) ? end : length
      that.tableData = that.tmpList.slice(start, end)
    })
  },
}
</script>