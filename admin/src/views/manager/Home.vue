<template>
  <div class="main-content">
    <div class="card" style="padding: 15px; margin-bottom: 10px">
      您好，{{ user?.name }}！欢迎使用本系统
      <el-button type="primary" style="margin-left: 10px" @click="$router.push('/student')">前往管理</el-button>
    </div>

    <!-- 第一行：折线图（课程数和请假数）和 饼状图（活动数和活动报名数） -->
    <div style="display: flex; justify-content: space-between; margin-bottom: 10px">
      <!-- 折线图 -->
      <div style="width: 49%; height: 300px">
        <div ref="lineChart" style="width: 100%; height: 100%;"></div>
      </div>
      <!-- 饼状图 -->
      <div style="width: 49%; height: 300px">
        <div ref="pieChart" style="width: 100%; height: 100%;"></div>
      </div>
    </div>

    <!-- 第二行：柱状图（学生数和家长数）和 问卷发布公告 -->
    <div style="display: flex; justify-content: space-between">
      <!-- 柱状图 -->
      <div style="width: 49%; height: 300px">
        <div ref="barChart" style="width: 100%; height: 100%;"></div>
      </div>
      <!-- 问卷发布公告 -->
      <div style="width: 49%; height: 300px">
        <div ref="surveyChart" style="width: 100%; height: 100%; padding: 20px; box-sizing: border-box; overflow: auto;">
          <div style="font-size: 18px; font-weight: bold; margin-bottom: 10px;">问卷发布公告</div>
          <ul>
            <li v-for="(survey, index) in staticData.surveys" :key="index">
              {{ survey.title }} - {{ survey.status }}
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  name: 'Home',
  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      staticData: {
        courses: 5, // 课程数
        leaves: 3, // 请假数
        activities: 10, // 活动数
        activitySignups: 50, // 活动报名数
        students: 200, // 学生数
        parents: 300, // 家长数
        surveys: [
          { title: '学生满意度调查', status: '已发布' },
          { title: '家长满意度调查', status: '已发布' },
          { title: '教学质量调查', status: '未发布' },
          { title: '校园环境调查', status: '已发布' }
        ]
      }
    }
  },
  mounted() {
    this.initLineChart();
    this.initPieChart();
    this.initBarChart();
  },
  methods: {
    initLineChart() {
      const lineChart = echarts.init(this.$refs.lineChart);
      const option = {
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          data: ['课程数', '请假数']
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: [this.staticData.courses, this.staticData.leaves],
          type: 'line'
        }]
      };
      lineChart.setOption(option);
    },
    initPieChart() {
      const pieChart = echarts.init(this.$refs.pieChart);
      const option = {
        tooltip: {
          trigger: 'item'
        },
        series: [
          {
            name: '活动统计',
            type: 'pie',
            radius: '50%',
            data: [
              { value: this.staticData.activities, name: '活动数' },
              { value: this.staticData.activitySignups, name: '活动报名数' }
            ]
          }
        ]
      };
      pieChart.setOption(option);
    },
    initBarChart() {
      const barChart = echarts.init(this.$refs.barChart);
      const option = {
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          data: ['学生数', '家长数']
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: [this.staticData.students, this.staticData.parents],
          type: 'bar'
        }]
      };
      barChart.setOption(option);
    }
  }
}
</script>