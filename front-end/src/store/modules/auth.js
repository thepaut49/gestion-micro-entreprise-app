import axios from "axios";
import { API } from "../../shared/config";

const state = {
  user: null,
};

const getters = {
  isAuthenticated: (state) => !!state.user,
  stateUser: (state) => state.user,
};

const actions = {
  async loginAction({ commit }, user) {
    const response = await axios.post(`${API}/public/login`, user);
    const token = response.headers["authorization"];
    const userData = response.data;
    await commit("setUser", userData);
    localStorage.setItem("token", token);
  },

  async logoutAction({ commit }) {
    commit("logout");
    localStorage.removeItem("token");
  },
};

const mutations = {
  setUser(state, user) {
    state.user = user;
  },
  logout(state) {
    state.user = null;
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};
