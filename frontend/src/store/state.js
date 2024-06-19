export default {
  isMain: false,

  isLogin: false,
  isRegistration: false,
  isProfile: false,

  isCatalog: false,
  isShowProduct: false,
  isCreateProduct: false,

  isSettings: false,
  isChangeNameAndSurname: false,
  isChangeEmail: false,
  isChangePassword: false,
  isDeleteAccount: false,

  isAuthenticated: localStorage.getItem('isAuthenticated') === 'true' || false,
  isProductManager: false,
  isSupport: false,
  isAdmin: false
};
