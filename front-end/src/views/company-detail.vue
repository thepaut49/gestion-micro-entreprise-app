<template>
  <section>
    <header>
      <h2 class="title">{{ title }}</h2>
      <p class="card-header-title">{{ company.companyName }}</p>
    </header>
    <main>
      <div class="field">
        <label class="label" for="id">id</label>
        <label class="input" name="id" readonly>{{ company.id }}</label>
      </div>
      <div class="field">
        <label class="label" for="companyName">Nom de l'entreprise</label>
        <input class="input" name="companyName" v-model="company.companyName" />
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

      <h3>Email et téléphone</h3>

      <h3>Adresse</h3>

      <h3>Gérant</h3>
    </main>
    <footer>
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

export default {
  name: "CompanyDetail",
  props: {
    id: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
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
        phone: "",
        email: "",
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
      this.company = { ...this.getCompanyById(this.id) };
    }
  },
  computed: {
    ...mapGetters(["getCompanyById"]),
    isAddMode() {
      return !this.id;
    },
    title() {
      return `${this.isAddMode ? "Créer" : "Modifier"} l'entreprise`;
    },
  },
  methods: {
    ...mapActions(["updateCompanyAction", "addCompanyAction"]),
    cancelCompany() {
      this.$router.push({ name: "/companies" });
    },
    async saveCompany() {
      this.company.id
        ? await this.updateCompanyAction(this.company)
        : await this.addCompanyAction(this.company);
      this.$router.push({ name: "/companies" });
    },
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
</style>
