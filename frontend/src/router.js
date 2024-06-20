import { createRouter, createWebHistory } from 'vue-router';
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
import OrdersComponent from "@/components/profile/OrdersComponent.vue";
import ShowSupportComponent from "@/components/support/ShowSupportComponent.vue";
import CreateSupportComponent from "@/components/support/CreateSupportComponent.vue";

const routes = [
  { path: '/', name: 'MainComponent', component: MainComponent },

  { path: '/login', name: 'LoginComponent', component: LoginComponent },
  { path: '/registration', name: 'RegistrationComponent', component: RegistrationComponent },

  { path: '/catalog', name: 'CatalogComponent', component: CatalogComponent },
  { path: '/catalog/create', name: 'CreateProductComponent', component: CreateProductComponent },
  { path: '/catalog/:id', name: 'ShowProductComponent', component: ShowProductComponent, props: true },
  { path: '/catalog/:id/edit', name: 'UpdateProductComponent', component: UpdateProductComponent, props: true },

  { path: '/support/create', name: 'CreateSupportComponent', component: CreateSupportComponent },
  { path: '/support/show', name: 'ShowSupportComponent', component: ShowSupportComponent },

  { path: '/profile', name: 'ProfileComponent', component: ProfileComponent },

  { path: '/profile/cart', name: 'CartComponent', component: CartComponent },
  { path: '/profile/orders', name: 'OrdersComponent', component: OrdersComponent },

  { path: '/profile/settings', name: 'SettingsComponent', component: SettingsComponent },
  { path: '/profile/settings/change-name-and-surname', name: 'ChangeNameAndSurnameComponent', component: ChangeNameAndSurnameComponent },
  { path: '/profile/settings/change-email', name: 'ChangeEmailComponent', component: ChangeEmailComponent },
  { path: '/profile/settings/change-password', name: 'ChangePasswordComponent', component: ChangePasswordComponent },
  { path: '/profile/settings/delete-account', name: 'DeleteAccountComponent', component: DeleteAccountComponent }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;