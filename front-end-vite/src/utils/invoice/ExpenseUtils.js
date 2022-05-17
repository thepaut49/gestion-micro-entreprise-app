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
    lineNumber: newLineNumber(invoiceLines),
    description: "",
    taxPercentage: 19.6,
    accountingCode: {
      code: 0,
      account: "code par defaut",
      compteDuBilan: "",
      accountType: "",
    },
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
  let isExpenseValid = true;

  if (
    !expense.supplier.supplierType ||
    expense.supplier.supplierType.length === 0
  ) {
    error.supplier.supplierType = "Le type de fournisseur est obligatoire !";
    isExpenseValid = false;
  }

  if (!expense.supplier.name || expense.supplier.name.length === 0) {
    error.supplier.supplierType = "Le nom du fournisseur est obligatoire !";
    isExpenseValid = false;
  }

  return [error, isExpenseValid];
};

export const mapPersonToSupplier = (pPerson) => {
  const person = Object.assign(pPerson);
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

export const mapCompanyToSupplier = (pCompany) => {
  const company = Object.assign(pCompany);
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

export const mapMicroToMicroCompanyExpense = (pMicroCompany) => {
  const microCompany = Object.assign(pMicroCompany);
  return {
    id: microCompany.id,
    companyName: microCompany.companyName,
    siret: microCompany.siret,
    siren: microCompany.siren,
    address: microCompany.address,
    phone: microCompany.phone,
    email: microCompany.email,
  };
};
