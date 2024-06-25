<template>
  <div class="role-container">
    <h2>Смена роли</h2>
    <div class="form-group">
      <label for="ident">Email or ID:</label>
      <input type="text" v-model="ident" id="ident" />
      <button class="role-button" @click="getUser">Get User</button>
    </div>
    <div v-if="user">
      <div class="role-item">
        <p>User: {{ user.name }} {{ user.surname }}</p>
        <p>Email: {{ user.email }}</p>
        <p>Roles: {{ user.roles.join(', ') }}</p>
      </div>
      <div class="form-group">
        <label for="newRole">Add Role:</label>
        <select v-model="newRole" id="newRole">
          <option value="ROLE_ADMIN">ROLE_ADMIN</option>
          <option value="ROLE_PRODUCT_MANAGER">ROLE_PRODUCT_MANAGER</option>
          <option value="ROLE_SUPPORT">ROLE_SUPPORT</option>
        </select>
        <button class="role-button" @click="addRole">Add Role</button>
      </div>
      <div class="form-group">
        <label for="roleToDelete">Delete Role:</label>
        <select v-model="roleToDelete" id="roleToDelete">
          <option v-for="role in user.roles" :key="role" :value="role">{{ role }}</option>
        </select>
        <button class="delete-button" @click="deleteRole">Delete Role</button>
      </div>
    </div>
  </div>
</template>

<script>
import VueCookies from "vue-cookies";

export default {
  name: "RoleComponent",
  data() {
    return {
      ident: '',
      user: null,
      newRole: '',
      roleToDelete: ''
    };
  },
  methods: {
    async getUser() {
      try {
        const token = VueCookies.get("accessToken");
        const response = await this.$axios.get(`/role/${this.ident}`,
            {headers: {'Authorization': `Bearer ${token}`}});
        this.user = response.data;
      } catch (error) {
        console.error("Error fetching user:", error);
      }
    },
    async addRole() {
      try {
        const token = VueCookies.get("accessToken");
        const response = await this.$axios.post(
            `/role/${this.ident}`,
            { role: this.newRole },
            {headers: {'Authorization': `Bearer ${token}`}}
        );
        this.user = response.data;
      } catch (error) {
        console.error("Error adding role:", error);
      }
    },
    async deleteRole() {
      try {
        const token = VueCookies.get("accessToken");
        const response = await this.$axios.delete(`/role/${this.ident}`, {
          headers: { 'Authorization': `Bearer ${token}` },
          data: { role: this.roleToDelete }
        });
        this.user = response.data;
      } catch (error) {
        console.error("Error deleting role:", error);
      }
    }
  }
};
</script>

<style scoped>
.role-container {
  background-color: #566e3a;
  color: #bbd49d;
  border-radius: 10px;
  padding: 20px;
  max-width: 800px;
  margin: 0 auto;
  font-family: 'Roboto', sans-serif;
}

.role-item {
  background-color: #bbd49d;
  color: #566e3a;
  border-radius: 10px;
  margin-bottom: 10px;
  padding: 10px;
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

.form-group textarea,
.form-group select,
.form-group input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  border: 2px solid #4d5c40;
  border-radius: 10px;
  background-color: #bbd49d;
  color: #566e3a;
  font-family: 'Roboto', sans-serif;
  resize: vertical;
}

.role-button {
  width: 100%;
  padding: 10px;
  background-color: #4d5c40;
  color: #bbd49d;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-family: 'Roboto', sans-serif;
  transition: background-color 0.3s ease;
  margin-top: 5px;
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

.role-button:hover {
  background-color: #36442d;
}

@media (max-width: 900px) {
  .role-container {
    max-width: 600px;
  }
  .role-item {
    padding: 8px;
  }
}

@media (max-width: 600px) {
  .role-container {
    max-width: 400px;
    padding: 10px;
  }
  .role-item {
    padding: 6px;
  }
}

@media (max-width: 375px) {
  .role-container {
    max-width: 300px;
    padding: 8px;
  }
  .role-item {
    padding: 5px;
  }
}

@media (max-width: 320px) {
  .role-container {
    max-width: 280px;
    padding: 6px;
  }
  .role-item {
    padding: 4px;
  }
}
</style>