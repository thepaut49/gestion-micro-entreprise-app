<template>
  <form @submit.prevent="login">
    <fieldset>
      <legend>Connexion</legend>
      <BaseInput v-model="username" label="Username" type="text" />

      <BaseInput v-model="password" label="Password" type="password" />

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
        .then(() => {
          this.$router.push({ name: "dashboard" });
        })
        .catch((err) => {
          this.error = err.response.data.error;
        });
    },
  },
};
</script>

<style scoped></style>
