<template>
  <section class="listEntitiesSection">
    <header class="listEntitiesHeader">
      <h2>Factures clients</h2>
      <router-link
        tag="button"
        class="button addButton"
        :to="{ name: 'revenue-invoice-detail' }"
      >
        <font-awesome-icon icon="plus" />
      </router-link>
      <button @click="reloadRevenues">
        <font-awesome-icon icon="sync" />
      </button>
    </header>

    <main class="listEntitiesMain" v-show="!isLoading">
      <RevenueCard
        v-for="revenue in revenues"
        :key="revenue.id"
        :revenue="revenue"
        @askToDeleteRevenueEvent="askToDelete(revenue)"
      />
    </main>
    <p v-show="isLoading">
      Récupération des facture clients en cours, veuillez patienter
    </p>
    <Modal
      :message="modalMessage"
      :isOpen="showModal"
      @handleNo="closeModal"
      @handleYes="deleteRevenue"
    >
    </Modal>
  </section>
</template>

<script>
import { useStore } from "vuex";
import { computed, ref } from "vue";

export default {
  name: "RevenueInvoices",
  setup() {
    const store = useStore();
    let isLoading = ref(true);
    let revenueToDelete = null;
    let showModal = false;
    let modalMessage = "";

    store
      .dispatch("getRevenueInvoicesAction")
      .then(() => {
        isLoading.value = false;
        console.log("loading complete");
      })
      .catch((error) => {
        isLoading.value = false;
        console.log(error);
      });

    const askToDelete = (revenue) => {
      revenueToDelete = revenue;
      showModal = true;
      modalMessage = "Voulez vous supprimer la facture client " + revenue.id;
    };

    const closeModal = () => {
      showModal = false;
    };

    const deleteRevenue = () => {
      closeModal();
      if (revenueToDelete) {
        store.dispatch("deleteRevenueAction", revenueToDelete);
      }
      store
        .dispatch("getRevenueInvoicesAction")
        .then(() => {
          isLoading = false;
        })
        .catch((error) => {
          isLoading = false;
          console.log(error);
        });
    };

    const reloadRevenues = () => {
      isLoading.value = true;
      store
        .dispatch("getRevenueInvoicesAction")
        .then(() => {
          isLoading.value = false;
          console.log("loading complete");
        })
        .catch((error) => {
          isLoading.value = false;
          console.log(error);
        });
    };

    const revenues = computed(() => {
      return store.state.microCompany.revenueInvoices;
    });

    return {
      isLoading,
      revenueToDelete,
      showModal,
      modalMessage,
      askToDelete,
      closeModal,
      deleteRevenue,
      reloadRevenues,
      revenues,
    };
  },
};
</script>

<style lang="scss" scoped></style>
