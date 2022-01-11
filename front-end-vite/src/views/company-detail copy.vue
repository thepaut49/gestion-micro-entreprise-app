<template>
  <section class="company-form">
    <header>
      <h2 class="title">{{ title }}</h2>
      <p class="card-header-title">{{ company.companyName }}</p>
    </header>
    <main class="company-form-main">
      <aside class="company-form-aside">
        <button @click="showSection('infoGene')">Info générales</button>
        <button @click="showSection('emailPhone')">Email et téléphone</button>
        <button @click="showSection('adresse')">Adresse</button>
        <button @click="showSection('gerant')">Gérant</button>
      </aside>
      <section class="company-form-content" v-show="showSectionInfoGene">
        <h3>Info générales</h3>
        <div class="field">
          <label class="label" for="id">id</label>
          <label class="input" name="id" readonly>{{ company.id }}</label>
        </div>
        <div class="field">
          <label class="label" for="companyName">Nom de l'entreprise</label>
          <input
            class="input"
            name="companyName"
            v-model="company.companyName"
          />
        </div>
        <div class="field">
          <label class="label" for="siret">Numéro siret</label>
          <input class="input" name="siret" v-model="company.siret" />
        </div>
        <div class="field">
          <label class="label" for="siren">Numéro siren</label>
          <input class="input" name="siren" v-model="company.siren" />
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
      </section>
      <section class="company-form-content" v-show="showSectionEmailPhone">
        <h3>Email et téléphone</h3>
        <div class="field">
          <label class="label" for="companyEmail">Email de l'entreprise</label>
          <input class="input" name="companyEmail" v-model="company.email" />
        </div>
        <div class="field">
          <label class="label" for="companyPhone">Numéro téléphone</label>
          <input class="input" name="companyPhone" v-model="company.phone" />
        </div>
      </section>

      <section class="company-form-content" v-show="showSectionAdresse">
        <h3>Adresse</h3>
        <AddressForm v-model="company.address" />
      </section>

      <section class="company-form-content" v-show="showSectionGerant">
        <h3>Gérant</h3>
        <ManagerForm v-model="company.manager" />
      </section>
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
import { mapActions, mapGetters } from "vuex";
import AddressForm from "../components/formulaire/AddressForm.vue";
import ManagerForm from "../components/formulaire/ManagerForm.vue";

export default {
  name: "CompanyDetail",
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
  data() {
    return {
      sectionToShow: "infoGene",
      company: {},
    };
  },
  created() {
    if (this.isAddMode) {
      this.company = {
        id: undefined,
        companyName: "",
        siret: "",
        siren: "",
        phone: "",
        email: "",
        createdAt: new Date().toISOString(),
        modifiedAt: new Date().toISOString(),
        manager: {
          id: undefined,
          familyName: "",
          firstName: "",
          createdAt: new Date().toISOString(),
          modifiedAt: new Date().toISOString(),
          phone: "",
          email: "",
        },
        address: {
          id: undefined,
          addressLine1: "",
          addressLine2: "",
          cityName: "",
          countryName: "",
          postalCode: "",
          createdAt: new Date().toISOString(),
          modifiedAt: new Date().toISOString(),
        },
      };
    } else {
      this.company = this.getCompanyById(this.id);
      console.log(this.company);
    }
    console.log(this.company);
  },
  computed: {
    isAddMode() {
      return !this.id;
    },
    title() {
      return `${this.isAddMode ? "Créer" : "Modifier"} l'entreprise`;
    },
    showSectionInfoGene() {
      return this.sectionToShow === "infoGene";
    },
    showSectionEmailPhone() {
      return this.sectionToShow === "emailPhone";
    },
    showSectionAdresse() {
      return this.sectionToShow === "adresse";
    },
    showSectionGerant() {
      return this.sectionToShow === "gerant";
    },
  },
  methods: {
    //...mapGetters["getCompanies"],
    ...mapActions(["updateCompanyAction", "addCompanyAction"]),
    showSection(sectionName) {
      this.sectionToShow = sectionName;
    },
    getCompanyById(id) {
      const companies = this.$store.getters.getCompanies();
      return { ...companies.find((h) => h.id === id) };
    },
    /*cancelCompany() {
      this.$router.push({ name: "/companies" });
    },
    async saveCompany() {
      this.company.id
        ? await this.updateCompanyAction(this.company)
        : await this.addCompanyAction(this.company);
      this.$router.push({ name: "/companies" });
    },*/
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
