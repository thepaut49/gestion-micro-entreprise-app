<template>
  <div class="entity-form-container">
    <aside class="entity-form-aside">
      <button @click="showSection('infoGene')">Info générales</button>
      <button @click="showSection('emailPhone')">Email et téléphone</button>
      <button @click="showSection('adresse')">Adresse</button>
      <button @click="showSection('gerant')">Gérant</button>
    </aside>
    <section class="entity-form">
      <header class="title">
        <h2>{{ title }}</h2>
        <router-link
          class="button link-button"
          :to="{ name: 'microCompany-detail', params: { id: microCompany.id } }"
        >
          <font-awesome-icon icon="edit" />
        </router-link>
      </header>
      <main>
        <fieldset class="entity-form-content" v-show="showSectionInfoGene">
          <legend>Info générales</legend>
          <div class="field-label">
            <BaseLabel v-model="microCompany.id" label="Id" />
          </div>
          <div class="field">
            <BaseLabel
              v-model="microCompany.companyName"
              label="Nom de l'entreprise"
            />
          </div>
          <div class="field">
            <BaseLabel v-model="microCompany.siret" label="Numéro siret" />
          </div>
          <div class="field">
            <BaseLabel v-model="microCompany.siren" label="Numéro siren" />
          </div>
          <div class="field-label">
            <BaseLabel v-model="microCompany.createdAt" label="Créée le" />
          </div>
          <div class="field-label">
            <BaseLabel v-model="microCompany.modifiedAt" label="Modifié le" />
          </div>
        </fieldset>
        <fieldset
          class="microCompany-form-content"
          v-show="showSectionEmailPhone"
        >
          <legend>Email et téléphone</legend>
          <div class="field">
            <BaseLabel
              v-model="microCompany.email"
              label="Email de l'entreprise"
            />
          </div>
          <div class="field">
            <BaseLabel v-model="microCompany.phone" label="Numéro téléphone" />
          </div>
        </fieldset>

        <fieldset class="entity-form-content" v-show="showSectionAdresse">
          <legend>Adresse</legend>
          <AddressFormVisu :address="microCompany.address" />
        </fieldset>

        <fieldset class="entity-form-content" v-show="showSectionGerant">
          <legend>Gérant</legend>
          <ManagerFormVisu :manager="microCompany.manager" />
        </fieldset>
      </main>
    </section>
  </div>
</template>

<script>
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { ref, computed } from "vue";

export default {
  name: "MicroMicroCompanyDetailVisu",
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

    const id = props.id;

    const title = ref("Ma micro entreprise");

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

    store
      .dispatch("getMicroCompanyForUserAction")
      .catch((error) => console.log(error));

    const microCompany = computed(() => {
      return store.state.microCompany.microCompany;
    });

    const cancelMicroCompany = function () {
      router.go(-1);
    };

    return {
      sectionToShow,
      microCompany,
      title,
      showSectionInfoGene,
      showSectionEmailPhone,
      showSectionAdresse,
      showSectionGerant,
      showSection,
    };
  },
};
</script>

<style scoped>
@import "../../assets/styles/entityEditForm.css";

.title {
  display: flex;
  align-items: center;
}
</style>
