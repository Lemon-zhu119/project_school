<template>
  <div>
    <div class="search">
      <el-input placeholder="请输入名称查询" style="width: 200px" v-model="student.name"></el-input>
      <el-button type="info" plain style="margin-left: 10px" @click="find">查询</el-button>
      <el-button type="warning" plain style="margin-left: 10px" @click="reset">重置</el-button>
    </div>

    <div class="operation">
      <el-button type="primary" plain @click="handleAdd">新增</el-button>
      <el-button type="danger" plain @click="delBatch">批量删除</el-button>
    </div>

    <div class="table">
      <el-table :data="students" strip @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="id" label="序号" width="70" align="center" sortable></el-table-column>
        <el-table-column prop="name" label="名称"></el-table-column>
        <el-table-column label="封面">
          <template v-slot="scope">
            <el-image :src="scope.row.picture" style="width: 50px; height: 30px; border-radius: 5px"></el-image>
          </template>
        </el-table-column>
        <el-table-column prop="gender" label="性别"></el-table-column>
        <el-table-column prop="pusername" label="家长账号"></el-table-column>
        <el-table-column prop="classes" label="班级"></el-table-column>
        <el-table-column label="操作" align="center" width="80" fixed="right">
          <template v-slot="scope">
            <el-button size="mini" type="primary" plain @click="handleEdit(scope.row)">修改</el-button>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="80" fixed="right">
          <template v-slot="scope">
            <el-button size="mini" type="danger" plain @click="del(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>


      <el-dialog title="修改信息" :visible.sync="dialogFormVisible">
        <el-form :model="form" label-width="100px">
          <el-form-item label="姓名">
            <el-input v-model="form.name" autocomplete="off" placeholder="请输入姓名"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-select v-model="form.gender" placeholder="请选择性别">
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="班级">
            <el-select v-model="form.classes" placeholder="请选择班级">
              <el-option label="1班" value="1班"></el-option>
              <el-option label="2班" value="2班"></el-option>
              <el-option label="3班" value="3班"></el-option>
              <el-option label="4班" value="4班"></el-option>
            </el-select>
          </el-form-item>
          <!-- ...其他表单项... -->
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="save()">确 定</el-button>
        </div>
      </el-dialog>

      <!-- 分页器 -->



      <div class="pagination">
        <el-pagination background :current-page="pageNum" :page-sizes="[5, 10, 20]" :page-size="pageSize"
          layout="total, prev, pager, next" :total="total">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import { getAll, updateOne, delOne, queryOne,addOne } from '@/api/example';
export default {
  name: "Students",
  data() {
    return {
      student: {
        id: null,
        name: null,
      },
      students: [],
      tableData: [],  // 所有的数数据
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      name: null,
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      ids: [],
      dialogFormVisible: false,
      materialList: [],
      stepVisible: false,
      stepList: [],
      form: {},
    }
  },
  created() {
    this.fetchFormData();
  },
  methods: {
    fetchFormData() {
      getAll("student").then(res => {
        this.students = res.data;
        console.log(this.students)
      })
    },
    handleEdit(row) {   // 编辑数据
      this.form = JSON.parse(JSON.stringify(row))  // 给form对象赋值  注意要深拷贝数据
      this.dialogFormVisible = true   // 打开弹窗
    },
    save() {
      if (!(this.form.id === 0)) {
        updateOne(this.form, 'student').then(res => {
          console.log(res.data)
          if (res.code === 20000) {
            this.$message.success("更新成功")   
            this.fetchFormData()
          } else {
            this.$message.error("更新失败")
          }
          this.fetchFormData();
        })
      } else {
        addOne(this.form, 'student').then(res => {
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
      this.student.id = id;  // 单个删除
      this.$confirm('您确定删除吗？', '确认删除', { type: "warning" }).then(response => {
        console.log(this.student.id)
        delOne(this.student.id, 'student').then(res => {
          if (res.code === 20000) {   // 表示操作成功
            this.$message.success('操作成功')
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
          this.fetchFormData()
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
        console.log(this.id)
        this.$request.delete('/student/deleteBatch', { data: this.ids }).then(res => {
          if (res.code === 20000) {   // 表示操作成功
            this.$message.success('操作成功')
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
          this.fetchFormData()
        })
      }).catch(() => {
      })
    },
    reset() {
      this.student.name = null
      this.fetchFormData()
    },
    find() {
      console.log(this.student)
      queryOne(this.student, 'student').then(res => {
        this.students = res.data;
      })
    }
  }
}
</script>