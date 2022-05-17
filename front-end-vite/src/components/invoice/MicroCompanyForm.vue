<template>
  <fieldset>
    <legend>Micro entreprise</legend>

    <div class="field">
      <BaseInput
        v-model="microCompany.companyName"
        label="Nom de l'entreprise"
        type="text"
        :error="error.companyName"
      />
    </div>
    <div class="field">
      <BaseInput
        v-model="microCompany.siret"
        label="Numéro siret"
        type="text"
        :error="error.siret"
      />
    </div>
    <div class="field">
      <BaseInput
        v-model="microCompany.siren"
        label="Numéro siren"
        type="text"
        :error="error.siren"
      />
    </div>
    <fieldset>
      <legend>Email et téléphone</legend>
      <div class="field">
        <BaseInput
          v-model="microCompany.email"
          label="Email de l'entreprise"
          type="text"
        />
      </div>
      <div class="field">
        <BaseInput
          v-model="microCompany.phone"
          label="Numéro téléphone"
          type="text"
        />
      </div>
    </fieldset>

    <fieldset>
      <legend>Adresse</legend>
      <AddressForm
        :address="microCompany.address"
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
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { ref, computed } from "vue";

const emptyError = {
  companyName: "",
  siret: "",
  siren: "",
};

export default {
  name: "MicroCompanyForm",
  inheritAttrs: false,
  props: {
    microCompany: {
      type: Object,
    },
  },
  setup(props) {
    const router = useRouter();
    const store = useStore();
    const error = ref(emptyError);

    return {
      error,
    };
  },
};
</script>

<style></style>
