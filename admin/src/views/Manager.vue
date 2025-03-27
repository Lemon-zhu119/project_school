<template>
  <div class="manager-container">
    <!--  头部  -->
    <div class="manager-header">
      <div class="manager-header-left">
        <img src="@/assets/imgs/logo.png" />
        <div class="title">后台管理系统</div>
      </div>

      <div class="manager-header-center">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item :to="{ path: $route.path }">{{ $route.meta.name }}</el-breadcrumb-item>
        </el-breadcrumb>
      </div>

      <div class="manager-header-right">
        <el-dropdown placement="bottom">
          <div class="avatar">
            <img :src="user.picture || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" />
            <div>{{ user.name ||  '管理员' }}</div>
          </div>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="goToPerson">个人信息</el-dropdown-item>
            <el-dropdown-item @click.native="$router.push('/password')">修改密码</el-dropdown-item>
            <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>

    <!--  主体  -->
    <div class="manager-main">
      <!--  侧边栏  -->
      <div class="manager-main-left">
        <el-menu  router style="border: none" unique-opened :default-active="$route.path">
          <el-menu-item index="/home">
            <i class="el-icon-s-home"></i>
            <span slot="title">系统首页</span>
          </el-menu-item>
          <el-submenu index="info">
            <template slot="title">
              <i class="el-icon-menu"></i><span>学生管理</span>
            </template>
            <el-menu-item index="/student">学生信息</el-menu-item>
            <el-menu-item index="/scores">学生成绩</el-menu-item>
          </el-submenu>
          <el-submenu index="course">
            <template slot="title">
              <i class="el-icon-menu"></i><span>课程管理</span>
            </template>
            <el-menu-item index="/course">课程信息</el-menu-item>
            <el-menu-item index="/teacher">教师信息</el-menu-item>
            <el-menu-item index="/leaverequest">请假记录</el-menu-item>
          </el-submenu>
          <el-submenu index="activity">
            <template slot="title">
              <i class="el-icon-menu"></i><span>活动管理</span>
            </template>
            <el-menu-item index="/activity">活动信息</el-menu-item>
            <el-menu-item index="/registration">报名记录</el-menu-item>
          </el-submenu>
          <el-submenu index="survey">
            <template slot="title">
              <i class="el-icon-menu"></i><span>问卷管理</span>
            </template>
            <el-menu-item index="/survey">问卷信息</el-menu-item>
            <el-menu-item index="/feedback">反馈记录</el-menu-item>
          </el-submenu>
          <el-submenu index="user">
            <template slot="title">
              <i class="el-icon-menu"></i><span>用户管理</span>
            </template>
            <el-menu-item index="/admin">管理员信息</el-menu-item>
            <el-menu-item index="/user">用户信息</el-menu-item>
          </el-submenu>
          <el-submenu index="dish">
            <template slot="title">
              <i class="el-icon-menu"></i><span>食品管理</span>
            </template>
            <el-menu-item index="/dish">食品信息</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>

      <!--  数据表格  -->
      <div class="manager-main-right">
        <router-view @update:user="updateUser" />
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "Manager",
  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
    }
  },
  created() {
    if (!this.user.id) {
      this.$router.push('/login')
    }
  },
  methods: {
    updateUser() {
      this.user = JSON.parse(localStorage.getItem('xm-user') || '{}')   // 重新获取下用户的最新信息
    },
    goToPerson() {
        this.$router.push('/adminPerson')
    },
    logout() {
      localStorage.removeItem('xm-user')
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
@import "@/assets/css/manager.css";
</style>