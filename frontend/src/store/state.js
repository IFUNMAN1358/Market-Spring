export default {
  isAuthenticated: localStorage.getItem('isAuthenticated') === 'true' || false,
  isProductManager: false,
  isSupport: false,
  isAdmin: false
};
