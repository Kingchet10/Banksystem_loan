import './assets/main.css' // 确保引入了你的全局 CSS 文件
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'
import App from './App.vue'
import router from './router'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import axios from 'axios'

const app = createApp(App)

axios.defaults.baseURL = 'http://localhost:8080';
axios.interceptors.request.use(config => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
}, error => {
    return Promise.reject(error);
});

// 添加响应拦截器，处理 token 过期的情况
axios.interceptors.response.use(response => {
    return response;
}, error => {
    if (error.response.status === 401) {
        console.error('Token 过期或未授权');
        localStorage.removeItem('token');
        router.push('/login'); 
    }
    return Promise.reject(error);
});

app.config.globalProperties.$axios = axios
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.use(createPinia())
app.use(router)
app.use(ElementPlus)
app.mount('#app')
