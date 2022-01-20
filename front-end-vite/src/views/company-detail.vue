<template>
  <section class="company-form">
    <header>
      <h2 class="title">{{ title }}</h2>
    </header>
    <main class="company-form-main">
      <aside class="company-form-aside">
        <button @click="showSection('infoGene')">Info générales</button>
        <button @click="showSection('emailPhone')">Email et téléphone</button>
        <button @click="showSection('adresse')">Adresse</button>
        <button @click="showSection('gerant')">Gérant</button>
      </aside>
      <fieldset class="company-form-content" v-show="showSectionInfoGene">
        <legend>Info générales</legend>
        <div class="field-label">
          <BaseLabel v-model="company.id" label="Id" />
        </div>
        <div class="field">
          <BaseInput
            v-model="company.companyName"
            label="Nom de l'entreprise"
            type="text"
            :error="error.companyName"
          />
        </div>
        <div class="field">
          <BaseInput
            v-model="company.siret"
            label="Numéro siret"
            type="text"
            :error="error.siret"
          />
        </div>
        <div class="field">
          <BaseInput
            v-model="company.siren"
            label="Numéro siren"
            type="text"
            :error="error.siren"
          />
        </div>
        <div class="field-label">
          <BaseLabel v-model="company.createdAt" label="Créée le" />
        </div>
        <div class="field-label">
          <BaseLabel v-model="company.modifiedAt" label="Modifié le" />
        </div>
      </fieldset>
      <fieldset class="company-form-content" v-show="showSectionEmailPhone">
        <legend>Email et téléphone</legend>
        <div class="field">
          <BaseInput
            v-model="company.email"
            label="Email de l'entreprise"
            type="text"
          />
        </div>
        <div class="field">
          <BaseInput
            v-model="company.phone"
            label="Numéro téléphone"
            type="text"
          />
        </div>
      </fieldset>

      <fieldset class="company-form-content" v-show="showSectionAdresse">
        <legend>Adresse</legend>
        <AddressForm
          :address="company.address"
          @input="
            (newAddress) => {
              address = newAddress;
            }
          "
        />
      </fieldset>

      <fieldset class="company-form-content" v-show="showSectionGerant">
        <legend>Gérant</legend>
        <ManagerForm
          :manager="company.manager"
          @input="
            (newManager) => {
              manager = newManager;
            }
          "
        />
      </fieldset>
    </main>
    <footer class="company-form-footer">
      <button @click="cancelCompany()">
        <i class="fas fa-undo"></i>
        <span>Cancel</span>
      </button>
      <button @click="saveCompany()">
        <i class="fas fa-save"></i>
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
  companyName: "",
  siret: "",
  siren: "",
};

export default {
  name: "CompanyDetail",
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
        ? "Modifier l'entreprise " + company.value.companyName
        : "Créer l'entreprise";
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
        .dispatch("createNewCompanyAction")
        .catch((error) => console.log(error));
    } else {
      store
        .dispatch("getCompanyAction", props.id)
        .catch((error) => console.log(error));
    }

    const company = computed(() => {
      return store.state.company.company;
    });

    const cancelCompany = function () {
      router.push({ name: "companies" });
    };

    const saveCompany = () => {
      error.value = validateCompany(company.value, error.value);
      console.log(errorObjectIsEmpty(error.value));
      if (errorObjectIsEmpty(error.value)) {
        if (isAddMode.value) {
          store
            .dispatch("addCompanyAction", company.value)
            .catch((errorCatch) => console.log(errorCatch));
        } else {
          store
            .dispatch("updateCompanyAction", company.value)
            .catch((errorCatch) => console.log(errorCatch));
        }
        router.push({ name: "companies" });
      }
    };

    return {
      sectionToShow,
      company,
      isAddMode,
      error,
      title,
      showSectionInfoGene,
      showSectionEmailPhone,
      showSectionAdresse,
      showSectionGerant,
      showSection,
      cancelCompany,
      saveCompany,
    };
  },
};

const validateCompany = (company, error) => {
  error = emptyError;
  if (!company.companyName || company.companyName.length === 0) {
    error = {
      ...error,
      companyName: "Le nom de l'entreprise est obligatoire",
    };
  }

  if (company.siret && company.siret.length !== 14) {
    error = { ...error, siret: "Le numéro siret doit faire 14 caractères" };
  }

  if (company.siren && company.siren.length !== 9) {
    error = { ...error, siren: "Le numéro siren doit faire 9 caractères" };
  }
  return error;
};

const errorObjectIsEmpty = (error) => {
  return Object.values(error).every((value) => value.length === 0);
};
</script>

<style scoped>
.field-label {
  display: flex;
  flex-direction: row;
}
.field {
  display: flex;
  flex-direction: column;
}

label {
  font-weight: bold;
  justify-content: left;
}
.company-form-footer {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  flex-shrink: 1;
}

.company-form-aside {
  border: solid black;
  flex-shrink: 1;
}

.company-form-content {
  border: solid blue;
  flex-grow: 1;
  margin-left: 0.5em;
  display: flex;
  flex-direction: column;
  gap: 1em;
}

.company-form-main {
  display: flex;
  flex-direction: row;
  flex-grow: 1;
}

.company-form {
  display: flex;
  flex-direction: column;
  align-items: stretch;
}
</style>
