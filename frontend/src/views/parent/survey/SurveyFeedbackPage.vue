<template>
    <div style="display: block;">
        <h1 class="title">首页/反馈意见</h1>
        <hr>
        <div style="margin:0 auto; width: 100%; padding: 30px; background-color: white; border-radius: 5px; ">
            <el-form :model="form" ref="formRef">
                <div class="form-row">
                    <div class="form-item">
                        <p>问卷名称：</p>
                        <el-form-item style="flex: 1;" prop="name">
                            <el-input style="width: 100%; margin-top: 20px;" placeholder="请输入问卷名称"
                                v-model="form.name"></el-input>
                        </el-form-item>
                    </div>
                    <div class="form-item">
                        <p>反馈时间：</p>
                        <el-form-item style="flex: 1;" prop="time">
                            <el-input type="date" style="width: 100%; margin-top: 20px;" placeholder="请输入反馈时间"
                                v-model="form.feedbacktime"></el-input>
                        </el-form-item>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-item">
                        <p>家长账号：</p>
                        <el-form-item  style="flex: 1;" prop="location">
                            <el-input :disabled="true" style="width: 100%; margin-top: 20px;" placeholder="请输入家长账号"
                                v-model="parent.username"></el-input>
                        </el-form-item>
                    </div>
                    <div class="form-item">
                        <p>家长姓名：</p>
                        <el-form-item style="flex: 1;" prop="number">
                            <el-input :disabled="true" style="width: 100%; margin-top: 20px;" placeholder="请输入家长姓名"
                                v-model="parent.name"></el-input>
                        </el-form-item>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-item">
                        <p>学号：</p>
                        <el-form-item style="flex: 1;" prop="sId">
                            <el-input :disabled="true" style="width: 100%;" placeholder="请输入学号" v-model="Student.id"></el-input>
                        </el-form-item>
                    </div>
                    <div class="form-item">
                        <p>学生姓名：</p>
                        <el-form-item style="flex: 1;" prop="sname">
                            <el-input  :disabled="true" style="width: 100%; margin-top: 20px;" placeholder="请输入学生姓名"
                                v-model="Student.name" readonly></el-input>
                        </el-form-item>
                    </div>
                </div>
                <div class="form-row-content">
                    <p>反馈内容：</p>
                    <el-form-item style="flex: 1;"   prop="feedbackContent">
                        <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4 }" placeholder="请输入内容"
                            v-model="form.feedbackContent">
                        </el-input>
                    </el-form-item>
                </div>
                <el-form-item>
                    <el-button
                        style="margin-left: 80px;border-radius: 15px; width: 30%; background-color:#9db637;color: white;"
                        @click="cansel">取消
                    </el-button>
                    <el-button
                        style="margin-left: 20px;border-radius: 15px; width: 30%; background-color:#ff9100;color: white;"
                        @click="insert">保存
                    </el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>

</template>

<script>
import { addOne, findOne } from '@/api/example';
export default {
    name: "SurveyFeedback",
    data() {
        return {
            form: {
                id: null,
            },
            tableData: [],
            search: '',
            Student: {},
            Parent: "",
            courses: [],
            survey: {}
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
            this.parent = JSON.parse(localStorage.getItem('xm-parent') || '{}')
            this.Student = JSON.parse(localStorage.getItem('xm-student') || '{}')
            this.survey.id = this.$route.params.surveyId;
            findOne(this.survey.id, "survey").then(res => {
                this.form = res.data;
            })

        },
        cansel() {
            this.$router.push('/parent/surveydatil' + this.$route.params.SurveyId)
        },
        insert() {
            this.form.parentId = this.parent.id;
            this.form.studentId = this.Student.id;
            this.form.surveyId=this.survey.id
            console.log(this.form.surveyId);
            addOne(this.form, "surveyFeedback").then(res => {
                if (res.code === 20000) {
                    this.$message.success("成功")
                } else {
                    this.$message.error("失败")
                }
            })
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