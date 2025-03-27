<template>
  <div>
    <!--头部-->
    <div class="front-header">
      <div class="front-header-left">
        <img src="@/assets/imgs/logo.png" alt="">
        <div class="title" @click="back()">家校通</div>
      </div>
      <div class="front-header-center">
        <div class="front-header-nav">
          <el-menu class="custom-menu" :default-active="$route.path" mode="horizontal" router>
            <el-menu-item index="/parent/home">首页</el-menu-item>
            <el-menu-item index="/parent/courses">课程信息</el-menu-item>
            <el-submenu index="/parent/entertainment-management">
              <template #title>文娱管理</template>
              <el-menu-item index="/parent/activityform">活动信息</el-menu-item>
              <el-menu-item index="/parent/dishform">日常菜谱</el-menu-item>
            </el-submenu>
            <el-menu-item index="/parent/surveys">问卷调查</el-menu-item>
            <el-menu-item index="/parent/user">个人中心</el-menu-item>
          </el-menu>
        </div>
      </div>
      <div class="front-header-right">
        <div v-if="!user.username">
          <el-button @click="$router.push('/login')">登录</el-button>
          <el-button @click="$router.push('/register')">注册</el-button>
        </div>
        <div v-else>
          <el-dropdown>
            <div class="front-header-dropdown">
              <img :src="user.picture" alt="">
              <div style="margin-left: 10px">
                <span>{{ user.name }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
              </div>
            </div>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <div style="text-decoration: none" @click="logout">退出</div>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </div>
    <div class="block">
      <el-carousel trigger="click" height="500px">
        <el-carousel-item v-for="item in backgroundImages" :key="item.id">
          <img :src="item.src" alt="item.alt" style="width: 100%; height: 100%; object-fit: cover;">
        </el-carousel-item>
      </el-carousel>
    </div>
    <!--主体-->
    <div class="main-body">
      <router-view ref="child" @update:user="updateUser" />
    </div>
  </div>

</template>

<script>
import { logout } from '@/api/user';
export default {
  name: "Parent",
  data() {
    return {
      backgroundImages: [
        { id: 1, src: require('@/assets/imgs/bg3.jpg'), alt: 'Image 1' },
        { id: 2, src: require('@/assets/imgs/bg.jpg'), alt: 'Image 2' },
        { id: 3, src: require('@/assets/imgs/zjitc.jpg'), alt: 'Image 2' },
        { id: 4, src: require('@/assets/imgs/bg4.jpg'), alt: 'Image 4' },
      ],
      top: '',
      notice: [],
      user: JSON.parse(localStorage.getItem("xm-parent") || '{}'),
    }
  },

  mounted() {
  },
  methods: {
    navTo(url) {
      location.href = url
    },
    back() {
      this.$router.push('/parent/home')
    },
    updateUser() {
      this.user = JSON.parse(localStorage.getItem('xm-parent') || '{}')
    },
    logout() {
      logout(this.user.username);
      localStorage.removeItem("xm-parent");
      this.$router.push("/login");
    },
    toPerson() {
      location.href = '/parent/my'
    }
  }

}
</script>

<style scoped>
.custom-menu {
  text-align: center;
  display: flex;
  justify-content: center;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}

.el-carousel__item img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  /* 确保图片按比例缩放并填充容器 */
}

@import "@/assets/css/front.css";
</style>