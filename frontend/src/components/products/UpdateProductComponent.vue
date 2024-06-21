<template>
  <div class="product-form">
    <h2>Редактировать продукт</h2>
    <form @submit.prevent="updateProduct">
      <div class="form-group">
        <label for="productName">Название продукта</label>
        <input type="text" id="productName" v-model="product.productName" required />
      </div>
      <div class="form-group">
        <label for="productType">Тип продукта</label>
        <input type="text" id="productType" v-model="product.productType" required />
      </div>
      <div class="form-group">
        <label for="brand">Бренд</label>
        <input type="text" id="brand" v-model="product.brand" required />
      </div>
      <div class="form-group">
        <label for="ageCategory">Возрастная категория</label>
        <input type="text" id="ageCategory" v-model="product.ageCategory" required />
      </div>
      <div class="form-group">
        <label for="animalType">Тип животного</label>
        <input type="text" id="animalType" v-model="product.animalType" required />
      </div>
      <div class="form-group">
        <label for="productWeight">Вес продукта (граммы)</label>
        <input type="number" id="productWeight" v-model="product.productWeight" required />
      </div>
      <div class="form-group">
        <label for="stockQuantity">Количество на складе</label>
        <input type="number" id="stockQuantity" v-model="product.stockQuantity" required />
      </div>
      <div class="form-group">
        <label for="description">Описание</label>
        <textarea id="description" v-model="product.description" required></textarea>
      </div>
      <div class="form-group">
        <label for="productIngredients">Ингредиенты</label>
        <textarea id="productIngredients" v-model="product.productIngredients" required></textarea>
      </div>
      <div class="form-group">
        <label for="countryOfOrigin">Страна происхождения</label>
        <input type="text" id="countryOfOrigin" v-model="product.countryOfOrigin" required />
      </div>
      <div class="form-group">
        <label for="expDateMonths">Срок годности (месяцы)</label>
        <input type="number" id="expDateMonths" v-model="product.expDateMonths" required />
      </div>
      <div class="form-group">
        <label for="price">Цена (рубли)</label>
        <input type="number" id="price" v-model="product.price" required />
      </div>
      <div class="form-group">
        <label for="currentImage">Текущая фотография</label>
        <img :src="`${axiosUrl}${product.imageUrl}`" :alt="product.productName" class="current-product-image" />
      </div>
      <div class="form-group">
        <label for="image">Изображение</label>
        <input type="file" id="image" @change="handleImageUpload" class="file-input" />
      </div>
      <button type="submit" class="submit-button">Обновить</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'UpdateProductComponent',

  data() {
    return {
      product: {
        productName: '',
        productType: '',
        brand: '',
        ageCategory: '',
        animalType: '',
        productWeight: '',
        stockQuantity: '',
        description: '',
        productIngredients: '',
        countryOfOrigin: '',
        expDateMonths: '',
        price: '',
        imageUrl: ''
      },
      imageFile: null,
      axiosUrl: this.$axios.defaults.baseURL
    }
  },

  methods: {
    handleImageUpload(event) {
      this.imageFile = event.target.files[0];
    },

    async fetchProduct() {
      try {
        const response = await this.$axios.get(`/catalog/${this.$route.params.id}`);
        this.product = response.data;
      } catch (error) {
        console.error('Ошибка при получении товара:', error);
      }
    },

    async updateProduct() {
      try {
        const formData = new FormData();
        for (let key in this.product) {
          formData.append(key, this.product[key]);
        }
        if (this.imageFile) {
          formData.append('image', this.imageFile);
        }

        const token = this.$cookies.get('accessToken');
        const response = await this.$axios.patch(`/catalog/${this.$route.params.id}`, formData, {
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'multipart/form-data'
          }
        });
        console.log(response.data);
        this.$router.push({ name: 'ShowProductComponent', params: { id: this.$route.params.id } });
      } catch (error) {
        console.error('Ошибка при обновлении продукта:', error.response ? error.response.data : error.message);
      }
    }
  },

  mounted() {
    this.fetchProduct();
  }
};
</script>

<style scoped>
.product-form {
  background-color: #566E3A;
  padding: 20px;
  border-radius: 20px;
  width: 300px;
  margin: 30px auto;
  max-width: 100%;
  box-sizing: border-box;
}

.product-form h2 {
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

.form-group .file-input {
  background-color: #BBD49D;
  color: #566E3A;
}

.form-group .current-product-image {
  width: 100%;
  margin-top: 10px;
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

.submit-button {
  margin-top: 20px;
}
</style>
