<template>
  <div class="registration-form">
    <h2>РЕГИСТРАЦИЯ</h2>
    <form @submit.prevent="register">
      <div class="form-group">
        <label for="name">Имя</label>
        <input type="text" id="name" v-model="name" required />
      </div>
      <div class="form-group">
        <label for="surname">Фамилия</label>
        <input type="text" id="surname" v-model="surname" required />
      </div>
      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" id="email" v-model="email" required />
      </div>
      <div class="form-group">
        <label for="password">Пароль</label>
        <input type="password" id="password" v-model="password" required />
      </div>
      <div class="form-group">
        <label for="confirmPassword">Повторите пароль</label>
        <input type="password" id="confirmPassword" v-model="confirmPassword" required />
      </div>
      <button type="submit" class="register-button">Зарегистрироваться</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'RegisterComponent',
  data() {
    return {
      name: '',
      surname: '',
      email: '',
      password: '',
      confirmPassword: ''
    }
  },
  methods: {
    async register() {
      if (this.password !== this.confirmPassword) {
        alert("Пароли не совпадают");
        return;
      }
      try {
        const response = await this.$axios.post('/register', {
          name: this.name,
          surname: this.surname,
          email: this.email,
          password: this.password
        });
        console.log(response.data);
        this.$emit('closeRegistration');
      } catch (error) {
        console.error('Ошибка при регистрации:', error.response ? error.response.data : error.message);
      }
    }
  }
};
</script>

<style scoped>
.registration-form {
  background-color: #566E3A;
  padding: 20px;
  border-radius: 20px;
  width: 300px;
  margin: 30px auto;
  max-width: 100%;
  box-sizing: border-box;
}

.registration-form h2 {
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

.register-button {
  margin-top: 20px;
}
</style>