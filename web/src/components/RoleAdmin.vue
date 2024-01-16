<template>
<div>
      <div class="searchBar">
      <el-input placeholder="搜索角色" v-model="input" style="margin: 20px 30px;">
      <el-button slot="append" icon="el-icon-search"></el-button>
      </el-input>
      <el-button type="primary" style="margin: 20px 30px;">添加角色</el-button>
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
      label="名称"
      width="180">
      </el-table-column>
      <el-table-column
      prop="description"
      label="描述"
      width="380">
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
            name: '最高管理员',
            description: '管理所有人，是项目所有者'
      },
      {
            id: '2',
            name: '管理员',
            description: '管理用户'
      },
      {
            id: '3',
            name: '高级用户',
            description: '该类用户价值很高'
      },
      {
            id: '4',
            name: '用户',
            description: '一般的用户'
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