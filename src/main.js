import Vue from 'vue'
import App from './App.vue'
import VueRouter from './router'

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router: VueRouter,
  component: App
}).$mount('#app')
