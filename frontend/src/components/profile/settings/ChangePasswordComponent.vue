<template>
  <div class="change-password-form">
    <h2>ИЗМЕНИТЬ ПАРОЛЬ</h2>
    <form @submit.prevent="changePassword">
      <div class="form-group">
        <label for="password">Новый пароль</label>
        <input type="password" id="password" v-model="password" required />
      </div>
      <div class="form-group">
        <label for="confirmPassword">Повторите новый пароль</label>
        <input type="password" id="confirmPassword" v-model="confirmPassword" required />
      </div>
      <button type="submit" class="change-button">Изменить</button>
    </form>
    <router-link :to="{ name: 'SettingsComponent' }">
      <button class="back-button">Назад</button>
    </router-link>
  </div>
</template>

<script>
export default {
  name: 'ChangePasswordComponent',
  data() {
    return {
      password: '',
      confirmPassword: ''
    }
  },
  methods: {
    async changePassword() {
      if (this.password !== this.confirmPassword) {
        alert('Пароли не совпадают');
        return;
      }
      try {
        const token = this.$cookies.get('accessToken');
        const response = await this.$axios.patch('/user/change-password', {
          password: this.password,
        }, {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
        console.log(response);
        this.$router.push({ name: 'ProfileComponent' });
      } catch (error) {
        console.error('Ошибка при изменении пароля:', error.response ? error.response.data : error.message);
      }
    }
  }
};
</script>

<style scoped>
.change-password-form {
  background-color: #566E3A;
  padding: 20px;
  border-radius: 20px;
  width: 300px;
  margin: 30px auto;
  max-width: 100%;
  box-sizing: border-box;
}

.change-password-form h2 {
  color: #BBD49D;
  font-family: 'Roboto', sans-serif;
  font-weight: bold;
  text-align: center;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  color: #BBD49D;
  font-size: small;
  margin-bottom: 5px;
  font-family: 'Roboto', sans-serif;
}

.form-group input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  border: none;
  border-radius: 10px;
  background-color: #BBD49D;
  color: #566E3A;
  font-family: 'Roboto', sans-serif;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #BBD49D;
  color: #566E3A;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  font-family: 'Roboto', sans-serif;
}

button:hover {
  background-color: #a7bf8f;
}

.change-button {
  margin-top: 10px;
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
