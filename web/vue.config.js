const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      ["/proxy"]: {
        target: "http://localhost:8080/",
        changeOrigin: true,
        pathRewrite: {
          '^/proxy': ''
        }
      },
    }
  },

})
