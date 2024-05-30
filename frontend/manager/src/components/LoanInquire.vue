<template>
  <div class="page-container">
    <el-form ref="passwordForm" :model="passwordData" label-position="top" @submit.native.prevent="submitForm">
      <el-form-item label="账户名" prop="username">
        <el-input v-model="passwordData.username" placeholder="请输入账户名" />
      </el-form-item>
      <el-form-item label="新密码" prop="newPassword">
        <el-input v-model="passwordData.newPassword" type="password" placeholder="请输入新密码" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { ElForm, ElFormItem, ElInput, ElButton, ElMessage } from 'element-plus';

export default {
  components: {
    ElForm, ElFormItem, ElInput, ElButton, ElMessage
  },
  data() {
    return {
      passwordData: {
        username: '',
        newPassword: ''
      }
    };
  },
  methods: {
    async submitForm() {
      try {
          const response = await this.$axios.put('/update-officer-password', null, { params: this.passwordData, });
        this.$message.success('密码修改成功！');
      } catch (error) {
        console.error('修改密码时发生错误:', error);
        this.$message.error('修改失败。');
      }
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
