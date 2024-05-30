<template>
    <div class="login-page">
      <div class="login-container">
        <el-form :model="loginForm" ref="loginForm" class="login-form">
          <h2>登录</h2>
          <el-form-item prop="username" :rules="[{ required: true, message: '请输入用户名', trigger: 'blur' }]">
            <el-input v-model="loginForm.username" placeholder="用户名"></el-input>
          </el-form-item>
          <el-form-item prop="password" :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]">
            <el-input type="password" v-model="loginForm.password" placeholder="密码"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit" class="login-button">登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </template>
  
  <script>
  import { ref } from 'vue';
  import { useRouter } from 'vue-router';
  import axios from 'axios';
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
            axios.post('/api/login', {
              username: loginForm.value.username,
              password: loginForm.value.password
            })
            .then(response => {
              const token = response.data.token;
              localStorage.setItem('token', token);
              console.log('登录成功');
              router.push('/main'); // 跳转到主页面
            })
            .catch(error => {
              console.error('登录失败', error);
              alert('登录失败，请检查用户名和密码');
            });
          } else {
            console.log('登录表单验证失败');
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
    background-color: #ffffff; /* 设置背景为白色 */
  }
  
  .login-container {
    width: 400px;
    padding: 40px;
    border-radius: 8px;
    background-color: #f9f9f9;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
    text-align: center;
  }
  
  .login-form h2 {
    margin-bottom: 20px;
    font-weight: bold;
    color: #409eff;
  }
  
  .login-button {
    width: 100%;
  }
  </style>
  