import { createRouter, createWebHistory } from "vue-router";
import Home from "./views/Home.vue";
import About from "./views/About.vue";
import Calendar from "./views/Calendar.vue";
import Dashboard from "./views/Dashboard.vue";
import RegisterUser from "./views/RegisterUser.vue";
import LoginUser from "./views/LoginUser.vue";
import Companies from "./views/companies.vue";
import CompanyDetail from "./views/company-detail.vue";
import Persons from "./views/person/persons.vue";
import PersonDetail from "./views/person/person-detail.vue";
import MicroCompanyDetailVisu from "./views/microcompany/microCompany-detail-visu.vue";
import MicroCompanyDetail from "./views/microcompany/microCompany-detail.vue";
import ExpenseInvoices from "./views/invoice/expense/expenseInvoices.vue";
import ExpenseInvoiceDetail from "./views/invoice/expense/expense-invoice-detail.vue";
import ExpenseInvoiceDetailVisu from "./views/invoice/expense/expense-invoice-detail-visu.vue";
import RevenueInvoices from "./views/invoice/revenue/revenueInvoices.vue";
import RevenueInvoiceDetail from "./views/invoice/revenue/revenue-invoice-detail.vue";
import RevenueInvoiceDetailVisu from "./views/invoice/revenue/revenue-invoice-detail-visu.vue";

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
      path: "/calendar",
      name: "calendar",
      component: Calendar,
      meta: { requiresAuth: true },
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
    {
      path: "/persons",
      name: "persons",
      component: Persons,
      meta: { requiresAuth: true },
    },
    {
      path: "/persons/person-detail/:id?",
      name: "person-detail",
      component: PersonDetail,
      props: true,
      meta: { requiresAuth: true },
    },
    {
      path: "/micro-companies/me",
      name: "microCompany-detail-visu",
      component: MicroCompanyDetailVisu,
      meta: { requiresAuth: true },
    },
    {
      path: "/micro-companies/microcompany-detail",
      name: "microCompany-detail",
      component: MicroCompanyDetail,
      props: true,
      meta: { requiresAuth: true },
    },
    {
      path: "/expense-invoices/me",
      name: "expenseInvoices",
      component: ExpenseInvoices,
      meta: { requiresAuth: true },
    },
    {
      path: "/expense-invoices/expense-invoice-detail-visu/:id?",
      name: "expense-invoice-detail-visu",
      component: ExpenseInvoiceDetailVisu,
      props: true,
      meta: { requiresAuth: true },
    },
    {
      path: "/expense-invoices/expense-invoice-detail/:id?",
      name: "expense-invoice-detail",
      component: ExpenseInvoiceDetail,
      props: true,
      meta: { requiresAuth: true },
    },
    {
      path: "/revenue-invoices/me",
      name: "revenueInvoices",
      component: RevenueInvoices,
      meta: { requiresAuth: true },
    },
    {
      path: "/revenue-invoices/revenue-invoice-detail-visu/:id?",
      name: "revenue-invoice-detail-visu",
      component: RevenueInvoiceDetailVisu,
      props: true,
      meta: { requiresAuth: true },
    },
    {
      path: "/revenue-invoices/revenue-invoice-detail/:id?",
      name: "revenue-invoice-detail",
      component: RevenueInvoiceDetail,
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
