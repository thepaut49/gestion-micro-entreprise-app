<template>
  <fieldset class="person-card">
    <legend>{{ fullName }}</legend>
    <main class="person-card-content">
      <div class="field-label">
        <BaseLabel v-model="person.email" label="Email : " />
      </div>
      <div class="field-label">
        <BaseLabel v-model="person.phone" label="Phone : " />
      </div>
    </main>
    <footer>
      <button @click="askToDelete(person)">
        <span>Supprimer</span>
      </button>
      <router-link
        class="button link-button"
        :to="{ name: 'person-detail', params: { id: person.id } }"
      >
        <span>Sélectioner</span>
      </router-link>
    </footer>
  </fieldset>
</template>

<script>
import { computed } from "vue";

export default {
  name: "PersonCard",
  props: {
    person: {
      type: Object,
      default: () => {
        return {};
      },
    },
  },
  setup(props, { emit }) {
    const personProp = props.person;

    const askToDelete = () => {
      emit("askToDeletePersonEvent", props.person);
    };

    const fullName = computed(() => {
      return personProp.familyName + " " + personProp.firstName;
    });

    return {
      fullName,
      askToDelete,
    };
  },
};
</script>

<style scoped>
.person-card {
  padding: 1em;
  border: solid 1px #2c3e50;
  cursor: pointer;
  transition: all 0.2s linear;
  display: flex;
  flex-direction: column;
}
.person-card:hover {
  transform: scale(1.01);
  box-shadow: 0 3px 12px 0 rgba(0, 0, 0, 0.2), 0 1px 15px 0 rgba(0, 0, 0, 0.19);
}

.person-card-content {
  text-align: left;
  display: flex;
  flex-direction: column;
}

.person-card footer {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-around;
}
</style>
