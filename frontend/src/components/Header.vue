<template>
  <header>
    <nav class="navbar navbar-expand-lg navbar-custom">
      <a class="navbar-brand" href="" @click.prevent="showMain">
        <img src="@/img/PetFoodLogo.png" alt="Logo" class="header-logo">
      </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        Меню
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mx-auto">
          <li class="nav-item">
            <a class="nav-link" href="" @click.prevent="showMain">ГЛАВНАЯ</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="" @click.prevent="showCatalog">КАТАЛОГ</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="">ПОДДЕРЖКА</a>
          </li>
        </ul>
        <ul class="navbar-nav ml-auto">
          <li class="nav-item" v-if="!isAuthenticated">
            <a class="nav-link large-nav-link" href="" @click.prevent="showLogin">ВХОД</a>
          </li>
          <li class="nav-item" v-if="!isAuthenticated">
            <a class="nav-link large-nav-link" href="" @click.prevent="showRegistration">РЕГИСТРАЦИЯ</a>
          </li>
          <li class="nav-item" v-if="isAuthenticated">
            <a class="nav-link large-nav-link" href="">КОРЗИНА</a>
          </li>
          <li class="nav-item" v-if="isAuthenticated">
            <a class="nav-link large-nav-link" href="" @click.prevent="showProfile">ПРОФИЛЬ</a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
</template>

<script>
import { mapActions, mapState } from "vuex";

export default {
  name: 'HeaderComponent',
  computed: {
    ...mapState(['isAuthenticated'])
  },
  methods: {
    ...mapActions(['showMain', 'showRegistration', 'showLogin', 'showProfile', 'showCatalog'])
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