import { createRouter, createWebHistory } from 'vue-router'
import ApplyVue from '@/components/Apply.vue'
import QueryVue from '@/components/Query.vue'
import RepayVue from '@/components/Repay.vue'
import InformVue from '@/components/inform.vue'
import LoginVue from '@/components/login.vue'
import Zhengxin from '@/components/metest.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/ask',
      component: ApplyVue
    },
    {
      path: '/back',
      component: RepayVue
    },
    {
      path: '/inquire',
      component: QueryVue
    },
    {
      path: '/me',
      component: Zhengxin
    },
    {
      path: '/inform',
      component: InformVue
    },
    {
      path: '/login',
      component: LoginVue    
    },
  ]
})

export default router
