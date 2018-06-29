// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import helperClass from './helperClass/myClass.css';
import locale from 'element-ui/lib/locale/lang/en';
import draggable from 'vuedraggable';

import VueResource from 'vue-resource'

import VueDragResize from 'vue-drag-resize'

import base from './http'
Vue.component('vue-drag-resize', VueDragResize)
Vue.use(VueResource);
Vue.use(draggable);
Vue.use(BootstrapVue);
Vue.config.productionTip = false;
Vue.use(ElementUI, { locale });
Vue.use(helperClass);
//var httpUsers = 'http://minhamesarpg.com/#/minhaMesaRpg-0.1/user/list'
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
})
