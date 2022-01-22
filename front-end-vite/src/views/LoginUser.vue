<template>
  <form @submit.prevent="login">
    <fieldset class="loginForm">
      <legend>Connexion</legend>
      <div class="field">
        <BaseInput v-model="username" label="Username" type="text" />
      </div>
      <div class="field">
        <BaseInput v-model="password" label="Password" type="password" />
      </div>

      <button type="submit" name="button">Login</button>

      <p>{{ error }}</p>

      <router-link to="/register">
        Don't have an account? Register.
      </router-link>
    </fieldset>
  </form>
</template>

<script>
export default {
  data() {
    return {
      username: "",
      password: "",
      error: null,
    };
  },
  methods: {
    login() {
      this.$store
        .dispatch("login", {
          username: this.username,
          password: this.password,
        })
        .then((response) => {
          this.$router.push({ name: "dashboard" });
          this.error = response;
        })
        .catch((err) => {
          console.log(err);
          /* if (err.response.data && err.response.data.error) {
            this.error = err.response.data.error;
          } else {
            this.error = err.response;
          }*/
          this.error = err;
        });
    },
  },
};
</script>

<style scoped>
.loginForm {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  gap: 1em;
  padding: 1em;
}
</style>
