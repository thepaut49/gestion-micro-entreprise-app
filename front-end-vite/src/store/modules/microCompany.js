import axios from "axios";
import { VITE_APP_API_URL } from "../helpers";
import { parseItem } from "../../shared/data.service";

const newMicroCompany = {
  id: undefined,
  companyName: "",
  siren: "",
  siret: "",
  phone: "",
  email: "",
  createdAt: new Date().toISOString(),
  modifiedAt: new Date().toISOString(),
  address: {
    id: undefined,
    addressLine1: "",
    addressLine2: "",
    cityName: "",
    countryName: "",
    postalCode: "",
    createdAt: new Date().toISOString(),
    modifiedAt: new Date().toISOString(),
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

const state = {
  microCompanies: [],
  microCompany: newMicroCompany,
};

const mutations = {
  addMicroCompany(state, microCompany) {
    state.microCompanies.unshift(microCompany); // mutable addition
  },
  updateMicroCompany(state, microCompany) {
    const index = state.microCompanies.findIndex(
      (h) => h.id === microCompany.id
    );
    state.microCompanies.splice(index, 1, microCompany);
    state.microCompanies = [...state.microCompanies];
  },
  setMicroCompanies(state, microCompanies) {
    state.microCompanies = microCompanies;
  },
  deleteMicroCompany(state, microCompanyId) {
    state.microCompanies = [
      ...state.microCompanies.filter((p) => p.id !== microCompanyId),
    ];
  },
  setMicroCompany(state, microCompany) {
    state.microCompany = microCompany;
  },
};

const getters = {
  // parameterized getters are not cached. so this is just a convenience to get the state.
  getMicroCompaniesTOTO(state) {
    return state.microCompanies;
  },
};

const actions = {
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
  getMicroCompaniesAction({ commit }) {
    return axios
      .get(VITE_APP_API_URL + "/api/micro-companies")
      .then((response) => {
        commit("setMicroCompanies", response.data.items);
      })
      .catch((error) => {
        console.log(error);
      });
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
  getMicroCompanyAction({ commit, state }, id) {
    if (id) {
      const existingMicroCompany = state.microCompanies.find(
        (microCompany) => microCompany.id === id
      );
      if (existingMicroCompany) {
        commit("setMicroCompany", existingMicroCompany);
      } else {
        return axios
          .get(`${VITE_APP_API_URL}/api/micro-companies/${id}`)
          .then((response) => {
            const microCompany = parseItem(response, 200);
            commit("setMicroCompany", microCompany);
          })
          .catch((error) => console.error(error));
      }
    }
  },
  createNewMicroCompanyAction({ commit }) {
    commit("setMicroCompany", newMicroCompany);
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};
