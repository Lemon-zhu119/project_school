<template>
  <div class="container">
    <div style="background-color: white; height: 70px;">
      <div style="margin: 0 auto; width: 50%; height: 100%; display: flex; align-items: center">
        <img src="@/assets/imgs/logo.png" alt="" style="width: 50px">
        <span style="font-weight: bold; font-size: 24px; margin-left: 5px">食味美食</span>
      </div>
    </div>

    <div style="margin: 0 auto; width: 50%; height: calc(100vh - 140px); display: flex; grid-gap: 20px; align-items: center">
      <div style="flex: 1">
        <img style="width: 100%;" src="@/assets/imgs/login.png" alt="">
      </div>

      <div style="flex: 1">
        <div style="width: 350px; padding: 50px 30px; background-color: white; border-radius: 5px;">
          <div style="text-align: center; font-size: 20px; margin-bottom: 30px; color: #333">欢 迎 注 册</div>
          <el-form :model="form" :rules="rules" ref="formRef">
            <el-form-item prop="username">
              <el-input size="medium" prefix-icon="el-icon-user" placeholder="请输入账号" v-model="form.username"></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input size="medium" autocomplete="new-password" prefix-icon="el-icon-lock" placeholder="请输入密码" show-password  v-model="form.password"></el-input>
            </el-form-item>
            <el-form-item prop="confirmPassword">
              <el-input size="medium" autocomplete="new-password" prefix-icon="el-icon-lock" placeholder="请确认密码" show-password  v-model="form.confirmPassword"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button size="medium" style="width: 100%; background-color: #fecd4a; border-color: #fecd4a; color: #333" @click="login">注 册</el-button>
            </el-form-item>
            <div style="display: flex; align-items: center">
              <div style="flex: 1"></div>
              <div style="flex: 1; text-align: right">
                已有账号？请 <a href="/login">登录</a>
              </div>
            </div>
          </el-form>
        </div>
      </div>
    </div>

    <div style="height: 50px; line-height: 70px; color: #666; text-align: center">
      2009-2024 家校通有限公司 证书备31888号 备1101312328888号 网文[2014]0774-174号
    </div>

  </div>
</template>

<script>
export default {
  name: "Register",
  data() {
    // 验证码校验
    const validatePassword = (rule, confirmPass, callback) => {
      if (confirmPass === '') {
        callback(new Error('请确认密码'))
      } else if (confirmPass !== this.form.password) {
        callback(new Error('两次输入的密码不一致'))
      } else {
        callback()
      }
    }
    return {
      form: { role: 'USER' },
      rules: {
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ],
        confirmPassword: [
          { validator: validatePassword, trigger: 'blur' },
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
          this.$request.post('/register', this.form).then(res => {
            if (res.code === '200') {
              this.$router.push('/login')  // 跳转主页
              this.$message.success('注册成功')
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