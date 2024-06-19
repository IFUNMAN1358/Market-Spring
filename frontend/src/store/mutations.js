export default {

  setMain(state, value) {
    state.isMain = value;
  },


  setCatalog(state, value) {
    state.isCatalog = value;
  },
  setShowProduct(state, value) {
    state.isShowProduct = value;
  },
  setCreateProduct(state, value) {
    state.isCreateProduct = value;
  },


  setLogin(state, value) {
    state.isLogin = value;
  },
  setRegistration(state, value) {
    state.isRegistration = value;
  },
  setProfile(state, value) {
    state.isProfile = value;
  },


  setSettings(state, value) {
    state.isSettings = value;
  },
  setChangeNameAndSurname(state, value) {
    state.isChangeNameAndSurname = value;
  },
  setChangeEmail(state, value) {
    state.isChangeEmail = value;
  },
  setChangePassword(state, value) {
    state.isChangePassword = value;
  },
  setDeleteAccount(state, value) {
    state.isDeleteAccount = value;
  },


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


  closeAll(state) {
    state.isMain = false;

    state.isCatalog = false;
    state.isShowProduct = false;
    state.isCreateProduct = false;

    state.isLogin = false;
    state.isRegistration = false;
    state.isProfile = false;

    state.isSettings = false;
    state.isChangeNameAndSurname = false;
    state.isChangeEmail = false;
    state.isChangePassword = false;
    state.isDeleteAccount = false;
  },
};
