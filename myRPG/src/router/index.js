import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import create from '@/components/create'
import list from '@/components/show/list'
import inicio from '@/components/inicio'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/create',
      name: 'Create',
      component: create
    },
    {
      path: '/list',
      name: 'list',
      component: list,
    },
    {
      path: '/inicio/:name:id',
      name: 'inicio',
      component: inicio,
      props: true
    }
  ]
})
