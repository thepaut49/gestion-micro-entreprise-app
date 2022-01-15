<template>
  <div class="field">
    <label class="label" for="idManager">id du gérant</label>
    <label class="input" name="idManager" readonly v-bind="$attrs">{{
      manager.id
    }}</label>
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
  <div class="field">
    <label class="label" for="managerCreatedAt">Créée le</label>
    <label class="input" name="managerCreatedAt" readonly v-bind="$attrs">{{
      manager.createdAt
    }}</label>
  </div>
  <div class="field">
    <label class="label" for="managerModifiedAt">Modifié le</label>
    <label class="input" name="managerModifiedAt" readonly v-bind="$attrs">{{
      manager.modifiedAt
    }}</label>
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
