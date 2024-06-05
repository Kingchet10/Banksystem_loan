<template>
    <div class="login-page">
      <el-form :model="loginForm" ref="loginForm" class="login-form">
        <el-form-item prop="username" :rules="[{ required: true, message: '请输入用户名', trigger: 'blur' }]">
          <el-input v-model="loginForm.username" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password" :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]">
          <el-input type="password" v-model="loginForm.password" placeholder="密码"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </template>
  
  <script>
  import { ref } from 'vue';
  import { useRouter } from 'vue-router';
  import { ElForm, ElFormItem, ElInput, ElButton } from 'element-plus';
  
  export default {
    components: {
      ElForm, ElFormItem, ElInput, ElButton
    },
    setup() {
      const loginForm = ref({
        username: '',
        password: ''
      });
  
      const router = useRouter();
  
      const onSubmit = () => {
        const loginFormRef = ref('loginForm');
        loginFormRef.value.validate((valid) => {
          if (valid) {
            console.log('登录成功');
            // 模拟登录成功逻辑
            router.push('/apply'); // 跳转到 Book 页面
          } else {
            console.log('登录失败');
            return false;
          }
        });
      };
  
      return {
        loginForm,
        onSubmit
      };
    }
  };
  </script>
  
  <style scoped>
  .login-page {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #f5f5f5;
  }
  
  .login-form {
    width: 300px;
    padding: 20px;
    border-radius: 8px;
    background-color: #fff;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  }
  </style>
  