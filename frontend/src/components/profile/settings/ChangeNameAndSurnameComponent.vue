<template>
  <div class="change-name-form">
    <h2>ИЗМЕНИТЬ ИМЯ И ФАМИЛИЮ</h2>
    <form @submit.prevent="changeNameAndSurname">
      <div class="form-group">
        <label for="name">Имя</label>
        <input type="text" id="name" v-model="name" required />
      </div>
      <div class="form-group">
        <label for="surname">Фамилия</label>
        <input type="text" id="surname" v-model="surname" required />
      </div>
      <button type="submit" class="change-button">Изменить</button>
    </form>
    <button @click.prevent='showSettings' class="back-button">Назад</button>
  </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: 'ChangeNameAndSurnameComponent',
  data() {
    return {
      name: '',
      surname: ''
    }
  },
  methods: {
    ...mapActions(['showSettings']),
    async changeNameAndSurname() {
      try {
        const token = this.$cookies.get('accessToken');
        const response = await this.$axios.patch('/user/change-name-and-surname', {
          name: this.name,
          surname: this.surname,
        }, {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
        console.log(response);
        this.$emit('closeChangeNameAndSurname');
      } catch (error) {
        console.error('Ошибка при изменении:', error.response ? error.response.data : error.message);
      }
    }
  }
};
</script>

<style scoped>
.change-name-form {
  background-color: #566E3A;
  padding: 20px;
  border-radius: 20px;
  width: 300px;
  margin: 30px auto;
  max-width: 100%;
  box-sizing: border-box;
}

.change-name-form h2 {
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
