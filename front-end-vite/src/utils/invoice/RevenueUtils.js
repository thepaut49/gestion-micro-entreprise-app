/*** objects ***/

export const clientTypes = ["ENTREPRISE", "PARTICULIER", "ETAT"];

export const emptyError = {
  client: {
    clientType: "",
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

export const validateRevenue = (revenue) => {
  let error = emptyError;
  let isRevenueValid = true;

  if (!revenue.client.clientType || revenue.client.clientType.length === 0) {
    error.client.clientType = "Le type de fournisseur est obligatoire !";
    isRevenueValid = false;
  }

  if (!revenue.client.name || revenue.client.name.length === 0) {
    error.client.clientType = "Le nom du fournisseur est obligatoire !";
    isRevenueValid = false;
  }

  return [error, isRevenueValid];
};

export const mapPersonToClient = (pPerson) => {
  const person = Object.assign(pPerson);
  return {
    id: person.id,
    name: person.familyName + " " + person.firstName,
    clientType: "PARTICULIER",
    siret: undefined,
    siren: undefined,
    address: person.address,
    phone: person.phone,
    email: person.email,
  };
};

export const mapCompanyToClient = (pCompany) => {
  const company = Object.assign(pCompany);
  return {
    id: company.id,
    name: company.companyName,
    clientType: "ENTREPRISE",
    siret: company.siret,
    siren: company.siren,
    address: company.address,
    phone: company.phone,
    email: company.email,
  };
};

export const mapMicroToMicroCompanyRevenue = (pMicroCompany) => {
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
