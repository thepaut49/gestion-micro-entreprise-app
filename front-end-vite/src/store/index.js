import { createStore } from "vuex";
// import createPersistedState from "vuex-persistedstate";
import auth from "./modules/auth";
import company from "./modules/company";
import person from "./modules/person";

const store = createStore({
  modules: {
    auth,
    company,
    person,
  },
});

// Create store
export default store;
