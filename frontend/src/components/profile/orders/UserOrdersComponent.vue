<template>
  <div class="orders-container">
    <h2>Мои заказы</h2>
    <button @click="$router.push({ name: 'ProfileComponent' })" class="back-to-profile-button">Вернуться в профиль</button>
    <div v-if="orders.length === 0" class="empty-orders">
      У вас пока нет заказов.
    </div>
    <div v-else>
      <div v-for="order in sortedOrders" :key="order.orderId" class="order-item">
        <p>Данные: {{ order.name }} {{ order.surname }} </p>
        <p>Email: {{ order.email }}</p>
        <p>Телефон: {{ order.phone }}</p>
        <p>Адрес: {{ formatAddress(order) }}</p>
        <p>Дата: {{ formatDate(order.orderDate) }}</p>
        <p>Продукты:</p>
        <ul>
          <li v-for="product in order.products" :key="product.id">
            {{ product.productName }} {{ product.price }} руб.
          </li>
        </ul>
        <p>Общая сумма: {{ order.totalAmount }} руб.</p>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import VueCookies from 'vue-cookies';

export default {
  name: 'UserOrdersComponent',
  data() {
    return {
      orders: []
    };
  },
  computed: {
    sortedOrders() {
      return this.orders.slice().sort((a, b) => new Date(b.orderDate) - new Date(a.orderDate));
    }
  },
  methods: {
    async getOrders() {
      try {
        const token = VueCookies.get('accessToken');
        const response = await axios.get('/order', {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
        this.orders = response.data;
      } catch (error) {
        console.error('Ошибка при получении заказов:', error);
      }
    },
    formatDate(date) {
      const options = { year: 'numeric', month: 'long', day: 'numeric', hour: 'numeric', minute: 'numeric' };
      return new Date(date).toLocaleDateString('ru-RU', options);
    },
    formatAddress(order) {
      return `${order.postalCode}, ${order.city}, ${order.street}, ${order.houseNumber}`;
    }
  },
  created() {
    this.getOrders();
  }
};
</script>

<style scoped>
.orders-container {
  background-color: #566e3a;
  color: #bbd49d;
  border-radius: 10px;
  padding: 20px;
  max-width: 800px;
  margin: 0 auto;
  font-family: 'Roboto', sans-serif;
}

.empty-orders {
  text-align: center;
  font-size: 1.2rem;
}

.order-item {
  background-color: #bbd49d;
  color: #566e3a;
  border-radius: 10px;
  margin-bottom: 10px;
  padding: 10px;
}

.back-to-profile-button {
  background-color: #4d5c40;
  color: #bbd49d;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  width: 100%;
  text-align: center;
  margin-bottom: 10px;
}

.back-to-profile-button:hover {
  background-color: #36442d;
}

@media (max-width: 900px) {
  .orders-container {
    max-width: 600px;
  }
  .empty-orders {
    font-size: 1rem;
  }
  .order-item {
    padding: 8px;
  }
}

@media (max-width: 600px) {
  .orders-container {
    max-width: 400px;
    padding: 10px;
  }
  .empty-orders {
    font-size: 0.9rem;
  }
  .order-item {
    padding: 6px;
  }
}

@media (max-width: 375px) {
  .orders-container {
    max-width: 300px;
    padding: 8px;
  }
  .empty-orders {
    font-size: 0.8rem;
  }
  .order-item {
    padding: 5px;
  }
}

@media (max-width: 320px) {
  .orders-container {
    max-width: 280px;
    padding: 6px;
  }
  .empty-orders {
    font-size: 0.7rem;
  }
  .order-item {
    padding: 4px;
  }
}
</style>