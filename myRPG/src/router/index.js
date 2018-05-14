import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import create from '@/components/create'
import list from '@/components/list'

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
      path: '/list/:name:id',
      name: 'list',
      component: list,
      props: true
    }
  ]
})
