import * as axios from "axios";

import { format } from "date-fns";
import { inputDateFormat } from "./constants";

import { API } from "./config";

const getUsers = async function () {
  try {
    const response = await axios.get(`${API}/users`);
    const users = parseList(response);
    return users;
  } catch (error) {
    console.error(error);
    return [];
  }
};

const getUser = async function (id) {
  try {
    const response = await axios.get(`${API}/users/${id}`);
    const user = parseItem(response, 200);
    return user;
  } catch (error) {
    console.error(error);
    return null;
  }
};

const updateUser = async function (user) {
  try {
    const response = await axios.put(`${API}/users/${user.id}`, user);
    const updatedUser = parseItem(response, 200);
    return updatedUser;
  } catch (error) {
    console.error(error);
    return null;
  }
};

const addUser = async function (user) {
  try {
    const response = await axios.post(`${API}/users`, user);
    const addedUser = parseItem(response, 201);
    return addedUser;
  } catch (error) {
    console.error(error);
    return null;
  }
};

const deleteUser = async function (user) {
  try {
    const response = await axios.delete(`${API}/users/${user.id}`);
    parseItem(response, 200);
    return user.id;
  } catch (error) {
    console.error(error);
    return null;
  }
};

const getCompanies = async function () {
  try {
    const response = await axios.get(`${API}/companies`);
    let data = parseList(response);
    const companies = data.map((v) => {
      v.originDate = format(v.originDate, inputDateFormat);
      return v;
    });
    return companies;
  } catch (error) {
    console.error(error);
    return [];
  }
};

const getCompany = async function (id) {
  try {
    const response = await axios.get(`${API}/companies/${id}`);
    let company = parseItem(response, 200);
    company.fullName = `${company.firstName} ${company.lastName}`;
    return company;
  } catch (error) {
    console.error(error);
    return null;
  }
};

const updateCompany = async function (company) {
  try {
    const response = await axios.put(`${API}/companies/${company.id}`, company);
    const updatedCompany = parseItem(response, 200);
    return updatedCompany;
  } catch (error) {
    console.error(error);
    return null;
  }
};

const addCompany = async function (company) {
  try {
    const response = await axios.post(`${API}/companies`, company);
    const addedCompany = parseItem(response, 201);
    return addedCompany;
  } catch (error) {
    console.error(error);
    return null;
  }
};

const deleteCompany = async function (company) {
  try {
    const response = await axios.delete(`${API}/companies/${company.id}`);
    parseItem(response, 200);
    return company.id;
  } catch (error) {
    console.error(error);
    return null;
  }
};

export const parseList = (response) => {
  if (response.status !== 200) throw Error(response.message);
  if (!response.data) return [];
  let list = response.data;
  if (typeof list !== "object") {
    list = [];
  }
  return list;
};

export const parseItem = (response, code) => {
  if (response.status !== code) throw Error(response.message);
  let item = response.data;
  if (typeof item !== "object") {
    item = undefined;
  }
  return item;
};

export const dataService = {
  getUsers,
  getUser,
  updateUser,
  addUser,
  deleteUser,
  getCompanies,
  getCompany,
  updateCompany,
  addCompany,
  deleteCompany,
};
