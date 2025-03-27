<template>
    <div>
      <el-table :data="scores" style="width:100%">
        <el-table-column label="ID" prop="id"></el-table-column>
        <el-table-column label="学生用户名" prop="studentId"></el-table-column>
        <el-table-column label="课程名称" prop="courseName"></el-table-column>
        <el-table-column label="成绩" prop="score"></el-table-column>
      </el-table>
    </div>
  </template>
<script>
import { addOne, findOne } from '@/api/example';
export default {
    name: "courseScore",
    data() {
        return {
            Student: {},
            courseScore: {},
            scores:[]
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
            this.Student = JSON.parse(localStorage.getItem('xm-student') || '{}')
            findOne(this.Student.id, "courseScore").then(res => {
                this.scores = res.data;
            })

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

.form-row {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.form-item {
    display: flex;
    width: 40%;
    height: 50px;
    align-items: center;
}

.form-item p {
    float: right;
    width: 20%;
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

.form-row-content {
    display: flex;
    width: 100%;
    align-items: center;
}

</style>