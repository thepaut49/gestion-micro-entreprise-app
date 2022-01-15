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
        <div class="field">
          <label class="label" for="id">id</label>
          <label class="input" name="id" readonly>{{ company.id }}</label>
        </div>
        <div class="field">
          <BaseInput
            v-model="company.companyName"
            label="Nom de l'entreprise"
            type="text"
          />
        </div>
        <div class="field">
          <BaseInput v-model="company.siret" label="Numéro siret" type="text" />
        </div>
        <div class="field">
          <BaseInput v-model="company.siren" label="Numéro siren" type="text" />
        </div>
        <div class="field">
          <label class="label" for="createdAt">Créée le</label>
          <label class="input" name="createdAt" readonly>{{
            company.createdAt
          }}</label>
        </div>
        <div class="field">
          <label class="label" for="modifiedAt">Modifié le</label>
          <label class="input" name="modifiedAt" readonly>{{
            company.modifiedAt
          }}</label>
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
import AddressForm from "../components/formulaire/AddressForm.vue";
import ManagerForm from "../components/formulaire/ManagerForm.vue";
import _ from "lodash";

export default {
  name: "CompanyDetail",
  inheritAttrs: false,
  props: {
    id: {
      type: String,
      default: 0,
    },
  },
  components: {
    AddressForm,
    ManagerForm,
  },
  setup(props) {
    const router = useRouter();
    const store = useStore();
    const sectionToShow = ref("infoGene");

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
        .dispatch("createCompanyAction")
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
      if (isAddMode.value) {
        store
          .dispatch("addCompanyAction", company.value)
          .catch((error) => console.log(error));
      } else {
        store
          .dispatch("updateCompanyAction", company.value)
          .catch((error) => console.log(error));
      }
      router.push({ name: "companies" });
    };

    return {
      sectionToShow,
      company,
      isAddMode,
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
</script>

<style scoped>
.field {
  display: flex;
  flex-direction: column;
  margin: 0.5em;
}

.field label {
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
