import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    { 
      path: '/', 
      redirect: { name: 'login' }
    },
    {
      path: '/login',
      name: 'login',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('./views/login/login.vue')
    },
    {
      path: '/home',
      name: 'home',
      component: () => import('@/views/home/home.vue')
    }
  ]
})
