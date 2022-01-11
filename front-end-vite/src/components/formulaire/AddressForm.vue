<template>
  <div class="field">
    <label class="label" for="addressLine1">Première ligne d'adresse</label>
    <input class="input" name="addressLine1" v-model="$attrs.addressLine1" />
  </div>
  <div class="field">
    <label class="label" for="addressLine2">Deuxième ligne d'adresse</label>
    <input class="input" name="addressLine2" v-model="$attrs.addressLine2" />
  </div>
  <div class="field">
    <label class="label" for="postalCode">Code Postal</label>
    <input class="input" name="postalCode" v-model="$attrs.postalCode" />
  </div>
  <div class="field">
    <label class="label" for="cityName">Ville</label>
    <input class="input" name="cityName" v-model="$attrs.cityName" />
  </div>
  <div class="field">
    <label class="label" for="countryName">Ville</label>
    <input class="input" name="countryName" v-model="$attrs.countryName" />
  </div>
  <div class="field">
    <label class="label" for="addressCreatedAt">Créée le</label>
    <label class="input" name="addressCreatedAt" readonly>{{
      $attrs.createdAt
    }}</label>
  </div>
  <div class="field">
    <label class="label" for="addressModifiedAt">Modifié le</label>
    <label class="input" name="addressModifiedAt" readonly>{{
      $attrs.modifiedAt
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
};
</script>

<style></style>
