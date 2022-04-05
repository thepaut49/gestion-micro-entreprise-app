<template>
  <fieldset class="invoice-form-content">
    <legend>Fournisseur</legend>
    <BaseSelect
      :options="supplierTypes"
      v-model="supplier.supplierType"
      label="Type fournisseur"
      name="supplierType"
      @change="
        (supplierType) => {
          supplier.supplierType = supplierType;
        }
      "
    />
    <BaseInput v-model="supplier.name" label="Nom" type="text" />
    <BaseInput
      v-model="supplier.siren"
      label="Siren"
      type="text"
      v-show="showCompanyFields"
    />
    <BaseInput
      v-model="supplier.siret"
      label="Siret"
      type="text"
      v-show="showCompanyFields"
    />
    <fieldset class="invoice-form-content">
      <legend>Email et téléphone</legend>
      <BaseInput v-model="supplier.email" label="Email" type="text" />
      <BaseInput v-model="supplier.phone" label="Téléphone" type="text" />
    </fieldset>

    <fieldset class="invoice-form-content">
      <legend>Adresse</legend>
      <AddressForm
        :address="supplier.address"
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
export default {
  name: "SupplierForm",
  props: {
    supplier: {
      type: Object,
      default: () => {
        return {};
      },
    },
  },
  data() {
    const supplierTypes = ["ENTREPRISE", "PARTICULIER", "ETAT"];
    return {
      showCompanyFields: false,
      supplierTypes,
    };
  },
  method: {
    updateSupplierType() {
      if (supplier && supplier.supplierType === "ENTREPRISE") {
        showCompanyFields = true;
      } else {
        showCompanyFields = false;
      }
    },
  },
  watch: {
    supplier() {
      this.$emit("input", this.supplier);
    },
  },
};
</script>

<style scoped>
label {
  font-weight: bold;
}
</style>
