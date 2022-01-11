import axios from "axios";
import { VITE_APP_API_URL } from "../helpers";

const state = {
  user: null,
};

const getters = {
  loggedIn(state) {
    return !!state.user;
  },
  getToken(state) {
    if (loggedIn(state)) return state.user.token;
    else return "";
  },
};

const actions = {
  register({ commit }, credentials) {
    return axios.post(baseUrl + "/users", credentials).then(({ data }) => {
      commit("SET_USER_DATA", data);
    });
  },
  login({ commit }, credentials) {
    return axios
      .post(`${VITE_APP_API_URL}/api/public/login`, credentials)
      .then(({ data }) => {
        commit("SET_USER_DATA", data);
      });
  },
  logout({ commit }) {
    commit("CLEAR_USER_DATA");
  },
};

const mutations = {
  SET_USER_DATA(state, userData) {
    state.user = userData;
    localStorage.setItem("user", JSON.stringify(userData));
    axios.defaults.headers.common["Authorization"] = `Bearer ${userData.token}`;
  },
  CLEAR_USER_DATA() {
    localStorage.removeItem("user");
    location.reload();
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};
