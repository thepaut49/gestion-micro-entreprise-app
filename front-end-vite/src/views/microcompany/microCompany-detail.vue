<template>
  <div class="entity-form-container">
    <aside class="entity-form-aside">
      <button @click="showSection('infoGene')">Info générales</button>
      <button @click="showSection('emailPhone')">Email et téléphone</button>
      <button @click="showSection('adresse')">Adresse</button>
      <button @click="showSection('gerant')">Gérant</button>
    </aside>
    <section class="entity-form">
      <header class="title">
        <h2>{{ title }}</h2>
      </header>
      <main>
        <fieldset class="entity-form-content" v-show="showSectionInfoGene">
          <legend>Info générales</legend>
          <div class="field-label">
            <BaseLabel v-model="microCompany.id" label="Id" />
          </div>
          <div class="field">
            <BaseInput
              v-model="microCompany.companyName"
              label="Nom de l'entreprise"
              type="text"
              :error="error.companyName"
            />
          </div>
          <div class="field">
            <BaseInput
              v-model="microCompany.siret"
              label="Numéro siret"
              type="text"
              :error="error.siret"
            />
          </div>
          <div class="field">
            <BaseInput
              v-model="microCompany.siren"
              label="Numéro siren"
              type="text"
              :error="error.siren"
            />
          </div>
          <div class="field-label">
            <BaseLabel v-model="microCompany.createdAt" label="Créée le" />
          </div>
          <div class="field-label">
            <BaseLabel v-model="microCompany.modifiedAt" label="Modifié le" />
          </div>
        </fieldset>
        <fieldset
          class="microCompany-form-content"
          v-show="showSectionEmailPhone"
        >
          <legend>Email et téléphone</legend>
          <div class="field">
            <BaseInput
              v-model="microCompany.email"
              label="Email de l'entreprise"
              type="text"
            />
          </div>
          <div class="field">
            <BaseInput
              v-model="microCompany.phone"
              label="Numéro téléphone"
              type="text"
            />
          </div>
        </fieldset>

        <fieldset class="entity-form-content" v-show="showSectionAdresse">
          <legend>Adresse</legend>
          <AddressForm
            :address="microCompany.address"
            @input="
              (newAddress) => {
                address = newAddress;
              }
            "
          />
        </fieldset>

        <fieldset class="entity-form-content" v-show="showSectionGerant">
          <legend>Gérant</legend>
          <ManagerForm
            :manager="microCompany.manager"
            @input="
              (newManager) => {
                manager = newManager;
              }
            "
          />
        </fieldset>
      </main>
      <footer class="entity-form-footer">
        <button @click="cancelMicroCompany()">
          <span>Cancel</span>
        </button>
        <button @click="saveMicroCompany()">
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
  companyName: "",
  siret: "",
  siren: "",
};

export default {
  name: "MicroMicroCompanyDetail",
  inheritAttrs: false,
  props: {
    id: {
      type: String,
    },
  },
  setup(props) {
    const router = useRouter();
    const store = useStore();
    const sectionToShow = ref("infoGene");
    const error = ref(emptyError);

    const id = props.id;

    const isAddMode = computed(() => {
      return !props.id;
    });
    const title = computed(() => {
      return isAddMode
        ? "Créer une micro entreprise"
        : "Modifier la micro entreprise " + microCompany.value.companyName;
    });

    const showSectionInfoGene = computed(() => {
      return sectionToShow.value === "infoGene";
    });
    const showSectionEmailPhone = computed(() => {
      return sectionToShow.value === "emailPhone";
    });
    const showSectionAdresse = computed(() => {
      return sectionToShow.value === "adresse";
    });
    const showSectionGerant = computed(() => {
      return sectionToShow.value === "gerant";
    });

    const showSection = function (sectionName) {
      return (sectionToShow.value = sectionName);
    };

    if (isAddMode.value) {
      store
        .dispatch("createNewMicroCompanyAction")
        .catch((error) => console.log(error));
    } else {
      store
        .dispatch("getMicroCompanyAction", props.id)
        .catch((error) => console.log(error));
    }

    const microCompany = computed(() => {
      return store.state.microCompany.microCompany;
    });

    const cancelMicroCompany = function () {
      router.push({ name: "microCompanies" });
    };

    const saveMicroCompany = () => {
      error.value = validateMicroCompany(microCompany.value, error.value);
      console.log(errorObjectIsEmpty(error.value));
      if (errorObjectIsEmpty(error.value)) {
        if (isAddMode.value) {
          store
            .dispatch("addMicroCompanyAction", microCompany.value)
            .catch((errorCatch) => console.log(errorCatch));
        } else {
          store
            .dispatch("updateMicroCompanyAction", microCompany.value)
            .catch((errorCatch) => console.log(errorCatch));
        }
        router.push({ name: "microCompanies" });
      }
    };

    return {
      sectionToShow,
      microCompany,
      isAddMode,
      error,
      title,
      showSectionInfoGene,
      showSectionEmailPhone,
      showSectionAdresse,
      showSectionGerant,
      showSection,
      cancelMicroCompany,
      saveMicroCompany,
    };
  },
};

const validateMicroCompany = (microCompany, error) => {
  error = emptyError;
  if (!microCompany.companyName || microCompany.companyName.length === 0) {
    error = {
      ...error,
      companyName: "Le nom de l'entreprise est obligatoire",
    };
  }

  if (microCompany.siret && microCompany.siret.length !== 14) {
    error = { ...error, siret: "Le numéro siret doit faire 14 caractères" };
  }

  if (microCompany.siren && microCompany.siren.length !== 9) {
    error = { ...error, siren: "Le numéro siren doit faire 9 caractères" };
  }
  return error;
};

const errorObjectIsEmpty = (error) => {
  return Object.values(error).every((value) => value.length === 0);
};
</script>

<style>
@import "../../assets/styles/entityEditForm.css";
</style>
