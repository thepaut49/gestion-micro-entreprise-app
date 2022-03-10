<template>
  <section class="home">
    <h1>Welcome to the App!</h1>
    <div v-if="!loggedIn">
      To use this app you'll need to
      <router-link to="/login"> Login </router-link>
      or
      <router-link to="/register"> Register </router-link>
    </div>
  </section>
</template>

<script>
import { authComputed } from "../store/helpers.js";
export default {
  computed: {
    ...authComputed,
  },
};
</script>

<style scoped>
.home {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  height: 100%;
  width: 100%;
}
</style>
