<template>
<div>
      <div class="searchBar">
      <el-input placeholder="搜索权限" v-model="input" style="margin: 20px 30px;">
      <el-button slot="append" icon="el-icon-search"></el-button>
      </el-input>
      <el-button type="primary" style="margin: 20px 30px;">添加权限</el-button>
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
            name: '编辑最高管理员',
            description: '可以增删改查最高管理员'
      },
      {
            id: '2',
            name: '编辑管理员',
            description: '可以增删改查管理员'
      },
      {
            id: '3',
            name: '编辑用户',
            description: '可以增删改查用户'
      },
      {
            id: '4',
            name: '查看信息',
            description: '可以查看网站信息'
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