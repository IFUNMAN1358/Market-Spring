<template>
  <div class="main-container">
    <router-link v-if="isProductManager" :to="{ name: 'CreateProductComponent' }">
      <button class="add-product-button">Добавить продукт</button>
    </router-link>
    <div class="intro">
      <div class="intro-text">
        <input v-model="searchQuery" @input="filterProducts" placeholder="Поиск..." class="search-input" />
      </div>
      <div class="filters">
        <div class="filter-group">
          <label for="filter-type">Тип продукта:</label>
          <select id="filter-type" v-model="filters.productType" @change="filterProducts" class="filter-select">
            <option value="">Все</option>
            <option value="dry">Сухой</option>
            <option value="wet">Влажный</option>
          </select>
        </div>
        <div class="filter-group">
          <label for="filter-brand">Бренд:</label>
          <select id="filter-brand" v-model="filters.brand" @change="filterProducts" class="filter-select">
            <option value="">Все</option>
            <option value="PurinaOne">PurinaOne</option>
            <option value="Whiskas">Whiskas</option>
            <option value="Kitekat">Kitekat</option>
          </select>
        </div>
        <div class="filter-group">
          <label for="filter-age">Возрастная категория:</label>
          <select id="filter-age" v-model="filters.ageCategory" @change="filterProducts" class="filter-select">
            <option value="">Все</option>
            <option value="children">Для детей</option>
            <option value="adult">Для взрослых</option>
            <option value="senior">Для пожилых</option>
          </select>
        </div>
        <div class="filter-group">
          <label for="filter-animal">Тип животного:</label>
          <select id="filter-animal" v-model="filters.animalType" @change="filterProducts" class="filter-select">
            <option value="">Все</option>
            <option value="cat">Кошачий</option>
            <option value="dog">Собачий</option>
          </select>
        </div>
      </div>
    </div>
    <div class="catalog">
      <div class="products-wrapper">
        <div class="products">
          <div v-for="product in paginatedProducts" :key="product.id" class="product" @click="viewProduct(product.id)">
            <img :src="`${axiosUrl}${product.imageUrl}`" :alt="product.productName" class="product-image" />
            <div class="product-info">
              <h3 class="product-name">{{ product.productName }}</h3>
              <p class="product-weight">Вес: {{ product.productWeight }} гр.</p>
              <p class="product-price">Цена: {{ product.price }} руб.</p>
            </div>
            <button @click.stop="addToCart(product)" class="add-to-cart">
              <img :src="CartIcon" alt="Добавить в корзину" />
            </button>
          </div>
        </div>
      </div>
      <button class="scroll-to-top" @click="scrollToTop" v-if="scrollPosition > 100">Наверх</button>
    </div>
  </div>
</template>

<script>
import debounce from 'lodash/debounce';
import { mapState } from 'vuex';

import CartIcon from '@/img/CartIcon.png';

export default {
  name: 'CatalogComponent',


  data() {
    return {
      axiosUrl: this.$axios.defaults.baseURL,
      CartIcon: CartIcon,
      products: new Set(),
      searchQuery: '',
      filters: {
        productType: '',
        brand: '',
        ageCategory: '',
        animalType: '',
      },
      currentPage: 1,
      productsPerPage: 20,
      scrollPosition: 0,
    };
  },

  computed: {
    ...mapState(["isProductManager"]),
    filteredProducts() {
      return Array.from(this.products).filter(product => {
        return (
          product.productName.toLowerCase().includes(this.searchQuery.toLowerCase()) &&
          (this.filters.productType ? product.productType === this.filters.productType : true) &&
          (this.filters.brand ? product.brand === this.filters.brand : true) &&
          (this.filters.ageCategory ? product.ageCategory === this.filters.ageCategory : true) &&
          (this.filters.animalType ? product.animalType === this.filters.animalType : true)
        );
      });
    },
    paginatedProducts() {
      return this.filteredProducts.slice(0, this.currentPage * this.productsPerPage);
    },
  },


  methods: {
    viewProduct(productId) {
      this.$router.push({ name: 'ShowProductComponent', params: { id: productId } });
    },
    addToCart(productId) {
      this.addToCart({ id: productId });
    },


    async getProducts(offset = 0, limit = 20, search = '', productType = '', brand = '', ageCategory = '', animalType = '') {
      try {
        const response = await this.$axios.get('/catalog', {
          params: { offset, limit, search, productType, brand, ageCategory, animalType }
        });

        const newProducts = new Set(this.products);
        response.data.forEach(product => newProducts.add(product));

        this.products = newProducts;
      } catch (error) {
        console.error('Ошибка при получении товаров:', error);
      }
    },
    async filterProducts() {
      this.currentPage = 1;
      const newProducts = await this.fetchFilteredProducts();
      this.products = new Set(newProducts);
    },
    async fetchFilteredProducts() {
      try {
        const response = await this.$axios.get('/catalog', {
          params: {
            offset: 0,
            limit: this.productsPerPage * this.currentPage,
            search: this.searchQuery,
            productType: this.filters.productType,
            brand: this.filters.brand,
            ageCategory: this.filters.ageCategory,
            animalType: this.filters.animalType
          }
        });
        return response.data;
      } catch (error) {
        console.error('Ошибка при получении товаров:', error);
        return [];
      }
    },
    async loadMoreProducts() {
      this.currentPage++;
      await this.getProducts((this.currentPage - 1) * this.productsPerPage, this.productsPerPage, this.searchQuery, this.filters.productType, this.filters.brand, this.filters.ageCategory, this.filters.animalType);
    },
    scrollToTop() {
      window.scrollTo({ top: 0, behavior: 'smooth' });
    },
    handleScroll() {
      this.scrollPosition = window.scrollY;
      const bottomOfWindow = document.documentElement.scrollTop + window.innerHeight === document.documentElement.offsetHeight;
      if (bottomOfWindow) {
        this.loadMoreProducts();
      }
    },
  },
  created() {
    this.getProducts();
    window.addEventListener('scroll', this.handleScroll);
    this.debouncedFilterProducts = debounce(this.filterProducts, 300);
  },
  unmounted() {
    window.removeEventListener('scroll', this.handleScroll);
  },
  watch: {
    searchQuery: 'debouncedFilterProducts',
    'filters.productType': 'debouncedFilterProducts',
    'filters.brand': 'debouncedFilterProducts',
    'filters.ageCategory': 'debouncedFilterProducts',
    'filters.animalType': 'debouncedFilterProducts',
  },
};
</script>

