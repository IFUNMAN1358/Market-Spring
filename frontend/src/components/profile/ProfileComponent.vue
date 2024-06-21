<template>
  <div class="profile-container">
    <h2>Профиль</h2>
    <div v-if="user" class="profile-details">
      <p><strong>Имя:</strong> {{ user.name }}</p>
      <p><strong>Фамилия:</strong> {{ user.surname }}</p>
      <p><strong>Email:</strong> {{ user.email }}</p>
    </div>
    <div class="profile-actions">
      <button @click="$router.push({name: 'OrdersComponent'})" class="action-button">История заказов</button>
      <button @click="$router.push({name: 'SettingsComponent'})" class="action-button">Настройки</button>
      <button @click="logout" class="action-button">Выйти из аккаунта</button>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: 'ProfileComponent',
  data() {
    return {
      user: null
    };
  },
  methods: {
    ...mapActions(['removeTokens']),
    async fetchUserData() {
      try {
        const token = this.$cookies.get('accessToken');
        const response = await this.$axios.get('/user', {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
        this.user = response.data;
      } catch (error) {
        console.error('Ошибка при получении данных пользователя:', error.response ? error.response.data : error.message);
      }
    },
    async logout() {
      try {
        await this.removeTokens();
        this.$router.push({ name: 'MainComponent' });
      } catch (error) {
        console.error('Ошибка при выходе из аккаунта:', error.response ? error.response.data : error.message);
      }
    },
  },
  created() {
    this.fetchUserData();
  }
};
</script>

<style scoped>
.profile-container {
  background-color: #566E3A;
  padding: 20px;
  border-radius: 20px;
  width: 350px;
  margin: 30px auto;
  max-width: 100%;
  box-sizing: border-box;
  color: #BBD49D;
  font-family: 'Roboto', sans-serif;
}

.profile-container h2,
.profile-container h3 {
  text-align: center;
  font-weight: bold;
}

.profile-details p,
.order-history li {
  margin-bottom: 10px;
}

.profile-actions {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.action-button {
  width: 100%;
  padding: 10px;
  background-color: #BBD49D;
  color: #566E3A;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  font-family: 'Roboto', sans-serif;
  margin: 5px 0;
}

.action-button:hover {
  background-color: #a7bf8f;
}
</style>