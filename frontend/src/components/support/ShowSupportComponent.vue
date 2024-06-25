<template>
  <div class="support-container">
    <h2>Обращения пользователей</h2>
    <div v-if="supportRequests.length === 0" class="empty-requests">
      Нет обращений.
    </div>
    <div v-else>
      <div v-for="request in supportRequests" :key="request.id" class="support-item">
        <p>Имя: {{ request.senderName }}</p>
        <p>Фамилия: {{ request.senderSurname }}</p>
        <p>Email: {{ request.senderEmail }}</p>
        <p class="issue">Проблема: {{ request.issue }}</p>
        <form @submit.prevent="sendResponse(request)">
          <div class="form-group">
            <label for="response">Ответ</label>
            <textarea id="response" v-model="request.response" required></textarea>
          </div>
          <button type="submit" class="response-button">Отправить ответ</button>
          <button @click="deleteSupport(request.id)" class="delete-button">Удалить</button>
        </form>
      </div>
    </div>
  </div>
</template>


<script>
import VueCookies from 'vue-cookies';

export default {
  name: 'ShowSupportComponent',
  data() {
    return {
      supportRequests: []
    };
  },
  methods: {
    async getSupportRequests() {
      try {
        const token = VueCookies.get('accessToken');
        const response = await this.$axios.get('/support', {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
        this.supportRequests = response.data;
      } catch (error) {
        console.error('Ошибка при получении обращений:', error);
      }
    },
    async sendResponse(request) {
      try {
        const token = VueCookies.get('accessToken');
        const response = await this.$axios.post(`/support/${request.id}`, { response: request.response }, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
        console.log(response);
        await this.getSupportRequests();
      } catch (error) {
        console.error('Ошибка при отправке ответа:', error);
      }
    },
    async deleteSupport(requestId) {
      try {
        const token = VueCookies.get('accessToken');
        const response = await this.$axios.delete(`/support/${requestId}`, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
        console.log(response);
        await this.getSupportRequests();
      } catch (error) {
        console.error('Ошибка при удалении вопроса:', error);
      }
    }
  },
  created() {
    this.getSupportRequests();
  }
};
</script>


<style scoped>
.support-container {
  background-color: #566e3a;
  color: #bbd49d;
  border-radius: 10px;
  padding: 20px;
  max-width: 800px;
  margin: 0 auto;
  font-family: 'Roboto', sans-serif;
}

.empty-requests {
  text-align: center;
  font-size: 1.2rem;
}

.support-item {
  background-color: #bbd49d;
  color: #566e3a;
  border-radius: 10px;
  margin-bottom: 10px;
  padding: 10px;
}

.issue {
  word-wrap: break-word;
  white-space: pre-wrap;
}

.form-group {
  margin-bottom: 10px;
}

.form-group label {
  display: block;
  color: #566e3a;
  font-size: small;
  margin-bottom: 5px;
}

.form-group textarea {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  border: 2px solid #4d5c40;
  border-radius: 10px;
  background-color: #bbd49d;
  color: #566e3a;
  font-family: 'Roboto', sans-serif;
  resize: vertical;
  height: 100px;
}

.response-button {
  width: 100%;
  padding: 10px;
  background-color: #4d5c40;
  color: #bbd49d;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-family: 'Roboto', sans-serif;
  transition: background-color 0.3s ease;
}

.delete-button {
  width: 100%;
  padding: 10px;
  background-color: #FF4C4C;
  color: whitesmoke;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-family: 'Roboto', sans-serif;
  transition: background-color 0.3s ease;
  margin-top: 5px;
}

.response-button:hover {
  background-color: #36442d;
}

@media (max-width: 900px) {
  .support-container {
    max-width: 600px;
  }
  .empty-requests {
    font-size: 1rem;
  }
  .support-item {
    padding: 8px;
  }
}

@media (max-width: 600px) {
  .support-container {
    max-width: 400px;
    padding: 10px;
  }
  .empty-requests {
    font-size: 0.9rem;
  }
  .support-item {
    padding: 6px;
  }
}

@media (max-width: 375px) {
  .support-container {
    max-width: 300px;
    padding: 8px;
  }
  .empty-requests {
    font-size: 0.8rem;
  }
  .support-item {
    padding: 5px;
  }
}

@media (max-width: 320px) {
  .support-container {
    max-width: 280px;
    padding: 6px;
  }
  .empty-requests {
    font-size: 0.7rem;
  }
  .support-item {
    padding: 4px;
  }
}
</style>

