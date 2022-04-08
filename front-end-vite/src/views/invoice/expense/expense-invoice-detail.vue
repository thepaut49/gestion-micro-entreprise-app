<template>
  <section class="invoice-form-container">
    <header class="title">
      <h2>{{ title }}</h2>
    </header>
    <main>
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
              <td>{{ expense.amountExcludingTax }} €</td>
            </tr>
            <tr>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <th>Total TTC</th>
              <td>{{ expense.amountWithTax }} €</td>
            </tr>
          </tfoot>
        </table>
      </section>
    </main>
    <footer class="invoice-form-footer">
      <button class="button">
        <span>Cancel</span>
      </button>
      <button class="button">
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

const createNewLine = (invoiceLines) => {
  let invoiceLinesLocal = invoiceLines;

  const newLine = {
    invoiceId: undefined,
    lineNumber: newLineNumber(invoiceLines),
    description: "",
    taxPercentage: 19.6,
    accountingCode: "000",
    quantity: 1,
    quantityType: "NO_TYPE",
    amountForRefQuantity: 0.0,
    amountExcludingTax: 0.0,
    amountWithTax: 0.0,
  };

  invoiceLinesLocal.push(newLine);

  return invoiceLinesLocal;
};

const newLineNumber = (invoiceLines) => {
  let newLineNumber = 0;
  invoiceLines.forEach((invoiceLine) => {
    if (invoiceLine.lineNumber > newLineNumber) {
      newLineNumber = invoiceLine.lineNumber;
    }
  });
  return newLineNumber + 1;
};

const emptyError = {
  supplier: {
    supplierType: "",
    name: "",
    siret: "",
    siren: "",
    address: {
      addressLine1: "",
      addressLine2: "",
      cityName: "",
      countryName: "",
      postalCode: "",
    },
    phone: "",
    email: "",
  },
  microCompany: {
    companyName: "",
    siren: "",
    siret: "",
    address: {
      addressLine1: "",
      addressLine2: "",
      cityName: "",
      countryName: "",
      postalCode: "",
    },
    email: "",
    phone: "",
  },
  invoiceLines: [],
  amountExcludingTax: "",
  amountWithTax: "",
  dueDate: "",
  paymentDate: "",
  quote: "",
  paymentMethod: "",
  payed: "",
};

export default {
  name: "ExpenseInvoiceDetail",
  inheritAttrs: false,
  props: {
    id: {
      type: String,
    },
  },
  setup(props) {
    const router = useRouter();
    const store = useStore();
    const error = ref(emptyError);
    const supplierTypes = ["ENTREPRISE", "PARTICULIER", "ETAT"];
    let showCompanyFields = ref(false);
    const id = props.id;
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

    const cancelExpense = function () {
      router.go(-1);
    };

    const saveExpense = () => {
      error.value = validateExpense(expense.value, error.value);
      console.log(errorObjectIsEmpty(error.value));
      if (errorObjectIsEmpty(error.value)) {
        if (isAddMode.value) {
          store
            .dispatch("addExpenseInvoiceAction", expense.value)
            .catch((errorCatch) => console.log(errorCatch));
        } else {
          store
            .dispatch("updateExpenseInvoiceAction", expense.value)
            .catch((errorCatch) => console.log(errorCatch));
        }
        router.go(-1);
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
    };
  },
  components: { SupplierForm, ExpenseInvoiceLineDetail },
};

const validateExpense = (expense, error) => {
  error = emptyError;
  return error;
};

const errorObjectIsEmpty = (error) => {
  return Object.values(error).every((value) => value.length === 0);
};
</script>

<style>
@import "../../../assets/styles/invoiceEditForm.css";

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
