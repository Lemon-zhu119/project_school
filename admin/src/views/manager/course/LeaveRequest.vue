<template>
  <div>
    <div class="search">
      <el-form :data="leaveRequest" class="form-search">
        <el-form-item label="科目："><el-input placeholder="请输入科目查询" style="width: 200px"
            v-model="leaveRequest.course"></el-input></el-form-item>
        <el-form-item label="原因："><el-input placeholder="请输入原因名称查询" style="width: 200px"
            v-model="leaveRequest.reason"></el-input></el-form-item>
        <el-button type="info" plain style="margin-left: 10px" @click="find">查询</el-button>
        <el-button type="warning" plain style="margin-left: 10px" @click="reset">重置</el-button>
      </el-form>

    </div>

    <div class="operation">
      <el-button type="primary" plain @click="handleAdd">新增</el-button>
      <el-button type="danger" plain @click="delBatch">批量删除</el-button>
    </div>

    <div class="table">
      <el-table :data="leaveRequests" strip @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="id" label="序号" width="70" align="center" sortable></el-table-column>
        <el-table-column prop="course" label="科目"></el-table-column>
        <el-table-column prop="reason" label="原因">
        </el-table-column>
        <el-table-column prop="teacherId" label="教师编号"></el-table-column>
        <el-table-column prop="teacher" label="教师名称"></el-table-column>
        <el-table-column prop="parentUsername" label="家长账号"></el-table-column>
        <el-table-column prop="parentName" label="家长姓名"></el-table-column>
        <el-table-column prop="studentName" label="学生姓名"></el-table-column>
        <el-table-column prop="reviewResponse" label="审核回复"></el-table-column>
        <el-table-column prop="status" label="当前状态" :formatter="statusFormatter"></el-table-column>
        <el-table-column label="操作" align="center" width="80" fixed="right">
          <template v-slot="scope">
            <el-button v-if="scope.row.status === 0" size="mini" type="primary" plain
              @click="handleEdit(scope.row)">去审核</el-button>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="80" fixed="right">
          <template v-slot="scope">
            <el-button size="mini" type="danger" plain @click="del(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
        <!-- ...其他表单项... -->
      </el-table>
    </div>

    <el-dialog title="修改或添加信息" :visible.sync="dialogFormVisible">
      <el-form :model="form" label-width="100px">
        <el-form-item label="科目" prop="course">
          <el-input :disabled="true" v-model="form.course"></el-input>
        </el-form-item>
        <el-form-item label="家长姓名" prop="parentName">
          <el-input :disabled="true" v-model="form.parentName"></el-input>
        </el-form-item>
        <el-form-item label="学生姓名" prop="studentName">
          <el-input :disabled="true" v-model="form.studentName"></el-input>
        </el-form-item>
        <el-form-item label="请假原因" prop="reason">
          <el-input v-model="form.reason"></el-input>
        </el-form-item>
        <el-form-item label="回复" prop="content">
          <el-input type="textarea" placeholder="请输入内容" v-model="form.reviewResponse"></el-input>
        </el-form-item>
        <!-- ...其他表单项... -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="success" @click="approve">通 过</el-button>
        <el-button type="danger" @click="reject">驳 回</el-button>
      </div>
    </el-dialog>



    <!-- 分页器 -->
    <div class="pagination">
      <el-pagination background :current-page="pageNum" :page-sizes="[5, 10, 20]" :page-size="pageSize"
        layout="total, prev, pager, next" :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import { getAll, updateOne, delOne, queryOne, addOne } from '@/api/example';
export default {
  name: "leaveRequest",
  data() {
    return {
      query: {},
      leaveRequest: {
      },
      tableData: [],  // 所有的数数据
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      name: null,
      students: [],
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      ids: [],
      leaveRequests: [],
      dialogFormVisible: false,
      materialList: [],
      stepVisible: false,
      stepList: [],
      form: {
        id: 0,
      },
      teachers: []
    }
  },
  created() {
    this.fetchFormData();
  },
  methods: {
    fetchFormData() {
      getAll("leaveRequest").then(res => {
        this.leaveRequests = res.data
        console.log(this.leaveRequests)
      })
      getAll("teacher").then(res => (
        this.teachers = res.data.map(teacher => ({
          value: teacher.id,
          label: teacher.name
        }))
      ))
    },
    approve() {
      this.form.status = 1;
      this.save()
    },
    reject() {
      this.form.status = 2;
      this.save()
    },
    handleTeacherChange(value) {
      this.form.teacherId = value;
    },
    handleEdit(row) {   // 编辑数据
      this.form = JSON.parse(JSON.stringify(row)) // 给form对象赋值  注意要深拷贝数据
      this.dialogFormVisible = true   // 打开弹窗
    },
    save() {
      if (!(this.form.id === 0)) {
        updateOne(this.form, 'leaveRequest').then(res => {
          console.log(res.data)
          if (res.code === 20000) {
            this.$message.success("更新成功")
          } else {
            this.$message.error("更新失败")
          }
          this.fetchFormData();
        })
      } else {
        addOne(this.form, 'leaveRequest').then(res => {
          if (res.code === 20000) {
            this.$message.success("添加成功")
          } else {
            this.$message.error("添加失败")
          }
          this.dialogFormVisible = false;
          this.fetchFormData();
        })
      }
    },
    handleAdd() {
      this.form = {
        id: 0
      };
      this.dialogFormVisible = true
    },
    viewMaterial(material) {
      this.materialList = JSON.parse(material || '[]')
      this.materialVisible = true
    },
    viewSteps(steps) {
      this.stepList = JSON.parse(steps || '[]')
      this.stepVisible = true
    },
    handleSelectionChange(rows) {   // 当前选中的所有的行数据
      this.ids = rows.map(v => v.id)   //  [1,2]
    },
    del(id) {
      // 单个删除
      this.$confirm('您确定删除吗？', '确认删除', { type: "warning" }).then(response => {
        console.log(id)
        delOne(id, 'leaveRequest').then(res => {
          if (res.code === 20000) {   // 表示操作成功
            this.$message.success('操作成功')
            this.fetchFormData();
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    delBatch() {   // 批量删除
      if (!this.ids.length) {
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('您确定批量删除这些数据吗？', '确认删除', { type: "warning" }).then(response => {
        console.log(this.ids)
        this.$request.delete('/leaveRequest/deleteBatch', { data: this.ids }).then(res => {
          if (res.code === 20000) {   // 表示操作成功
            this.$message.success('操作成功')
            this.fetchFormData();
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    reset() {
      this.leaveRequest.name = null
      this.leaveRequest.leaveRequestTeacherName = null
      this.fetchFormData()
    },
    find() {
      console.log(this.leaveRequest)
      queryOne(this.leaveRequest, 'leaveRequest').then(res => {
        this.leaveRequests = res.data;
      })
    },
    statusFormatter(row, column, cellValue, index) {
      const statusMap = {
        0: '待审核',
        1: '已通过',
        2: '已驳回'
      };
      return statusMap[cellValue] || '未知状态';
    }
  }
}
</script>
<style scoped>
.form-search {
  display: flex;
  flex-wrap: wrap;
}

.el-form-item--small {
  width: 40%;
}
</style>