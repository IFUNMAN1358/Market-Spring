<template>
  <div class="access-denied-container">
    <div class="access-denied-content">
      <h2>Доступ запрещён</h2>
      <p>У вас нет прав доступа к этому ресурсу.</p>
      <button @click="$router.push({name: 'MainComponent'})">Вернуться на главную</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AccessDeniedComponent'
};
</script>

<style scoped>
.access-denied-container {
  background-color: #566E3A;
  color: #BBD49D;
  border-radius: 10px;
  font-family: 'Roboto', sans-serif;
  padding: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 50vh;
}

.access-denied-content {
  text-align: center;
}

button {
  padding: 10px;
  background-color: #BBD49D;
  color: #566E3A;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  font-family: 'Roboto', sans-serif;
  margin-top: 20px;
}

button:hover {
  background-color: #a7bf8f;
}
</style>