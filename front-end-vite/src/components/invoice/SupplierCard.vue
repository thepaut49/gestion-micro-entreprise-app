<template>
  <fieldset class="supplier-card">
    <legend>Fournisseur</legend>
    <div>
      <BaseLabel
        v-model="supplier.supplierType"
        label="Type de fournisseur : "
      />
    </div>
    <div>
      <BaseLabel v-model="supplier.name" label="Nom du fournisseur : " />
    </div>
    <div v-show="isSupplierACompany()">
      <BaseLabel v-model="supplier.siren" label="Siren : " />
    </div>
    <div v-show="isSupplierACompany()">
      <BaseLabel v-model="supplier.siret" label="Siret : " />
    </div>
    <div>
      <button @click="selectSupplierGranChild(supplier)">
        <span>Sélectioner</span>
      </button>
    </div>
  </fieldset>
</template>

<script>
export default {
  name: "SupplierCard",
  props: {
    supplier: {
      type: Object,
      default: () => {
        return {};
      },
    },
  },
  setup(props, { emit }) {
    const supplierProp = props.supplier;

    const isSupplierACompany = () => {
      return supplierProp.supplierType === "ENTREPRISE";
    };

    const selectSupplierGranChild = () => {
      emit("handleSelectSupplierGranChild", supplierProp);
    };

    return {
      selectSupplierGranChild,
      isSupplierACompany,
    };
  },
};
</script>

<style scoped>
.supplier-card {
  padding: 1em;
  border: solid 1px #2c3e50;
  cursor: pointer;
  transition: all 0.2s linear;
  display: flex;
  flex-direction: column;
}
.supplier-card:hover {
  transform: scale(1.01);
  box-shadow: 0 3px 12px 0 rgba(0, 0, 0, 0.2), 0 1px 15px 0 rgba(0, 0, 0, 0.19);
}
</style>
