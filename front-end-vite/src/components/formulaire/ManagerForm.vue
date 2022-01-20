<template>
  <div class="field-label">
    <BaseLabel v-model="manager.id" label="Id du gérant" v-bind="$attrs" />
  </div>
  <div class="field">
    <BaseInput
      v-model="manager.familyName"
      label="Nom de famille"
      type="text"
      v-bind="$attrs"
    />
  </div>
  <div class="field">
    <BaseInput
      v-model="manager.firstName"
      label="Prénom"
      type="text"
      v-bind="$attrs"
    />
  </div>
  <div class="field">
    <BaseInput
      v-model="manager.email"
      label="Email"
      type="text"
      v-bind="$attrs"
    />
  </div>
  <div class="field">
    <BaseInput
      v-model="manager.phone"
      label="Téléphone"
      type="text"
      v-bind="$attrs"
    />
  </div>
  <div class="field-label">
    <BaseLabel v-model="manager.createdAt" label="Créée le" v-bind="$attrs" />
  </div>
  <div class="field-label">
    <BaseLabel
      v-model="manager.modifiedAt"
      label="Modifié le"
      v-bind="$attrs"
    />
  </div>
</template>

<script>
export default {
  name: "ManagerForm",
  props: {
    manager: {
      type: Object,
      default: () => {
        return {};
      },
    },
  },
  watch: {
    manager() {
      this.$emit("input", this.manager);
    },
  },
};
</script>
