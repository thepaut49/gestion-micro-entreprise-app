<template>
  <section class="listCompanies">
    <h2>Companies</h2>
    <router-link tag="button" :to="{ name: 'company-detail' }">
      <span>Ajouter une nouvelle entreprise</span>
    </router-link>
    <template v-if="!isLoading">
      <CompanyCard
        v-for="company in companies"
        :key="company.id"
        :company="company"
      />
    </template>
    <p v-else>Récupération des entreprises en cours, veuillez patienter</p>
  </section>
</template>

<script>
import CompanyCard from "../components/CompanyCard.vue";

export default {
  name: "Companies",
  data() {
    return {
      isLoading: true,
      companyToDelete: null,
      showModal: false,
    };
  },
  components: {
    CompanyCard,
  },
  created() {
    this.$store
      .dispatch("getCompaniesAction")
      .then(() => {
        this.isLoading = false;
      })
      .catch((error) => {
        this.isLoading = false;
        console.log(error);
      });
  },
  methods: {
    askToDelete(company) {
      this.companyToDelete = company;
      this.showModal = true;
    },
    closeModal() {
      this.showModal = false;
    },
    deleteCompany() {
      this.closeModal();
      if (this.companyToDelete) {
        this.deleteCompanyAction(this.companyToDelete);
      }
      this.$store
        .dispatch("getCompaniesAction")
        .then(() => {
          this.isLoading = false;
        })
        .catch((error) => {
          this.isLoading = false;
          console.log(error);
        });
    },
  },
  computed: {
    companies() {
      return this.$store.state.company.companies;
    },
  },
};
</script>

<style scoped>
.listCompanies {
  justify-content: start;
}
</style>
