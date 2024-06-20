<template>
  <div class="settings-container">
    <h2>Настройки</h2>
    <div class="profile-actions">
      <router-link :to="{ name: 'ChangeNameAndSurnameComponent' }">
        <button>Смена имени и фамилии</button>
      </router-link>
      <router-link :to="{ name: 'ChangeEmailComponent' }">
        <button>Смена почты</button>
      </router-link>
      <router-link :to="{ name: 'ChangePasswordComponent' }">
        <button>Смена пароля</button>
      </router-link>
      <router-link :to="{ name: 'DeleteAccountComponent' }">
        <button>Удалить аккаунт</button>
      </router-link>
      <router-link :to="{ name: 'ProfileComponent' }">
        <button class="back-button">Назад</button>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SettingsComponent'
}
</script>

<style scoped>
.settings-container {
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

.profile-container h2 {
  text-align: center;
  font-weight: bold;
}

.profile-details p,
.order-history li {
  margin-bottom: 10px;
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

.profile-actions .back-button {
  margin-top: 20px;
  width: 100%;
  max-width: 200px;
  padding: 10px;
  background-color: #BBD49D;
  color: #566E3A;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  font-family: 'Roboto', sans-serif;
}

.profile-actions .back-button:hover {
  background-color: #a7bf8f;
}
</style>