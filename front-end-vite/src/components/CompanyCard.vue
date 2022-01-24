<template>
  <fieldset class="company-card">
    <legend>
      {{ company.companyName }}
    </legend>
    <main class="company-card-content">
      <div class="field-label">
        <BaseLabel v-model="company.siren" label="Siren : " />
      </div>
      <div class="field-label">
        <BaseLabel v-model="company.siret" label="Siret : " />
      </div>
    </main>
    <footer>
      <button @click="askToDelete(company)">
        <span>Supprimer</span>
      </button>
      <router-link
        class="button link-button"
        :to="{ name: 'company-detail', params: { id: company.id } }"
      >
        <span>Sélectioner</span>
      </router-link>
    </footer>
  </fieldset>
</template>

<script>
export default {
  name: "CompanyCard",
  props: {
    company: {
      type: Object,
      default: () => {
        return {};
      },
    },
  },
  setup(props, { emit }) {
    const askToDelete = () => {
      emit("askToDeleteCompanyEvent", props.company);
    };

    return {
      askToDelete,
    };
  },
};
</script>

<style scoped>
.company-card {
  padding: 1em;
  border: solid 1px #2c3e50;
  cursor: pointer;
  transition: all 0.2s linear;
  display: flex;
  flex-direction: column;
}
.company-card:hover {
  transform: scale(1.01);
  box-shadow: 0 3px 12px 0 rgba(0, 0, 0, 0.2), 0 1px 15px 0 rgba(0, 0, 0, 0.19);
}
.company-card h4 {
  font-size: 1.4em;
  margin-top: 0.5em;
  margin-bottom: 0.3em;
}

.company-card-content {
  text-align: left;
  display: flex;
  flex-direction: column;
}

.company-card footer {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-around;
}
</style>
