<template>
    <div class="surveyfeedback-item">
        <div class="title">
            <h1>首页 / 反馈意见</h1>
            <hr>
        </div>
        <div class="surveyfeedback-item-content">
            <div class="left-side">
            </div>
            <div class="right-side">
                <el-form label-width="120px">
                    <el-form-item label="反馈内容">
                        {{ surveyfeedback.feedbackContent }}
                    </el-form-item>
                    <el-form-item label="反馈时间">
                        {{ surveyfeedback.time }}
                    </el-form-item>
                    <el-form-item label="家长账号">
                        {{ parent.username }}
                    </el-form-item>
                    <el-form-item label="家长姓名">
                        {{ parent.name }}
                    </el-form-item>
                    <el-form-item label="学号">
                        {{ student.id }}
                    </el-form-item>
                    <el-form-item label="学生姓名">
                        {{ student.name }}
                    </el-form-item>
                    <el-form-item label="审核状态">
                        <template>
                            <span
                                :class="['status-capsule', { 'pending': student.status === 0, 'approved': student.status === 1, 'rejected': student.status === 2 }]">
                                {{ student.status === 0 ? '待审核' : (student.status === 1 ? '已通过' : '已驳回') }}
                            </span>
                        </template>
                    </el-form-item>
                </el-form>
            </div>
        </div>
        <div class="content">
            <div class="content-title">
                <h3></h3>
            </div>
            <p></p>
        </div>
    </div>
</template>

<script>
import { findOne } from '@/api/example';

export default {
    name: 'SurveyfeedbackDetails',
    data() {
        return {
            surveyfeedback: {
                id: '',
                content: '',
                time: ''
            },
            parent: {},
            student: {}
        }
    },
    created() {
        this.fetchSurveyfeedbackData();
    },
    methods: {
        fetchSurveyfeedbackData() {
            this.parent = JSON.parse(localStorage.getItem('xm-parent') || '{}')
            this.student = JSON.parse(localStorage.getItem('xm-student') || '{}')
            this.surveyfeedback.id = this.$route.params.surveyfeedbackId;
            console.log(this.surveyfeedback.id);
            findOne(this.surveyfeedback.id, "surveyFeedback").then(res => {
                this.surveyfeedback = res.data;
                console.log(this.surveyfeedback)
            })
        }
    }
};
</script>

<style scoped>
.title {
    font-size: smaller;
}

.surveyfeedback-item-content {
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
    width: 100%;
    background-image: radial-gradient(circle at right, rgba(255, 255, 255, 0) 0%, rgba(255, 165, 0, 0.5) 100%);
    color: #9a6201;
    border-radius: 5%;
    height: 50px;
}

.content-title h3 {
    font-size: 30px;
}
</style>