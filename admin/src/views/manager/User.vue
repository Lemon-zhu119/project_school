<template>
  <div>
    <div class="search">
      <el-form :data="parent" class="form-search">
        <el-form-item label="姓名：">
          <el-input placeholder="请输入姓名查询" style="width: 200px" v-model="parent.name">
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="info" plain style="margin-left: 10px" @click="find">查询</el-button>
          <el-button type="warning" plain style="margin-left: 10px" @click="reset">重置</el-button>
        </el-form-item>

      </el-form>


    </div>
    <div class="operation">
      <el-button type="primary" plain @click="handleAdd">新增</el-button>
      <el-button type="danger" plain @click="delBatch">批量删除</el-button>
    </div>
    <div class="table">
      <el-table :data="parents" strip @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="id" label="序号" width="70" align="center" sortable></el-table-column>
        <el-table-column prop="username" label="账号"></el-table-column>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column label="封面">
          <template v-slot="scope">
            <el-image :src="scope.row.picture" style="width: 50px; height: 30px; border-radius: 5px"></el-image>
          </template>
        </el-table-column>
        <el-table-column prop="number" label="电话号码"></el-table-column>
        <el-table-column prop="sid" label="学生学号"></el-table-column>
        <el-table-column prop="online" label="当前状态" :formatter="statusFormatter"></el-table-column>
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
          <el-form-item label="账号：">
            <el-input v-model="form.username" autocomplete="off" placeholder="请输入账号"></el-input>
          </el-form-item>
          <el-form-item label="姓名：">
            <el-input v-model="form.name" autocomplete="off" placeholder="请输入姓名"></el-input>
          </el-form-item>
          <el-form-item label="电话号码">
            <el-input v-model="form.number" autocomplete="off" placeholder="请输入电话号码"></el-input>
          </el-form-item>
          <!-- ...其他表单项... -->
          <el-form-item style="width: 240px;" prop="sid" label="学生学号">
            <el-select v-model="form.sid" placeholder="请选择学号" @change="onSidChange" style="width: 100%">
              <el-option v-for="student in students" :key="student.id" :label="student.id"
                :value="student.id">
              </el-option>
            </el-select>
          </el-form-item>
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
import { getAll, updateOne, delOne, queryOne ,addOne} from '@/api/example';
export default {
  name: "Parents",
  data() {
    return {
      parent: {
      },
      parents: [],
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
      students:[],
    }
  },
  created() {
    this.fetchFormData();
  },
  methods: {
    fetchFormData() {
      getAll("parent").then(res => {
        this.parents = res.data;
        console.log(this.parents)
      })
      getAll("student").then(res => {
        this.students = res.data;
        this.onSidChange(this.students.sid);
      })
    },
    handleEdit(row) {   // 编辑数据
      this.form = JSON.parse(JSON.stringify(row))  // 给form对象赋值  注意要深拷贝数据
      this.dialogFormVisible = true   // 打开弹窗
    },
    save() {
      if (!(this.form.id === 0)) {
        updateOne(this.form, 'parent').then(res => {
          console.log(res.data)
          if (res.code === 20000) {
            this.$message.success("更新成功")
          } else {
            this.$message.error("更新失败")
          }
          this.fetchFormData();
        })
      } else {
        addOne(this.form, 'parent').then(res => {
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
      this.parent.id = id;  // 单个删除
      this.$confirm('您确定删除吗？', '确认删除', { type: "warning" }).then(response => {
        console.log(this.parent.id)
        delOne(this.parent.id, 'parent').then(res => {
          if (res.code === 20000) {   // 表示操作成功
            this.$message.success('操作成功')
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
          this.fetchFormData();
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
        this.$request.delete('/parent/deleteBatch', { data: this.ids }).then(res => {
          if (res.code === 20000) {   // 表示操作成功
            this.$message.success('操作成功')
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
          this.fetchFormData();
        })
      }).catch(() => {
      })
    },
    reset() {
      this.parent.name = null
      this.fetchFormData()
    },
    find() {
      console.log(this.parent)
      queryOne(this.parent, 'parent').then(res => {
        this.parents = res.data;
      })
    },
    statusFormatter(row, column, cellValue, index) {
      return cellValue === 1 ? '在线' : '离线';
    },
    onSidChange(value) {
      this.selectedStudent = this.students.find(student => student.id === value);
    },
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