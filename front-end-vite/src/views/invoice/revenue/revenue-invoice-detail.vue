<template>
  <div class="invoice-form-container">
    <section class="invoice-form">
      <header class="title">
        <h2>{{ title }}</h2>
      </header>
      <main>
        <fieldset class="invoice-form-content">
          <legend>Fournisseur</legend>
          <div class="field-label">
            <BaseLabel v-model="expense.id" label="Id" />
          </div>
          <div class="field">
            <BaseInput
              v-model="expense.name"
              label="Nom fournisseur"
              type="text"
              :error="error.name"
            />
          </div>
          <div class="field">
            <BaseInput
              v-model="expense.firstName"
              label="Prénom"
              type="text"
              :error="error.firstName"
            />
          </div>
          <div class="field-label">
            <BaseLabel v-model="expense.createdAt" label="Créée le" />
          </div>
          <div class="field-label">
            <BaseLabel v-model="expense.modifiedAt" label="Modifié le" />
          </div>
          <fieldset class="expense-form-content">
            <legend>Email et téléphone</legend>
            <div class="field">
              <BaseInput
                v-model="expense.email"
                label="Email de l'entreprise"
                type="text"
              />
            </div>
            <div class="field">
              <BaseInput
                v-model="expense.phone"
                label="Numéro téléphone"
                type="text"
              />
            </div>
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
  </div>
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
</style>
