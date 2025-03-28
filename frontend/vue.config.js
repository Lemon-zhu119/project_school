const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true,
    devServer: {
        port: 9091
    },
    chainWebpack: config => {
        config.plugin('html')
            .tap(args => {
                args[0].title = "家校通";
                return args;
            })
    }
})