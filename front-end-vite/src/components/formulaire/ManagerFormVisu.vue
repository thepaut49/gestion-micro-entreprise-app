<template>
  <div class="field">
    <BaseLabel
      v-model="manager.familyName"
      label="Nom de famille"
      v-bind="$attrs"
    />
  </div>
  <div class="field">
    <BaseLabel v-model="manager.firstName" label="Prénom" v-bind="$attrs" />
  </div>
  <div class="field">
    <BaseLabel v-model="manager.email" label="Email" v-bind="$attrs" />
  </div>
  <div class="field">
    <BaseLabel v-model="manager.phone" label="Téléphone" v-bind="$attrs" />
  </div>
</template>

<script>
export default {
  name: "ManagerFormVisu",
  props: {
    manager: {
      type: Object,
      default: () => {
        return {};
      },
    },
  },
};
</script>
