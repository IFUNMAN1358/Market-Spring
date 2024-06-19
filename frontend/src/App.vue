<template>
  <div class="main-wrapper">
    <Header/>
    <div class="content">
      <MainComponent v-if="isMain"/>

      <CatalogComponent v-if="isCatalog"/>
      <ShowProductComponent v-if="isShowProduct" @closeShowProduct="closeAllAndOpenCatalog"/>
      <CreateProductComponent v-if="isCreateProduct" @closeCreateProduct="closeAllAndOpenProduct"/>

      <LoginComponent v-if="isLogin" @closeLogin="closeAllAndOpenMain"/>
      <RegistrationComponent v-if="isRegistration" @closeRegistration="closeAllAndOpenMain"/>
      <ProfileComponent v-if="isProfile" @closeProfile="closeAllAndOpenMain"/>

      <SettingsComponent v-if="isSettings" @closeSettings="closeAllAndOpenMain"/>
      <ChangeNameAndSurnameComponent v-if="isChangeNameAndSurname" @closeChangeNameAndSurname="closeAllAndOpenProfile"/>
      <ChangeEmailComponent v-if="isChangeEmail" @closeChangeEmail="closeAllAndOpenProfile"/>
      <ChangePasswordComponent v-if="isChangePassword" @closeChangePassword="closeAllAndOpenProfile"/>
      <DeleteAccountComponent v-if="isDeleteAccount" @closeDeleteAccount="closeAllAndOpenMain"/>
    </div>
    <Footer/>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";

import Header from './components/Header.vue';
import Footer from './components/Footer.vue';

import MainComponent from "@/components/MainComponent.vue";

import CatalogComponent from "@/components/products/CatalogComponent.vue";
import ShowProductComponent from "@/components/products/ShowProductComponent.vue";
import CreateProductComponent from "@/components/products/CreateProductComponent.vue";

import LoginComponent from "@/components/LoginComponent.vue";
import RegistrationComponent from "@/components/RegistrationComponent.vue";
import ProfileComponent from "@/components/profile/ProfileComponent.vue";

import SettingsComponent from "@/components/profile/SettingsComponent.vue";
import ChangeNameAndSurnameComponent from "@/components/profile/settings/ChangeNameAndSurnameComponent.vue";
import ChangeEmailComponent from "@/components/profile/settings/ChangeEmailComponent.vue";
import ChangePasswordComponent from "@/components/profile/settings/ChangePasswordComponent.vue";
import DeleteAccountComponent from "@/components/profile/settings/DeleteAccountComponent.vue";

export default {
  name: 'App',
  components: {
    ShowProductComponent,
    CreateProductComponent,
    CatalogComponent,
    Header,
    Footer,

    MainComponent,

    LoginComponent,
    RegistrationComponent,
    ProfileComponent,

    SettingsComponent,
    ChangeNameAndSurnameComponent,
    ChangeEmailComponent,
    ChangePasswordComponent,
    DeleteAccountComponent
  },
  computed: {
    ...mapState([
      'isMain',
      'isCatalog', 'isShowProduct', 'isCreateProduct',
      'isLogin', 'isRegistration', 'isProfile',
      'isSettings', 'isChangeNameAndSurname', 'isChangeEmail', 'isChangePassword', 'isDeleteAccount'
    ])
  },
  methods: {
    ...mapActions(['closeAll', 'checkAuth', 'showMain', 'showProfile', 'showCatalog', 'showShowProduct']),
    closeAllAndOpenMain() {
      this.checkAuth();
      this.closeAll();
      this.showMain();
    },
    closeAllAndOpenProfile() {
      this.checkAuth();
      this.closeAll();
      this.showProfile();
    },
    closeAllAndOpenCatalog() {
      this.checkAuth();
      this.closeAll();
      this.showCatalog();
    },
    closeAllAndOpenProduct() {
      this.checkAuth();
      this.closeAll();
      this.showShowProduct();
    }
  },
  created() {
    this.showMain();
  }
};
</script>

<style scoped>
body {
  margin: 0;
  font-family: 'Roboto', sans-serif;
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.main-wrapper {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.content {
  flex: 1;
  padding: 30px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>