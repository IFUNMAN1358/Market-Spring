import { createApp } from 'vue';
import App from './App.vue';
import axios from '@/axios.js';
import store from '@/store/index.js';
import VueCookies from 'vue-cookies';
import router from '@/router.js';

import 'jquery/src/jquery.js'
import '@popperjs/core/dist/umd/popper';
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'


const app = createApp(App);

app.config.globalProperties.$axios = axios;

store.dispatch('checkAuth').then(() => {
  app.use(router)
  app.use(VueCookies);
  app.use(store);
  app.mount('#app');
});
