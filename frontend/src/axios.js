import axios from 'axios';
import store from './store';

axios.defaults.baseURL = 'http://localhost:8000'

axios.interceptors.response.use(
  async response => {
    return response;
  },
  async error => {
    const originalRequest = error.config;

    if (error.response.status === 403 && !originalRequest._retry) {
      originalRequest._retry = true;

      try {
        await store.dispatch('refreshToken');
        return axios(originalRequest);
      } catch (e) {
        console.log('Ошибка обновления токена:', e);
        await store.dispatch('removeTokens');
        return Promise.reject(e);
      }
    }

    return Promise.reject(error);
  }
);

export default axios;