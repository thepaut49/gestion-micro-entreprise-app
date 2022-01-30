<template>
  <section class="listEntitiesSection">
    <header class="listEntitiesHeader">
      <h2>Persons</h2>
      <router-link
        tag="button"
        class="button addButton"
        :to="{ name: 'person-detail' }"
      >
        <font-awesome-icon icon="plus" />
      </router-link>
      <button @click="reloadPersons">
        <font-awesome-icon icon="sync" />
      </button>
    </header>

    <main class="listEntitiesMain" v-if="!isLoading">
      <PersonCard
        v-for="person in persons"
        :key="person.id"
        :person="person"
        @askToDeletePersonEvent="askToDelete(person)"
      />
    </main>
    <p v-else>Récupération des personnes en cours, veuillez patienter</p>
    <Modal
      :message="modalMessage"
      :isOpen="showModal"
      @handleNo="closeModal"
      @handleYes="deletePerson"
    >
    </Modal>
  </section>
</template>

<script>
export default {
  name: "Persons",
  data() {
    return {
      isLoading: true,
      personToDelete: null,
      showModal: false,
      modalMessage: "",
    };
  },
  created() {
    this.$store
      .dispatch("getPersonsAction")
      .then(() => {
        this.isLoading = false;
      })
      .catch((error) => {
        this.isLoading = false;
        console.log(error);
      });
  },
  methods: {
    askToDelete(person) {
      this.personToDelete = person;
      this.showModal = true;
      this.modalMessage =
        "Voulez vous supprimer la personne " +
        person.familyName +
        " " +
        person.firstName +
        " ?";
    },
    closeModal() {
      this.showModal = false;
    },
    deletePerson() {
      this.closeModal();
      if (this.personToDelete) {
        this.$store.dispatch("deletePersonAction", this.personToDelete);
      }
      this.$store
        .dispatch("getPersonsAction")
        .then(() => {
          this.isLoading = false;
        })
        .catch((error) => {
          this.isLoading = false;
          console.log(error);
        });
    },
    reloadPersons() {
      this.isLoading = true;
      this.$store
        .dispatch("getPersonsAction")
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
    persons() {
      return this.$store.state.person.persons;
    },
  },
};
</script>
