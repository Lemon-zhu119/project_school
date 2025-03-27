<template>
    <div class="all">

        <!-- 主导航栏，这里可以是一个全局组件或布局组件的一部分 -->
        <div class="top-img">
            <img class="grzx" src="http://localhost:9090/files/个人中心.jpg" alt="个人中心图标">
        </div>
        <!-- 根据当前路由决定是否显示子导航栏 -->
        <div class="main-content">
            <!-- 左边导航栏 -->
            <div class="left-nav">
                <nav class="sub-nav">
                    <router-link class="my-link" to="/parent/user/profile">个人中心</router-link>
                    <router-link class="my-link" to="/parent/user/change-password">修改密码</router-link>
                    <router-link class="my-link" to="/parent/leaveRequest">请假申请</router-link>
                    <router-link class="my-link" to="/parent/entertainment-management">我的活动</router-link>
                    <router-link class="my-link" to="/parent/surveyfeedback">反馈意见</router-link>
                    <router-link class="my-link" to="/parent/user/student-management">学生管理</router-link>
                    <!-- <router-link class="my-link" to="/parent/user/my-favorites-management">我的收藏管理</router-link> -->
                </nav>
            </div>

            <!-- 右边页面内容 -->
            <div class="right-content">
                <router-view></router-view>
            </div>
        </div>
    </div>

</template>

<script>
export default {
    name: "UserCenter",
};
</script>

<style scoped>
.all {  
  display: flex;  
  flex-direction: column; 
  flex: 1;
}  
  
.top-img {  
  text-align: center; 
  padding: 20px; 
  background-color: #ffffff; 
}  
  
.top-img img {  
  max-width: 100%; 
  height: auto; 
}  
  
.main-content { 
  display: flex; /* 使用Flexbox布局   */
  flex: 1; /* 占据剩余的空间    */
  padding: 20px; /* 可选：设置内边距 */  
  box-sizing: border-box; /* 包含内边距和边框在内计算宽度 */  
}  
  
.left-nav {  
  flex: 0 0 250px; 
  background-color: #f8f9fa; /* 可选：设置背景色 */  
  padding: 20px; /* 可选：设置内边距 */  
  box-sizing: border-box; /* 包含内边距和边框在内计算宽度 */  
}  
  
.right-content {  
  flex: 1; /* 设置右边页面内容占据剩余的空间 */  
  padding: 20px; /* 可选：设置内边距 */  
  box-sizing: border-box; /* 包含内边距和边框在内计算宽度 */  
  overflow-y: auto; /* 可选：使内容在垂直方向上可滚动，如果内容超出视口高度 */  
}  
.sub-nav {
    /* 设置导航栏的样式，例如背景色、内边距等 */
    background-color: #f8f9fa;
    /* 浅灰色背景 */
    padding: 10px;
    /* 内边距 */
    /* 如果需要，可以添加边框、圆角等 */
    border-radius: 5px;
}

.my-link {
  color: red; /* 改变文字颜色或其他样式 */  
  border-radius: 10px; /* 使边缘弯曲，10px是弯曲的程度，你可以根据需要调整 */  
  /* 你可以添加更多的样式，比如背景色、边框等 */  
    /* 设置导航链接的样式，例如文本颜色、字体大小、行高等 */
    color: #343a40;
    /* 深灰色文本 */
    text-decoration: none;
    /* 去除下划线 */
    font-size: 15px;
    text-align: center;
    /* 字体大小 */
    line-height: 30px;
    /* 行高，这将决定链接之间的垂直间距 */
    display: block;
    /* 使其成为块级元素，从而竖着排列 */
    padding: 5px 10px;
    /* 内边距，增加点击区域 */
}

.my-link:hover {
    background-color: #f05e09;
    color: #212529;
}
.my-link:active {  
  color: red; /* 改变文字颜色 */  
  font-weight: bold;  
  /* 其他样式... */  
}  
.router-link-active.my-link {  
  color: white; /* 改变文字颜色 */  
  font-weight: bold; /* 加粗文字 */  
  /* 其他样式... */  
  background-color:  #f05e09;
}
</style>