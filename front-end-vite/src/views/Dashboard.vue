<template>
  <div>
    <h2>Dashboard</h2>
    <section class="dashboard-content">
      <router-link
        tag="button"
        class="button createMicroButton"
        :to="{ name: 'microCompany-detail' }"
        v-if="canCreateMicroCompany"
      >
        Créer une micro entreprise
      </router-link>
    </section>
  </div>
</template>

<script>
import axios from "axios";
import { ref, computed } from "vue";
import { useStore } from "vuex";

export default {
  name: "Dashboard",
  setup() {
    const store = useStore();
    const isLoading = ref(false);
    const canCreateMicroCompany = computed(() => {
      return store.state.microCompany.canCreateMicroCompany;
    });
    store.dispatch("canCreateMicroCompanyAction").catch((error) => {
      console.log(error);
    });
    return {
      isLoading,
      canCreateMicroCompany,
    };
  },
};
</script>

<style scoped>
.dashboard-content {
  display: flex;
  flex-direction: column;
}
.createMicroButton {
  /*color: white;*/
  display: block;
  text-decoration: none;
  font-weight: bold;
  text-align: center;
  padding: 10em;
  margin: 0.5em;
  color: white;
}
</style>
