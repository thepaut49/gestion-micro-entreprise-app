<template>
  <section class="invoice-form-container">
    <header class="title">
      <h2>{{ title }}</h2>
    </header>
    <main>
      <div class="supplier-button">
        <button
          v-show="!isSupplierSelected()"
          @click="showModalToSelectSupplier()"
        >
          Sélectionner le fournisseur
        </button>
        <button
          v-show="isSupplierSelected()"
          @click="showModalToSelectSupplier()"
        >
          Changer de fournisseur
        </button>
      </div>

      <SupplierForm
        :supplier="expense.supplier"
        @input="
          (newSupplier) => {
            supplier = newSupplier;
          }
        "
        @change="
          (newSupplier) => {
            supplier = newSupplier;
          }
        "
      />

      <MicroCompanyForm :microCompany="expense.microCompany" />

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
            <ExpenseInvoiceLineDetail
              v-for="invoiceLine in expense.invoiceLines"
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
              <td class="cellAmount">{{ expense.amountExcludingTax }} €</td>
            </tr>
            <tr>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <th>Total TTC</th>
              <td class="cellAmount">{{ expense.amountWithTax }} €</td>
            </tr>
          </tfoot>
        </table>
      </section>
      <ModalSelectSupplier
        :isOpen="showModal"
        @handleSelectSupplierChild="selectSupplier"
        @handleCancel="closeModal"
      />
    </main>
    <footer class="invoice-form-footer">
      <button @click="cancelExpense()">
        <span>Cancel</span>
      </button>
      <button @click="saveExpense()">
        <span>Save</span>
      </button>
    </footer>
  </section>
</template>

<script>
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { ref, computed, watchEffect } from "vue";
import SupplierForm from "../../../components/formulaire/SupplierForm.vue";
import ExpenseInvoiceLineDetail from "./expense-invoice-line-detail.vue";
import {
  supplierTypes,
  emptyError,
  createNewLine,
  validateExpense,
} from "../../../utils/invoice/ExpenseUtils";
import MicroCompanyForm from "../../../components/invoice/MicroCompanyForm.vue";

export default {
  name: "ExpenseInvoiceDetail",
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

    const showModalToSelectSupplier = () => {
      showModal.value = true;
    };

    const closeModal = () => {
      showModal.value = false;
    };

    const selectSupplier = (selectedSupplier) => {
      showModal.value = false;
      expense.value.supplier = selectedSupplier;
    };

    const isAddMode = computed(() => {
      return !id;
    });

    const title = computed(() => {
      return isAddMode
        ? "Créer une facture fournisseur"
        : "Modifier la facture fournisseur " + expense.value.id;
    });
    if (isAddMode.value) {
      store
        .dispatch("createNewExpenseInvoiceAction")
        .catch((error) => console.log(error));
    } else {
      store
        .dispatch("getExpenseInvoiceAction", props.id)
        .catch((error) => console.log(error));
    }
    const expense = computed(() => {
      return store.state.microCompany.expenseInvoice;
    });

    const addInvoiceLine = () => {
      expense.value.invoiceLines = createNewLine(expense.value.invoiceLines);
    };

    const isSupplierSelected = () => {
      if (expense.value.supplier.id) {
        return true;
      }
      return false;
    };

    watchEffect(() => {
      expense.value.amountExcludingTax = 0.0;
      expense.value.amountWithTax = 0.0;
      expense.value.invoiceLines.forEach((invoiceLine) => {
        expense.value.amountExcludingTax += invoiceLine.amountExcludingTax;
        expense.value.amountWithTax += invoiceLine.amountWithTax;
        expense.value.amountExcludingTax =
          Math.round(expense.value.amountExcludingTax * 100) / 100;
        expense.value.amountWithTax =
          Math.round(expense.value.amountWithTax * 100) / 100;
      });
    });

    const cancelExpense = () => {
      router.go(-1);
    };

    const saveExpense = () => {
      let expenseIsValid = true;
      [error.value, expenseIsValid] = validateExpense(expense.value);
      try {
        if (expenseIsValid) {
          if (isAddMode.value) {
            store
              .dispatch("addExpenseInvoiceAction", expense.value)
              .then(() => {
                router.push({ name: "expenseInvoices" });
              })
              .catch((error) => console.log(error));
          } else {
            store
              .dispatch("updateExpenseInvoiceAction", expense.value)
              .then(() => router.push({ name: "expenseInvoices" }))
              .catch((error) => console.log(error));
          }
        }
      } catch (error) {
        console.log(error);
      }
    };

    function updateSupplierType() {
      if (expense.supplier && expense.supplier.supplierType === "ENTREPRISE") {
        showCompanyFields = true;
      } else {
        showCompanyFields = false;
      }
    }

    return {
      closeModal,
      selectSupplier,
      showModal,
      showModalToSelectSupplier,
      expense,
      isAddMode,
      error,
      title,
      supplierTypes,
      updateSupplierType,
      cancelExpense,
      saveExpense,
      showCompanyFields,
      addInvoiceLine,
      isSupplierSelected,
    };
  },
  components: { SupplierForm, ExpenseInvoiceLineDetail, MicroCompanyForm },
};
</script>

<style>
@import "../../../assets/styles/invoiceEditForm.css";

.supplier-button {
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
