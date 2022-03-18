<template>
  <div class="entity-form-container">
    <aside class="entity-form-aside">
      <button class="button">Créer une facture</button>
      <button class="button">Créer un devis</button>
    </aside>
    <section class="entity-form">
      <header class="title">
        <h2>Dashboard</h2>
      </header>
      <main class="dashboard-content">
        Main
        <router-link
          tag="button"
          class="button createMicroButton"
          :to="{ name: 'microCompany-detail', props: { isAddMode: true } }"
          v-if="canCreateMicroCompany"
        >
          Créer une micro entreprise
        </router-link>
      </main>
      <footer class="entity-form-footer">Toto le ousititi</footer>
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
  flex-grow: 1;
  margin-left: 0.5em;
  display: flex;
  flex-direction: column;
  gap: 1em;
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
