<template>
  <nav id="nav" class="noprint">
    <template v-if="loggedIn">
      <router-link to="/"> Home </router-link>
      <router-link to="/dashboard"> Dashboard </router-link>
      <router-link to="/calendar"> Calendrier </router-link>
      <router-link v-if="hasMicroCompany" to="/micro-companies/me">
        Micro entreprise
      </router-link>
      <router-link to="/expense-invoices/me">
        Factures fournisseur
      </router-link>
      <router-link to="/revenue-invoices/me"> Factures client </router-link>
      <router-link to="/companies"> Companies </router-link>
      <router-link to="/persons"> Personnes </router-link>
      <button type="button" class="button logoutButton" @click="logout">
        Logout
      </button>
    </template>
    <router-link v-if="!loggedIn" to="/login" class="button">
      Login
    </router-link>
  </nav>
</template>

<script>
import { authComputed, hasMicroCompany } from "../store/helpers.js";
export default {
  computed: {
    ...authComputed,
    ...hasMicroCompany,
  },
  methods: {
    logout() {
      this.$store.dispatch("logout");
    },
  },
};
</script>

<style scoped>
#nav {
  display: flex;
  align-items: center;
  min-height: 50px;
  padding: 0.2em 1em;
  background: linear-gradient(to right, #16c0b0, #84cf6a);
  flex-grow: 1;
}
.nav-welcome {
  margin-left: auto;
  margin-right: 1rem;
  color: white;
}
a {
  font-weight: bold;
  color: #2c3e50;
  margin: auto 0.8em auto 0.4em;
  text-decoration: none;
  border-top: 2px solid transparent;
  border-bottom: 2px solid transparent;
}
.router-link-exact-active {
  color: white;
  border-bottom: 2px solid #fff;
}

.button {
  margin-left: auto;
  background: white;
  text-decoration: none;
  color: #2c3e50;
}
button.router-link-active,
.button.router-link-active {
  color: #2c3e50;
}
.logoutButton {
  cursor: pointer;
}
.nav-welcome + button {
  margin-left: 0;
}
</style>
