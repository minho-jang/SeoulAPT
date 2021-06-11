import Vue from 'vue';
import VueRouter from 'vue-router';
import HomeView from '../views/HomeView.vue';
import AboutView from '../views/AboutView.vue';
import SearchView from '../views/SearchView.vue';
import QnaView from '../views/QnaView.vue';
import QnaDetailView from '../views/QnaDetailView.vue';
import QnaFormView from '../views/QnaFormView.vue';
import QnaModifyFormView from '../views/QnaModifyFormView.vue';
import LoginView from '../views/LoginView.vue';
import JoinView from '../views/JoinView.vue';
import ProfileView from '../views/ProfileView.vue';
import ProfileModifyView from '../views/ProfileModifyView.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    component: HomeView,
  },
  {
    path: '/about',
    component: AboutView,
  },
  {
    path: '/search',
    component: SearchView,
  },
  {
    path: '/qna',
    component: QnaView,
  },
  {
    path: '/qna/write',
    component: QnaFormView,
  },
  {
    path: '/qna/:id',
    component: QnaDetailView,
  },
  {
    path: '/qna/:id/modify',
    component: QnaModifyFormView,
  },
  {
    path: '/user/login',
    component: LoginView,
  },
  {
    path: '/user/join',
    component: JoinView,
  },
  {
    path: '/user/profile',
    component: ProfileView,
  },
  {
    path: '/user/modify',
    component: ProfileModifyView,
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
