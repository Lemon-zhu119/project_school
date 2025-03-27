<template>
  <div class="container">
    <div style="background-color: white; height: 70px;">
      <div style="margin: 0 auto; width: 50%; height: 100%; display: flex; align-items: center">
        <img src="@/assets/imgs/logo.png" alt="" style="width: 50px">
        <span style="font-weight: bold; font-size: 24px; margin-left: 5px">家校通</span>
      </div>
    </div>

    <div style="margin: 0 auto; width: 50%; height: calc(100vh - 140px); display: flex; grid-gap: 20px; align-items: center">
      <div style="flex: 1">
        <img style="width: 100%;" src="@/assets/imgs/login.jpg" alt="">
      </div>

      <div style="flex: 1">
        <div style="width: 350px; padding: 50px 30px; background-color: white; border-radius: 5px;">
          <div style="text-align: center; font-size: 20px; margin-bottom: 30px; color: #333">欢 迎 登 录 后 台</div>
          <el-form :model="form" :rules="rules" ref="formRef">
            <el-form-item prop="username">
              <el-input size="medium" prefix-icon="el-icon-user" placeholder="请输入账号" v-model="form.username"></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input size="medium" autocomplete="new-password" prefix-icon="el-icon-lock" placeholder="请输入密码" show-password  v-model="form.password"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button size="medium" style="width: 100%; background-color: #fecd4a; border-color: #fecd4a; color: #333" @click="login">登 录</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      form: {},
      rules: {
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ]
      }
    }
  },
  created() {

  },
  methods: {
    login() {
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          // 验证通过
          this.$request.post('/admin/login', this.form).then(res => {
            if (res.code === 20000) {
              localStorage.setItem("xm-user", JSON.stringify(res.data)) 
              this.$message.success('登录成功')
                  location.href = '/home'    // 跳转主页   // 跳转主页
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.container {
  height: 100vh;
  overflow: hidden;
  background-color: #f6f6f6;
}
a {
  color: #2a60c9;
}
</style>