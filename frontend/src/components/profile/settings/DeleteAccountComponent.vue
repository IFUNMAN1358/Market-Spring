<template>
  <div class="delete-account-form">
    <h2>Удаление аккаунта</h2>
    <p>Вы уверены, что хотите удалить свой аккаунт? Это действие необратимо и приведет к удалению всех ваших данных и истории покупок.</p>
    <button @click="deleteUser" class="delete-button">Удалить</button>
    <router-link :to="{ name: 'SettingsComponent' }">
      <button class="back-button">Назад</button>
    </router-link>
  </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: 'DeleteAccountComponent',
  methods: {
    ...mapActions(['removeTokens']),
    async deleteUser() {
      try {
        const token = this.$cookies.get('accessToken');
        const response = await this.$axios.delete('/user', {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
        console.log(response);
        await this.removeTokens();
        this.$router.push({ name: 'MainComponent' });
      } catch (error) {
        console.error('Ошибка при удалении:', error.response ? error.response.data : error.message);
      }
    }
  }
};
</script>

<style scoped>
.delete-account-form {
  background-color: #566E3A;
  padding: 20px;
  border-radius: 20px;
  width: 300px;
  margin: 30px auto;
  max-width: 100%;
  box-sizing: border-box;
}

.delete-account-form h2 {
  color: #BBD49D;
  font-family: 'Roboto', sans-serif;
  font-weight: bold;
  text-align: center;
}

.delete-account-form p {
  color: #BBD49D;
  font-family: 'Roboto', sans-serif;
  text-align: center;
  margin-bottom: 20px;
}

button {
  width: 100%;
  padding: 10px;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  font-family: 'Roboto', sans-serif;
}

.delete-button {
  margin-top: 10px;
  background-color: #FF4C4C;
  color: #FFFFFF;
}

.delete-button:hover {
  background-color: #FF1C1C;
}

.back-button {
  margin-top: 20px;
  background-color: #BBD49D;
  color: #566E3A;
}

.back-button:hover {
  background-color: #a7bf8f;
}
</style>