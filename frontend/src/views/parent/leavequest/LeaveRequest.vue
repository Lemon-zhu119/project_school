<template>
  <div>
    <h1 class="title">首页/请假申请</h1>
    <div class="form">
      <div class="form-item">
        <p style="width: 94px;">教师姓名：</p>
        <el-input v-model="leaveRequest.teacher" style="height: auto; width: 240px;" placeholder="教师姓名"></el-input>
        <el-button
          style="border-radius: 15px; width: 30%; background-image: radial-gradient(circle,rgba(255,255,255,0) 0%,rgba(255, 165, 0,0.5) 80%); color: #9a6201;"
          @click="findByTeacherName">搜索</el-button>
        <el-button style="border-radius: 15px; width: 30%;background-color: #ffffff;" @click="insert">新增</el-button>
      </div>
    </div>
    <el-table :data="tableData" border style="width: 100%" :header-cell-style="headerCellStyle">
      <el-table-column label="序号" prop="courseId">
      </el-table-column>
      <el-table-column label="请假日期" prop="date">
      </el-table-column>
      <el-table-column label="请假课程" prop="course">
      </el-table-column>
      <el-table-column label="请假原因" prop="reason">
      </el-table-column>
      <el-table-column label="教师工号" prop="teacherId">
      </el-table-column>
      <el-table-column label="教师姓名" prop="teacher">
      </el-table-column>
      <el-table-column label="家长账号" prop="parentUsername">
      </el-table-column>
      <el-table-column label="家长姓名" prop="parentName">
      </el-table-column>
      <el-table-column label="学号" prop="sid">
      </el-table-column>
      <el-table-column label="学生姓名" prop="studentName">
      </el-table-column>
      <el-table-column label="班级" prop="classes">
      </el-table-column>
      <el-table-column label="审核回复" prop="reviewResponse">
      </el-table-column>
      <el-table-column label="审核状态" prop="status">
        <template slot-scope="scope">
          <span :class="['status-capsule', { 'pending': scope.row.status === 0, 'approved': scope.row.status === 1 ,'rejected':scope.row.status===2}]">
            {{ scope.row.status === 0 ? '待审核' : (scope.row.status === 1 ? '已通过' : '已驳回') }}
          </span>
        </template>
      </el-table-column>
    </el-table>
  </div>

</template>

<script>
import { findById, queryOne } from '@/api/example';

export default {
  name: "LeaveRequest",
  data() {
    return {
      tableData: [],
      search: '',
      student: {},
      leaveRequest:{}
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
      this.student = JSON.parse(localStorage.getItem("xm-student") || '{}');
      findById(this.student.id, "leaveRequest").then(res => {
        console.log(res.data);
        this.tableData = res.data;
      })
    },
    findByTeacherName() {
      console.log(this.teacherName)
      queryOne(this.leaveRequest, "leaveRequest").then(res => {
        this.tableData = res.data;
        console.log(res.data);
      })
    },
    insert() {
      this.$router.push('/parent/leaveRequestPage')
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

.form-item {
  display: flex;
  width: 600px;
  align-items: center;
}

/* 定义胶囊样式 */
.status-capsule {
  display: inline-block;
  padding: 5px 10px;
  border-radius: 15px;
  font-size: 12px;
}

.pending {
  background-color: #ffffcc; /* 待审核的背景色 */
  color: #666600; /* 待审核的字体颜色 */
}

.approved {
  background-color: #ccffcc;
  /* 已通过的背景色 */
  color: #008000;
  /* 已通过的字体颜色 */
}
.rejected {
  background-color: #ffcccc; /* 已驳回的背景色 */
  color: #ff0000; /* 已驳回的字体颜色 */
}
</style>