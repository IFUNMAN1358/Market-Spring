import { createStore } from 'vuex';
import { jwtDecode } from 'jwt-decode';
import VueCookies from "vue-cookies";

export default createStore({
  state: {
    isRegistration: false,
    isLogin: false,
    isRoles: false,
    isProfile: false,
    isMain: false,
    isAuthenticated: localStorage.getItem('isAuthenticated') === 'true' || false
  },
  mutations: {
    setRegistration(state, value) {
      state.isRegistration = value;
    },
    setLogin(state, value) {
      state.isLogin = value;
    },
    setRoles(state, value) {
      state.isRoles = value;
    },
    setProfile(state, value) {
      state.isProfile = value;
    },
    setMain(state, value) {
      state.isMain = value;
    },
    setAuthenticated(state, value) {
      state.isAuthenticated = value;
      localStorage.setItem('isAuthenticated', value);
    },
    closeAll(state) {
      state.isRegistration = false;
      state.isLogin = false;
      state.isRoles = false;
      state.isProfile = false;
      state.isMain = false;
    }
  },
  actions: {
    showRegistration({ commit }) {
      commit('closeAll');
      commit('setRegistration', true);
    },
    showLogin({ commit }) {
      commit('closeAll');
      commit('setLogin', true);
    },
    showRoles({ commit }) {
      commit('closeAll');
      commit('setRoles', true);
    },
    showProfile({ commit }) {
      commit('closeAll');
      commit('setProfile', true);
    },
    showMain({ commit }) {
      commit('closeAll');
      commit('setMain', true);
    },
    closeAll({ commit }) {
      commit('closeAll');
    },
    checkAuth({ commit }) {
      const token = VueCookies.get('accessToken');
      if (token) {
        try {
          const decodedToken = jwtDecode(token);
          const currentTime = Date.now() / 1000;
          if (decodedToken.exp && decodedToken.exp > currentTime) {
            commit('setAuthenticated', true);
          } else {
            commit('setAuthenticated', false);
          }
        } catch (error) {
          console.error('Ошибка при декодировании токена:', error);
          commit('setAuthenticated', false);
        }
      } else {
        commit('setAuthenticated', false);
      }
    },
    saveJwtTokens({ commit }, { accessToken, refreshToken }) {
      VueCookies.set('accessToken', accessToken, { secure: true, httpOnly: true });
      VueCookies.set('refreshToken', refreshToken, { secure: true, httpOnly: true });
      commit('setAuthenticated', true);
    },
    removeTokens({ commit }) {
      VueCookies.remove('accessToken');
      VueCookies.remove('refreshToken');
      commit('setAuthenticated', false);
    }
  },
  modules: {}
});