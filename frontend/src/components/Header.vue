<template>
  <footer class="footer">
    <p>&copy; {{ currentYear }} Мой сайт. Все права защищены.</p>
  </footer>
</template>

<script>
export default {
  name: 'FooterComponent',
  data() {
    return {
      currentYear: new Date().getFullYear()
    };
  }
};
</script>

<style scoped>
.footer {
  background-color: #333;
  color: #fff;
  padding: 20px;
  text-align: center;
}
</style>