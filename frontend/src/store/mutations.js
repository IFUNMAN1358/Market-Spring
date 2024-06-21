export default {

  setAuthenticated(state, value) {
    state.isAuthenticated = value;
    localStorage.setItem('isAuthenticated', value);
  },

  setUserRole(state, hasRole) {
    if (hasRole) state.roles.push('ROLE_USER');
  },
  setProductManagerRole(state, hasRole) {
    if (hasRole) state.roles.push('ROLE_PRODUCT_MANAGER');
  },
  setSupportRole(state, hasRole) {
    if (hasRole) state.roles.push('ROLE_SUPPORT');
  },
  setAdminRole(state, hasRole) {
    if (hasRole) state.roles.push('ROLE_ADMIN');
  },

  clearRoles(state) {
    state.roles = [];
  }
};
