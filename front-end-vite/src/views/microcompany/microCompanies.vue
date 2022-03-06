<template>
  <section class="listEntitiesSection">
    <header class="listEntitiesHeader">
      <h2>Micro entreprises</h2>
      <router-link
        tag="button"
        class="button addButton"
        :to="{ name: 'microCompany-detail' }"
        v-if="canCreateMicroCompany"
      >
        <font-awesome-icon icon="plus" />
      </router-link>
      <button @click="reloadMicroCompanies">
        <font-awesome-icon icon="sync" />
      </button>
    </header>

    <main class="listEntitiesMain" v-if="!isLoading">
      <MicroCompanyCard
        v-for="microCompany in microCompanies"
        :key="microCompany.id"
        :microCompany="microCompany"
        @askToDeleteMicroCompanyEvent="askToDelete(microCompany)"
      />
    </main>
    <p v-else>Récupération des entreprises en cours, veuillez patienter</p>
    <Modal
      :message="modalMessage"
      :isOpen="showModal"
      @handleNo="closeModal"
      @handleYes="deleteMicroCompany"
    >
    </Modal>
  </section>
</template>

<script>
import MicroCompanyCard from "../../components/MicroCompanyCard.vue";

export default {
  name: "MicroCompanies",
  data() {
    return {
      isLoading: true,
      microCompanyToDelete: null,
      showModal: false,
      modalMessage: "",
    };
  },
  components: {
    MicroCompanyCard,
  },
  created() {
    this.$store
      .dispatch("getMicroCompaniesAction")
      .then(() => {
        this.isLoading = false;
      })
      .catch((error) => {
        this.isLoading = false;
        console.log(error);
      });
    this.$store.dispatch("canCreateMicroCompanyAction").catch((error) => {
      console.log(error);
    });
  },
  methods: {
    askToDelete(microCompany) {
      this.microCompanyToDelete = microCompany;
      this.showModal = true;
      this.modalMessage =
        "Voulez vous supprimer l'entreprise " + microCompany.companyName + " ?";
    },
    closeModal() {
      this.showModal = false;
    },
    deleteMicroCompany() {
      this.closeModal();
      if (this.microCompanyToDelete) {
        this.$store.dispatch(
          "deleteMicroCompanyAction",
          this.microCompanyToDelete
        );
        this.$store.dispatch("canCreateMicroCompanyAction").catch((error) => {
          console.log(error);
        });
      }
      this.$store
        .dispatch("getMicroCompaniesAction")
        .then(() => {
          this.isLoading = false;
        })
        .catch((error) => {
          this.isLoading = false;
          console.log(error);
        });
    },
    reloadMicroCompanies() {
      this.isLoading = true;
      this.$store
        .dispatch("getMicroCompaniesAction")
        .then(() => {
          this.isLoading = false;
        })
        .catch((error) => {
          this.isLoading = false;
          console.log(error);
        });
      this.$store.dispatch("canCreateMicroCompanyAction").catch((error) => {
        console.log(error);
      });
    },
  },
  computed: {
    microCompanies() {
      return this.$store.state.microCompany.microCompanies;
    },
    canCreateMicroCompany() {
      return this.$store.state.microCompany.canCreateMicroCompany;
    },
  },
};
</script>
