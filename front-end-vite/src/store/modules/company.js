import axios from "axios";

const state = {
  companies: [],
};

const mutations = {
  addCompany(state, company) {
    state.companies.unshift(company); // mutable addition
  },
  updateCompany(state, company) {
    const index = state.companies.findIndex((h) => h.id === company.id);
    state.companies.splice(index, 1, company);
    state.companies = [...state.companies];
  },
  getCompanies(state, companies) {
    state.companies = companies;
  },
  deleteCompany(state, companyId) {
    state.companies = [...state.companies.filter((p) => p.id !== companyId)];
  },
};

const getters = {
  // parameterized getters are not cached. so this is just a convenience to get the state.
  getCompanyById: (state) => (id) => state.companies.find((h) => h.id === id),
};

const actions = {
  // actions let us get to ({ state, getters, commit, dispatch }) {
  async addCompanyAction({ commit }, company) {
    try {
      const headers = {
        headers: {
          authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      };
      const response = await axios.post(`${API}/companies`, company, headers);
      const addedCompany = parseItem(response, 201);
      commit("addCompany", addedCompany);
    } catch (error) {
      console.error(error);
    }
  },
  async deleteCompanyAction({ commit }, company) {
    try {
      const headers = {
        headers: {
          authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      };
      const response = await axios.delete(
        `${API}/companies/${company.id}`,
        headers
      );
      parseItem(response, 200);
      commit("deleteCompany", company.id);
    } catch (error) {
      console.error(error);
    }
  },
  async getCompaniesAction({ commit }) {
    try {
      const headers = {
        headers: {
          authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      };
      const response = await axios.get(`${API}/companies`, headers);
      const companies = parseList(response).items;
      commit("getCompanies", companies);
    } catch (error) {
      console.error(error);
    }
  },
  async updateCompanyAction({ commit }, company) {
    try {
      const headers = {
        headers: {
          authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      };
      const response = await axios.put(
        `${API}/companies/${company.id}`,
        company,
        headers
      );
      const updatedCompany = parseItem(response, 200);
      commit("updateCompany", updatedCompany);
    } catch (error) {
      console.error(error);
    }
  },
  async getCompanyAction({ commit }, id) {
    try {
      const headers = {
        headers: {
          authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      };
      const response = await axios.get(`${API}/companies/${id}`, headers);
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
