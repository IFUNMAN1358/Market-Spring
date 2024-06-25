<template>
  <div class="support-form">
    <h2>Поддержка</h2>
    <form @submit.prevent="submitSupportRequest">
      <div class="form-group">
        <label for="name">Имя</label>
        <input type="text" id="name" v-model="form.name" required />
      </div>
      <div class="form-group">
        <label for="surname">Фамилия</label>
        <input type="text" id="surname" v-model="form.surname" required />
      </div>
      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" id="email" v-model="form.email" required />
      </div>
      <div class="form-group">
        <label for="issue">Описание проблемы</label>
        <textarea id="issue" v-model="form.issue" required></textarea>
      </div>
      <div class="alert">
        Ваше сообщение будет получено поддержкой, и ответ будет отправлен на ваш email в течение ближайшего времени.
      </div>
      <button type="submit" class="support-button">Отправить</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'CreateSupportComponent',
  data() {
    return {
      form: {
        name: '',
        surname: '',
        email: '',
        issue: ''
      }
    };
  },
  mounted() {
    this.fetchUserData();
  },
  methods: {
    async fetchUserData() {
      try {
        const response = await this.$axios.get('/user', {
          headers: {
            Authorization: `Bearer ${this.$cookies.get('accessToken')}`
          }
        });
        const userData = response.data;
        this.form.name = userData.name;
        this.form.surname = userData.surname;
        this.form.email = userData.email;
      } catch (error) {
        console.error('Ошибка при получении данных пользователя:', error);
      }
    },
    async submitSupportRequest() {
      try {
        const supportData = {
          senderName: this.form.name,
          senderSurname: this.form.surname,
          senderEmail: this.form.email,
          issue: this.form.issue
        };

        const response = await this.$axios.post('/support', supportData);
        console.log(response);
        this.$router.push({ name: 'MainComponent' });
      } catch (error) {
        console.error('Ошибка при отправке запроса в поддержку:', error);
      }
    }
  }
};
</script>

<style scoped>
.support-form {
  background-color: #566E3A;
  padding: 20px;
  border-radius: 20px;
  width: 300px;
  margin: 30px auto;
  max-width: 100%;
  box-sizing: border-box;
}

.support-form h2 {
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

.form-group input, .form-group textarea {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  border: none;
  border-radius: 10px;
  background-color: #BBD49D;
  color: #566E3A;
  font-family: 'Roboto', sans-serif;
}

.form-group textarea {
  resize: vertical;
  height: 100px;
}

.alert {
  background-color: #FFD700;
  color: #566E3A;
  padding: 10px;
  border-radius: 10px;
  margin-bottom: 15px;
  text-align: center;
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

.support-button {
  margin-top: 20px;
}
</style>