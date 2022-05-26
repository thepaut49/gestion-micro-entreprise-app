<template>
  <!-- Modal content -->
  <div class="modal" v-show="isOpen">
    <section class="modal-content">
      <header class="modal-header">
        <h2>Sélection du fournisseur</h2>
        <button class="close" @click="onCancel">&times;</button>
      </header>
      <main class="modal-body">
        <fieldset class="fieldset-filters">
          <legend>Critères de recherche</legend>
          <div class="field">
            <BaseSelect
              :options="supplierTypes"
              v-model="supplierType"
              label="Type fournisseur"
              name="supplierType"
            />
          </div>
          <div class="field" v-show="isSupplierACompany()">
            <BaseInput
              v-model="companyName"
              label="Nom de l'entreprise"
              type="text"
            />
          </div>
          <div class="field" v-show="isSupplierACompany()">
            <BaseInput v-model="siren" label="Siren" type="text" />
          </div>
          <div class="field" v-show="isSupplierACompany()">
            <BaseInput v-model="siret" label="Siren" type="text" />
          </div>
          <div class="field" v-show="!isSupplierACompany()">
            <BaseInput v-model="firstName" label="Prénom" type="text" />
          </div>
          <div class="field" v-show="!isSupplierACompany()">
            <BaseInput v-model="familyName" label="Nom" type="text" />
          </div>
          <div>
            <button @click="searchSupplier">Rechercher</button>
          </div>
        </fieldset>
        <div v-show="foundSuppliers">
          <SupplierCard
            v-for="supplier in suppliers"
            :key="supplier.id"
            :supplier="supplier"
            v-bind="$attrs"
            @handleSelectSupplierGranChild="selectSupplierChild"
          />
        </div>
        <p v-show="!foundSuppliers">
          Pas de fournisseur(s) correspondant au critère(s).
        </p>
      </main>
    </section>
  </div>
</template>

<script>
import BaseSelect from "../../commons/BaseSelect.vue";
import { ref, computed } from "vue";
import { useStore } from "vuex";
import {
  supplierTypes,
  mapPersonToSupplier,
  mapCompanyToSupplier,
} from "../../../utils/invoice/ExpenseUtils";
import SupplierCard from "./SupplierCard.vue";

export default {
  name: "ModalSelectSupplier",
  props: {
    isOpen: {
      type: Boolean,
      default: () => false,
    },
  },
  setup(props, { emit }) {
    const store = useStore();

    const supplierType = ref("");
    const companyName = ref("");
    const siren = ref("");
    const siret = ref("");
    const firstName = ref("");
    const familyName = ref("");
    const suppliers = ref([]);

    const selectSupplierChild = (supplier) => {
      emit("handleSelectSupplierChild", supplier);
    };

    const searchSupplierInCompanies = () => {
      const companies = ref(store.state.company.companies);

      companies.value.forEach((company) => {
        console.log("company.companyName : " + company.companyName);
        console.log("company.siren : " + company.siren);
        console.log("company.siret : " + company.siret);
        let addCompany = true;
        if (
          companyName.value !== "" &&
          !company.companyName.includes(companyName.value)
        ) {
          addCompany = false;
        }
        if (siren.value !== "" && !company.siren.includes(siren.value)) {
          addCompany = false;
        }
        if (siret.value !== "" && !company.siret.includes(siret.value)) {
          addCompany = false;
        }

        if (addCompany) {
          suppliers.value.push(mapCompanyToSupplier(company));
        }
      });
    };

    const searchSupplierInPersons = () => {
      const persons = ref(store.state.person.persons);
      persons.value.forEach((person) => {
        console.log("Person : " + person);
        let addPerson = true;
        console.log("person.firstName : " + person.firstName);
        console.log("person.familyName : " + person.familyName);
        if (
          firstName.value !== "" &&
          !person.firstName.includes(firstName.value)
        ) {
          addPerson = false;
        }
        if (
          familyName.value !== "" &&
          !person.familyName.includes(familyName.value)
        ) {
          addPerson = false;
        }
        if (addPerson) {
          suppliers.value.push(mapPersonToSupplier(person));
        }
      });
    };

    const searchSupplier = () => {
      suppliers.value = [];
      console.log("Supplier Type : " + supplierType.value);
      if (supplierType.value === "ENTREPRISE") {
        searchSupplierInCompanies();
      } else if (supplierType.value === "PARTICULIER") {
        searchSupplierInPersons();
      } else {
        searchSupplierInCompanies();
        searchSupplierInPersons();
      }
    };

    const foundSuppliers = computed(() => {
      return suppliers.value && suppliers.value.length > 0;
    });

    const isSupplierACompany = () => {
      return supplierType.value === "ENTREPRISE";
    };

    const onCancel = () => {
      emit("handleCancel");
    };

    return {
      foundSuppliers,
      suppliers,
      selectSupplierChild,
      searchSupplierInCompanies,
      searchSupplierInPersons,
      searchSupplier,
      isSupplierACompany,
      supplierTypes,
      supplierType,
      companyName,
      siren,
      siret,
      firstName,
      familyName,
      onCancel,
    };
  },
  components: { BaseSelect, SupplierCard },
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

.modal-footer {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.fieldset-filters {
  display: flex;
  flex-direction: column;
  align-items: stretch;
}
</style>
