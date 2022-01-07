import axios from "axios";

const baseUrl = "toto"; // process.env.VUE_APP_API;

const state = {
  user: null,
};

const getters = {
  loggedIn(state) {
    return !!state.user;
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
      .post("http://localhost:9090/api/public/login", credentials)
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
