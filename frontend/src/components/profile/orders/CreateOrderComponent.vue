<template>
  <div class="order-form">
    <h2>Оформление заказа</h2>
    <form @submit.prevent="submitOrder">
      <div class="form-group">
        <label for="name">Имя</label>
        <input type="text" id="name" v-model="form.name" required/>
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
        <label for="phone">Телефон</label>
        <input type="text" id="phone" v-model="form.phone" required />
      </div>
      <div class="form-group">
        <label for="postalCode">Почтовый индекс</label>
        <input type="text" id="postalCode" v-model="form.postalCode" required />
      </div>
      <div class="form-group">
        <label for="city">Город</label>
        <input type="text" id="city" v-model="form.city" required />
      </div>
      <div class="form-group">
        <label for="street">Улица</label>
        <input type="text" id="street" v-model="form.street" required />
      </div>
      <div class="form-group">
        <label for="houseNumber">Номер дома</label>
        <input type="text" id="houseNumber" v-model="form.houseNumber" required />
      </div>
      <button type="submit" class="order-button">Заказать</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "CreateOrderComponent",
  data() {
    return {
      form: {
        name: "",
        surname: "",
        email: "",
        phone: "",
        postalCode: "",
        city: "",
        street: "",
        houseNumber: ""
      }
    };
  },
  mounted() {
    this.fetchUserData();
  },
  methods: {
    async fetchUserData() {
      try {
        const response = await this.$axios.get("/user", {
          headers: {
            Authorization: `Bearer ${this.$cookies.get("accessToken")}`
          }
        });
        const userData = response.data;
        this.form.name = userData.name;
        this.form.surname = userData.surname;
        this.form.email = userData.email;
      } catch (error) {
        console.error("Ошибка при получении данных пользователя:", error);
      }
    },
    async submitOrder() {
      try {
        const orderData = {
          name: this.form.name,
          surname: this.form.surname,
          email: this.form.email,
          phone: this.form.phone,
          postalCode: this.form.postalCode,
          city: this.form.city,
          street: this.form.street,
          houseNumber: this.form.houseNumber
        };

        const response = await this.$axios.post("/order",
            orderData,
            {headers: { Authorization: `Bearer ${this.$cookies.get("accessToken")}` }}
        );
        console.log(response);
        this.$router.push({name: 'UserOrdersComponent'});
      } catch (error) {
        console.error("Ошибка при оформлении заказа:", error);
      }
    }
  }
};
</script>

<style scoped>
.order-form {
  background-color: #566E3A;
  padding: 20px;
  border-radius: 20px;
  width: 300px;
  margin: 30px auto;
  max-width: 100%;
  box-sizing: border-box;
}

.order-form h2 {
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

.order-button {
  margin-top: 20px;
}
</style>