<template>
  <div class="home" v-if="userAuthenticated">
    <h2>Home Page</h2>
  </div>
  <div v-else>
    <Login />
  </div>
</template>

<script>
import Login from "@/components/Login";

export default {
  data() {
    return {
      userAuthenticated: false,
    };
  },
  components: {
    Login,
  },
};
</script>
