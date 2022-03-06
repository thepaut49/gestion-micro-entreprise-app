<template>
  <div>
    <h1>Dashboard</h1>
    <template v-if="!isLoading">
      <p>Is loaded !!!</p>
    </template>
    <p v-else>Loading events</p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      isLoading: true,
      events: [],
    };
  },
  created() {
    axios.get("//localhost:3000/dashboard").then(({ data }) => {
      this.events = data.events.events;
      this.isLoading = false;
    });
  },
};
</script>
