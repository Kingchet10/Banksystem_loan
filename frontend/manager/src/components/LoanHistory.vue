<template>
    <div class="page-container">
        <el-form ref="permissionForm" :model="permissionData" :rules="rules" label-position="top" @submit.native.prevent="submitForm">
            <el-form-item label="账户名" prop="username">
                <el-input v-model="permissionData.username" placeholder="请输入账户名" />
            </el-form-item>
            <el-form-item label="权限" prop="newPermission">
                <el-select v-model="permissionData.newPermission" placeholder="请选择权限">
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
                permissionData: {
                    username: '',
                    newPermission: ''
                },
                rules: {
                    username: [
                        { required: true, message: '请输入账户名', trigger: 'blur' }
                    ],
                    newPermission: [
                        { required: true, message: '请选择权限', trigger: 'change' }
                    ]
                }
            };
        },
        methods: {
            async submitForm() {
                this.$refs.permissionForm.validate(async (valid) => {
                    if (valid) {
                        try {
                            const response = await this.$axios.put('/update-officer-permission', null, { params: this.permissionData });
                            this.$message.success('权限修改成功！');
                        } catch (error) {
                            console.error('修改权限时发生错误:', error);
                            if (error.response && error.response.status === 404) {
                                this.$message.error('未找到该账户名。');
                            } else {
                                this.$message.error('修改失败。');
                            }
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
