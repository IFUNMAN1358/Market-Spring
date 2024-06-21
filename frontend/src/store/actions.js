import { jwtDecode } from 'jwt-decode';
import VueCookies from 'vue-cookies';
import axios from '@/axios.js';

export default {

  saveJwtTokens({ commit }, { accessToken, refreshToken }) {
    VueCookies.set('accessToken', accessToken, { secure: true, httpOnly: true });
    VueCookies.set('refreshToken', refreshToken, { secure: true, httpOnly: true });
    commit('setAuthenticated', true);
  },
  removeTokens({ commit }) {
    VueCookies.remove('accessToken');
    VueCookies.remove('refreshToken');
    commit('setAuthenticated', false);
  },

  checkRole({ commit }, roles) {
    commit('clearRoles');
    commit('setUserRole', roles.includes('ROLE_USER'));
    commit('setProductManagerRole', roles.includes('ROLE_PRODUCT_MANAGER'));
    commit('setSupportRole', roles.includes('ROLE_SUPPORT'));
    commit('setAdminRole', roles.includes('ROLE_ADMIN'));
  },

  ///////////////////////////////////////////////////
  // Check Auth
  ///////////////////////////////////////////////////

  checkAuth({ commit, dispatch }) {
    const token = VueCookies.get('accessToken');
    if (token) {
      try {
        const decodedToken = jwtDecode(token);
        const currentTime = Date.now() / 1000;
        if (decodedToken.exp && decodedToken.exp > currentTime) {
          commit('setAuthenticated', true);
          dispatch("checkRole", decodedToken.roles);
        } else {
          commit('setAuthenticated', false);
        }
      } catch (error) {
        commit('setAuthenticated', false);
      }
    } else {
      commit('clearRoles');
      commit('setAuthenticated', false);
    }
  },

  ///////////////////////////////////////////////////
  // Refresh Token
  ///////////////////////////////////////////////////

  async refreshToken({ commit, dispatch }) {
    const refreshToken = VueCookies.get('refreshToken');
    if (refreshToken) {
      try {
        const response = await axios.post('/token', { refreshToken });
        const { accessToken } = response.data;
        VueCookies.set('accessToken', accessToken, { secure: true, httpOnly: true });
        commit('setAuthenticated', true);
      } catch (error) {
        dispatch('removeTokens');
      }
    } else {
      dispatch('removeTokens');
    }
  },

};