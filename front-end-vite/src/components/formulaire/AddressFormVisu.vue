<template>
  <div class="field">
    <BaseLabel
      v-model="address.addressLine1"
      label="Première ligne d'adresse"
      v-bind="$attrs"
    />
  </div>
  <div class="field">
    <BaseLabel
      v-model="address.addressLine2"
      label="Deuxième ligne d'adresse"
      v-bind="$attrs"
    />
  </div>
  <div class="field">
    <BaseLabel
      v-model="address.postalCode"
      label="Code Postal"
      v-bind="$attrs"
    />
  </div>
  <div class="field">
    <BaseLabel v-model="address.cityName" label="Ville" v-bind="$attrs" />
  </div>
  <div class="field">
    <BaseLabel v-model="address.countryName" label="Pays" v-bind="$attrs" />
  </div>
</template>

<script>
import BaseLabel from "../commons/BaseLabel.vue";
export default {
  name: "AddressFormVisu",
  props: {
    address: {
      type: Object,
      default: () => {
        return {};
      },
    },
  },
  components: { BaseLabel },
};
</script>

<style scoped>
label {
  font-weight: bold;
}
</style>
