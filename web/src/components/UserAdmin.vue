<template>
  <div>
    <div class="searchBar">
      <el-input placeholder="搜索用户" v-model="input" style="margin: 20px 30px;">
        <el-button slot="append" icon="el-icon-search"></el-button>
      </el-input>
      <el-button type="primary" style="margin: 20px 30px;">添加用户</el-button>
    </div>
    <el-table
    :data="tableData"
    stripe
    class="table">
    <el-table-column
      prop="id"
      label="ID"
      width="180">
    </el-table-column>
    <el-table-column
      prop="name"
      label="姓名"
      width="180">
    </el-table-column>
    <el-table-column
      prop="status"
      label="状态"
      width="180">
    </el-table-column>
    <el-table-column
      prop="email"
      label="邮箱">
    </el-table-column>
    <el-table-column
      label="操作">
      <el-button icon="el-icon-search" circle></el-button>
      <el-button type="primary" icon="el-icon-edit" circle></el-button>
      <el-button type="danger" icon="el-icon-delete" circle></el-button>
    </el-table-column>
  </el-table>
  </div>
</template>

<script>
export default {
  data(){
    return{
      input: '',
      tableData: [
        {
          id: '1',
          name: '王小虎',
          status: '正常',
          email: '12345@pku.edu.cn'
        },
        {
          id: '2',
          name: 'lili',
          status: '正常',
          email: '1w2345@pku.edu.cn'
        },
        {
          id: '3',
          name: '哒哒哒',
          status: '正常',
          email: 'wwwww@pku.edu.cn'
        },
        {
          id: '4',
          name: '王微微',
          status: '正常',
          email: 'wddd@pku.edu.cn'
        },
        {
          id: '5',
          name: '王微',
          status: '正常',
          email: 'wddd@pku.edu.cn'
        },
        {
          id: '6',
          name: '微微',
          status: '正常',
          email: 'wddd@pku.edu.cn'
        },
        {
          id: '7',
          name: '李莉莉',
          status: '正常',
          email: 'wddd@pku.edu.cn'
        },
        {
          id: '8',
          name: '呜呜呜',
          status: '正常',
          email: 'wddd@pku.edu.cn'
        },
      ]
    }
  }
}
</script>

<style>
.searchBar{
  display: flex;
  justify-content: space-between; 
}
.table{
  overflow: auto;
  height: 60vh;
}
</style>