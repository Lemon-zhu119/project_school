<template>
  <div class="course-item">
    <div class="title">
      <h1>首页 / 活动信息</h1>
      <hr>
    </div>
    <div class="course-item-content">
      <div class="left-side">
        <img :src="surveyForm.picture" alt="">
      </div>
      <div class="right-side">
        <el-form label-width="120px">
          <el-form-item label="问卷名称：">
            <span class="name">{{ surveyForm.name }}</span>
          </el-form-item>

          <el-form-item label="问卷内容">
            <div v-if="surveyForm.fileFlag">
              <a :href="surveyForm.fileFlag" download target="_blank" class="el-button">
                下载问卷内容
              </a>
            </div>
          </el-form-item>
          <el-form-item label="提出意见">
            <router-link :to="{ name: 'SurveyFeedbackPage', params: { surveyId: surveyForm.id } }" class="el-button">
              反馈
            </router-link>
          </el-form-item>

        </el-form>
      </div>
    </div>
    <div class="content">
      <div class="content-title">
        <h3>活动内容</h3>
      </div>
      <p>{{ surveyForm.content }}</p>
    </div>
  </div>
</template>

<script>
import { findOne } from '@/api/example';

export default {
  name: 'SurveyDetail',
  data() {
    return {
      surveyForm: {
        content: '',
        fileFlag: '',
        id: '',
        name: '',
        picture: '',
        time: '',
      }
    }
  },
  created() {
    this.fetchSurveyData();
  },
  methods: {
    fetchSurveyData() {
      const surveyId = this.$route.params.surveyId;
      console.log(surveyId);
      findOne(surveyId, "survey").then(res => {
        this.surveyForm = res.data;
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
  height: 160px;
  margin-top: 3%;
  padding: auto;
  margin-left: 1%;
  border: #cecdca solid 1px;
}

.content-title {
  width:100%;
  background-image: radial-gradient(circle at right, rgba(255, 255, 255, 0) 0%, rgba(255, 165, 0, 0.5) 100%);
  color: #9a6201;
  border-radius: 5%;
  height: 50px;
}
.content-title h3{
  font-size: 30px;
}
</style>