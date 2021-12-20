import axios from "axios";
import { API } from "../../shared/config";
import { parseItem, parseList } from "../../shared/data.service";

const state = {
  company: null,
  companies: [],
};

const mutations = {
  addCompany(state, company) {
    state.companyes.unshift(company); // mutable addition
  },
  updateCompany(state, company) {
    const index = state.companyes.findIndex((h) => h.id === company.id);
    state.companies.splice(index, 1, company);
    state.companies = [...state.companies];
  },
  getCompanies(state, companies) {
    state.companies = companies;
  },
  deleteCompany(state, companyId) {
    state.companies = [...state.companies.filter((p) => p.id !== companyId)];
  },
  getCompany(state, company) {
    state.company = company;
  },
};

const getters = {
  stateCompany: (state) => state.company,
  stateCompanies: (state) => state.companies,
};

const actions = {
  // actions let us get to ({ state, getters, commit, dispatch }) {
  async addCompanyAction({ commit }, company) {
    try {
      const response = await axios.post(`${API}/companies`, company);
      const addedCompany = parseItem(response, 201);
      commit("addCompany", addedCompany);
    } catch (error) {
      console.error(error);
    }
  },
  async deleteCompanyAction({ commit }, company) {
    try {
      const response = await axios.delete(`${API}/companies/${company.id}`);
      parseItem(response, 200);
      commit("deleteCompany", company.id);
    } catch (error) {
      console.error(error);
    }
  },
  async getCompaniesAction({ commit }) {
    try {
      const response = await axios.get(`${API}/companies`);
      const companies = parseList(response);
      commit("getCompanies", companies);
    } catch (error) {
      console.error(error);
    }
  },
  async updateCompanyAction({ commit }, company) {
    try {
      const response = await axios.put(
        `${API}/companies/${company.id}`,
        company
      );
      const updatedCompany = parseItem(response, 200);
      commit("updateCompany", updatedCompany);
    } catch (error) {
      console.error(error);
    }
  },
  async getCompanyAction({ commit }, id) {
    try {
      const response = await axios.get(`${API}/companies/${id}`);
      const company = parseItem(response, 200);
      commit("getCompany", company);
    } catch (error) {
      console.error(error);
    }
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};
