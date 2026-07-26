
const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true,
    publicPath: "/",
    devServer: {
        port: 9092
    },
    chainWebpack: config => {
        config.plugin('html')
            .tap(args => {
                args[0].title = "家校通";
                return args;
            })
    }
})