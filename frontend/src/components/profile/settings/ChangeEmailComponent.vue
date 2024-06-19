<template>
  <div class="change-email-form">
    <h2>ИЗМЕНИТЬ ПОЧТУ</h2>
    <form @submit.prevent="changeEmail">
      <div class="form-group">
        <label for="email">Новая почта</label>
        <input type="email" id="email" v-model="email" required />
      </div>
      <button type="submit" class="change-button">Изменить</button>
    </form>
    <button @click.prevent='showSettings' class="back-button">Назад</button>
  </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: 'ChangeEmailComponent',
  data() {
    return {
      email: ''
    }
  },
  methods: {
    ...mapActions(['showSettings']),
    async changeEmail() {
      try {
        const token = this.$cookies.get('accessToken');
        const response = await this.$axios.patch('/user/change-email', {
          email: this.email,
        }, {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
        console.log(response);
        this.$emit('closeChangeEmail');
      } catch (error) {
        console.error('Ошибка при изменении:', error.response ? error.response.data : error.message);
      }
    }
  }
};
</script>

<style scoped>
.change-email-form {
  background-color: #566E3A;
  padding: 20px;
  border-radius: 20px;
  width: 300px;
  margin: 30px auto;
  max-width: 100%;
  box-sizing: border-box;
}

.change-email-form h2 {
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
