<template>
  <div class="container">
    <div style="width: 400px; padding: 30px; background-color: white; border-radius: 5px;">
      <div style="text-align: center; font-size: 20px; margin-bottom: 20px; color: #333">家校信息互通服务平台注册</div>
      <el-form :model="form" :rules="rules" ref="formRef">
        <div class="form">
          <p>学号：</p>
          <el-form-item prop="username">
            <el-input style="width: 240px;" placeholder="请输入学号" v-model="form.username"></el-input>
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
          <p>学生姓名：</p>
          <el-form-item prop="name">
            <el-input style="width: 240px;" placeholder="请输入学生姓名" v-model="form.name"></el-input>
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
          <p>家长账号：</p>
          <el-form-item style="width: 240px;" prop="Pusername">
            <el-select v-model="selectedPusername" placeholder="请选择家长账号" @change="onPUsernameChange" style="width: 100%">
              <el-option v-for="parent in parents" :key="parent.username" :label="parent.username"
                :value="parent.username">
              </el-option>
            </el-select>
          </el-form-item>
        </div>
        <div class="form">
          <p>家长姓名：</p>
          <el-form-item prop="Pname">
            <el-input style="width: 240px;" placeholder="请输入手机号码" v-model="selectedParent.name" readonly></el-input>
          </el-form-item>
        </div>
        <div class="form">
          <p>班级：</p>
          <el-form-item style="width: 240px;" prop="classes">
            <el-select v-model="form.classes" placeholder="请选择班级" style="width: 100%">
              <el-option v-for="option in classesList" :key="option.classes" :label="option.classes"
                :value="option.classes">
              </el-option>
            </el-select>
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
  name: "RegisterS",
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
      selectedPusername: '',
      selectedParent: {
        username: null,
        name: null,
      }
      ,
      classesList:[
        {classes:"1班"},
        {classes:"2班"},
        {classes:"3班"},
        {classes:"4班"},
      ],
      parents: {
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
      getAll("parent").then(res => {
        this.parents = res.data;
      })
    },
    register() {
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          // 验证通过
          this.form.pUsername=this.selectedParent.username;
          this.form.userType="student";
          this.form.picture=this.imageUrl;
          this.$request.post('/student/insert', this.form).then(res => {
            console.log(res.code);
            if (res.code === 20000) {
              console.log(res.data);
              this.$request.post('/user/insert', this.form).then(res => {
                console.log(res.data);
                if (res.code === 20000) {
                  this.$router.push('/login')  // 跳转登录页面
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
      // 上传成功后的处理逻辑  
      this.imageUrl = response.data; // 假设服务器返回了图片的URL  
    },
    beforeAvatarUpload(file) {
      // 上传之前的验证逻辑  
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
    onPUsernameChange(value) {
      // 当学号改变时，找到对应的学生对象并赋值给 selectedParent  
      this.selectedParent = this.parents.find(parent => parent.username === value);
      console.log(this.selectedParent);
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