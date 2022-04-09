<template>
  <div class="field">
    <BaseInput
      v-model="address.addressLine1"
      label="Première ligne d'adresse"
      type="text"
      v-bind="$attrs"
    />
  </div>
  <div class="field">
    <BaseInput
      v-model="address.addressLine2"
      label="Deuxième ligne d'adresse"
      type="text"
      v-bind="$attrs"
    />
  </div>
  <div class="field">
    <BaseInput
      v-model="address.postalCode"
      label="Code Postal"
      type="text"
      v-bind="$attrs"
    />
  </div>
  <div class="field">
    <BaseInput
      v-model="address.cityName"
      label="Ville"
      type="text"
      v-bind="$attrs"
    />
  </div>
  <div class="field">
    <BaseInput
      v-model="address.countryName"
      label="Pays"
      type="text"
      v-bind="$attrs"
    />
  </div>
</template>

<script>
import BaseLabel from "../commons/BaseLabel.vue";
export default {
  name: "AddressForm",
  props: {
    address: {
      type: Object,
      default: () => {
        return {};
      },
    },
  },
  watch: {
    address() {
      this.$emit("input", this.address);
    },
  },
  components: { BaseLabel },
};
</script>

<style scoped></style>
