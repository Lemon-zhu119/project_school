<template>
  <div style="display: block;">
    <h1 class="title">首页/活动报名</h1>
    <hr>
    <div style="margin:0 auto; width: 100vh; padding: 30px; background-color: white; border-radius: 5px; ">
      <el-form :model="form" ref="formRef">
        <div class="form-row">
          <div class="form-item">
            <p>活动名称：</p>
            <el-form-item prop="name">
              <el-input style="width: 240px; margin-top: 20px;" placeholder="请输入活动名称" v-model="form.name"></el-input>
            </el-form-item>
          </div>
          <div class="form-item">
            <p>活动时间：</p>
            <el-form-item prop="date">
              <el-input style="width: 240px; margin-top: 20px;" placeholder="请输入请假日期" v-model="form.date"></el-input>
            </el-form-item>
          </div>
        </div>
        <div class="form-row">
          <div class="form-item">
            <p>活动地点：</p>
            <el-form-item prop="location">
              <el-input style="width: 240px; margin-top: 20px;" placeholder="请输入请假日期"
                v-model="form.location"></el-input>
            </el-form-item>
          </div>
          <div class="form-item">
            <p>报名人数：</p>
            <el-form-item>
              <el-input style="width: 240px; margin-top: 20px; margin-top: 20px;" placeholder="请输入报名人数"
                v-model="form.registrationCount"></el-input>
            </el-form-item>
          </div>
        </div>
        <div class="form-row">
          <div class="form-item">
            <p>报名时间：</p>
            <el-form-item prop="registrationTime">
              <el-date-picker type="datetime" style="width: 240px; margin-top: 20px;" placeholder="请输入报名时间"
                v-model="form.registrationTime" format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss">
              </el-date-picker>
            </el-form-item>
          </div>
          <div class="form-item">
            <p>学号：</p>
            <el-input style="width: 240px;" placeholder="请输入手机号码" v-model="Student.id"></el-input>
          </div>
        </div>
        <div class="form-row">
          <div class="form-item">
            <p>学生姓名：</p>
            <el-form-item prop="sname">
              <el-input style="width: 240px; margin-top: 20px;" placeholder="请输入学生姓名" v-model="Student.name"
                readonly></el-input>
            </el-form-item>

          </div>
          <div class="form-item">
            <p>班级：</p>
            <el-form-item prop="sclass">
              <el-input style="width: 240px; margin-top: 20px;" placeholder="请输入班级" v-model="Student.classes"
                readonly></el-input>
            </el-form-item>
          </div>
        </div>


        <div class="form-row">


        </div>
        <div class="form-row">
          <div class="form-item">
            <p>家长账号：</p>
            <el-form-item prop="number">
              <el-input style="width: 240px; margin-top: 20px;" placeholder="请输入手机号码"
                v-model="parent.username"></el-input>
            </el-form-item>
          </div>
          <div class="form-item">
            <p>家长姓名：</p>
            <el-form-item prop="number">
              <el-input style="width: 240px; margin-top: 20px;" placeholder="请输入手机号码" v-model="parent.name"></el-input>
            </el-form-item>
          </div>
        </div>
        <el-form-item>
          <el-button style="margin-left: 80px;border-radius: 15px; width: 30%; background-color:#9db637;color: white;"
            @click="cansel">取消
          </el-button>
          <el-button style="margin-left: 20px;border-radius: 15px; width: 30%; background-color:#ff9100;color: white;"
            @click="insert">保存
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>

</template>

<script>
import { addOne, findOne } from '@/api/example';
export default {
  name: "ActivityRegistrationPage",
  data() {
    return {
      form: {
        id: null,
      },
      tableData: [],
      search: '',
      Student: {},
      Parent: "",
      courses: [],
      activity: {}
    }
  },
  created() {
    this.fetchFormData();
  },
  computed: {
    headerCellStyle() {
      return {
        backgroundImage: 'linear-gradient(to bottom, #fe7d1d, #ffcc00)', // 从上到下的渐变色  
        height: '30px',
        color: '#000000',
        border: '1px solid tan',
        textAlign: 'center',
      };
    },
  },
  methods: {
    fetchFormData() {
      this.parent = JSON.parse(localStorage.getItem('xm-parent') || '{}')
      this.Student = JSON.parse(localStorage.getItem('xm-student') || '{}')
      this.activity.id = this.$route.query.id
      findOne(this.activity.id, "activity").then(res => {
        this.form = res.data;
      })

    },
    cansel() {
      this.$router.push('/parent/activity-item'+this.$route.query.id)
    },
    insert() {
      this.form.activityId=this.activity.id;
      this.form.parentId = this.parent.id;
      this.form.studentId=this.Student.id;
      addOne(this.form, "activityRegistration").then(res => {
        if (res.code === 20000) {
          this.$message.success("成功")
        } else {
          this.$message.error("失败")
        }
      })
    }
  }
}
</script>
<style scoped>
.title {
  font-size: 16px;
}

.form {
  width: 100%;
  height: 50px;
  display: flex;
  /* 将 .form 设置为 flex 容器 */
  justify-content: center;
  /* 水平居中 */
  align-items: center;
  /* 垂直居中 */
  border: 1px solid rgb(123, 122, 122);
  box-shadow: 0 2px 4px rgba(159, 159, 159, 0.12), 0 0 6px rgba(0, 0, 0, .04);
}

.form-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.form-item {
  display: flex;
  width: 600px;
  height: 50px;
  align-items: center;
}

.form-item p {
  float: right;
  width: 80px;
}

.form-item-select {
  margin-top: 20px;
  width: 300px;
}

/* 定义胶囊样式 */
.status-capsule {
  display: inline-block;
  padding: 5px 10px;
  border-radius: 15px;
  font-size: 12px;
}

.pending {
  background-color: #ffcccc;
  /* 待审核的背景色 */
  color: #ff0000;
  /* 待审核的字体颜色 */
}

.approved {
  background-color: #ccffcc;
  /* 已通过的背景色 */
  color: #008000;
  /* 已通过的字体颜色 */
}
</style>