<template>
    <div>
        <h1 class="title">问卷信息</h1>
        <div class="form">
            <div class="form-item">
                <p style="width: 94px;">问卷主题：</p>
                <el-input v-model="survey.name" style="height: auto; width: 240px;" placeholder="问卷主题"></el-input>
                <el-button
                    style="border-radius: 15px; width: 30%; background-image: radial-gradient(circle,rgba(255,255,255,0) 0%,rgba(255, 165, 0,0.5) 80%); color: #9a6201;"
                    @click="searchSurveys">搜索</el-button>
            </div>
        </div>
        <el-table @row-click="handleRowClick" :data="surveys" border style="width: 100%"
            :header-cell-style="headerCellStyle">
            <el-table-column label="序号" prop="id">
            </el-table-column>
            <el-table-column label="问卷主题" prop="name">
            </el-table-column>
            <el-table-column label="封面" prop="picture">
                <template slot-scope="scope">
                    <img :src="scope.row.picture" alt="封面" style="width: 100px; height: 120px;" />
                </template>
            </el-table-column>
            <el-table-column label="调查内容" prop="fileFlag">
                <template slot-scope="scope">
                    <div v-if="scope.row.fileFlag">
                        <a :href="scope.row.fileFlag" target="_blank">下载</a>
                    </div>
                    <div v-else>
                        无
                    </div>
                </template>
            </el-table-column>
            <el-table-column label="问卷时间" prop="time">
            </el-table-column>
            
        </el-table>
    </div>
</template>

<script>
import { getAll, queryOne } from '@/api/example';

export default {
    name: "LeaveRequest",
    data() {
        return {
            surveys: [],
            survey: {},
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
            getAll("survey").then(res => {
                this.surveys = res.data;
                console.log(res.data)
            })
        },
        searchSurveys() {
            queryOne(this.survey, "survey").then(res => {
                this.surveys = res.data;
                console.log(res.data);
            })
        },
        handleRowClick(row, column, event) {
            this.$router.push({name: 'SurveyDetail', params: { "surveyId": row.id }});
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