<template>
  <div class="course-item">
    <div class="title">
      <h1>课程详细信息</h1>
      <hr>
    </div>
    <div class="course-item-content">
      <div class="left-side">
        <img :src="course.picture" alt="课程图片">
      </div>
      <div class="right-side">
        <el-form label-width="120px">
          <el-form-item label="课程ID">
            {{ course.id }}
          </el-form-item>
          <el-form-item label="课程名称">
            {{ course.name }}
          </el-form-item>
          <el-form-item label="教师工号">
            {{ course.teacherId }}
          </el-form-item>
          <el-form-item label="课程内容">
            {{ course.content }}
          </el-form-item>
          <el-form-item label="课程时间">
            {{ course.time }}
          </el-form-item>
          <el-form-item label="课程地点">
            {{ course.location }}
          </el-form-item>
          <el-form-item label="授课教师">
            {{ course.courseTeacherName }}
          </el-form-item>
          <el-form-item>
            <router-link :to="{ name: 'LeaveRequestPage' }" class="leave-request-link">
              请假申请
            </router-link>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <div class="content">
      <div class="content-title">
        <h3>课程介绍</h3>
      </div>
      <p>{{ course.content }}</p>
    </div>
  </div>
</template>

<script>
import { findOne } from '@/api/example';

export default {
  name: 'CourseDetails',
  data() {
    return {
      course: {
        id: '',
        name: '',
        teacherId: '',
        status: null,
        content: '',
        picture: '',
        time: '',
        location: '',
        course_teacher_name: ''
      }
    }
  },
  created() {
    this.fetchCourseData();
  },
  methods: {
    fetchCourseData() {
      const courseId = this.$route.params.courseId;
      console.log(courseId)
      findOne(courseId, "course").then(res => {
        this.course = res.data;
        console.log(res.data);
      });
    }
  }
};
</script>

<style scoped>
.title {
  font-size: smaller;
}

.course-item-content {
  margin-top: 1.5%;
  background-color: #fff;
  border-radius: 8px;
  overflow: hidden;
  text-align: center;
  display: flex;
}

.left-side {
  height: 60vh;
  padding: 6% 0%;
  background-color: #fff;
  width: 40%;
  text-align: center;
  box-shadow: 0px 6px 4px rgba(0, 0, 0, 0.1);
  border: rgb(149, 146, 146) solid 1px;
  border-radius: 5%;
  margin-right: 10%;
}

.right-side {
  width: 50%;
  float: left;
  text-align: left;
}

.left-side img {
  max-width: 100%;
  height: 100%;
}

.info-container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  height: auto;
}

.content {
  height: 60px;
  margin-top: 3%;
  padding: auto;
  margin-left: 1%;
  border: #cecdca solid 1px;
}

.content-title {
  width: 100%;
  background-image: radial-gradient(circle at right, rgba(255, 255, 255, 0) 0%, rgba(255, 165, 0, 0.5) 100%);
  color: #9a6201;
  border-radius: 5%;
  height: 50%;
}
</style>