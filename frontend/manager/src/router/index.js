import { createRouter, createWebHistory } from 'vue-router';
import Login from '@/components/Login.vue';
import Main from '@/components/Main.vue';
import LoanApproval from '@/components/LoanApproval.vue';
import LoanHistory from '@/components/LoanHistory.vue';
import LoanInquire from '@/components/LoanInquire.vue';
import SecretKey from '@/components/changer.vue';

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/main',
    name: 'Main',
    component: Main,
meta: { requiresAuth: true },
    children: [
      {
        path: 'add',
        name: 'LoanApproval',
        component: LoanApproval,
    meta: { requiresAuth: true }
      },
      {
        path: 'change1',
        name: 'LoanHistory',
        component: LoanHistory,
    meta: { requiresAuth: true }
      },
      {
        path: 'change2',
        name: 'LoanInquire',
        component: LoanInquire,
    meta: { requiresAuth: true }
      },
      {
        path: 'delete',
        name: 'SecretKey',
        component: SecretKey,
    meta: { requiresAuth: true }
      }
    ]
  }
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
});

// 路由守卫
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token');
  if (to.matched.some(record => record.meta.requiresAuth) && !token) {
    next({ name: 'Login' });
  } else {
    next();
  }
});

export default router;
