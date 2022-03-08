import axios from "axios";
import { VITE_APP_API_URL } from "../helpers";
import { parseItem, parseList } from "../../shared/data.service";

const newCompany = {
  id: undefined,
  companyName: "",
  siret: "",
  siren: "",
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
};

const state = {
  companies: [],
  company: newCompany,
};

const mutations = {
  addCompany(state, company) {
    state.companies.unshift(company); // mutable addition
    state.company = newCompany;
  },
  updateCompany(state, company) {
    const index = state.companies.findIndex((h) => h.id === company.id);
    state.companies.splice(index, 1, company);
    state.companies = [...state.companies];
    state.company = newCompany;
  },
  setCompanies(state, companies) {
    state.companies = companies;
  },
  deleteCompany(state, companyId) {
    state.companies = [...state.companies.filter((p) => p.id !== companyId)];
  },
  setCompany(state, company) {
    state.company = company;
  },
};

const getters = {
  // parameterized getters are not cached. so this is just a convenience to get the state.
  getCompaniesTOTO(state) {
    return state.companies;
  },
};

const actions = {
  // actions let us get to ({ state, getters, commit, dispatch }) {
  addCompanyAction({ commit }, company) {
    return axios
      .post(`${VITE_APP_API_URL}/api/companies`, company)
      .then((response) => {
        const addedCompany = parseItem(response, 201);
        commit("addCompany", addedCompany);
      })
      .catch((error) => console.error(error));
  },
  deleteCompanyAction({ commit }, company) {
    return axios
      .delete(`${VITE_APP_API_URL}/api/companies/${company.id}`)
      .then((response) => {
        parseItem(response, 200);
        commit("deleteCompany", company.id);
      })
      .catch((error) => console.error(error));
  },
  getCompaniesAction({ commit }) {
    return axios
      .get(VITE_APP_API_URL + "/api/companies")
      .then((response) => {
        commit("setCompanies", response.data.items);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  updateCompanyAction({ commit }, company) {
    return axios
      .put(`${VITE_APP_API_URL}/api/companies/${company.id}`, company)
      .then((response) => {
        const updatedCompany = parseItem(response, 200);
        commit("updateCompany", updatedCompany);
      })
      .catch((error) => console.error(error));
  },
  getCompanyAction({ commit, state }, id) {
    if (id) {
      const existingCompany = state.companies.find(
        (company) => company.id === id
      );
      if (existingCompany) {
        commit("setCompany", existingCompany);
      } else {
        return axios
          .get(`${VITE_APP_API_URL}/api/companies/${id}`)
          .then((response) => {
            const company = parseItem(response, 200);
            commit("setCompany", company);
          })
          .catch((error) => console.error(error));
      }
    }
  },
  createNewCompanyAction({ commit }) {
    commit("setCompany", newCompany);
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};
