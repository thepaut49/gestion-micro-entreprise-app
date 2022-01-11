<template>
  <div class="field">
    <label class="label" for="idManager">id du gérant</label>
    <label class="input" name="idManager" readonly>{{ $attrs.id }}</label>
  </div>
  <div class="field">
    <label class="label" for="familyName">Nom de famille</label>
    <input class="input" name="familyName" v-model="$attrs.familyName" />
  </div>
  <div class="field">
    <label class="label" for="firstName">Prénom</label>
    <input class="input" name="firstName" v-model="$attrs.firstName" />
  </div>
  <div class="field">
    <label class="label" for="managerEmail">Email</label>
    <input class="input" name="managerEmail" v-model="$attrs.email" />
  </div>
  <div class="field">
    <label class="label" for="managerPhone">Téléphone</label>
    <input class="input" name="managerPhone" v-model="$attrs.phone" />
  </div>
  <div class="field">
    <label class="label" for="managerCreatedAt">Créée le</label>
    <label class="input" name="managerCreatedAt" readonly>{{
      $attrs.createdAt
    }}</label>
  </div>
  <div class="field">
    <label class="label" for="managerModifiedAt">Modifié le</label>
    <label class="input" name="managerModifiedAt" readonly>{{
      $attrs.modifiedAt
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
};
</script>
