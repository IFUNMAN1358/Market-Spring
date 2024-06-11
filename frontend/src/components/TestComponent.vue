<template>
  <div>
    <h1>Response from Server</h1>
    <div v-if="response">
      {{ response }}
    </div>
    <div v-else>
      Loading...
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'TestComponent',
  data() {
    return {
      response: null,
      loading: true,
      error: null
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      try {
        const res = await axios.get('http://localhost:8000/test');
        this.response = res.data;
      } catch (error) {
        this.error = 'Error fetching data';
        console.error('Error fetching data:', error);
      } finally {
        this.loading = false;
      }
    }
  }
};
</script>

<style scoped>
/* Добавьте стили, если необходимо */
</style>