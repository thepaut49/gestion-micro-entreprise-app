<template>
  <section class="company-card">
    <header>
      <h4>{{ company.companyName }}</h4>
    </header>
    <main>
      <label>Siren : </label>
      <div>{{ company.siren }}</div>
      <label>Siret : </label>
      <div>{{ company.siret }}</div>
    </main>
    <footer>
      <button @click="askToDelete(company)">
        <span>Supprimer</span>
      </button>
      <router-link
        tag="button"
        :to="{ name: 'company-detail', params: { id: company.id } }"
      >
        <span>Selectioner</span>
      </router-link>
    </footer>
  </section>
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
};
</script>

<style scoped>
.company-card {
  width: 90%;
  margin: 1em auto 1em auto;
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

.company-card main {
  text-align: left;
}

.company-card footer {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-around;
}

.company-card footer button :hover {
  transform: scale(1.01);
  box-shadow: 0 3px 12px 0 rgba(0, 0, 0, 0.2), 0 1px 15px 0 rgba(0, 0, 0, 0.19);
}
</style>
