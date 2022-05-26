<template>
  <section class="invoice-form-container">
    <header class="title">
      <h2>{{ title }}</h2>
    </header>
    <main>
      <div class="client-button">
        <button v-show="!isClientSelected()" @click="showModalToSelectClient()">
          Sélectionner le client
        </button>
        <button v-show="isClientSelected()" @click="showModalToSelectClient()">
          Changer de client
        </button>
      </div>

      <div class="field">
        <BaseInputDate
          v-model="revenue.dueDate"
          label="Date d'échéance"
          name="dueDate"
        />
      </div>

      <div class="field-checkbox">
        <BaseCheckboxAfterLabel
          v-model="revenue.quote"
          label="Devis"
          name="quote"
        />
      </div>

      <div class="field">
        <BaseSelect
          :options="paymentMethods"
          v-model="revenue.paymentMethod"
          label="Méthode de paiement"
          name="paymentMethod"
        />
      </div>

      <div class="field-checkbox">
        <BaseCheckboxAfterLabel
          v-model="revenue.payed"
          label="Payé"
          name="payed"
        />
      </div>

      <div class="field">
        <BaseInputDate
          v-model="revenue.paymentDate"
          label="Date de règlement"
          name="paymentDate"
        />
      </div>

      <ClientForm
        :client="revenue.client"
        @input="
          (newClient) => {
            client = newClient;
          }
        "
        @change="
          (newClient) => {
            client = newClient;
          }
        "
      />

      <MicroCompanyForm :microCompany="revenue.microCompany" />

      <section>
        <br />
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
            <RevenueInvoiceLineDetail
              v-for="invoiceLine in revenue.invoiceLines"
              :key="invoiceLine.id"
              :invoiceLine="invoiceLine"
            />
          </tbody>
          <tfoot>
            <tr>
              <td colspan="7">
                <button class="invoice-add-line" @click="addInvoiceLine()">
                  Ajouter une ligne
                </button>
              </td>
            </tr>
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
      </section>
      <ModalSelectClient
        :isOpen="showModal"
        @handleSelectClientChild="selectClient"
        @handleCancel="closeModal"
      />
    </main>
    <footer class="invoice-form-footer">
      <button @click="cancelRevenue()">
        <span>Cancel</span>
      </button>
      <button @click="saveRevenue()">
        <span>Save</span>
      </button>
    </footer>
  </section>
</template>

<script>
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { ref, computed, watchEffect } from "vue";
import ClientForm from "../../../components/formulaire/revenue/ClientForm.vue";
import RevenueInvoiceLineDetail from "./revenue-invoice-line-detail.vue";
import {
  clientTypes,
  emptyError,
  createNewLine,
  validateRevenue,
} from "../../../utils/invoice/RevenueUtils";
import MicroCompanyForm from "../../../components/invoice/MicroCompanyForm.vue";
import BaseCheckbox from "../../../components/commons/BaseCheckbox.vue";
import { paymentMethods } from "../../../utils/invoice/InvoiceUtils";
import BaseCheckboxAfterLabel from "../../../components/commons/BaseCheckboxAfterLabel.vue";

export default {
  name: "RevenueInvoiceDetail",
  props: {
    id: {
      type: String,
    },
  },
  setup(props) {
    const router = useRouter();
    const store = useStore();
    const error = ref(emptyError);
    let showCompanyFields = ref(false);
    const id = props.id;
    let showModal = ref(false);

    const showModalToSelectClient = () => {
      showModal.value = true;
    };

    const closeModal = () => {
      showModal.value = false;
    };

    const selectClient = (selectedClient) => {
      showModal.value = false;
      revenue.value.client = selectedClient;
    };

    const isAddMode = computed(() => {
      return !id;
    });

    const title = computed(() => {
      return isAddMode.value
        ? "Créer une facture client"
        : "Modifier la facture client " + revenue.value.id;
    });
    if (isAddMode.value) {
      store
        .dispatch("createNewRevenueInvoiceAction")
        .catch((error) => console.log(error));
    } else {
      store
        .dispatch("getRevenueInvoiceAction", id)
        .catch((error) => console.log(error));
    }
    const revenue = computed(() => {
      return store.state.microCompany.revenueInvoice;
    });

    const addInvoiceLine = () => {
      revenue.value.invoiceLines = createNewLine(revenue.value.invoiceLines);
    };

    const isClientSelected = () => {
      if (revenue.value.client.id) {
        return true;
      }
      return false;
    };

    watchEffect(() => {
      revenue.value.amountExcludingTax = 0.0;
      revenue.value.amountWithTax = 0.0;
      revenue.value.invoiceLines.forEach((invoiceLine) => {
        revenue.value.amountExcludingTax += invoiceLine.amountExcludingTax;
        revenue.value.amountWithTax += invoiceLine.amountWithTax;
        revenue.value.amountExcludingTax =
          Math.round(revenue.value.amountExcludingTax * 100) / 100;
        revenue.value.amountWithTax =
          Math.round(revenue.value.amountWithTax * 100) / 100;
      });
    });

    const cancelRevenue = () => {
      router.go(-1);
    };

    const saveRevenue = () => {
      console.log("save revenue");
      let revenueIsValid = true;
      [error.value, revenueIsValid] = validateRevenue(revenue.value);
      try {
        console.log(" try save revenue : " + revenueIsValid);
        if (revenueIsValid) {
          if (isAddMode.value) {
            store
              .dispatch("addRevenueInvoiceAction", revenue.value)
              .then(() => {
                router.push({ name: "revenueInvoices" });
              })
              .catch((error) => console.log(error));
          } else {
            store
              .dispatch("updateRevenueInvoiceAction", revenue.value)
              .then(() => router.push({ name: "revenueInvoices" }))
              .catch((error) => console.log(error));
          }
        }
      } catch (error) {
        console.log(error);
      }
    };

    function updateClientType() {
      if (revenue.client && revenue.client.clientType === "ENTREPRISE") {
        showCompanyFields = true;
      } else {
        showCompanyFields = false;
      }
    }

    return {
      closeModal,
      selectClient,
      showModal,
      showModalToSelectClient,
      revenue,
      isAddMode,
      error,
      title,
      clientTypes,
      paymentMethods,
      updateClientType,
      cancelRevenue,
      saveRevenue,
      showCompanyFields,
      addInvoiceLine,
      isClientSelected,
    };
  },
  components: {
    ClientForm,
    RevenueInvoiceLineDetail,
    MicroCompanyForm,
    BaseCheckbox,
    BaseCheckboxAfterLabel,
  },
};
</script>

<style>
@import "../../../assets/styles/invoiceEditForm.css";

.client-button {
  display: flex;
  flex-direction: row;
  gap: 0.5em;
}

th {
  background-color: #42b983;
  color: white;
}

td {
  height: 1em;
}

table {
  border: 2px solid #42b983;
  border-radius: 3px;
  background-color: #fff;
  width: 100%;
}
</style>
