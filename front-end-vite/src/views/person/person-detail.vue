<template>
  <div class="entity-form-container">
    <aside class="entity-form-aside">
      <button @click="showSection('infoGene')">Info générales</button>
      <button @click="showSection('emailPhone')">Email et téléphone</button>
      <button @click="showSection('adresse')">Adresse</button>
    </aside>
    <section class="entity-form">
      <header class="title">
        <h2>{{ title }}</h2>
      </header>
      <main>
        <fieldset class="entity-form-content" v-show="showSectionInfoGene">
          <legend>Info générales</legend>
          <div class="field-label">
            <BaseLabel v-model="person.id" label="Id" />
          </div>
          <div class="field">
            <BaseInput
              v-model="person.familyName"
              label="Nom"
              type="text"
              :error="error.familyName"
            />
          </div>
          <div class="field">
            <BaseInput
              v-model="person.firstName"
              label="Prénom"
              type="text"
              :error="error.firstName"
            />
          </div>
          <div class="field-label">
            <BaseLabel v-model="person.createdAt" label="Créée le" />
          </div>
          <div class="field-label">
            <BaseLabel v-model="person.modifiedAt" label="Modifié le" />
          </div>
        </fieldset>
        <fieldset class="person-form-content" v-show="showSectionEmailPhone">
          <legend>Email et téléphone</legend>
          <div class="field">
            <BaseInput
              v-model="person.email"
              label="Email de l'entreprise"
              type="text"
            />
          </div>
          <div class="field">
            <BaseInput
              v-model="person.phone"
              label="Numéro téléphone"
              type="text"
            />
          </div>
        </fieldset>

        <fieldset class="entity-form-content" v-show="showSectionAdresse">
          <legend>Adresse</legend>
          <AddressForm
            :address="person.address"
            @input="
              (newAddress) => {
                address = newAddress;
              }
            "
          />
        </fieldset>
      </main>
      <footer class="entity-form-footer">
        <button @click="cancelPerson()">
          <span>Cancel</span>
        </button>
        <button @click="savePerson()">
          <span>Save</span>
        </button>
      </footer>
    </section>
  </div>
</template>

<script>
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { ref, computed } from "vue";

const emptyError = {
  familyName: "",
  firstName: "",
};

export default {
  name: "PersonDetail",
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
    const error = ref(emptyError);

    const id = props.id;

    const isAddMode = computed(() => {
      return !props.id;
    });
    const title = computed(() => {
      return isAddMode
        ? "Créer une personne"
        : "Modifier la personne " +
            person.value.familyName +
            " " +
            person.value.firstName;
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

    const showSection = function (sectionName) {
      return (sectionToShow.value = sectionName);
    };

    if (isAddMode.value) {
      store
        .dispatch("createNewPersonAction")
        .catch((error) => console.log(error));
    } else {
      store
        .dispatch("getPersonAction", props.id)
        .catch((error) => console.log(error));
    }

    const person = computed(() => {
      return store.state.person.person;
    });

    const cancelPerson = function () {
      router.push({ name: "persons" });
    };

    const savePerson = () => {
      error.value = validatePerson(person.value, error.value);
      console.log(errorObjectIsEmpty(error.value));
      if (errorObjectIsEmpty(error.value)) {
        if (isAddMode.value) {
          store
            .dispatch("addPersonAction", person.value)
            .catch((errorCatch) => console.log(errorCatch));
        } else {
          store
            .dispatch("updatePersonAction", person.value)
            .catch((errorCatch) => console.log(errorCatch));
        }
        router.push({ name: "persons" });
      }
    };

    return {
      sectionToShow,
      person,
      isAddMode,
      error,
      title,
      showSectionInfoGene,
      showSectionEmailPhone,
      showSectionAdresse,
      showSection,
      cancelPerson,
      savePerson,
    };
  },
};

const validatePerson = (person, error) => {
  error = emptyError;
  if (!person.familyName || person.familyName.length === 0) {
    error = {
      ...error,
      familyName: "Le nom est obligatoire",
    };
  }

  if (!person.firstName && person.firstName.length === 0) {
    error = { ...error, firstName: "Le prénom est obligatoire" };
  }
  return error;
};

const errorObjectIsEmpty = (error) => {
  return Object.values(error).every((value) => value.length === 0);
};
</script>

<style scoped>
@import "../../assets/styles/entityEditForm.css";
</style>
