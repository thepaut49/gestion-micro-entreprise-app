<template>
  <section>
    <section>
      <div class="listTitle">
        <h2 class="pageTitle">Companies</h2>
        <button @click="loadCompanies()">
          <i class="fas fa-sync"></i>
        </button>
        <router-link
          tag="button"
          :to="{ name: 'company-detail', params: { id: 0 } }"
        >
          <i class="fas fa-plus"></i>
        </router-link>
      </div>
      <ul>
        <li v-for="company in companies" :key="company.id">
          <div>{{ company.companytName }}</div>
          <button @click="askToDelete(company)">
            <span>Supprimer</span>
          </button>
          <router-link
            tag="button"
            :to="{ name: 'company-detail', params: { id: company.id } }"
          >
            <span>Selectioner</span>
          </router-link>
        </li>
      </ul>
    </section>
    <div v-show="message">{{ message }}</div>
  </section>
</template>

<script>
import { mapActions, mapState } from "vuex";
//import Modal from "@/components/modal";

export default {
  name: "Companies",
  data() {
    return {
      companyToDelete: null,
      message: "",
      showModal: false,
    };
  },
  /*components: {
    Modal,
  },*/
  async created() {
    await this.loadCompanies();
  },
  methods: {
    ...mapActions(["getCompaniesAction", "deleteCompanyAction"]),
    askToDelete(company) {
      this.companyToDelete = company;
      this.showModal = true;
    },
    closeModal() {
      this.showModal = false;
    },
    async deleteCompany() {
      this.closeModal();
      if (this.companyToDelete) {
        await this.deleteCompanyAction(this.companyToDelete);
      }
      await this.loadCompanies();
    },
    async loadCompanies() {
      this.message =
        "Récupération des entreprises en cours, veuillez patienter";
      await this.getCompaniesAction();
      this.message = "";
    },
  },
  computed: {
    ...mapState(["companies"]),
    modalMessage() {
      const name =
        this.companyToDelete && this.companyToDelete.fullName
          ? this.companyToDelete.fullName
          : "";
      return `Voulez vous supprimer l'entreprise ${name} ?`;
    },
  },
};
</script>

<style scoped>
.listTitle {
  display: flex;
  align-items: center;
}

.listTitle > * {
  padding: 0 10px;
}
</style>
