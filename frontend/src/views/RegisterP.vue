<template>
  <div class="container">
    <div style="width: 400px; padding: 30px; background-color: white; border-radius: 5px;">
      <div style="text-align: center; font-size: 20px; margin-bottom: 20px; color: #333">家校信息互通服务平台注册</div>
      <el-form :model="form" :rules="rules" ref="formRef">
        <div class="form">
          <p>家长账号：</p>
          <el-form-item prop="username">
            <el-input style="width: 240px;" placeholder="请输入家长账号" v-model="form.username"></el-input>
          </el-form-item>
        </div>
        <div class="form">
          <p>密码：</p>
          <el-form-item prop="password">
            <el-input style="width: 240px;" placeholder="请输入密码" show-password v-model="form.password"></el-input>
          </el-form-item>
        </div>
        <div class="form">
          <p>确认密码：</p>
          <el-form-item prop="confirmPass">
            <el-input style="width: 240px;" placeholder="请输入确认密码" show-password v-model="form.confirmPass"></el-input>
          </el-form-item>
        </div>
        <div class="form">
          <p>家长姓名：</p>
          <el-form-item prop="name">
            <el-input style="width: 240px;" placeholder="请输入家长姓名" v-model="form.name"></el-input>
          </el-form-item>
        </div>
        <div class="form">
          <p>头像：</p>
          <el-form-item prop="picture">
            <el-upload class="avatar-uploader" action="http://localhost:9090/files/upload" :show-file-list="false"
              :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
              <img v-if="imageUrl" :src="imageUrl" class="avatar">
              <i style="height: 80px; width: 60px;" v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
            <!-- <el-input v-model="form.picture" type="hidden" /> 隐藏输入字段，用于存储头像URL -->
          </el-form-item>
        </div>
        <div class="form">
          <p>性别：</p>
          <el-form-item style="width: 240px;" prop="gender">
            <el-select v-model="form.gender" placeholder="请选择性别" style="width: 100%">
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>
        </div>
        <div class="form">
          <p>手机号码：</p>
          <el-form-item prop="number">
            <el-input style="width: 240px;" placeholder="请输入手机号码" v-model="form.number"></el-input>
          </el-form-item>
        </div>
        <div class="form">
          <p>学号：</p>
          <el-form-item style="width: 240px;" prop="sid">
            <el-select v-model="selectedSid" placeholder="请选择学号" @change="onSidChange" style="width: 100%">
              <el-option v-for="student in students" :key="student.id" :label="student.id"
                :value="student.id">
              </el-option>
            </el-select>
          </el-form-item>
        </div>
        <div class="form">
          <p>学生姓名：</p>
          <el-form-item prop="sname">
            <el-input style="width: 240px;" placeholder="请输入学生姓名" v-model="selectedStudent.name" readonly></el-input>
          </el-form-item>
        </div>
        <div class="form">
          <p>班级：</p>
          <el-form-item prop="sclass">
            <el-input style="width: 240px;" placeholder="请输入班级" v-model="selectedStudent.classes" readonly></el-input>
          </el-form-item>
        </div>
        <el-form-item>
          <el-button style="width: 100%; background-color: #333; border-color: #333; color: white" @click="register">注
            册</el-button>
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
</template>

<script>
import { getAll } from '@/api/example';
export default {
  name: "RegisterP",
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
      form: {
         
      },
      imageUrl: '',
      user: {},
      selectedSid: '',
      selectedStudent: {
        username: null,
        name: null,
        classes: null
      }
      ,
      students: {
      },
      rules: {
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ],
        confirmPass: [
          { validator: validatePassword, trigger: 'blur' }
        ]
      },
    }
  },
  created() {
    this.fetchFormData();
  },
  methods: {
    fetchFormData() {
      getAll("student").then(res => {
        this.students = res.data;
      })
    },
    register() {
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          // 验证通过
          this.form.sid=this.selectedStudent.id;
          this.form.picture=this.imageUrl;
          this.$request.post('/parent/insertOne', this.form).then(res => {
            console.log(res.code);
            if (res.code === 20000) {
              this.$request.post('/user/insertOne', this.form).then(res => {
                if (res.code === 20000) {
                  console.log(res.data)
                  this.$router.push('/login') 
                  this.$message.success('注册成功')
                } else {
                  this.$message.error(res.msg)
                }
              })
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    },
    handleAvatarSuccess(response, file) {
      console.log(response.data) 
      this.imageUrl = response.data;
    },
    beforeAvatarUpload(file) { 
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    },
    onSidChange(value) {
      this.selectedStudent = this.students.find(student => student.id === value);
    }
  }
}
</script>

<style scoped>
.avatar-uploader{
  height: 100px;
  width: 240px;
}
.container {
  height: 100vh;
  overflow: hidden;
  background-image: url("@/assets/imgs/bg1.jpg");
  background-size: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #666;
}
.avatar {
    width: 60px;
    height: 80px;
    display: block;
  }
a {
  color: #2a60c9;
}

p {
  width: 80px;
  margin-top: -10px;
  text-align: right;
}

.form {
  display: flex;
  align-items: center;
}

.el-icon-plus {
  width: 240px;
  border: 2px solid #409EFF;
  font-size: 40px;
  color: #409EFF;
}
</style>