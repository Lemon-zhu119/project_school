import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

// 解决导航栏或者底部导航tabBar中的vue-router在3.0版本以上频繁点击菜单报错的问题。
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}

const routes = [
  {
    path: '/',
    name: 'Manager',
    component: () => import('../views/Manager.vue'),
    redirect: '/home',  // 重定向到主页
    children: [
      { path: '403', name: 'NoAuth', meta: { name: '无权限' }, component: () => import('../views/manager/403') },
      { path: 'home', name: 'Home', meta: { name: '系统首页' }, component: () => import('../views/manager/Home') },
      { path: 'admin', name: 'Admin', meta: { name: '管理员信息' }, component: () => import('../views/manager/Admin') },
      { path: 'adminPerson', name: 'AdminPerson', meta: { name: '个人信息' }, component: () => import('../views/manager/AdminPerson') },
      { path: 'password', name: 'Password', meta: { name: '修改密码' }, component: () => import('../views/manager/Password') },
      { path: 'survey', name: 'Survey', meta: { name: '问卷信息' }, component: () => import('../views/manager/survey/Survey.vue') },
      { path: 'dish', name: 'Dish', meta: { name: '问卷信息' }, component: () => import('../views/manager/dish/Dish.vue') },
      { path: 'user', name: 'User', meta: { name: '用户信息' }, component: () => import('../views/manager/User.vue') },
      { path: 'registration', name: 'Registration', meta: { name: '报名信息' }, component: () => import('../views/manager/activity/Registration.vue') },
      { path: 'activity', name: 'Activity', meta: { name: '活动信息' }, component: () => import('../views/manager/activity/Activity.vue') },
      { path: 'leaverequest', name: 'LeaveRequest', meta: { name: '请假信息' }, component: () => import('../views/manager/course/LeaveRequest.vue') },
      { path: 'teacher', name: 'Teacher', meta: { name: '教师信息' }, component: () => import('../views/manager/course/Teacher.vue') },
      { path: 'course', name: 'Course', meta: { name: '课程信息' }, component: () => import('../views/manager/course/Course.vue') },
      { path: 'scores', name: 'Scores', meta: { name: '成绩信息' }, component: () => import('../views/manager/student/StudentScores.vue') },
      { path: 'student', name: 'Student', meta: { name: '学生信息' }, component: () => import('../views/manager/student/Students.vue') },
      { path: 'feedback', name: 'FeedBack', meta: { name: '反馈信息' }, component: () => import('../views/manager/survey/FeedBack.vue') },
    ]
  },
  { path: '/login', name: 'Login', meta: { name: '登录' }, component: () => import('../views/Login.vue') },
  { path: '/register', name: 'Register', meta: { name: '注册' }, component: () => import('../views/Register.vue') },
  { path: '*', name: 'NotFound', meta: { name: '无法访问' }, component: () => import('../views/404.vue') },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
export default router
