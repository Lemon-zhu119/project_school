<template>
  <div>
    <div class="search">
      <el-form :data="admin" class="form-search">
        <el-form-item label="姓名：">
          <el-input placeholder="请输入姓名查询" style="width: 200px" v-model="admin.name">
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
      <el-table :data="admins" strip @selection-change="handleSelectionChange">
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
        <el-table-column prop="email" label="邮箱"></el-table-column>
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
          <el-form-item label="邮箱">
            <el-input v-model="form.email" autocomplete="off" placeholder="请输入邮箱"></el-input>
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
import { getAll, updateOne, delOne, queryOne ,addOne} from '@/api/example';
export default {
  name: "Admins",
  data() {
    return {
      admin: {
      },
      admins: [],
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
      getAll("admin").then(res => {
        this.admins = res.data;
        console.log(this.admins)
      })
    },
    handleEdit(row) {   // 编辑数据
      this.form = JSON.parse(JSON.stringify(row))  // 给form对象赋值  注意要深拷贝数据
      this.dialogFormVisible = true   // 打开弹窗
    },
    save() {
      if (!(this.form.id === 0)) {
        updateOne(this.form, 'admin').then(res => {
          console.log(res.data)
          if (res.code === 20000) {
            this.$message.success("更新成功")
          } else {
            this.$message.error("更新失败")
          }
          this.fetchFormData();
        })
      } else {
        addOne(this.form, 'admin').then(res => {
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
      this.admin.id = id;  // 单个删除
      this.$confirm('您确定删除吗？', '确认删除', { type: "warning" }).then(response => {
        console.log(this.admin.id)
        delOne(this.admin.id, 'admin').then(res => {
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
        this.$request.delete('/admin/deleteBatch', { data: this.ids }).then(res => {
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
      this.admin.name = null
      this.fetchFormData()
    },
    find() {
      console.log(this.admin)
      queryOne(this.admin, 'admin').then(res => {
        this.admins = res.data;
      })
    },
    statusFormatter(row, column, cellValue, index) {
      return cellValue === 1 ? '在线' : '离线';
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