import { createStore } from "vuex";
// import createPersistedState from "vuex-persistedstate";
import auth from "./modules/auth";
import company from "./modules/company";

const store = createStore({
  modules: {
    auth,
    company,
  },
});

// Create store
export default store;
