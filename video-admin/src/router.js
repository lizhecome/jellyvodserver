import Vue from 'vue'
import Router from 'vue-router'

import shortroutes from '@/router-short/index.js'
Vue.use(Router)

const routes = shortroutes

export default new Router({
  // mode: 'history',
  routes
})