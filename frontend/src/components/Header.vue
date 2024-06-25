<template>
  <header>
    <nav class="navbar navbar-expand-lg navbar-custom">
      <router-link :to="{ name: 'MainComponent' }" class="navbar-brand">
        <img src="@/img/PetFoodLogo.png" alt="Logo" class="header-logo">
      </router-link>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        Меню
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mx-auto">
          <li class="nav-item">
            <router-link :to="{ name: 'MainComponent' }" class="nav-link">ГЛАВНАЯ</router-link>
          </li>
          <li class="nav-item">
            <router-link :to="{ name: 'CatalogComponent' }" class="nav-link">КАТАЛОГ</router-link>
          </li>

          <li v-if="hasRole('ROLE_SUPPORT')" class="nav-item">
            <router-link :to="{ name: 'ShowSupportComponent' }" class="nav-link">ПОДДЕРЖКА</router-link>
          </li>
          <li v-else class="nav-item">
            <router-link :to="{ name: 'CreateSupportComponent' }" class="nav-link">ПОДДЕРЖКА</router-link>
          </li>

          <li v-if="hasRole('ROLE_ADMIN')" class="nav-item">
            <router-link :to="{ name: 'RoleComponent' }" class="nav-link">РОЛИ</router-link>
          </li>

        </ul>
        <ul class="navbar-nav ml-auto">
          <li class="nav-item" v-if="!isAuthenticated">
            <router-link :to="{ name: 'LoginComponent' }" class="nav-link large-nav-link">ВХОД</router-link>
          </li>
          <li class="nav-item" v-if="!isAuthenticated">
            <router-link :to="{ name: 'RegistrationComponent' }" class="nav-link large-nav-link">РЕГИСТРАЦИЯ</router-link>
          </li>
          <li class="nav-item" v-if="isAuthenticated">
            <router-link :to="{ name: 'CartComponent' }" class="nav-link large-nav-link">КОРЗИНА</router-link>
          </li>
          <li class="nav-item" v-if="isAuthenticated">
            <router-link :to="{ name: 'ProfileComponent' }" class="nav-link large-nav-link">ПРОФИЛЬ</router-link>
          </li>
        </ul>
      </div>
    </nav>
  </header>
</template>

<script>
import { mapState, mapGetters } from "vuex";

export default {
  name: 'HeaderComponent',
  computed: {
    ...mapState(['isAuthenticated']),
    ...mapGetters(['hasRole'])
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