<template>
    <div>
        <h1 class="title">首页/每日菜谱</h1>
        <div class="form">
            <div class="form-item">
                <p style="width: 94px;">菜谱名称：</p>
                <el-input v-model="searchQuery" style="height: auto; width: 240px;" placeholder="菜谱名称"></el-input>
                <el-button
                    style="border-radius: 15px; width: 30%; background-image: radial-gradient(circle,rgba(255,255,255,0) 0%,rgba(255, 165, 0,0.5) 80%); color: #9a6201;"
                    @click="searchDishes">搜索</el-button>
            </div>
        </div>
        <div class="container">
            <router-link v-for="dish in dishes" :key="dish.id" :to="{ name: 'DishDetail', params: { dishId: dish.id } }"
                class="item" style="text-decoration: none; color: inherit;">
                <div class="content-wrapper">
                    <img :src="dish.picture" alt="菜谱图片" class="image">
                    <div class="text">{{ dish.name }}</div>
                    <div class="text">{{ dish.date }}</div>
                </div>
            </router-link>
        </div>
    </div>
</template>

<script>
import { getAll } from '@/api/example'; // 假设这是您的API方法

export default {
    name: "DishForm",
    data() {
        return {
            searchQuery: '',
            dishes: [],
        }
    },
    created() {
        this.fetchDishes();
    },
    methods: {
        fetchDishes() {
            getAll("dish").then(res => {
                console.log(res.data);
                this.dishes = res.data;
            })
        },
        searchDishes() {
            this.dishes = this.dishes.filter(dish =>
                dish.name.toLowerCase().includes(this.searchQuery.toLowerCase())
            );
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
  justify-content: center;
  align-items: center;
  border: 1px solid rgb(123, 122, 122);
  box-shadow: 0 2px 4px rgba(159, 159, 159, 0.12), 0 0 6px rgba(0, 0, 0, .04);
}

.form-item {
  display: flex;
  width: 600px;
  align-items: center;
}

.container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  padding: 20px;
}

.item {
  width: 23%;
  /* 设置每个项目占据的宽度 */
  margin: 10px;
  /* 设置每个项目之间的间隔 */
  display: flex;
  flex-direction: column;
  align-items: center;
  background-size: cover;
  /* 背景图片覆盖整个容器 */
  background-position: center;
  /* 背景图片居中显示 */
  border-radius: 4px;
  /* 容器圆角 */
  overflow: hidden;
  /* 防止内容溢出 */
  text-decoration: none;
  /* 去除链接下划线 */
  color: inherit;
  /* 继承文本颜色 */
}

.content-wrapper {
  border: 0.6px solid rgb(173, 172, 170);
  width: 100%;
  height: 100%;
  /* 使内容区域高度与容器一致 */
  text-align: center;
  display: flex;
  flex-direction: column;
  justify-content: center;
  /* 垂直居中内容 */
}

.image {
  width: 100%;
  /* 图片宽度 */
  height: 35vh;
  /* 图片高度 */
  object-fit: cover;
  /* 确保图片覆盖整个区域 */
}

.text {
  text-align: center;
  margin-top: 5px;
}
</style>