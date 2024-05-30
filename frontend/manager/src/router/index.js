
import { createRouter, createWebHistory } from 'vue-router';
import Login from '@/components/Login.vue';
import Main from '@/components/Main.vue';
import LoanApproval from '@/components/LoanApproval.vue';
import LoanHistory from '@/components/LoanHistory.vue';
import LoanInquire from '@/components/LoanInquire.vue';
import SecretKey from '@/components/changer.vue';
//import { s } from 'vite/dist/node/types.d-jgA8ss1A';
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
    children: [
      {
        path: 'add',
        name: 'LoanApproval',
        component: LoanApproval
      },
      {
        path: 'change1',
        name: 'LoanHistory',
        component: LoanHistory
      },
      {
        path: 'change2',
        name: 'LoanInquire',
        component: LoanInquire
      },
      {
        path: 'delete',
        name: 'secret',
        component: SecretKey
      }
    ]
  }
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
});

export default router;
