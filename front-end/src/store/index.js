import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";
import auth from "./modules/auth";
import company from "./modules/company";

// Create store
export default createStore({
  modules: {
    auth,
    company,
  },
  plugins: [createPersistedState()],
});
