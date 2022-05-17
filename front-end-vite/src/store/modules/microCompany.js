import axios from "axios";
import { VITE_APP_API_URL } from "../helpers";
import { parseItem } from "../../shared/data.service";
import { mapMicroToMicroCompanyExpense } from "../../utils/invoice/ExpenseUtils";

const newMicroCompany = {
  id: undefined,
  companyName: "",
  siren: "",
  siret: "",
  phone: "",
  email: "",
  createdAt: new Date().toISOString(),
  modifiedAt: new Date().toISOString(),
  manager: {
    familyName: "",
    firstName: "",
    phone: "",
    email: "",
  },
  address: {
    addressLine1: "",
    addressLine2: "",
    cityName: "",
    countryName: "",
    postalCode: "",
  },
  user: {
    id: undefined,
    username: "",
    authorities: [],
    enabled: "",
    createdAt: new Date().toISOString(),
    modifiedAt: new Date().toISOString(),
  },
  accountant: null,
  accessibleByAdmin: false,
};

const newRevenueInvoice = {
  id: undefined,
  client: {
    id: undefined,
    name: "",
    clientType: "",
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
    id: undefined,
    createdAt: new Date().toISOString(),
    modifiedAt: new Date().toISOString(),
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
  amountExcludingTax: 0.0,
  amountWithTax: 0.0,
  dueDate: new Date().toISOString(),
  paymentDate: new Date().toISOString(),
  quote: false,
  paymentMethod: "",
  payed: false,
  createdAt: new Date().toISOString(),
  modifiedAt: new Date().toISOString(),
};

const newExpenseInvoice = {
  id: undefined,
  supplier: {
    id: undefined,
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
    id: undefined,
    createdAt: new Date().toISOString(),
    modifiedAt: new Date().toISOString(),
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
  invoiceLines: [
    {
      lineNumber: 1,
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
    },
  ],
  amountExcludingTax: 0.0,
  amountWithTax: 0.0,
  dueDate: null, //new Date().format("dd-mm-yyyy"),
  paymentDate: null,
  quote: false,
  paymentMethod: "",
  payed: false,
  createdAt: new Date().toISOString(),
  modifiedAt: new Date().toISOString(),
};

const state = {
  microCompany: newMicroCompany,
  canCreateMicroCompany: false,
  revenueInvoices: [],
  revenueInvoice: newRevenueInvoice,
  expenseInvoices: [],
  expenseInvoice: newExpenseInvoice,
};

const getters = {
  hasMicroCompany(state) {
    // This is because null == undefined evaluates to true
    return state.microCompany.id != null && state.microCompany.id != undefined;
  },
};

const mutations = {
  // Micro company
  addMicroCompany(state, microCompany) {
    state.microCompanies.unshift(microCompany); // mutable addition
    state.microCompany = newMicroCompany;
  },
  updateMicroCompany(state, microCompany) {
    const index = state.microCompanies.findIndex(
      (h) => h.id === microCompany.id
    );
    state.microCompanies.splice(index, 1, microCompany);
    state.microCompanies = [...state.microCompanies];
    state.microCompany = newMicroCompany;
  },
  deleteMicroCompany(state) {
    state.microCompany = newMicroCompany;
  },
  setMicroCompany(state, microCompany) {
    state.microCompany = microCompany;
  },
  setCanCreateMicroCompany(state, canCreateMicroCompany) {
    state.canCreateMicroCompany = canCreateMicroCompany;
  },
  // Expense
  addExpenseInvoice(state, expense) {
    state.expenseInvoices.unshift(expense); // mutable addition
    state.expenseInvoice = newExpenseInvoice;
  },
  updateExpenseInvoice(state, expense) {
    const index = state.expenseInvoices.findIndex((h) => h.id === expense.id);
    state.expenseInvoices.splice(index, 1, expense);
    state.expenseInvoices = [...state.expenseInvoices];
    state.expenseInvoice = newExpenseInvoice;
  },
  setExpenseInvoices(state, expenseInvoices) {
    state.expenseInvoices = expenseInvoices;
  },
  setExpenseInvoice(state, expenseInvoice) {
    state.expenseInvoice = expenseInvoice;
  },
  deleteExpenseInvoice(state, invoiceId) {
    state.expenseInvoices = [
      ...state.expenseInvoices.filter((invoice) => invoice.id !== invoiceId),
    ];
  },
  deleteExpenseInvoices(state) {
    state.expenseInvoices = [];
  },
  // Revenue
  addRevenueInvoice(state, revenue) {
    state.revenueInvoices.unshift(revenue); // mutable addition
    state.revenueInvoice = newRevenueInvoice;
  },
  setRevenueInvoices(state, revenueInvoices) {
    state.revenueInvoices = revenueInvoices;
  },
  setRevenueInvoice(state, revenueInvoice) {
    state.revenueInvoice = revenueInvoice;
  },
  deleteRevenueInvoice(state, invoiceId) {
    state.revenueInvoices = [
      ...state.revenueInvoices.filter((invoice) => invoice.id !== invoiceId),
    ];
  },
  deleteRevenueInvoices(state) {
    state.revenueInvoices = [];
  },
};

const actions = {
  // Micro company
  // actions let us get to ({ state, getters, commit, dispatch }) {
  addMicroCompanyAction({ commit }, microCompany) {
    return axios
      .post(`${VITE_APP_API_URL}/api/micro-companies`, microCompany)
      .then((response) => {
        const addedMicroCompany = parseItem(response, 201);
        commit("addMicroCompany", addedMicroCompany);
      })
      .catch((error) => console.error(error));
  },
  deleteMicroCompanyAction({ commit }, microCompany) {
    return axios
      .delete(`${VITE_APP_API_URL}/api/micro-companies/${microCompany.id}`)
      .then((response) => {
        parseItem(response, 200);
        commit("deleteMicroCompany", microCompany.id);
      })
      .catch((error) => console.error(error));
  },
  updateMicroCompanyAction({ commit }, microCompany) {
    return axios
      .put(
        `${VITE_APP_API_URL}/api/micro-companies/${microCompany.id}`,
        microCompany
      )
      .then((response) => {
        const updatedMicroCompany = parseItem(response, 200);
        commit("updateMicroCompany", updatedMicroCompany);
      })
      .catch((error) => console.error(error));
  },
  getMicroCompanyForUserAction({ commit }) {
    return axios
      .get(`${VITE_APP_API_URL}/api/micro-companies/me`)
      .then((response) => {
        const microCompany = parseItem(response, 200);
        commit("setMicroCompany", microCompany);
      })
      .catch((error) => {
        if (error.response) {
          if (error.response.status && error.response.status === 404) {
            commit("setMicroCompany", newMicroCompany);
          } else {
            console.error(error);
          }
        } else {
          console.error(error);
        }
      });
  },
  createNewMicroCompanyAction({ commit }) {
    commit("setMicroCompany", newMicroCompany);
  },
  canCreateMicroCompanyAction({ commit }) {
    return axios
      .get(`${VITE_APP_API_URL}/api/micro-companies/canUserCreateMicroCompany`)
      .then(({ data }) => {
        commit("setCanCreateMicroCompany", data);
      });
  },

  // Expense invoice
  getExpenseInvoicesAction({ commit }) {
    return axios
      .get(VITE_APP_API_URL + "/api/expense-invoices/me")
      .then((response) => {
        commit("setExpenseInvoices", response.data.items);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  // actions let us get to ({ state, getters, commit, dispatch }) {
  addExpenseInvoiceAction({ commit }, expenseInvoice) {
    return axios
      .post(`${VITE_APP_API_URL}/api/expense-invoices`, expenseInvoice)
      .then((response) => {
        const addedExpenseInvoice = parseItem(response, 201);
        console.log("addExpenseInvoice : " + addedExpenseInvoice);
        commit("addExpenseInvoice", addedExpenseInvoice);
      })
      .catch((error) => console.error(error));
  },
  deleteExpenseInvoiceAction({ commit }, expenseInvoice) {
    return axios
      .delete(`${VITE_APP_API_URL}/api/expense-invoices/${expenseInvoice.id}`)
      .then((response) => {
        parseItem(response, 200);
        commit("deleteExpenseInvoice", expenseInvoice.id);
      })
      .catch((error) => console.error(error));
  },
  updateExpenseInvoiceAction({ commit }, expenseInvoice) {
    return axios
      .put(
        `${VITE_APP_API_URL}/api/expense-invoices/${expenseInvoice.id}`,
        expenseInvoice
      )
      .then((response) => {
        const updatedExpenseInvoice = parseItem(response, 200);
        console.log("updatedExpenseInvoice : " + updatedExpenseInvoice);
        commit("updateExpenseInvoice", updatedExpenseInvoice);
      })
      .catch((error) => console.error(error));
  },
  getExpenseInvoiceAction({ commit, state }, id) {
    if (id) {
      const existingExpenseInvoice = state.microCompanies.find(
        (expenseInvoice) => expenseInvoice.id === id
      );
      if (existingExpenseInvoice) {
        commit("setExpenseInvoice", existingExpenseInvoice);
      } else {
        return axios
          .get(`${VITE_APP_API_URL}/api/expense-invoices/${id}`)
          .then((response) => {
            const expenseInvoice = parseItem(response, 200);
            commit("setExpenseInvoice", expenseInvoice);
          })
          .catch((error) => console.error(error));
      }
    }
  },
  createNewExpenseInvoiceAction({ commit, state }) {
    newExpenseInvoice.microCompany = mapMicroToMicroCompanyExpense(
      state.microCompany
    );
    commit("setExpenseInvoice", newExpenseInvoice);
  },

  // Revenue Invoice
  getRevenueInvoicesAction({ commit }) {
    return axios
      .get(VITE_APP_API_URL + "/api/revenue-invoices")
      .then((response) => {
        commit("setRevenueInvoices", response.data.items);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  deleteRevenueInvoiceAction({ commit }, revenueInvoice) {
    return axios
      .delete(`${VITE_APP_API_URL}/api/revenue-invoices/${revenueInvoice.id}`)
      .then((response) => {
        parseItem(response, 200);
        commit("deleteRevenueInvoice", revenueInvoice.id);
      })
      .catch((error) => console.error(error));
  },
  updateRevenueInvoiceAction({ commit }, revenueInvoice) {
    return axios
      .put(
        `${VITE_APP_API_URL}/api/revenue-invoices/${revenueInvoice.id}`,
        revenueInvoice
      )
      .then((response) => {
        const updatedRevenueInvoice = parseItem(response, 200);
        commit("updateRevenueInvoice", updatedRevenueInvoice);
      })
      .catch((error) => console.error(error));
  },
  getRevenueInvoiceAction({ commit, state }, id) {
    if (id) {
      const existingRevenueInvoice = state.microCompanies.find(
        (revenueInvoice) => revenueInvoice.id === id
      );
      if (existingRevenueInvoice) {
        commit("setRevenueInvoice", existingRevenueInvoice);
      } else {
        return axios
          .get(`${VITE_APP_API_URL}/api/revenue-invoices/${id}`)
          .then((response) => {
            const revenueInvoice = parseItem(response, 200);
            commit("setRevenueInvoice", revenueInvoice);
          })
          .catch((error) => console.error(error));
      }
    }
  },
  createNewRevenueInvoiceAction({ commit }) {
    commit("setRevenueInvoice", newRevenueInvoice);
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};
