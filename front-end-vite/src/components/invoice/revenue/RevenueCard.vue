<template>
  <fieldset class="revenue-card">
    <legend>Facture client {{ revenue.id }}</legend>
    <div class="field-label">
      <BaseLabel v-model="revenue.client.name" label="Client : " />
    </div>
    <div class="field-label">
      <BaseLabel v-model="revenue.amountExcludingTax" label="Montant HT : " />
    </div>
    <div class="field-label">
      <BaseLabel v-model="revenue.amountWithTax" label="Montant TTC : " />
    </div>
    <div class="field-label">
      <BaseLabel v-model="revenue.dueDate" label="Date d'échéance : " />
    </div>
    <div class="field-label">
      <BaseLabel v-model="revenue.paymentDate" label="Date de règlement : " />
    </div>
    <!-- BaseLabel v-model="revenue.payed" label="Payé : " /-->
    <div class="revenue-card-buttons">
      <button @click="askToDelete(revenue)">
        <span>Supprimer</span>
      </button>
      <router-link
        class="button link-button"
        :to="{
          name: 'revenue-invoice-detail-visu',
          params: { id: revenue.id },
        }"
      >
        <span>Sélectioner</span>
      </router-link>
    </div>
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
      return "Facteur client " + revenueProp.id;
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

.revenue-card-buttons {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-around;
}
</style>
