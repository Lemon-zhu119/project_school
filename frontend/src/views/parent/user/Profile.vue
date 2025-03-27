<template>
  <div class="container">
    <div style="width: 100vh; padding: 30px; background-color: white; border-radius: 5px;">
      <el-form :model="form" ref="formRef">
        <div class="form-row">
          <div class="form-item">
            <p>家长账号：</p>
            <el-form-item prop="username">
              <el-input style="width: 240px;" placeholder="请输入家长账号" v-model="form.username"></el-input>
            </el-form-item>
          </div>
        </div>
        <div class="form-item">
          <p>家长姓名：</p>
          <el-form-item prop="name">
            <el-input style="width: 240px;" placeholder="请输入家长姓名" v-model="form.name"></el-input>
          </el-form-item>
        </div>
        <div class="form-item">
          <p>头像：</p>
          <el-form-item prop="picture">
            <img v-if="form.picture" :src="form.picture" class="avatar">请上传头像
          </el-form-item>
          <el-form-item>
            <el-upload class="avatar-uploader" action="http://localhost:9090/files/upload" :show-file-list="false"
              :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
              <img v-if="imageUrl" :src="imageUrl" class="avatar">
              <i style="margin-top: -30px; height: 80px; width: 60px;" v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
        </div>
        <div class="form-row">
          <div class="form-item">
          <p>性别：</p>
          <el-form-item style="width: 240px;" prop="gender">
            <el-select v-model="form.gender" placeholder="请选择性别" style="width: 100%">
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>
        </div>
        <div class="form-item">
          <p>手机号码：</p>
          <el-form-item prop="number">
            <el-input style="width: 240px;" placeholder="请输入手机号码" v-model="form.number"></el-input>
          </el-form-item>
        </div>
        </div>
        <div class="form-row">
          <div class="form-item">
          <p>学号：</p>
          <el-form-item style="width: 240px;" prop="sid">
            <el-select v-model="form.sid" placeholder="请选择学号" @change="onSidChange" style="width: 100%">
              <el-option v-for="student in students" :key="student.id" :label="student.id"
                :value="student.id">
              </el-option>
            </el-select>
          </el-form-item>
        </div>
        <div class="form-item">
          <p>学生姓名：</p>
          <el-form-item prop="sname">
            <el-input style="width: 240px;" placeholder="请输入学生姓名" v-model="selectedStudent.name" readonly></el-input>
          </el-form-item>
        </div>
        </div>
        <div class="form-item">
          <p>班级：</p>
          <el-form-item prop="sclass">
            <el-input style="width: 240px;" placeholder="请输入班级" v-model="selectedStudent.classes" readonly></el-input>
          </el-form-item>
        </div>
        <el-form-item>
          <el-button style="margin-left: 80px;border-radius: 15px; width: 30%; background-color:#9db637;color: white;"
            @click="updateUser">更新信息
          </el-button>
          <el-button style="margin-left: 20px;border-radius: 15px; width: 30%; background-color:#ff9100;color: white;"
            @click="logout">退出登录
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { getAll } from '@/api/example';
import { logout,updateUser } from '@/api/user';
export default {
  name: "Profile",
  data() {
    return {
      form: {},
      imageUrl: '',
      user: {},
      selectedSid: '',
      selectedStudent: {
        id: null,
        name: null,
        classes: null
      },
      students: {},
      rules: {
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ],
      },
      parentStudent:{}
    }
  },
  created() {
    this.fetchFormData();
  },
  methods: {
    fetchFormData() {
      this.form = JSON.parse(localStorage.getItem("xm-parent") || '{}');
      this.parentStudent = JSON.parse(localStorage.getItem("xm-student") || '{}');
      getAll("student").then(res => {
        this.students = res.data;
        this.onSidChange(this.parentStudent.id);
      })
    },
    handleAvatarSuccess(response, file) {
      this.form.picture = response.data;
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
    },
    logout() {
      logout(this.form.username);
      localStorage.removeItem("xm-parent");
      this.$router.push("/login");
    },
    updateUser(){
      this.form.sid=this.selectedStudent.id;
      updateUser(this.form).then(res=>{
        if(res.code===20000){
          this.$message.success("更新成功");
          localStorage.setItem("xm-parent", JSON.stringify(this.form))
        }else{
          this.$message.error("更新失败");
        }
      })
      
    }
  }
}
</script>

<style scoped>
.container {
  flex: 1;
  overflow: hidden;
  background-size: 100%;
  display: flex;
  /* align-items: center; */
  justify-content: center;
  color: #666;
}
.avatar {
  width: 60px;
  height: 80px;
  display: block;
}
p {
  width: 80px;
  margin-top: -20px;
  text-align: right;
}
.el-icon-plus {
  padding-top: 18px;
  width: auto;
  border: 2px solid #409EFF;
  font-size: 40px;
  color: #409EFF;
}
.form-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.form-item {
  display: flex;
  min-width: 250px;
  align-items: center;
}
</style>