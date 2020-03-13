module.exports = {
  // 设置前端开发时的代理
  devServer: {
    proxy: 'http://127.0.0.1:8081',    // 替换成你的接口域名地址, 后面不要加 '/'
  },
  productionSourceMap: false,
  // 设置生产环境和发布环境时的静态资源路径
  publicPath: process.env.NODE_ENV === 'production'
    ? '/resource/short-video/'
    : '/',
}

