/*** objects ***/

export const supplierTypes = ["ENTREPRISE", "PARTICULIER", "ETAT"];

export const emptyError = {
  supplier: {
    supplierType: "",
    name: "",
    siret: "",
    siren: "",
    address: {
      addressLine1: "",
      addressLine2: "",
      cityName: "",
      countryName: "",
      postalCode: "",
    },
    phone: "",
    email: "",
  },
  microCompany: {
    companyName: "",
    siren: "",
    siret: "",
    address: {
      addressLine1: "",
      addressLine2: "",
      cityName: "",
      countryName: "",
      postalCode: "",
    },
    email: "",
    phone: "",
  },
  invoiceLines: [],
  amountExcludingTax: "",
  amountWithTax: "",
  dueDate: "",
  paymentDate: "",
  quote: "",
  paymentMethod: "",
  payed: "",
};

/*** functions ***/

export const createNewLine = (invoiceLines) => {
  let invoiceLinesLocal = invoiceLines;

  const newLine = {
    invoiceId: undefined,
    lineNumber: newLineNumber(invoiceLines),
    description: "",
    taxPercentage: 19.6,
    accountingCode: "000",
    quantity: 1,
    quantityType: "NO_TYPE",
    amountForRefQuantity: 0.0,
    amountExcludingTax: 0.0,
    amountWithTax: 0.0,
  };

  invoiceLinesLocal.push(newLine);

  return invoiceLinesLocal;
};

const newLineNumber = (invoiceLines) => {
  let newLineNumber = 0;
  invoiceLines.forEach((invoiceLine) => {
    if (invoiceLine.lineNumber > newLineNumber) {
      newLineNumber = invoiceLine.lineNumber;
    }
  });
  return newLineNumber + 1;
};

export const validateExpense = (expense) => {
  let error = emptyError;
  let isExpenseValid = false;

  if (
    !expense.supplier.supplierType ||
    expense.supplier.supplierType.length === 0
  ) {
    error.supplier.supplierType = "Le type de fournisseur est obligatoire !";
    isExpenseValid = true;
  }

  if (!expense.supplier.name || expense.supplier.name.length === 0) {
    error.supplier.supplierType = "Le nom du fournisseur est obligatoire !";
    isExpenseValid = true;
  }

  return [error, isExpenseValid];
};

export const mapPersonToSupplier = (person) => {
  return {
    id: person.id,
    name: person.familyName + " " + person.firstName,
    supplierType: "PARTICULIER",
    siret: undefined,
    siren: undefined,
    address: person.address,
    phone: person.phone,
    email: person.email,
  };
};

export const mapCompanyToSupplier = (company) => {
  return {
    id: company.id,
    name: company.companyName,
    supplierType: "ENTREPRISE",
    siret: company.siret,
    siren: company.siren,
    address: company.address,
    phone: company.phone,
    email: company.email,
  };
};
