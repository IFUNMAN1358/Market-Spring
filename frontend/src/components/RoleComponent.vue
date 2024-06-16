<template>
  <div class="test-component">
    <h2>Тестирование токенов</h2>
    <button @click="testUser">USER</button>
    <button @click="testAdmin">ADMIN</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'RoleComponent',
  methods: {
    async testUser() {
      try {
        const token = this.$cookies.get('accessToken');
        const response = await axios.get('/user', {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
        console.log('User response:', response.data);
        this.$emit('roled');
      } catch (error) {
        console.error('Error with USER request:', error.response ? error.response.data : error.message);
      }
    },
    async testAdmin() {
      try {
        const token = this.$cookies.get('accessToken');
        const response = await axios.get('/admin', {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
        console.log('Admin response:', response.data);
      } catch (error) {
        console.error('Error with ADMIN request:', error.response ? error.response.data : error.message);
      }
    }
  }
};
</script>

<style scoped>
.test-component {
  background-color: #566E3A;
  padding: 20px;
  border-radius: 20px;
  width: 300px;
  margin: 30px auto;
  max-width: 100%;
  box-sizing: border-box;
}

.test-component h2 {
  color: #BBD49D;
  font-family: 'Roboto', sans-serif;
  font-weight: bold;
  text-align: center;
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
  margin-bottom: 10px;
}

button:hover {
  background-color: #a7bf8f;
}
</style>