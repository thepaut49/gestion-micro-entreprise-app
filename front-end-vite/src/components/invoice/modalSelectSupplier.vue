<template>
  <!-- Modal content -->
  <div class="modal" v-show="isOpen">
    <section class="modal-content">
      <header class="modal-header">
        <h2>Sélection du fournisseur</h2>
        <button class="close" @click="onCancel">&times;</button>
      </header>
      <main class="modal-body">
        <fieldset>
          <legend>Critères de recherche</legend>
          <div>
            <BaseSelect
              :options="supplierTypes"
              v-model="supplierType"
              label="Type fournisseur"
              name="supplierType"
            />
          </div>
          <div v-show="isSupplierACompany()">
            <BaseInput
              v-model="companyName"
              label="Nom de l'entreprise"
              type="text"
            />
          </div>
          <div v-show="isSupplierACompany()">
            <BaseInput v-model="siren" label="Siren" type="text" />
          </div>
          <div v-show="isSupplierACompany()">
            <BaseInput v-model="siret" label="Siren" type="text" />
          </div>
          <div v-show="!isSupplierACompany()">
            <BaseInput v-model="firstname" label="Prénom" type="text" />
          </div>
          <div v-show="!isSupplierACompany()">
            <BaseInput v-model="lastname" label="Nom" type="text" />
          </div>
        </fieldset>
      </main>
      <footer class="modal-footer">
        <button @click="onCancel">Annuler</button>
      </footer>
    </section>
  </div>
</template>

<script>
import BaseSelect from "../commons/BaseSelect.vue";
import { ref } from "vue";
import { supplierTypes } from "../../utils/invoice/ExpenseUtils";

export default {
  name: "ModalSelectSupplier",
  props: {
    isOpen: {
      type: Boolean,
      default: () => false,
    },
  },
  setup(props, { emit }) {
    let supplierType = ref("");
    let companyName = ref("");
    let siren = ref("");
    let siret = ref("");
    let firstname = ref("");
    let lastname = ref("");
    let selectedSupplier = ref({});

    const isSupplierACompany = () => {
      return supplierType.value === "ENTREPRISE";
    };

    const selectSupplier = () => {
      emit("handleSelectSupplier", selectedSupplier.value);
    };

    const onCancel = () => {
      emit("handleCancel");
    };

    return {
      selectSupplier,
      isSupplierACompany,
      supplierTypes,
      supplierType,
      companyName,
      siren,
      siret,
      firstname,
      lastname,
      onCancel,
    };
  },
  components: { BaseSelect },
};
</script>

<style scoped>
/* The Modal (background) */
.modal {
  display: block; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 100px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0, 0, 0); /* Fallback color */
  background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
  position: absolute;
  float: left;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  background-color: #fefefe;
  margin: auto;
  padding: 0.5em;
  border: 1px solid #888;
  border-radius: 5px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  -webkit-animation-name: animatetop;
  -webkit-animation-duration: 0.4s;
  animation-name: animatetop;
  animation-duration: 0.4s;
  /* layout */
  display: flex;
  flex-direction: column;
  justify-content: center;
}

/* Add Animation */
@-webkit-keyframes animatetop {
  from {
    top: 0;
    opacity: 0;
  }
  to {
    top: 50%;
    opacity: 1;
  }
}

@keyframes animatetop {
  from {
    top: 0;
    opacity: 0;
  }
  to {
    top: 50%;
    opacity: 1;
  }
}

/* The Close Button */
.close {
  color: white;
  float: right;
  font-size: 28px;
  font-weight: bold;
  width: 1em;
  height: 1em;
}

.close:hover,
.close:focus {
  text-decoration: none;
  cursor: pointer;
}

.modal-header {
  text-align: center;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.modal-body {
  text-align: center;
}

.modal-footer {
  display: flex;
  flex-direction: row;
  justify-content: center;
}
</style>
