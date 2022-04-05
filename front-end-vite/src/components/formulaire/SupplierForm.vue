<template>
  <fieldset class="invoice-form-content">
    <legend>Fournisseur</legend>
    <BaseSelect
      :options="supplierTypes"
      v-model="supplier.supplierType"
      label="Type fournisseur"
      name="supplierType"
    />
    <div class="field">
      <BaseInput v-model="supplier.name" label="Nom" type="text" />
    </div>

    <div class="field" v-show="showCompanyFields">
      <BaseInput v-model="supplier.siren" label="Siren" type="text" />
    </div>

    <div class="field" v-show="showCompanyFields">
      <BaseInput v-model="supplier.siret" label="Siret" type="text" />
    </div>
  </fieldset>
</template>

<script>
import { watchEffect, ref } from "vue";

export default {
  name: "SupplierForm",
  props: {
    supplier: {
      type: Object,
      default: () => {
        return {
          companyId: undefined,
          personId: undefined,
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
    let showCompanyFields = ref(false);
    const supplierTypes = ["ENTREPRISE", "PARTICULIER", "ETAT"];
    watchEffect(() => {
      if (props.supplier.supplierType === "ENTREPRISE") {
        showCompanyFields.value = true;
      } else {
        showCompanyFields.value = false;
      }
    });

    return {
      showCompanyFields,
      supplierTypes,
    };
  },
};
</script>

<style scoped>
label {
  font-weight: bold;
}
</style>
