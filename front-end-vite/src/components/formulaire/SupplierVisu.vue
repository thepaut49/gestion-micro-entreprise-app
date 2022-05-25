<template>
  <div>
    <BaseLabel v-model="supplier.name" label="Fournisseur : " v-bind="$attrs" />
  </div>

  <div>
    <BaseLabel v-model="supplier.email" label="Email : " v-bind="$attrs" />
  </div>

  <div>
    <BaseLabel v-model="supplier.phone" label="Téléphone : " v-bind="$attrs" />
  </div>

  <div v-show="showCompanyFields">
    <BaseLabel v-model="supplier.siren" label="Siren : " v-bind="$attrs" />
  </div>

  <div v-show="showCompanyFields">
    <BaseLabel v-model="supplier.siret" label="Siret : " v-bind="$attrs" />
  </div>
  <label><strong>Adresse :</strong></label>
  <AddressFormVisu :address="supplier.address" v-bind="$attrs" />
</template>

<script>
import { ref } from "vue";

export default {
  name: "SupplierVisu",
  inheritAttrs: true,
  props: {
    supplier: {
      type: Object,
      default: () => {
        return {
          id: undefined,
          supplierType: "",
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
    const showCompanyFields = ref(props.supplier.supplierType === "ENTREPRISE");
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
