<template>
  <div class="settings-container">
    <h2>Настройки</h2>
    <div class="profile-actions">
      <button @click.prevent="showChangeNameAndSurname">Смена имени и фамилии</button>
      <button @click.prevent="showChangeEmail">Смена почты</button>
      <button @click.prevent="showChangePassword">Смена пароля</button>
      <button @click.prevent="showDeleteAccount">Удалить аккаунт</button>
      <button @click.prevent="showProfile" class="back-button">Назад</button>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: 'SettingsComponent',
  methods: {
    ...mapActions(['showProfile', 'showChangeNameAndSurname', 'showChangeEmail', 'showChangePassword', 'showDeleteAccount']),
  }
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