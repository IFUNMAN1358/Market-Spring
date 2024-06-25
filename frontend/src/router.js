import { createRouter, createWebHistory } from 'vue-router';
import store from '@/store/index';
import MainComponent from "@/components/MainComponent.vue";
import ShowProductComponent from '@/components/products/ShowProductComponent.vue';
import LoginComponent from "@/components/LoginComponent.vue";
import RegistrationComponent from "@/components/RegistrationComponent.vue";
import ProfileComponent from "@/components/profile/ProfileComponent.vue";
import SettingsComponent from "@/components/profile/SettingsComponent.vue";
import ChangeEmailComponent from "@/components/profile/settings/ChangeEmailComponent.vue";
import ChangeNameAndSurnameComponent from "@/components/profile/settings/ChangeNameAndSurnameComponent.vue";
import ChangePasswordComponent from "@/components/profile/settings/ChangePasswordComponent.vue";
import DeleteAccountComponent from "@/components/profile/settings/DeleteAccountComponent.vue";
import CatalogComponent from "@/components/products/CatalogComponent.vue";
import CreateProductComponent from "@/components/products/CreateProductComponent.vue";
import UpdateProductComponent from "@/components/products/UpdateProductComponent.vue";
import CartComponent from "@/components/profile/CartComponent.vue";
import ShowSupportComponent from "@/components/support/ShowSupportComponent.vue";
import CreateSupportComponent from "@/components/support/CreateSupportComponent.vue";
import AccessDeniedComponent from "@/components/AccessDeniedComponent.vue";
import CreateOrderComponent from "@/components/profile/orders/CreateOrderComponent.vue";
import UserOrdersComponent from "@/components/profile/orders/UserOrdersComponent.vue";
import RoleComponent from "@/components/RoleComponent.vue";

const routes = [
  {path: '/', name: 'MainComponent', component: MainComponent},
  {path: '/access-denied', name: 'AccessDeniedComponent', component: AccessDeniedComponent},

  {path: '/login', name: 'LoginComponent', component: LoginComponent},
  {path: '/registration', name: 'RegistrationComponent', component: RegistrationComponent},

  {path: '/role', name: 'RoleComponent', component: RoleComponent, meta: {requiresRole: 'ROLE_ADMIN'}},

  {path: '/catalog', name: 'CatalogComponent', component: CatalogComponent},
  {path: '/catalog/create', name: 'CreateProductComponent', component: CreateProductComponent, meta: {requiresRole: 'ROLE_PRODUCT_MANAGER'}},
  {path: '/catalog/:id', name: 'ShowProductComponent', component: ShowProductComponent, props: true},
  {path: '/catalog/:id/edit', name: 'UpdateProductComponent', component: UpdateProductComponent, props: true, meta: {requiresRole: 'ROLE_PRODUCT_MANAGER'}},

  {path: '/support/create', name: 'CreateSupportComponent', component: CreateSupportComponent},
  {path: '/support/show', name: 'ShowSupportComponent', component: ShowSupportComponent, meta: {requiresRole: 'ROLE_SUPPORT'}},

  {path: '/profile', name: 'ProfileComponent', component: ProfileComponent, meta: {requiresRole: 'ROLE_USER'}},

  {path: '/profile/cart', name: 'CartComponent', component: CartComponent, meta: {requiresRole: 'ROLE_USER'}},
  {path: '/profile/order', name: 'CreateOrderComponent', component: CreateOrderComponent, meta: {requiresRole: 'ROLE_USER'}},
  {path: '/profile/orders', name: 'UserOrdersComponent', component: UserOrdersComponent, meta: {requiresRole: 'ROLE_USER'}},

  {path: '/profile/settings', name: 'SettingsComponent', component: SettingsComponent, meta: {requiresRole: 'ROLE_USER'}},
  {path: '/profile/settings/change-name-and-surname', name: 'ChangeNameAndSurnameComponent', component: ChangeNameAndSurnameComponent, meta: {requiresRole: 'ROLE_USER'}},
  {path: '/profile/settings/change-email', name: 'ChangeEmailComponent', component: ChangeEmailComponent, meta: {requiresRole: 'ROLE_USER'}},
  {path: '/profile/settings/change-password', name: 'ChangePasswordComponent', component: ChangePasswordComponent, meta: {requiresRole: 'ROLE_USER'}},
  {path: '/profile/settings/delete-account', name: 'DeleteAccountComponent', component: DeleteAccountComponent, meta: {requiresRole: 'ROLE_USER'}}
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

router.beforeEach(async (to, from, next) => {
  await store.dispatch("checkAuth");
  const requiresRole = to.meta.requiresRole;
  if (requiresRole && !store.getters.hasRole(requiresRole)) {
    next({ name: 'AccessDeniedComponent' });
  } else {
    next();
  }
});

export default router;