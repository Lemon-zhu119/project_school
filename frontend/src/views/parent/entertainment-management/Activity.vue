<template>
    <div class="content">
        <div class="title">
            <h1>活动信息推荐</h1>
        </div>
        <div class="container">
            <div v-for="activity in activities" :key="activity.id" class="item">
                <router-link :to="{ name: 'Activity-item' ,params: { itemId: activity.id  }}" class="clickable-div">
                    <div class="content-wrapper">
                        <img :src="activity.picture" alt="Description of image" class="image">
                        <div class="text">{{ activity.name }}</div>
                        <div class="text">
                            <p style="color: brown">{{ activity.rating }}</p>
                        </div>
                    </div>
                </router-link>
            </div>
        </div>

    </div>

</template>

<script>
import { getAll } from '@/api/example';
export default {
    name: 'Activity',
    data() {
        return {
            activities:[],
            activity:{}
        };
    },
    created(){
        this.fetchFormData();
    },
    methods: {
        fetchFormData() {
            getAll("activity").then(res => {
                this.activities = res.data;
                console.log(res.data)
            })
        },
    }
};  
</script>

<style>
.title {
    width: 100%;
    background-image: radial-gradient(circle at right, rgba(255, 255, 255, 0) 0%, rgba(255, 165, 0, 0.5) 100%);
    color: #9a6201;
}

.container {
    height: 100vh;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
}

.item {
    position: relative;
    height: 45vh;
    width: calc(33.333% - 20px);
    margin: 10px;
    background-color: white;
    box-sizing: border-box;
    border: 1px solid rgb(157, 155, 155);
}

.content-wrapper {
    position: relative;
}

.image {
    height: 100%;
    width: 100%;
    height: 35vh;
}

.text {
    font-size: 17px;
    bottom: 0;
    width: 100%;
    text-align: center;
    background-color: white;
    color: black;
    padding: 8px;
}
</style>