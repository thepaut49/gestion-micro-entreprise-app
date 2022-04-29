<template>
  <section class="login-container">
    <form @submit.prevent="login">
      <fieldset class="loginForm">
        <legend>Connexion</legend>
        <div class="field">
          <BaseInput v-model="username" label="Username" type="text" />
        </div>
        <div class="field">
          <BaseInput v-model="password" label="Password" type="password" />
        </div>

        <button class="button" type="submit" name="button">Login</button>

        <p>{{ error }}</p>

        <router-link to="/register">
          Don't have an account? Register.
        </router-link>
      </fieldset>
    </form>
    <Spinner v-if="showBouncer" />
  </section>
</template>

<script>
export default {
  data() {
    return {
      username: "",
      password: "",
      error: null,
      showBouncer: false,
    };
  },
  methods: {
    login() {
      this.showBouncer = true;
      this.$store
        .dispatch("login", {
          username: this.username,
          password: this.password,
        })
        .then(() => {
          this.$store
            .dispatch("getMicroCompanyForUserAction")
            .then(() => {
              this.$store.dispatch("getCompaniesAction");
              this.$store.dispatch("getPersonsAction");
              this.showBouncer = false;
              this.$router.push({ name: "dashboard" });
              this.error = null;
            })
            .catch((err) => {
              console.log(err);
              this.error = err;
            });
        })
        .catch((err) => {
          console.log(err);
          this.error = err;
        });
    },
  },
};
</script>

<style scoped>
.login-container {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}
.loginForm {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  gap: 1em;
  padding: 1em;
}
</style>
