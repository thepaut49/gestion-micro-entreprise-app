<template>
  <div>
    <BaseLabel v-model="address.addressLine1" v-bind="$attrs" />
  </div>
  <div>
    <BaseLabel v-model="address.addressLine2" v-bind="$attrs" />
  </div>
  <div>
    <BaseLabel v-model="address.postalCode" v-bind="$attrs" />
  </div>
  <div>
    <BaseLabel v-model="address.cityName" v-bind="$attrs" />
  </div>
  <div>
    <BaseLabel v-model="address.countryName" v-bind="$attrs" />
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
