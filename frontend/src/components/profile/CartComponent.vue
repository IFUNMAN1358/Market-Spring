<template>
  <div class="cart-container">
    <h2>Ваша корзина</h2>
    <div v-if="cart.length === 0" class="empty-cart">
      Ваша корзина пуста.
    </div>
    <div v-else>
      <div v-for="product in cart" :key="product.id" class="cart-item">
        <div class="cart-item-info" @click="$router.push({ name: 'ShowProductComponent', params: { id: product.id } })">
          <img :src="`${axiosUrl}${product.imageUrl}`" :alt="product.productName" class="cart-item-image" />
          <div class="cart-item-details">
            <h3>{{ product.productName }}</h3>
            <p>Вес: {{ product.productWeight }} гр.</p>
            <p>Цена: {{ product.price }} руб.</p>
          </div>
        </div>
        <button @click="removeFromCart(product.id)" class="remove-from-cart">Удалить</button>
      </div>
      <button @click="$router.push({name: 'CreateOrderComponent'})" class="checkout-button">Заказать</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import VueCookies from "vue-cookies";

export default {
  name: 'CartComponent',
  data() {
    return {
      axiosUrl: this.$axios.defaults.baseURL,
      cart: []
    };
  },
  methods: {
    async getCart() {
      try {
        const token = VueCookies.get("accessToken");
        const response = await axios.get("/cart", {
          headers: {
            "Authorization": `Bearer ${token}`
          }
        });
        this.cart = response.data;
      } catch (error) {
        console.error("Ошибка при получении корзины:", error);
      }
    },
    async removeFromCart(productId) {
      try {
        const token = VueCookies.get("accessToken");
        await axios.delete(`/cart/${productId}`, {
          headers: {
            "Authorization": `Bearer ${token}`
          }
        });
        await this.getCart();
      } catch (error) {
        console.error("Ошибка при удалении товара из корзины:", error);
      }
    },
  },
  created() {
    this.getCart();
  }
};
</script>

<style scoped>
.cart-container {
  background-color: #566e3a;
  color: #bbd49d;
  border-radius: 10px;
  padding: 20px;
  max-width: 800px;
  margin: 0 auto;
  font-family: 'Roboto', sans-serif;
}

.empty-cart {
  text-align: center;
  font-size: 1.2rem;
}

.cart-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #bbd49d;
  color: #566e3a;
  border-radius: 10px;
  margin-bottom: 10px;
  padding: 10px;
}

.cart-item-info {
  display: flex;
  align-items: center;
  cursor: pointer;
}

.cart-item-image {
  width: 80px;
  height: 80px;
  border-radius: 10px;
  object-fit: cover;
  margin-right: 10px;
}

.cart-item-details {
  display: flex;
  flex-direction: column;
}

.remove-from-cart {
  background-color: #a7bf8f;
  color: #566e3a;
  border: none;
  border-radius: 5px;
  padding: 8px 16px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.remove-from-cart:hover {
  background-color: #92ac79;
}

.checkout-button {
  background-color: #4d5c40;
  color: #bbd49d;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  width: 100%;
  text-align: center;
  margin-top: 10px;
}

.checkout-button:hover {
  background-color: #36442d;
}

@media (max-width: 900px) {
  .cart-container {
    max-width: 600px;
  }
  .empty-cart {
    font-size: 1rem;
  }
  .cart-item {
    padding: 8px;
  }
  .cart-item-image {
    width: 60px;
    height: 60px;
    margin-right: 8px;
  }
  .cart-item-details {
    font-size: 0.9rem;
  }
  .remove-from-cart {
    padding: 6px 12px;
  }
  .checkout-button {
    padding: 8px 16px;
    font-size: 0.9rem;
  }
}

@media (max-width: 600px) {
  .cart-container {
    max-width: 400px;
    padding: 10px;
  }
  .empty-cart {
    font-size: 0.9rem;
  }
  .cart-item {
    padding: 6px;
  }
  .cart-item-image {
    width: 50px;
    height: 50px;
    margin-right: 6px;
  }
  .cart-item-details {
    font-size: 0.8rem;
  }
  .remove-from-cart {
    padding: 6px 10px;
  }
  .checkout-button {
    padding: 6px 12px;
    font-size: 0.8rem;
  }
}

@media (max-width: 375px) {
  .cart-container {
    max-width: 300px;
    padding: 8px;
  }
  .empty-cart {
    font-size: 0.8rem;
  }
  .cart-item {
    padding: 5px;
  }
  .cart-item-image {
    width: 40px;
    height: 40px;
    margin-right: 5px;
  }
  .cart-item-details {
    font-size: 0.7rem;
  }
  .cart-item-details h3 {
    font-size: 0.7rem;
  }
  .remove-from-cart {
    padding: 5px 8px;
    font-size: 0.7rem;
  }
  .checkout-button {
    padding: 5px 10px;
    font-size: 0.7rem;
  }
}

@media (max-width: 320px) {
  .cart-container {
    max-width: 280px;
    padding: 6px;
  }
  .empty-cart {
    font-size: 0.7rem;
  }
  .cart-item {
    padding: 4px;
  }
  .cart-item-image {
    width: 36px;
    height: 36px;
    margin-right: 4px;
  }
  .cart-item-details {
    font-size: 0.6rem;
  }
  .cart-item-details h3 {
    font-size: 0.6rem;
  }
  .remove-from-cart {
    padding: 4px 6px;
    font-size: 0.6rem;
  }
  .checkout-button {
    padding: 4px 8px;
    font-size: 0.6rem;
  }
}
</style>