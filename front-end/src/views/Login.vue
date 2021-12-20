<template>
  <div class="login">
    <div>
      <form @submit.prevent="submit">
        <div>
          <label for="username">Username:</label>
          <input type="text" name="username" v-model="form.username" />
        </div>
        <div>
          <label for="password">Password:</label>
          <input type="password" name="password" v-model="form.password" />
        </div>
        <button type="submit">Submit</button>
      </form>
      <p v-if="showError" id="error"><pre>{{ errorLogin }}</pre></p>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "Login",
  components: {},
  data() {
    return {
      form: {
        username: "",
        password: "",
      },
      errorLogin: "",
      showError: false,
    };
  },
  methods: {
    ...mapActions(["loginAction"]),
    async submit() {
      if (this.form.username.length === 0) {
        this.errorLogin += "Le nom d'utilisateur est obligatoire ! \n";
      }
      if (this.form.password.length === 0) {
        this.errorLogin += "Le mot de passe est obligatoire ! \n";
      }

      const user = {
        username: this.form.username,
        password: this.form.password
      }
      try {
        await this.loginAction(user);
        this.$router.push("/");
        this.showError = false;
        this.errorLogin = "";
      } catch (error) {
        this.showError = true;
        this.errorLogin += "La tentative de connection a échoué ! \n";
        console.log(error);
      }
    },
  },
};
</script>

<style scoped>
* {
  box-sizing: border-box;
}

label {
  padding: 12px 12px 12px 0;
  display: inline-block;
}

button[type="submit"] {
  background-color: #4caf50;
  color: white;
  padding: 12px 20px;
  cursor: pointer;
  border-radius: 30px;
}

button[type="submit"]:hover {
  background-color: #45a049;
}

input {
  margin: 5px;
  box-shadow: 0 0 15px 4px rgba(0, 0, 0, 0.06);
  padding: 10px;
  border-radius: 30px;
}
#error {
  color: red;
}
</style>
