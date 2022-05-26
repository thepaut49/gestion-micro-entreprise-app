<template>
  <div>
    <BaseLabel v-model="client.name" label="Fournisseur : " v-bind="$attrs" />
  </div>

  <div>
    <BaseLabel v-model="client.email" label="Email : " v-bind="$attrs" />
  </div>

  <div>
    <BaseLabel v-model="client.phone" label="Téléphone : " v-bind="$attrs" />
  </div>

  <div v-show="showCompanyFields">
    <BaseLabel v-model="client.siren" label="Siren : " v-bind="$attrs" />
  </div>

  <div v-show="showCompanyFields">
    <BaseLabel v-model="client.siret" label="Siret : " v-bind="$attrs" />
  </div>
  <label><strong>Adresse :</strong></label>
  <AddressFormVisu :address="client.address" v-bind="$attrs" />
</template>

<script>
import { ref } from "vue";

export default {
  name: "ClientVisu",
  inheritAttrs: true,
  props: {
    client: {
      type: Object,
      default: () => {
        return {
          id: undefined,
          clientType: "",
          name: "",
          siret: "",
          siren: "",
          address: {
            addressLine1: "",
            addressLine2: "",
            cityName: "",
            countryName: "",
            postalCode: "",
          },
          phone: "",
          email: "",
        };
      },
    },
  },
  setup(props) {
    const showCompanyFields = ref(props.client.clientType === "ENTREPRISE");
    return {
      showCompanyFields,
    };
  },
};
</script>

<style scoped>
label {
  font-weight: bold;
}
</style>
