<template>
    <div class="page-container">
        <el-form ref="adminForm" :model="adminData" :rules="rules" label-position="top" @submit.native.prevent="submitForm">
            <el-form-item label="审查员姓名" prop="officer_name">
                <el-input v-model="adminData.officer_name" placeholder="请输入审查员姓名" />
            </el-form-item>
            <el-form-item label="电话号码" prop="phone_number">
                <el-input v-model="adminData.phone_number" placeholder="请输入电话号码" />
            </el-form-item>
            <el-form-item label="账户名" prop="username">
                <el-input v-model="adminData.username" placeholder="请输入账户名" />
            </el-form-item>
            <el-form-item label="账户密码" prop="password">
                <el-input v-model="adminData.password" placeholder="请输入账户密码" type="password" />
            </el-form-item>
            <el-form-item label="权限" prop="permissions">
                <el-select v-model="adminData.permissions" placeholder="请选择权限">
                    <el-option label="small" value="small" />
                    <el-option label="large" value="large" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm">提交</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import { ElForm, ElFormItem, ElInput, ElSelect, ElOption, ElButton, ElMessage } from 'element-plus';

    export default {
        components: {
            ElForm, ElFormItem, ElInput, ElSelect, ElOption, ElButton, ElMessage
        },
        data() {
            return {
                adminData: {
                    officer_name: '',
                    phone_number: '',
                    username: '',
                    password: '',
                    permissions: '',
                    manager_id: 1 // 假设manager_id为固定值或从其他地方获取
                },
                rules: {
                    officer_name: [
                        { required: true, message: '请输入审查员姓名', trigger: 'blur' }
                    ],
                    phone_number: [
                        { required: true, message: '请输入电话号码', trigger: 'blur' }
                    ],
                    username: [
                        { required: true, message: '请输入账户名', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入账户密码', trigger: 'blur' }
                    ],
                    permissions: [
                        { required: true, message: '请选择权限', trigger: 'change' }
                    ]
                }
            };
        },
        methods: {
            async submitForm() {
                this.$refs.adminForm.validate(async (valid) => {
                    if (valid) {
                        try {
                            const response = await this.$axios.post('/add-officer', this.adminData);
                            this.$message.success('管理员添加成功！');
                        } catch (error) {
                            console.error('添加管理员时发生错误:', error);
                            this.$message.error('添加失败。');
                        }
                    } else {
                        this.$message.error('请完整填写表单');
                        return false;
                    }
                });
            }
        }
    };
</script>

<style scoped>
    .page-container {
        max-width: 600px;
        margin: 20px auto;
        padding: 20px;
        background-color: #fff;
        border: 1px solid #ccc;
        border-radius: 10px;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    }
</style>
