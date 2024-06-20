export default {

  setAuthenticated(state, value) {
    state.isAuthenticated = value;
    localStorage.setItem('isAuthenticated', value);
  },
  setProductManagerRole(state, value) {
    state.isProductManager = value;
  },
  setSupportRole(state, value) {
    state.isSupport = value;
  },
  setAdminRole(state, value) {
    state.isAdmin = value;
  },

};
