<template>
  <fieldset class="client-card">
    <legend>Client</legend>
    <div>
      <BaseLabel v-model="client.clientType" label="Type de client : " />
    </div>
    <div>
      <BaseLabel v-model="client.name" label="Nom du client : " />
    </div>
    <div v-show="isClientACompany()">
      <BaseLabel v-model="client.siren" label="Siren : " />
    </div>
    <div v-show="isClientACompany()">
      <BaseLabel v-model="client.siret" label="Siret : " />
    </div>
    <div>
      <button @click="selectClientGranChild(client)">
        <span>Sélectioner</span>
      </button>
    </div>
  </fieldset>
</template>

<script>
export default {
  name: "ClientCard",
  props: {
    client: {
      type: Object,
      default: () => {
        return {};
      },
    },
  },
  setup(props, { emit }) {
    const clientProp = props.client;

    const isClientACompany = () => {
      return clientProp.clientType === "ENTREPRISE";
    };

    const selectClientGranChild = () => {
      emit("handleSelectClientGranChild", clientProp);
    };

    return {
      selectClientGranChild,
      isClientACompany,
    };
  },
};
</script>

<style scoped>
.client-card {
  padding: 1em;
  border: solid 1px #2c3e50;
  cursor: pointer;
  transition: all 0.2s linear;
  display: flex;
  flex-direction: column;
}
.client-card:hover {
  transform: scale(1.01);
  box-shadow: 0 3px 12px 0 rgba(0, 0, 0, 0.2), 0 1px 15px 0 rgba(0, 0, 0, 0.19);
}
</style>
