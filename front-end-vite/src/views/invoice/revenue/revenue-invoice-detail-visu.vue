<template>
  <section class="invoice-visu-container">
    <header class="title">
      <h2>{{ title }}</h2>
      <router-link
        class="button link-button"
        :to="{
          name: 'revenue-invoice-detail',
          props: { isAddMode: false },
          params: { id: revenue.id },
        }"
      >
        <font-awesome-icon icon="edit" />
      </router-link>
    </header>
    <main class="invoice-visu-parent">
      <div class="info-micro">
        <MicroCompanyVisu :microCompany="revenue.microCompany" />
      </div>
      <div class="info-client">
        <ClientVisu :client="revenue.client" />
      </div>
      <div class="donnees-facture">
        <table>
          <thead>
            <tr>
              <th>N°</th>
              <th>Description</th>
              <th>Prix unitaire HT (€)</th>
              <th>Quantité</th>
              <th>TVA (%)</th>
              <th>Montant HT (€)</th>
              <th>Montant TTC (€)</th>
            </tr>
          </thead>
          <tbody>
            <RevenueInvoiceLineDetailVisu
              v-for="invoiceLine in revenue.invoiceLines"
              :key="invoiceLine.id"
              :invoiceLine="invoiceLine"
              v-bind="$attrs"
            />
          </tbody>
          <tfoot>
            <tr>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <th>Total HT</th>
              <td class="cellAmount">{{ revenue.amountExcludingTax }} €</td>
            </tr>
            <tr>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <th>Total TTC</th>
              <td class="cellAmount">{{ revenue.amountWithTax }} €</td>
            </tr>
          </tfoot>
        </table>
      </div>
    </main>
  </section>
</template>

<script>
import { ref, computed } from "vue";
import { useStore } from "vuex";
import RevenueInvoiceLineDetailVisu from "./revenue-invoice-line-detail-visu.vue";

export default {
  name: "RevenueInvoiceDetailVisu",
  props: {
    id: {
      type: String,
    },
  },
  setup(props) {
    const store = useStore();

    store
      .dispatch("getRevenueInvoiceAction", props.id)
      .catch((error) => console.log(error));

    const revenue = computed(() => {
      return store.state.microCompany.revenueInvoice;
    });

    const title = computed(() => {
      if (revenue.value && revenue.value.id)
        return "Facture client " + revenue.value.id;
      return "Facture client";
    });
    return { title, revenue };
  },
  components: { RevenueInvoiceLineDetailVisu },
};
</script>

<style scoped>
.title {
  display: flex;
  align-items: center;
}

.invoice-visu-container {
  display: flex;
  flex-direction: column;
  margin: 0.5em;
}

.invoice-visu-parent {
  display: grid;
  grid-template-columns: repeat(12, 1fr);
  grid-template-rows: repeat(12, 1fr);
  grid-column-gap: 0.5em;
  grid-row-gap: 0.5em;
}

.info-client {
  grid-area: 1 / 1 / 4 / 7;
  display: flex;
  flex-direction: column;
}
.info-micro {
  grid-area: 1 / 7 / 4 / 13;
  display: flex;
  flex-direction: column;
}
.donnees-facture {
  grid-area: 4 / 1 / 13 / 13;
}

.cellAmount {
  text-align: right;
}

th {
  background-color: #42b983;
  color: white;
}

td {
  height: 1em;
  border-collapse: collapse;
}

table {
  border: 2px solid #42b983;
  border-radius: 3px;
  background-color: #fff;
  width: 100%;
}
</style>
