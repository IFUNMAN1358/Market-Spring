<template>
  <div class="main-wrapper">
    <Header />
    <div class="content">
        <MainComponent v-if="isMain"/>
        <LoginComponent v-if="isLogin" @closeLogin="closeAllAndAuth"/>
        <RegisterComponent v-if="isRegistration" @closeRegistration="closeAllAndAuth"/>
        <RoleComponent v-if="isRoles"/>
        <ProfileComponent v-if="isProfile" @closeProfile="closeAllAndAuth"/>
    </div>
    <Footer />
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import Header from './components/Header.vue';
import Footer from './components/Footer.vue';
import RegisterComponent from "@/components/RegisterComponent.vue";
import LoginComponent from "@/components/LoginComponent.vue";
import RoleComponent from "@/components/RoleComponent.vue";
import ProfileComponent from "@/components/ProfileComponent.vue";
import MainComponent from "@/components/MainComponent.vue";

export default {
  name: 'App',
  components: {
    MainComponent,
    ProfileComponent,
    RoleComponent,
    LoginComponent,
    RegisterComponent,
    Header,
    Footer
  },
  computed: {
    ...mapState(['isMain', 'isRegistration', 'isLogin', 'isRoles', 'isProfile'])
  },
  methods: {
    ...mapActions(['closeAll', 'checkAuth', 'showMain']),
    closeAllAndAuth() {
      this.closeAll();
      this.showMain();
      this.checkAuth();
    }
  },
  created() {
    this.checkAuth();
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