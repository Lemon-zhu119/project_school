<template>
    <div class="container">
        <div style="width: 100vh; padding: 30px; background-color: white; border-radius: 5px;">
            <el-form :model="form" :rules="rules" ref="formRef">
                <div class="form-row">
                    <div class="form-item">
                        <p>密码：</p>
                        <el-form-item prop="password">
                            <el-input style="width: 240px;" placeholder="请输入密码" show-password
                                v-model="form.password"></el-input>
                        </el-form-item>
                    </div>
                    <div class="form-item">
                        <p>新密码：</p>
                        <el-form-item prop="newpassword">
                            <el-input style="width: 240px;" placeholder="请输入新密码" show-password
                                v-model="form.newPassword"></el-input>
                        </el-form-item>
                    </div>
                </div>
                <div class="form-item">
                    <p>确认密码：</p>
                    <el-form-item prop="confirmPass">
                        <el-input style="width: 240px;" placeholder="请输入确认密码" show-password
                            v-model="form.confirmPass"></el-input>
                    </el-form-item>
                </div>
                <el-form-item>
                    <el-button
                        style="margin-left: 80px;border-radius: 15px; width: 30%; background-color:#9db637;color: white;"
                        @click="changePassword">修改密码
                    </el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
import { ChangePassword,logout} from '@/api/user';
export default {
    name: "ChangePassword",
    data() {
        const validatePassword = (rule, confirmPass, callback) => {
            if (confirmPass === '') {
                callback(new Error('请确认密码'))
            } else if (confirmPass !== this.form.password) {
                callback(new Error('两次输入的密码不一致'))
            } else {
                callback()
            }
        }
        return {
            form: {
            },
            rules: {
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                ],
                confirmPass: [
                    { validator: validatePassword, trigger: 'blur' }
                ]

            },
        }

    },
    created() {
        this.fetchFormData();
    },
    methods: {
        fetchFormData() {
        },
        changePassword() {
            const user = JSON.parse(localStorage.getItem("xm-parent") || '{}');
            this.form.username = user.username;
            ChangePassword(this.form).then(res => {
                if (res.code === 20000) {
                    this.$message.success("修改密码成功")
                    logout(user.username);
                    localStorage.removeItem("xm-parent");
                    this.$router.push("/login");
                } else {
                    this.$message.error(res.msg)
                }
            })
        }

    }
}
</script>

<style scoped>
.container {
    flex: 1;
    overflow: hidden;
    background-size: 100%;
    display: flex;
    /* align-items: center; */
    justify-content: center;
    color: #666;
}

p {
    width: 80px;
    margin-top: -20px;
    text-align: right;
}

.form-row {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.form-item {
    display: flex;
    min-width: 250px;
    align-items: center;
}
</style>