<template>
  <div class="settings-container">
    <h2>Настройки</h2>
    <div class="profile-actions">
        <button @click="$router.push({name: 'ChangeNameAndSurnameComponent'})" class="action-button">Смена имени и фамилии</button>
        <button @click="$router.push({name: 'ChangeEmailComponent'})" class="action-button">Смена почты</button>
        <button @click="$router.push({name: 'ChangePasswordComponent'})" class="action-button">Смена пароля</button>
        <button @click="$router.push({name: 'DeleteAccountComponent'})" class="action-button">Удалить аккаунт</button>
        <button @click="$router.push({name: 'ProfileComponent'})" class="action-button">Назад</button>
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

.settings-container h2 {
  text-align: center;
  font-weight: bold;
}

.settings-container p,
.order-history li {
  margin-bottom: 10px;
}

.profile-actions {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.action-button {
  width: 100%;
  padding: 10px;
  background-color: #BBD49D;
  color: #566E3A;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  font-family: 'Roboto', sans-serif;
  margin: 5px 0;
}

.action-button:hover {
  background-color: #a7bf8f;
}
</style>