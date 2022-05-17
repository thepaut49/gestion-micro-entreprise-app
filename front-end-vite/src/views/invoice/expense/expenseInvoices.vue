<template>
  <section class="listEntitiesSection">
    <header class="listEntitiesHeader">
      <h2>Factures fournisseurs</h2>
      <router-link
        tag="button"
        class="button addButton"
        :to="{ name: 'expense-invoice-detail' }"
      >
        <font-awesome-icon icon="plus" />
      </router-link>
      <button @click="reloadExpenses">
        <font-awesome-icon icon="sync" />
      </button>
    </header>

    <main class="listEntitiesMain" v-show="!isLoading">
      <ExpenseCard
        v-for="expense in expenses"
        :key="expense.id"
        :expense="expense"
        @askToDeleteExpenseEvent="askToDelete(expense)"
      />
    </main>
    <p v-show="isLoading">
      Récupération des facture fournisseurs en cours, veuillez patienter
    </p>
    <Modal
      :message="modalMessage"
      :isOpen="showModal"
      @handleNo="closeModal"
      @handleYes="deleteExpense"
    >
    </Modal>
  </section>
</template>

<script>
import { useStore } from "vuex";
import { computed, ref } from "vue";

export default {
  name: "ExpenseInvoices",
  setup() {
    const store = useStore();
    let isLoading = ref(true);
    let expenseToDelete = null;
    let showModal = false;
    let modalMessage = "";

    store
      .dispatch("getExpenseInvoicesAction")
      .then(() => {
        isLoading.value = false;
        console.log("loading complete");
      })
      .catch((error) => {
        isLoading.value = false;
        console.log(error);
      });

    const askToDelete = (expense) => {
      expenseToDelete = expense;
      showModal = true;
      modalMessage =
        "Voulez vous supprimer la facture fournisseur " + expense.id;
    };

    const closeModal = () => {
      showModal = false;
    };

    const deleteExpense = () => {
      closeModal();
      if (expenseToDelete) {
        store.dispatch("deleteExpenseAction", expenseToDelete);
      }
      store
        .dispatch("getExpenseInvoicesAction")
        .then(() => {
          isLoading = false;
        })
        .catch((error) => {
          isLoading = false;
          console.log(error);
        });
    };

    const reloadExpenses = () => {
      isLoading.value = true;
      store
        .dispatch("getExpenseInvoicesAction")
        .then(() => {
          isLoading.value = false;
          console.log("loading complete");
        })
        .catch((error) => {
          isLoading.value = false;
          console.log(error);
        });
    };

    const expenses = computed(() => {
      return store.state.microCompany.expenseInvoices;
    });

    return {
      isLoading,
      expenseToDelete,
      showModal,
      modalMessage,
      askToDelete,
      closeModal,
      deleteExpense,
      reloadExpenses,
      expenses,
    };
  },
};
</script>

<style lang="scss" scoped></style>
