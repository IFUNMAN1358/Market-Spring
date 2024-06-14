<template>
  <header>
    <nav class="navbar navbar-expand-lg navbar-custom">
      <a class="navbar-brand" href="">
        <img src="@/img/PetFoodLogo.png" alt="Logo" class="header-logo">
      </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        Меню
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mx-auto">
          <li class="nav-item">
            <a class="nav-link" href="">ГЛАВНАЯ</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="">КАТАЛОГ</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="" @click.prevent="showRoles">РОЛИ</a>
          </li>
        </ul>
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link large-nav-link" href="" @click.prevent="showLogin">ВХОД</a>
          </li>
          <li class="nav-item">
            <a class="nav-link large-nav-link" href="" @click.prevent="showRegistration">РЕГИСТРАЦИЯ</a>
          </li>
        </ul>
      </div>
    </nav>
    <LoginComponent v-if="isLogin" @logined="closeLogin"/>
    <RegisterComponent v-if="isRegistration" @registered="closeRegistration"/>
    <RoleComponent v-if="isRoles" @roled="closeRoles"/>
  </header>
</template>

<script>
import RegisterComponent from './RegisterComponent.vue';
import LoginComponent from './LoginComponent.vue';
import RoleComponent from "./RoleComponent.vue";

export default {
  name: 'HeaderComponent',
  components: {
    RoleComponent,
    RegisterComponent,
    LoginComponent
  },
  data() {
    return {
      isRegistration: false,
      isLogin: false,
      isRoles: false
    };
  },
  methods: {
    showRegistration() {
      this.isRegistration = true;
      this.isLogin = false;
      this.isRoles = false;
    },
    closeRegistration() {
      this.isRegistration = false;
    },
    showLogin() {
      this.isLogin = true;
      this.isRegistration = false;
      this.isRoles = false;
    },
    closeLogin() {
      this.isLogin = false;
    },
    showRoles() {
      this.isRoles = true;
      this.isLogin = false;
      this.isRegistration = false;
    },
    closeRoles() {
      this.isLogin = false;
    }
  }
};
</script>


<style scoped>
header {
  width: 100%;
}

.navbar-custom {
  background-color: #a1d078;
  font-family: 'Roboto', sans-serif;
  font-weight: bold;
}

.header-logo {
  width: 50px;
  height: auto;
  margin-left: 20px;
}

.nav-link {
  margin-left: 20px;
  font-family: 'Roboto', sans-serif;
  font-weight: bold;
  font-size: 1.25rem;
}

.large-nav-link {
  font-size: 1.5rem;
  margin-left: 20px;
  margin-right: 20px;
}

.navbar-toggler {
  font-size: 1.5rem;
  margin-right: 30px;
}
</style>