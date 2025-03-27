<template>
  <div class="activity-item">
    <div class="title">
      <h1>首页 / 活动信息</h1>
      <hr>
    </div>
    <div class="activity-item-content">
      <div class="left-side">
        <img :src="activityForm.picture">
      </div>
      <div class="right-side">
        <el-form label-width="120px">
          <!-- <el-form-item label="ID">
            {{ activityForm.id }}
          </el-form-item> -->
          <div class="info-container ">
            <span class="name">{{ activityForm.name }}
            </span>
            <el-button class="favorite-button" icon="el-icon-star-on" @click="handleFavorite">收藏</el-button>
          </div>
          <el-form-item label="活动类型">
            {{ activityForm.type }}
          </el-form-item>
          <el-form-item label="活动时间">
            {{ activityForm.date }}
          </el-form-item>
          <el-form-item label="活动地点">
            {{ activityForm.location }}
          </el-form-item>
          <el-form-item label="活动状态">
            {{ activityForm.status === 0 ? '进行中' : '已结束' }}
          </el-form-item>
          <el-form-item label="收藏数量">
            {{ activityForm.favoriteCount }}
          </el-form-item>
          <router-link to="/parent/activityRegistrationPage?id=1" class="activity-record-link">
            <el-button class="record-button">报名</el-button>
          </router-link>
        </el-form>
      </div>
    </div>
    <div class="content">
      <div class="content-title">
        <h3>活动内容</h3>
      </div>
      <p>{{ activityForm.content }}</p>
    </div>
  </div>
</template>

<script>
import { findOne} from '@/api/example';

export default {
  name: 'ActivityItem',
  data() {
    return {
      activityForm: {}
    }
  },
  created() {
    this.fetchFormData();
  },
  methods: {
    fetchFormData() {
      console.log("111")
      const activityId=this.$route.params.itemId;
      console.log(activityId)
      findOne(activityId, "activity").then(res => {
        this.activityForm = res.data;
        console.log(res.data);
      })
    }
  }

};


</script>

<style scoped>
.title {
  font-size: smaller;
}

.activity-item-content {
  margin-top: 1.5%;
  background-color: #fff;
  border-radius: 8px;
  overflow: hidden;
  text-align: center;
  display: flex;
  /* align-items: center; */
}


.left-side {
  padding: 6% 0%;
  background-color: #fff;
  width: 40%;
  /* 左边div占activity-item的40% */
  text-align: center;
  /* 使图片居中显示 */
  box-shadow: 0px 6px 4px rgba(0, 0, 0, 0.1);
  border: rgb(149, 146, 146) solid 1px;
  border-radius: 5%;
  margin-right: 10%;
}

.right-side {
  width: 50%;
  float: left;
  text-align: left;
  /* 右边div占activity-item的60% */
}

.left-side img {
  max-width: 100%;
  /* 图片最大宽度不超过其容器宽度 */
  height: auto;
  /* 图片高度自适应 */
}

.info-container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  height: auto;
}

.name {
  margin-left: 5%;
  height: 6%;
  width: 100%;
  color: white;
  /* 名称文字颜色为白色 */
  background-color: green;
  /* 背景颜色为绿色 */
  padding: 5px 10px;
  /* 内边距 */
  border-radius: 4px;
  /* 边框圆角 */
  margin-right: 10px;
  /* 与按钮的间距 */
}

.favorite-button {
  background-color: #ffc107;
  /* 按钮背景颜色 */
  color: white;
  /* 按钮文字颜色 */
  border: none;
  /* 无边框 */
  border-radius: 4px;
  /* 边框圆角 */
  padding: 5px 15px;
  /* 内边距 */
}

.record-button{
  margin-left: 6%;
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