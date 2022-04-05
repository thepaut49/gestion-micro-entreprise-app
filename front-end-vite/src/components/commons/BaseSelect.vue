<template>
  <label :for="uuid" v-if="label">{{ label }}</label>
  <select
    v-model="modelValue"
    @change="$emit('update:modelValue', $event.target.value)"
    :id="uuid"
  >
    <option v-for="option in options" :key="option">
      {{ option }}
    </option>
  </select>
</template>

<script>
import UniqueID from "../../features/UniqueID";

export default {
  props: {
    label: {
      type: String,
      default: "",
    },
    options: {
      type: Array,
      required: true,
    },
    name: {
      type: String,
      required: true,
    },
    modelValue: {
      type: [String, Number],
      required: true,
    },
  },
  setup() {
    const uuid = UniqueID().getID();
    return {
      uuid,
    };
  },
};
</script>

<style scoped></style>
