import { createApp } from 'vue';
import App from './App.vue';
import store from './js/store';
import axios from 'axios';
import VueCookies from 'vue-cookies';

import 'jquery/src/jquery.js'
import '@popperjs/core/dist/umd/popper';
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'

axios.defaults.baseURL = 'http://localhost:8000';

createApp(App)
.use(VueCookies)
.use(store)
.mount('#app');