<style scoped>
.main-container {
  background-color: #566e3a;
  color: #bbd49d;
  border-radius: 10px;
  font-family: 'Roboto', sans-serif;
  padding: 20px 25px;
  max-width: calc(1800px + 25px);
  margin: 0 auto;
  width: 100%;
  position: relative;
}

.add-product-button {
  position: absolute;
  top: 20px;
  right: 25px;
  padding: 10px 20px;
  background-color: #4d5c40;
  color: #bbd49d;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.add-product-button:hover {
  background-color: #36442d;
}

.intro {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 20px;
}

.intro-text {
  text-align: center;
}

.search-input {
  background-color: #bbd49d;
  color: #566e3a;
  border: none;
  border-radius: 5px;
  padding: 10px;
  width: 100%;
  max-width: 800px;
  margin-bottom: 10px;
}

.filters {
  margin-top: 10px;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.filter-group {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 5px;
}

.filter-select {
  background-color: #bbd49d;
  color: #566e3a;
  border: none;
  border-radius: 5px;
  padding: 5px 10px;
  margin-top: 5px;
}

.catalog {
  margin-bottom: 20px;
  min-height: 500px;
}

.products-wrapper {
  display: flex;
  justify-content: center;
}

.products {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  max-width: 1500px;
  width: 100%;
}

.product {
  background-color: #bbd49d;
  color: #566e3a;
  border-radius: 10px;
  padding: 10px;
  margin: 10px 3px;
  width: calc(25% - 5px);
  text-align: center;
  cursor: pointer;
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  transition: transform 0.2s ease-in-out;
}

.product:hover {
  transform: translateY(-5px);
}

.product-image {
  max-width: 100%;
  border-radius: 10px;
}

.product-info {
  margin-top: 10px;
  flex-grow: 1;
  margin-bottom: 40px;
}

.product-name {
  font-size: 1rem;
  font-weight: bold;
}

.product-weight,
.product-price {
  margin-top: 5px;
  font-size: 0.9rem;
}

.add-to-cart {
  padding: 8px 16px;
  background-color: #a7bf8f;
  color: #566e3a;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  position: absolute;
  bottom: 10px;
  left: 50%;
  transform: translateX(-50%);
  width: calc(100% - 20px);
}

.add-to-cart:hover {
  background-color: #92ac79;
}

.add-to-cart img {
  width: 20px;
  height: auto;
}

.scroll-to-top {
  position: fixed;
  bottom: 20px;
  right: 20px;
  padding: 20px 35px;
  background-color: #4d5c40;
  color: #bbd49d;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  font-weight: bold;
  font-size: 1rem;
}

.scroll-to-top:hover {
  background-color: #36442d;
}

@media (max-width: 768px) {
  .products {
    justify-content: center;
  }

  .product {
    width: calc(45% - 5px);
  }

  .product-name {
    font-size: 0.9rem;
  }

  .product-weight,
  .product-price {
    font-size: 0.8rem;
  }

  .add-to-cart {
    padding: 8px 16px;
    bottom: 5px;
  }
}
</style>
