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

// 设置 Axios 基础 URL
axios.defaults.baseURL = 'http://localhost:8080';

// 添加 Axios 到 Vue 实例的全局属性中
app.config.globalProperties.$axios = axios

// 注册 ElementPlus 图标组件
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.use(createPinia())
app.use(router)
app.use(ElementPlus)
app.mount('#app')
