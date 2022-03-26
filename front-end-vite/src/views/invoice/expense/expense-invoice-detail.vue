<template>
  <section class="invoice-form-container">
    <header class="title">
      <h2>{{ title }}</h2>
    </header>
    <main>
      <fieldset class="invoice-form-content">
        <legend>Fournisseur</legend>
        <BaseSelect
          :options="supplierTypes"
          v-model="expense.supplier.supplierType"
          label="Type fournisseur"
          name="supplierType"
          @change="updateSupplierType()"
        />
        <BaseInput v-model="expense.supplier.name" label="Nom" type="text" />
        <BaseInput
          v-model="expense.supplier.siren"
          label="Siren"
          type="text"
          :error="error.siren"
          v-show="showCompanyFields()"
        />
        <BaseInput
          v-model="expense.supplier.siret"
          label="Siret"
          type="text"
          :error="error.siret"
          v-show="showCompanyFields()"
        />
        <fieldset class="invoice-form-content">
          <legend>Email et téléphone</legend>
          <BaseInput
            v-model="expense.supplier.email"
            label="Email"
            type="text"
            :error="error.email"
          />
          <BaseInput
            v-model="expense.supplier.phone"
            label="Téléphone"
            type="text"
            :error="error.phone"
          />
        </fieldset>

        <fieldset class="invoice-form-content">
          <legend>Adresse</legend>
          <AddressForm
            :address="expense.address"
            @input="
              (newAddress) => {
                address = newAddress;
              }
            "
          />
        </fieldset>
      </fieldset>
      <section>
        <br />
        <table>
          <thead>
            <tr>
              <th>N°</th>
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
                <button class="invoice-add-line">Ajouter une ligne</button>
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
    const supplierTypes = ["ENTREPRISE", "PARTICULIER", "ETAT"];

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

    function updateSupplierType() {}

    function showCompanyFields() {
      if (expense.supplier && expense.supplier.supplierType === "ENTREPRISE") {
        return true;
      }
      return false;
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
    };
  },
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
