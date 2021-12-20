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
    const userLog = await axios.post(`${API}/public/login`, user);
    await commit("setUser", userLog);
  },

  async logoutAction({ commit }) {
    commit("logout");
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
