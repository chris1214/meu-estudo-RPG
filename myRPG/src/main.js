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
import locale from 'element-ui/lib/locale/lang/en'

import VueResource from 'vue-resource'

Vue.use(VueResource)
Vue.use(BootstrapVue);
Vue.config.productionTip = false;
Vue.use(ElementUI, { locale });
Vue.use(helperClass);
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
