<template>
  <fieldset>
    <legend>Client</legend>
    <div class="field">
      <BaseSelect
        :options="clientTypes"
        v-model="client.clientType"
        label="Type de client"
        name="clientType"
      />
    </div>

    <div class="field">
      <BaseInput v-model="client.name" label="Nom" type="text" />
    </div>

    <div class="field" v-show="showCompanyFields">
      <BaseInput v-model="client.siren" label="Siren" type="text" />
    </div>

    <div class="field" v-show="showCompanyFields">
      <BaseInput v-model="client.siret" label="Siret" type="text" />
    </div>

    <fieldset>
      <legend>Email et téléphone</legend>
      <div class="field">
        <BaseInput v-model="client.email" label="Email" type="text" />
      </div>

      <div class="field">
        <BaseInput v-model="client.phone" label="Téléphone" type="text" />
      </div>
    </fieldset>

    <fieldset>
      <legend>Adresse</legend>
      <AddressForm
        :address="client.address"
        @input="
          (newAddress) => {
            address = newAddress;
          }
        "
      />
    </fieldset>
  </fieldset>
</template>

<script>
import { watchEffect, ref } from "vue";

export default {
  name: "ClientForm",
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
    let showCompanyFields = ref(false);
    const clientTypes = ["ENTREPRISE", "PARTICULIER", "ETAT"];
    watchEffect(() => {
      if (props.client.clientType === "ENTREPRISE") {
        showCompanyFields.value = true;
      } else {
        showCompanyFields.value = false;
      }
    });

    return {
      showCompanyFields,
      clientTypes,
    };
  },
};
</script>

<style scoped>
label {
  font-weight: bold;
}
</style>
