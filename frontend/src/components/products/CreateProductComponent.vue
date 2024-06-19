<template>
  <div class="product-form">
    <h2>Создать продукт</h2>
    <form @submit.prevent="createProduct">
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
        <label for="image">Изображение</label>
        <input type="file" id="image" @change="handleImageUpload" class="file-input" required />
      </div>
      <button type="submit" class="submit-button">Создать</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'ProductForm',
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
      imageFile: null
    }
  },
  methods: {
    handleImageUpload(event) {
      this.imageFile = event.target.files[0];
    },
    async createProduct() {
      try {
        const formData = new FormData();
        for (let key in this.product) {
          formData.append(key, this.product[key]);
        }
        formData.append('image', this.imageFile);

        const response = await this.$axios.post('/products', formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        });
        console.log(response.data);
        this.$emit('closeCreateProduct', response.data);
      } catch (error) {
        console.error('Ошибка при создании продукта:', error.response ? error.response.data : error.message);
      }
    }
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