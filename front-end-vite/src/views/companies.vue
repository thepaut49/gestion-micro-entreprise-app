<template>
  <section class="listEntitiesSection">
    <header class="listEntitiesHeader">
      <h2>Companies</h2>
      <router-link
        tag="button"
        class="button addButton"
        :to="{ name: 'company-detail' }"
      >
        <font-awesome-icon icon="plus" />
      </router-link>
      <button @click="reloadCompanies">
        <font-awesome-icon icon="sync" />
      </button>
    </header>

    <main class="listEntitiesMain" v-if="!isLoading">
      <CompanyCard
        v-for="company in companies"
        :key="company.id"
        :company="company"
        @askToDeleteCompanyEvent="askToDelete(company)"
      />
    </main>
    <p v-else>Récupération des entreprises en cours, veuillez patienter</p>
    <Modal
      :message="modalMessage"
      :isOpen="showModal"
      @handleNo="closeModal"
      @handleYes="deleteCompany"
    >
    </Modal>
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
      modalMessage: "",
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
      this.modalMessage =
        "Voulez vous supprimer l'entreprise " + company.companyName + " ?";
    },
    closeModal() {
      this.showModal = false;
    },
    deleteCompany() {
      this.closeModal();
      if (this.companyToDelete) {
        this.$store.dispatch("deleteCompanyAction", this.companyToDelete);
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
    reloadCompanies() {
      this.isLoading = true;
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
