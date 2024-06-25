import { jwtDecode } from 'jwt-decode';
import VueCookies from 'vue-cookies';
import axios from 'axios';

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

  async refreshToken({ commit, dispatch }) {
    const refreshToken = VueCookies.get('refreshToken');
    if (refreshToken) {
      try {
        const response = await axios.post('/token', { refreshToken });
        const { accessToken } = response.data;
        VueCookies.set('accessToken', accessToken, { secure: true, httpOnly: true });
        const newDecodedToken = jwtDecode(accessToken);
        commit('setAuthenticated', true);
        dispatch("checkRole", newDecodedToken.roles);
      } catch (error) {
        dispatch('removeTokens');
        commit('setAuthenticated', false);
      }
    } else {
      dispatch('removeTokens');
      commit('setAuthenticated', false);
    }
  },

  async checkAuth({ commit, dispatch }) {
    const token = VueCookies.get('accessToken');
    if (token) {
      try {
        const decodedToken = jwtDecode(token);
        const currentTime = Date.now() / 1000;
        if (decodedToken.exp && decodedToken.exp > currentTime) {
          commit('setAuthenticated', true);
          dispatch("checkRole", decodedToken.roles);
        } else {
          await dispatch('refreshToken');
        }
      } catch (error) {
        commit('setAuthenticated', false);
        await dispatch('refreshToken');
      }
    } else {
      commit('clearRoles');
      commit('setAuthenticated', false);
    }
  }

};