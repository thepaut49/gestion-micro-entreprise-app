<template>
  <nav v-if="isLoggedIn">
    <span class="menuButtons">
      <router-link to="/" class="navMargin">Accueil</router-link>
      <router-link to="/companies" class="navMargin">Entreprise</router-link>
    </span>
    <button @click="logout" class="navMargin">Logout</button>
  </nav>
</template>

<script>
export default {
  name: "NavBar",
  computed: {
    isLoggedIn: function () {
      return this.$store.getters.isAuthenticated;
    },
  },
  methods: {
    async logout() {
      await this.$store.dispatch("logoutAction");
      this.$router.push("/login");
    },
  },
};
</script>

<style>
nav {
  background: #42b983;
  height: 100%;
  padding: 0.5em;
  align-items: center;
  display: flex;
  justify-content: space-between;
}

.menuButtons {
  align-items: center;
  display: flex;
}

.navMargin {
  padding: 0 10px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

a:hover {
  cursor: pointer;
}

nav a.router-link-exact-active {
  color: white;
}
</style>
