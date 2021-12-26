<template>
  <div class="content-container">
    <div class="columns">
      <div class="column is-8">
        <div class="section content-title-group">
          <h2 class="title">Companies</h2>
          <button class="button refresh-button" @click="loadCompanies()">
            <i class="fas fa-sync"></i>
          </button>
          <router-link
            tag="button"
            class="button add-button"
            :to="{ name: 'company-detail', params: { id: 0 } }"
          >
            <i class="fas fa-plus"></i>
          </router-link>
          <ul>
            <li v-for="company in companies" :key="company.id">
              <div class="card">
                <div class="card-content">
                  <div class="content">
                    <div :key="company.name" class="name">
                      {{ company.firstName }} {{ company.lastName }}
                    </div>
                    <div class="description">{{ company.description }}</div>
                  </div>
                </div>
                <footer class="card-footer">
                  <button
                    class="link card-footer-item"
                    @click="askToDelete(company)"
                  >
                    <i class="fas fa-trash"></i>
                    <span>Delete</span>
                  </button>
                  <router-link
                    tag="button"
                    class="link card-footer-item"
                    :to="{ name: 'company-detail', params: { id: company.id } }"
                  >
                    <i class="fas fa-check"></i>
                    <span>Select</span>
                  </router-link>
                </footer>
              </div>
            </li>
          </ul>
        </div>
        <div class="notification is-info" v-show="message">{{ message }}</div>
      </div>
    </div>
    <Modal
      :message="modalMessage"
      :isOpen="showModal"
      @handleNo="closeModal"
      @handleYes="deleteHero"
    >
    </Modal>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import Modal from "@/components/modal";

export default {
  name: "Companies",
  data() {
    return {
      companyToDelete: null,
      message: "",
      showModal: false,
    };
  },
  components: {
    Modal,
  },
  async created() {
    await this.loadCompanies();
  },
  methods: {
    ...mapActions(["getCompaniesAction", "deleteHeroAction"]),
    askToDelete(company) {
      this.companyToDelete = company;
      this.showModal = true;
    },
    closeModal() {
      this.showModal = false;
    },
    async deleteHero() {
      this.closeModal();
      if (this.companyToDelete) {
        await this.deleteHeroAction(this.companyToDelete);
      }
      await this.loadCompanies();
    },
    async loadCompanies() {
      this.message = "getting the companies, please be patient";
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
      return `Would you like to delete ${name} ?`;
    },
  },
};
</script>
