import { jwtDecode } from 'jwt-decode';
import VueCookies from 'vue-cookies';
import axios from '@/axios.js';

export default {

  ///////////////////////////////////////////////////
  // Show component
  ///////////////////////////////////////////////////

  showMain({ commit }) {
    commit('closeAll');
    commit('setMain', true);
  },


  showCatalog({ commit }) {
    commit('closeAll');
    commit('setCatalog', true);
  },
  showShowProduct({ commit }) {
    commit('closeAll');
    commit('setShowProduct', true);
  },
  showCreateProduct({ commit }) {
    commit('closeAll');
    commit('setCreateProduct', true);
  },


  showLogin({ commit }) {
    commit('closeAll');
    commit('setLogin', true);
  },
  showRegistration({ commit }) {
    commit('closeAll');
    commit('setRegistration', true);
  },
  showProfile({ commit }) {
    commit('closeAll');
    commit('setProfile', true);
  },


  showSettings({ commit }) {
    commit('closeAll');
    commit('setSettings', true);
  },
  showChangeNameAndSurname({ commit }) {
    commit('closeAll');
    commit('setChangeNameAndSurname', true);
  },
  showChangeEmail({ commit }) {
    commit('closeAll');
    commit('setChangeEmail', true);
  },
  showChangePassword({ commit }) {
    commit('closeAll');
    commit('setChangePassword', true);
  },
  showDeleteAccount({ commit }) {
    commit('closeAll');
    commit('setDeleteAccount', true);
  },


  closeAll({ commit }) {
    commit('closeAll');
  },

  ///////////////////////////////////////////////////
  // Jwt tokens
  ///////////////////////////////////////////////////

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

  ///////////////////////////////////////////////////
  // Auth
  ///////////////////////////////////////////////////

  checkRole({ commit }, roles) {
    if (roles.includes('ROLE_PRODUCT_MANAGER')) {
      commit("setProductManagerRole", true);

    } else if (roles.includes('ROLE_SUPPORT')) {
      commit("setSupportRole", true);

    } else if (roles.includes("ROLE_ADMIN")) {
      commit("setAdminRole", true);
    }
  },

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
      commit('setAuthenticated', false);
    }
  },

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
