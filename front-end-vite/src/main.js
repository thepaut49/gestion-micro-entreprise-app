import { createApp, h } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import axios from "axios";
import jwt_decode from "jwt-decode";

const app = createApp({
  created() {
    const userString = localStorage.getItem("user");
    console.log(userString);
    if (userString) {
      const userData = JSON.parse(userString);
      const decodedToken = jwt_decode(userData.token);
      // si le token n'est pas expiré on log le user direct
      // console.log(new Date(decodedToken.exp * 1000).toString());
      if (decodedToken.exp * 1000 > Date.now()) {
        this.$store.commit("SET_USER_DATA", userData);
      }
    }
    axios.interceptors.response.use(
      (response) => response,
      (error) => {
        if (error.response.status && error.response.status === 401) {
          this.$store.dispatch("logout");
        }
        return Promise.reject(error);
      }
    );
  },
  render: () => h(App),
})
  .use(store)
  .use(router)
  .mount("#app");

export default app;
