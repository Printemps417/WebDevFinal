<template>
    <div>
      <el-container>
        <el-main>
          <h1>student home</h1>
          <info-card></info-card>
          <!-- <log-out></log-out> -->
        </el-main>
      </el-container>
    </div>
  </template>
  
  <script>
//   import Logout from "@/components/logout";
  import InfoCard from "@/components/infoCard";
  export default {
    name: "home",
    components: { InfoCard },
  };
  </script>
  
  <style scoped></style>
  