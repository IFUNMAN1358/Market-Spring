<template>
  <div class="product-container">
    <div class="product-header">
      <h1>{{ product.productName }}</h1>
      <img :src="`${axiosUrl}${product.imageUrl}`" :alt="product.productName" class="product-image" />
    </div>
    <div class="product-details">
      <p><strong>Тип:</strong> {{ product.productType }}</p>
      <p><strong>Бренд:</strong> {{ product.brand }}</p>
      <p><strong>Возрастная категория:</strong> {{ product.ageCategory }}</p>
      <p><strong>Тип животного:</strong> {{ product.animalType }}</p>
      <p><strong>Вес:</strong> {{ product.productWeight }} грамм</p>
      <p><strong>Количество на складе:</strong> {{ product.stockQuantity }}</p>
      <p><strong>Описание:</strong> {{ product.description }}</p>
      <p><strong>Ингредиенты:</strong> {{ product.productIngredients }}</p>
      <p><strong>Страна происхождения:</strong> {{ product.countryOfOrigin }}</p>
      <p><strong>Срок годности:</strong> {{ product.expDateMonths }} месяцев</p>
      <p><strong>Цена:</strong> {{ product.price }} рублей</p>
    </div>
    <div class="product-actions">

      <button @click="addToCart(product)" class="action-button">Добавить в корзину</button>

      <router-link :to="{name: 'CatalogComponent'}">
        <button class="action-button">Назад к каталогу</button>
      </router-link>

      <router-link v-if="isProductManager" :to="{name: 'UpdateProductComponent', params: { id: product.id }}">
        <button class="action-button">Редактировать</button>
      </router-link>

      <button v-if="isProductManager" @click="deleteProduct(product)" class="action-button">Удалить</button>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  name: 'ShowProductComponent',
  data() {
    return {
      product: {},
      axiosUrl: this.$axios.defaults.baseURL,
    }
  },
  computed: {
    ...mapState(['isProductManager']),
  },
  methods: {
    async fetchProduct() {
      try {
        const response = await this.$axios.get(`/catalog/${this.$route.params.id}`);
        this.product = response.data;
      } catch (error) {
        console.error('Ошибка при получении товара:', error);
      }
    },
    addToCart(product) {
      this.addToCart({ id: product.id });
    },
    async deleteProduct(product) {
      try {
        await this.$axios.delete(`/catalog/${product.id}`);
        this.goBackToCatalog();
      } catch (error) {
        console.error('Ошибка при удалении товара:', error);
      }
    }
  },
  mounted() {
    this.fetchProduct();
  }
}
</script>


<style scoped>
.product-container {
  background-color: #566e3a;
  color: #bbd49d;
  border-radius: 10px;
  font-family: 'Roboto', sans-serif;
  padding: 20px;
  max-width: 800px;
  margin: 0 auto;
}

.product-header {
  text-align: center;
}

.product-image {
  max-width: 100%;
  border-radius: 10px;
  margin: 10px 0;
}

.product-details {
  margin: 20px 0;
}

.product-details p {
  margin: 5px 0;
}

.product-actions {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.action-button {
  padding: 10px 20px;
  background-color: #4d5c40;
  color: #bbd49d;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  width: 100%;
  text-align: center;
}

.action-button:hover {
  background-color: #36442d;
}

@media (max-width: 768px) {
  .product-container {
    padding: 15px;
  }

  .product-actions {
    flex-direction: column;
  }
}
</style>