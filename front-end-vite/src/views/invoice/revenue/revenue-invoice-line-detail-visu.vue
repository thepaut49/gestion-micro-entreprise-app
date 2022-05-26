<template>
  <tr>
    <td><BaseLabel v-model="invoiceLine.lineNumber" /></td>
    <td><BaseLabel v-model="invoiceLine.description" /></td>
    <td class="cellAmount">
      <BaseLabel v-model="invoiceLine.amountForRefQuantity" />
    </td>
    <td class="cellAmount"><BaseLabel v-model="invoiceLine.quantity" /></td>
    <td class="cellAmount">
      <BaseLabel v-model="invoiceLine.taxPercentage" />
    </td>
    <td class="cellAmount">{{ invoiceLine.amountExcludingTax }} €</td>
    <td class="cellAmount">{{ invoiceLine.amountWithTax }} €</td>
  </tr>
</template>

<script>
export default {
  name: "RevenueInvoiceLineDetailVisu",
  inheritAttrs: false,
  props: {
    invoiceLine: {
      type: Object,
    },
  },
  setup(props) {
    return {};
  },
};
</script>

<style scoped></style>
