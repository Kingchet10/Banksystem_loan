<template>
    <div class="change-password-page">
      <el-form :model="passwordForm" label-width="120px" class="password-form">
        <el-form-item label="当前密码">
          <el-input v-model="passwordForm.currentPassword" type="password" placeholder="请输入当前密码"></el-input>
        </el-form-item>
        <el-form-item label="新密码">
          <el-input v-model="passwordForm.newPassword" type="password" placeholder="请输入新密码"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="changePassword">修改密码</el-button>
        </el-form-item>
      </el-form>
    </div>
  </template>
  
  <script>
  import { ref } from 'vue';
  import { ElForm, ElFormItem, ElInput, ElButton, ElMessage } from 'element-plus';
  
  export default {
    components: {
      ElForm, ElFormItem, ElInput, ElButton, ElMessage
    },
    setup() {
      const passwordForm = ref({
        currentPassword: '',
        newPassword: ''
      });
  
      const changePassword = () => {
        // 模拟验证当前密码并修改密码的逻辑
        const isCurrentPasswordCorrect = passwordForm.value.currentPassword === 'correct-password';
        if (!isCurrentPasswordCorrect) {
          ElMessage.error('当前密码错误');
          return;
        }
        
        // 模拟修改密码成功
        ElMessage.success('密码修改成功');
        passwordForm.value.currentPassword = '';
        passwordForm.value.newPassword = '';
      };
  
      return {
        passwordForm,
        changePassword
      };
    }
  };
  </script>
  
  <style scoped>
  .change-password-page {
    padding: 20px;
    background-color: #f9f9f9;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
  }
  
  .password-form {
    width: 400px;
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  }
  </style>
  