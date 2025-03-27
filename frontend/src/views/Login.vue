<template>
  <div class="container">
    <div style="width: 0%"></div>
    <div style="width: 400px; padding: 30px; background-color: white; border-radius: 5px;">
      <div style="text-align: center; font-size: 20px; margin-bottom: 20px; color: #9a6201">欢迎登录家校信息互通服务平台</div>
      <el-form :model="form" :rules="rules" ref="formRef">
        <el-form-item prop="username">
          <el-input prefix-icon="el-icon-user" placeholder="请输入账号" v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input prefix-icon="el-icon-lock" placeholder="请输入密码" show-password v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item prop="role">
          <el-select v-model="form.userType" placeholder="请选择角色" style="width: 100%">
            <el-option label="家长" value="Parent"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button
            style="border-radius: 15px; width: 45%; background-image: radial-gradient(circle,rgba(255,255,255,0) 0%,rgba(200, 226, 160,1) 80%); color: #9a6201;"
            @click="login">登 录</el-button>
          <el-button
            style="border-radius: 15px; width: 45%; background-image: radial-gradient(circle,rgba(255,255,255,0) 0%,rgba(255, 165, 0,0.5) 80%); color: #9a6201;"
            @click="registerP">注册家长</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { findOne } from '@/api/example';

export default {
  name: "Login",
  data() {
    return {
      form: {
        userType: '家长'
      },
      rules: {
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ]
      },
    }
  },
  created() {

  },
  methods: {
    registerS() {
      this.$router.push('/registerS');
    },
    registerP() {
      this.$router.push('/registerP')
    },
    login() {
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          this.$request.post('/user/login', this.form).then(res => {
            if (res.code === 20000) {
              localStorage.setItem("xm-parent", JSON.stringify(res.data))
              findOne(res.data.sid,'student').then(res=>{
                localStorage.setItem("xm-student", JSON.stringify(res.data))
              })
              this.$router.push('/parent/home')
              this.$message.success('登录成功')
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
  background-image: url("@/assets/imgs/bg.jpg");
  background-size: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #666;
}

a {
  color: #2a60c9;
}
</style>