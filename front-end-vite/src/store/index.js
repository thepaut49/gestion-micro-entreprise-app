import { createStore } from "vuex";
// import createPersistedState from "vuex-persistedstate";
import auth from "./modules/auth";
import company from "./modules/company";
import person from "./modules/person";
import microCompany from "./modules/microCompany";

const store = createStore({
  modules: {
    auth,
    company,
    person,
    microCompany,
  },
});

// Create store
export default store;
