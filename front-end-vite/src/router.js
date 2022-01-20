import { createRouter, createWebHistory } from "vue-router";
import Home from "./views/Home.vue";
import About from "./views/About.vue";
import Dashboard from "./views/Dashboard.vue";
import RegisterUser from "./views/RegisterUser.vue";
import LoginUser from "./views/LoginUser.vue";
import Companies from "./views/companies.vue";
import CompanyDetail from "./views/company-detail.vue";

const parseProps = (r) => ({ id: parseInt(r.params.id) });

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/",
      name: "home",
      component: Home,
      meta: { requiresAuth: true },
    },
    {
      path: "/about",
      name: "about",
      component: About,
    },
    {
      path: "/dashboard",
      name: "dashboard",
      component: Dashboard,
      meta: { requiresAuth: true },
    },
    {
      path: "/register",
      name: "register",
      component: RegisterUser,
    },
    {
      path: "/login",
      name: "login",
      component: LoginUser,
    },
    {
      path: "/companies",
      name: "companies",
      component: Companies,
      meta: { requiresAuth: true },
    },
    {
      path: "/companies/company-detail/:id?",
      name: "company-detail",
      component: CompanyDetail,
      props: true,
      meta: { requiresAuth: true },
    },
  ],
});

router.beforeEach((to, from, next) => {
  const loggedIn = localStorage.getItem("user");

  if (to.matched.some((record) => record.meta.requiresAuth) && !loggedIn) {
    next("/login");
  } else next();
});

export default router;
