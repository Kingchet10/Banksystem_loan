import { createRouter, createWebHistory } from 'vue-router'
import BookVue from '@/components/Book.vue'
import CardVue from '@/components/Card.vue'
import BorrowVue from '@/components/Borrow.vue'
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
      component: BookVue
    },
    {
      path: '/back',
      component: BorrowVue
    },
    {
      path: '/inquire',
      component: CardVue
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
