<template>
  <tr>
    <td><BaseLabel v-model="invoiceLine.lineNumber" /></td>
    <td><BaseInput v-model="invoiceLine.description" type="text" /></td>
    <td>
      <BaseInput v-model="invoiceLine.amountForRefQuantity" />
    </td>
    <td><BaseInput v-model="invoiceLine.quantity" type="number" /></td>
    <td><BaseInput v-model="invoiceLine.taxPercentage" type="number" /></td>
    <td class="cellAmount">{{ invoiceLine.amountExcludingTax }} €</td>
    <td class="cellAmount">{{ invoiceLine.amountWithTax }} €</td>
  </tr>
</template>

<script>
import { ref, watchEffect } from "vue";

const emptyError = {
  lineNumber: "",
  description: "",
  amountForRefQuantity: "",
  quantity: "",
  taxPercentage: "",
  amountExcludingTax: "",
  amountWithTax: "",
};

export default {
  name: "ExpenseInvoiceLineDetail",
  inheritAttrs: false,
  props: {
    invoiceLine: {
      type: Object,
    },
  },
  setup(props) {
    const error = ref(emptyError);
    let invoiceLine = props.invoiceLine;
    watchEffect(() => {
      if (invoiceLine) {
        invoiceLine.amountExcludingTax =
          invoiceLine.quantity * invoiceLine.amountForRefQuantity;
        invoiceLine.amountWithTax =
          invoiceLine.amountExcludingTax +
          (invoiceLine.amountExcludingTax * invoiceLine.taxPercentage) / 100.0;
        invoiceLine.amountExcludingTax =
          Math.round(invoiceLine.amountExcludingTax * 100) / 100;
        invoiceLine.amountWithTax =
          Math.round(invoiceLine.amountWithTax * 100) / 100;
      }
    });

    return {
      error,
    };
  },
};

const errorObjectIsEmpty = (error) => {
  return Object.values(error).every((value) => value.length === 0);
};
</script>

<style>
@import "../../../assets/styles/invoiceEditForm.css";
</style>
