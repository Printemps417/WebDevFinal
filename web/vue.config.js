const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      ["/proxy"]: {
        target: "http://localhost:8088/",
        changeOrigin: true,
        pathRewrite:{
          '^/proxy':''
        }
      },
    }
},

})
