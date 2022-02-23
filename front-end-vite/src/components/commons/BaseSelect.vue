<template>
  <select
    v-model="modelValue"
    @change="$emit('update:modelValue', $event.target.value)"
  >
    <option v-for="option in options" :key="option">
      {{ option }}
    </option>
  </select>
</template>

<script>
export default {
  props: {
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
};
</script>

<style scoped>
.horizontal {
  margin-right: 20px;
}
</style>
