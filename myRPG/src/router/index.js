import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import create from '@/components/create'
import list from '@/components/show/list'
import inicio from '@/components/inicio'
import mesa from '@/components/mesa'
import example from '@/components/example'
import texteGrails from '@/components/texteGrails'
import faixaEtaria from '@/components/utils/faixaEtaria'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/inicio/example/',
      name: 'example',
      component: example
    },
    {
      path: '/texteGrails/',
      name: 'texteGrails',
      component: texteGrails
    },
    {
      path: '/create/',
      name: 'Create',
      component: create
    },
    {
      path: '/list/',
      name: 'list',
      component: list,
    },
    {
      path: '/inicio/:name/:id',
      name: 'inicio',
      component: inicio,
      props: true
    },
    {
      path: '/inicio/:name/:id/:title/:mesaId',
      name: 'mesa',
      component: mesa,
      props: true
    },
    {
      path: '/inicio/:name/:id/:title/:mesaId/:faixaEtaria',
      name: 'faixaEtaria',
      component: faixaEtaria,
      props: true
    }
  ]
})
