<template>
  <fieldset class="revenue-card">
    <legend>Facteur fournisseur {{ revenue.id }}</legend>
    <fieldset class="revenue-card-supplier">
      <legend>Fournisseur</legend>
      <BaseLabel v-model="revenue.supplier" label="Nom fournisseur : " />
    </fieldset>
    <fieldset class="revenue-card-details">
      <legend>Détails</legend>
      <BaseLabel v-model="revenue.externalRef" label="Réf externe : " />
      <BaseLabel v-model="revenue.amountExcludingTax" label="Montant HT : " />
      <BaseLabel v-model="revenue.amountWithTax" label="Montant TTC : " />
      <BaseLabel v-model="revenue.dueDate" label="Montant HT : " />
      <BaseLabel v-model="revenue.paymentDate" label="Date de règlement : " />
      <BaseLabel
        v-model="revenue.paymentMethod"
        label="Méthode de paiement : "
      />
      <BaseLabel v-model="revenue.payed" label="Payé : " />
      <BaseLabel v-model="revenue.quote" label="Devis : " />
    </fieldset>
    <main class="revenue-card-content">
      <div class="field-label">
        <BaseLabel v-model="revenue.email" label="Email : " />
      </div>
      <div class="field-label">
        <BaseLabel v-model="revenue.phone" label="Phone : " />
      </div>
    </main>
    <footer>
      <button @click="askToDelete(revenue)">
        <span>Supprimer</span>
      </button>
      <router-link
        class="button link-button"
        :to="{ name: 'revenue-detail', params: { id: revenue.id } }"
      >
        <span>Sélectioner</span>
      </router-link>
    </footer>
  </fieldset>
</template>

<script>
import { computed } from "vue";

export default {
  name: "RevenueCard",
  props: {
    revenue: {
      type: Object,
      default: () => {
        return {};
      },
    },
  },
  setup(props, { emit }) {
    const revenueProp = props.revenue;
    const askToDelete = () => {
      emit("askToDeleteRevenueEvent", revenueProp);
    };

    const fullName = computed(() => {
      return "Facteur fournisseur " + revenueProp.id;
    });

    return {
      fullName,
      askToDelete,
    };
  },
};
</script>

<style scoped>
.revenue-card {
  padding: 1em;
  border: solid 1px #2c3e50;
  cursor: pointer;
  transition: all 0.2s linear;
  display: flex;
  flex-direction: column;
}
.revenue-card:hover {
  transform: scale(1.01);
  box-shadow: 0 3px 12px 0 rgba(0, 0, 0, 0.2), 0 1px 15px 0 rgba(0, 0, 0, 0.19);
}

.revenue-card-content {
  text-align: left;
  display: flex;
  flex-direction: column;
}

.revenue-card footer {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-around;
}
</style>
