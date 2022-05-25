<template>
  <div>
    <BaseLabel v-model="microCompany.companyName" label="Client : " />
  </div>
  <div>
    <BaseLabel v-model="microCompany.email" label="Email : " />
  </div>
  <div>
    <BaseLabel v-model="microCompany.phone" label="Téléphone : " />
  </div>
  <div>
    <BaseLabel v-model="microCompany.siret" label="Siret : " />
  </div>
  <div>
    <BaseLabel v-model="microCompany.siren" label="Siren : " />
  </div>
  <label><strong>Adresse :</strong></label>
  <AddressFormVisu :address="microCompany.address" />
</template>

<script>
const emptyError = {
  companyName: "",
  siret: "",
  siren: "",
};

export default {
  name: "MicroCompanyVisu",
  inheritAttrs: false,
  props: {
    microCompany: {
      type: Object,
    },
  },
};
</script>

<style></style>
