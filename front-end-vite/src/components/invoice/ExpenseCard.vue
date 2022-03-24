<template>
  <fieldset class="expense-card">
    <legend>Facteur fournisseur {{ expense.id }}</legend>
    <fieldset class="expense-card-supplier">
      <legend>Fournisseur</legend>
      <BaseLabel v-model="expense.supplier" label="Nom fournisseur : " />
    </fieldset>
    <fieldset class="expense-card-details">
      <legend>Détails</legend>
      <BaseLabel v-model="expense.externalRef" label="Réf externe : " />
      <BaseLabel v-model="expense.amountExcludingTax" label="Montant HT : " />
      <BaseLabel v-model="expense.amountWithTax" label="Montant TTC : " />
      <BaseLabel v-model="expense.dueDate" label="Montant HT : " />
      <BaseLabel v-model="expense.paymentDate" label="Date de règlement : " />
      <BaseLabel
        v-model="expense.paymentMethod"
        label="Méthode de paiement : "
      />
      <BaseLabel v-model="expense.payed" label="Payé : " />
      <BaseLabel v-model="expense.quote" label="Devis : " />
    </fieldset>
    <main class="expense-card-content">
      <div class="field-label">
        <BaseLabel v-model="expense.email" label="Email : " />
      </div>
      <div class="field-label">
        <BaseLabel v-model="expense.phone" label="Phone : " />
      </div>
    </main>
    <footer>
      <button @click="askToDelete(expense)">
        <span>Supprimer</span>
      </button>
      <router-link
        class="button link-button"
        :to="{ name: 'expense-detail', params: { id: expense.id } }"
      >
        <span>Sélectioner</span>
      </router-link>
    </footer>
  </fieldset>
</template>

<script>
import { computed } from "vue";

export default {
  name: "ExpenseCard",
  props: {
    expense: {
      type: Object,
      default: () => {
        return {};
      },
    },
  },
  setup(props, { emit }) {
    const expenseProp = props.expense;
    const askToDelete = () => {
      emit("askToDeleteExpenseEvent", expenseProp);
    };

    const fullName = computed(() => {
      return "Facteur fournisseur " + expenseProp.id;
    });

    return {
      fullName,
      askToDelete,
    };
  },
};
</script>

<style scoped>
.expense-card {
  padding: 1em;
  border: solid 1px #2c3e50;
  cursor: pointer;
  transition: all 0.2s linear;
  display: flex;
  flex-direction: column;
}
.expense-card:hover {
  transform: scale(1.01);
  box-shadow: 0 3px 12px 0 rgba(0, 0, 0, 0.2), 0 1px 15px 0 rgba(0, 0, 0, 0.19);
}

.expense-card-content {
  text-align: left;
  display: flex;
  flex-direction: column;
}

.expense-card footer {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-around;
}
</style>
