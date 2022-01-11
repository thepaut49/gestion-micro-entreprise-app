import { mapGetters } from "vuex";

export const authComputed = {
  ...mapGetters(["loggedIn"]),
};

export const VITE_APP_API_URL = import.meta.env.VITE_APP_API_URL;
