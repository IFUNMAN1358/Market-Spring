<template>
  <div class="profile-container">
    <h2>Профиль</h2>
    <div v-if="user" class="profile-details">
      <p><strong>Имя:</strong> {{ user.name }}</p>
      <p><strong>Фамилия:</strong> {{ user.surname }}</p>
      <p><strong>Email:</strong> {{ user.email }}</p>
    </div>
    <div class="profile-actions">
      <button @click="fetchOrderHistory">История заказов</button>
      <button @click="logout">Выйти из аккаунта</button>
<!--      <button @click="deleteAccount" class="delete-account">Удалить аккаунт</button>-->
    </div>
    <div v-if="orders.length" class="order-history">
      <h3>История заказов</h3>
      <ul>
        <li v-for="order in orders" :key="order.id">
          <p><strong>Номер заказа:</strong> {{ order.id }}</p>
          <p><strong>Дата:</strong> {{ order.date }}</p>
          <p><strong>Сумма:</strong> {{ order.amount }} руб.</p>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { mapActions } from 'vuex';

export default {
  name: 'ProfileComponent',
  data() {
    return {
      user: null,
      orders: []
    };
  },
  methods: {
    ...mapActions(['removeTokens']),
    async fetchUserData() {
      try {
        const token = this.$cookies.get('accessToken');
        const response = await axios.get('/user', {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
        this.user = response.data;
      } catch (error) {
        console.error('Ошибка при получении данных пользователя:', error.response ? error.response.data : error.message);
      }
    },
    // async fetchOrderHistory() {
    //   try {
    //     const response = await axios.get('/user/orders');
    //     this.orders = response.data.orders;
    //   } catch (error) {
    //     console.error('Ошибка при получении истории заказов:', error.response ? error.response.data : error.message);
    //   }
    // },
    async logout() {
      try {
        await this.removeTokens();
        this.$emit('closeProfile');
      } catch (error) {
        console.error('Ошибка при выходе из аккаунта:', error.response ? error.response.data : error.message);
      }
    },
    // async deleteAccount() {
    //   if (confirm('Вы уверены, что хотите удалить свой аккаунт? Это действие необратимо.')) {
    //     try {
    //       await axios.delete('/user/profile');
    //       this.$store.dispatch('logout');
    //       this.$router.push('/register');
    //     } catch (error) {
    //       console.error('Ошибка при удалении аккаунта:', error.response ? error.response.data : error.message);
    //     }
    //   }
    // }
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

.order-history {
  list-style: none;
  padding: 0;
}

.profile-actions {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.profile-actions button {
  width: 100%;
  max-width: 200px;
  padding: 10px;
  background-color: #BBD49D;
  color: #566E3A;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  font-family: 'Roboto', sans-serif;
  margin: 5px 0;
}

.profile-actions button:hover {
  background-color: #a7bf8f;
}

.profile-actions .delete-account {
  background-color: #FF6347;
  color: white;
}

.profile-actions .delete-account:hover {
  background-color: #ff4d33;
}
</style>