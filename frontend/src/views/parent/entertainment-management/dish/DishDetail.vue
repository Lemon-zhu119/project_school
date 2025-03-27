<template>
    <div class="dish-item">
        <div class="title">
            <h1>菜谱详细信息</h1>
            <hr>
        </div>
        <div class="dish-item-content">
            <div class="left-side">
                <img :src="dish.picture" alt="菜谱图片">
            </div>
            <div class="right-side">
                <el-form label-width="120px">
                    <el-form-item label="菜谱ID">
                        {{ dish.id }}
                    </el-form-item>
                    <el-form-item label="菜谱名称">
                        {{ dish.name }}
                    </el-form-item>
                    <el-form-item label="日期">
                        {{ dish.date }}
                    </el-form-item>
                    <el-form-item label="收藏数量">
                        {{ dish.favouriteCount }}
                    </el-form-item>
                    <el-form-item label="菜谱内容">
                        {{ dish.content }}
                    </el-form-item>
                </el-form>
            </div>
        </div>
        <div class="content">
            <div class="content-title">
                <h3>菜谱介绍</h3>
            </div>
            <p>{{ dish.content }}</p>
        </div>
    </div>
</template>

<script>
import { findOne } from '@/api/example';

export default {
    name: 'DishDetail',
    data() {
        return {
            dish: {
                id: '',
                name: '',
                date: '',
                favouriteCount: 0,
                content: '',
                picture: ''
            }
        }
    },
    created() {
        this.fetchDishData();
    },
    methods: {
        fetchDishData() {
            const dishId = this.$route.params.dishId;
            console.log(dishId);
            findOne(dishId, "dish").then(res => {
                this.dish = res.data;
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

.dish-item-content {
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