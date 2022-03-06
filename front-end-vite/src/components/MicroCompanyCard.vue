<template>
  <fieldset class="microCompany-card">
    <legend>
      {{ microCompany.companyName }}
    </legend>
    <main class="microCompany-card-content">
      <div class="field-label">
        <BaseLabel v-model="microCompany.siren" label="Siren : " />
      </div>
      <div class="field-label">
        <BaseLabel v-model="microCompany.siret" label="Siret : " />
      </div>
    </main>
    <footer>
      <button @click="askToDelete(microCompany)">
        <span>Supprimer</span>
      </button>
      <router-link
        class="button link-button"
        :to="{ name: 'microCompany-detail', params: { id: microCompany.id } }"
      >
        <span>Sélectioner</span>
      </router-link>
    </footer>
  </fieldset>
</template>

<script>
export default {
  name: "MicroCompanyCard",
  props: {
    microCompany: {
      type: Object,
      default: () => {
        return {};
      },
    },
  },
  setup(props, { emit }) {
    const askToDelete = () => {
      emit("askToDeleteMicroCompanyEvent", props.microCompany);
    };

    return {
      askToDelete,
    };
  },
};
</script>

<style scoped>
.microCompany-card {
  padding: 1em;
  border: solid 1px #2c3e50;
  cursor: pointer;
  transition: all 0.2s linear;
  display: flex;
  flex-direction: column;
}
.microCompany-card:hover {
  transform: scale(1.01);
  box-shadow: 0 3px 12px 0 rgba(0, 0, 0, 0.2), 0 1px 15px 0 rgba(0, 0, 0, 0.19);
}

.microCompany-card-content {
  text-align: left;
  display: flex;
  flex-direction: column;
}

.microCompany-card footer {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-around;
}
</style>
