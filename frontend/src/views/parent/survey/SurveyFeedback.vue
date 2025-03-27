<template>
    <div>
        <h1 class="title">反馈信息</h1>
        <div class="form">
            <div class="form-item">
                <p style="width: 94px;">问卷名称：</p>
                <el-input v-model="surveyFeedback.name" style="height: auto; width: 240px;"
                    placeholder="问卷名称"></el-input>
                <el-button
                    style="border-radius: 15px; width: 30%; background-image: radial-gradient(circle,rgba(255,255,255,0) 0%,rgba(255, 165, 0,0.5) 80%); color: #9a6201;"
                    @click="searchSurveyFeedbacks">搜索</el-button>
                <el-button style="border-radius: 15px; width: 30%;background-color: #ffffff;"
                    @click="insert">新增</el-button>
            </div>
        </div>
        <el-table @row-click="handleRowClick" :data="surveyFeedbacks" border style="width: 100%"
            :header-cell-style="headerCellStyle">
            <el-table-column label="序号" prop="id">
            </el-table-column>
            <el-table-column label="问卷主题" prop="name">
            </el-table-column>
            <el-table-column label="反馈时间" prop="time">
            </el-table-column>
            <el-table-column label="家长账号" prop="parentUsername">
            </el-table-column>
            <el-table-column label="家长姓名" prop="parentName">
            </el-table-column>
            <el-table-column label="学号" prop="studentId">
            </el-table-column>
            <el-table-column label="学生姓名" prop="studentName">
            </el-table-column>

        </el-table>
    </div>
</template>

<script>
import { queryOne } from '@/api/example';

export default {
    name: "SurveyFeedback",
    data() {
        return {
            surveyFeedbacks: [],
            surveyFeedback: {},
            search: '',
            student: {},
        }
    },
    created() {
        this.fetchFormData();
    },
    computed: {
        headerCellStyle() {
            return {
                backgroundImage: 'linear-gradient(to bottom, #fe7d1d, #ffcc00)', // 从上到下的渐变色  
                height: '30px',
                color: '#000000',
                border: '1px solid tan',
                textAlign: 'center',
            };
        },

    },
    methods: {
        fetchFormData() {
            this.student = JSON.parse(localStorage.getItem('xm-student') || '{}')
            this.surveyFeedback.studentId = this.student.id;
            queryOne(this.surveyFeedback, "surveyFeedback").then(res => {
                this.surveyFeedbacks = res.data;
                console.log(res.data)
            })
        },
        searchSurveyFeedbacks() {
            this.surveyFeedback.studentId = this.student.id;
            queryOne(this.surveyFeedback, "surveyFeedback").then(res => {
                this.surveyFeedbacks = res.data;
                console.log(res.data);
            })
        },
        insert() {
            this.$router.push('/parent/leaveRequestPage')
        },
        handleRowClick(row, column, event) {
            this.$router.push({ name: 'SurveyFeedbackDetail', params: { "surveyfeedbackId": row.id } });
        },
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
    /* 将 .form 设置为 flex 容器 */
    justify-content: center;
    /* 水平居中 */
    align-items: center;
    /* 垂直居中 */
    border: 1px solid rgb(123, 122, 122);
    box-shadow: 0 2px 4px rgba(159, 159, 159, 0.12), 0 0 6px rgba(0, 0, 0, .04);
}

.form-item {
    display: flex;
    width: 600px;
    align-items: center;
}

/* 定义胶囊样式 */
.status-capsule {
    display: inline-block;
    padding: 5px 10px;
    border-radius: 15px;
    font-size: 12px;
}

.pending {
    background-color: #ffcccc;
    /* 待审核的背景色 */
    color: #ff0000;
    /* 待审核的字体颜色 */
}

.approved {
    background-color: #ccffcc;
    /* 已通过的背景色 */
    color: #008000;
    /* 已通过的字体颜色 */
}
</style>