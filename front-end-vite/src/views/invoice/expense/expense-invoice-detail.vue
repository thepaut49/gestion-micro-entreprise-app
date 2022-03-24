<template>
  <section class="invoice-form-container">
    <header class="title">
      <h2>titre</h2>
    </header>
    <main>
      <fieldset class="invoice-form-content">
        <legend>Fournisseur</legend>
        <div class="field-label">ID = id</div>
        <div class="field">Input</div>
        <div class="field">Input</div>
        <div class="field-label">Input</div>
        <div class="field-label">Input</div>
        <fieldset class="invoice-form-content">
          <legend>Email et téléphone</legend>
          <div class="field">Input</div>
          <div class="field">Input</div>
        </fieldset>

        <fieldset class="invoice-form-content">
          <legend>Adresse</legend>
          Input
        </fieldset>
      </fieldset>
      <section>
        <br />
        <table>
          <thead>
            <tr>
              <th></th>
              <th>Description</th>
              <th>Prix unitaire HT</th>
              <th>Quantité</th>
              <th>TVA (%)</th>
              <th>Montant HT</th>
              <th>Montant TTC</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
            </tr>
          </tbody>
          <tfoot>
            <tr>
              <td colspan="7">
                <button class="button">
                  <span>Ajouter une ligne</span>
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
              <td>100 €</td>
            </tr>
            <tr>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <th>Total TTC</th>
              <td>120 €</td>
            </tr>
          </tfoot>
        </table>
      </section>
    </main>
    <footer class="invoice-form-footer">
      <button>
        <span>Cancel</span>
      </button>
      <button>
        <span>Save</span>
      </button>
    </footer>
  </section>
</template>

<script>
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { ref, computed } from "vue";

const emptyError = {
  familyName: "",
  firstName: "",
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

    const id = props.id;

    const isAddMode = computed(() => {
      return !props.id;
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
      return store.state.expense.expense;
    });

    const cancelExpense = function () {
      router.push({ name: "expenses" });
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
        router.push({ name: "expenses" });
      }
    };

    return {
      expense,
      isAddMode,
      error,
      title,
      cancelExpense,
      saveExpense,
    };
  },
};

const validateExpense = (expense, error) => {
  error = emptyError;
  if (!expense.name || expense.name.length === 0) {
    error = {
      ...error,
      name: "Le nom est obligatoire",
    };
  }
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
