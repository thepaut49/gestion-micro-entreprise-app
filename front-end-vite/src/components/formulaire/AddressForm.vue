<template>
  <div class="field">
    <label class="label" for="id">id de l'adresse</label>
    <label class="input" name="id" readonly v-bind="$attrs">{{
      address.id
    }}</label>
  </div>
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
  <div class="field">
    <label class="label" for="addressCreatedAt">Créée le</label>
    <label class="input" name="addressCreatedAt" readonly v-bind="$attrs">{{
      address.createdAt
    }}</label>
  </div>
  <div class="field">
    <label class="label" for="addressModifiedAt">Modifié le</label>
    <label class="input" name="addressModifiedAt" readonly v-bind="$attrs">{{
      address.modifiedAt
    }}</label>
  </div>
</template>

<script>
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
};
</script>

<style></style>
