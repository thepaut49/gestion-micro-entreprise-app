import axios from "axios";
import { VITE_APP_API_URL } from "../helpers";
import { parseItem } from "../../shared/data.service";

const newPerson = {
  id: undefined,
  familyName: "",
  firstName: "",
  phone: "",
  email: "",
  createdAt: new Date().toISOString(),
  modifiedAt: new Date().toISOString(),
  address: {
    addressLine1: "",
    addressLine2: "",
    cityName: "",
    countryName: "",
    postalCode: "",
  },
};

const state = {
  persons: [],
  person: newPerson,
};

const mutations = {
  addPerson(state, person) {
    state.persons.unshift(person); // mutable addition
    state.person = newPerson;
  },
  updatePerson(state, person) {
    const index = state.persons.findIndex((h) => h.id === person.id);
    state.persons.splice(index, 1, person);
    state.persons = [...state.persons];
    state.person = newPerson;
  },
  setPersons(state, persons) {
    state.persons = persons;
  },
  deletePerson(state, personId) {
    state.persons = [...state.persons.filter((p) => p.id !== personId)];
  },
  setPerson(state, person) {
    state.person = person;
  },
};

const getters = {
  // parameterized getters are not cached. so this is just a convenience to get the state.
  getPersonsTOTO(state) {
    return state.persons;
  },
};

const actions = {
  // actions let us get to ({ state, getters, commit, dispatch }) {
  addPersonAction({ commit }, person) {
    return axios
      .post(`${VITE_APP_API_URL}/api/persons`, person)
      .then((response) => {
        const addedPerson = parseItem(response, 201);
        commit("addPerson", addedPerson);
      })
      .catch((error) => console.error(error));
  },
  deletePersonAction({ commit }, person) {
    return axios
      .delete(`${VITE_APP_API_URL}/api/persons/${person.id}`)
      .then((response) => {
        parseItem(response, 200);
        commit("deletePerson", person.id);
      })
      .catch((error) => console.error(error));
  },
  getPersonsAction({ commit }) {
    return axios
      .get(VITE_APP_API_URL + "/api/persons")
      .then((response) => {
        commit("setPersons", response.data.items);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  updatePersonAction({ commit }, person) {
    return axios
      .put(`${VITE_APP_API_URL}/api/persons/${person.id}`, person)
      .then((response) => {
        const updatedPerson = parseItem(response, 200);
        commit("updatePerson", updatedPerson);
      })
      .catch((error) => console.error(error));
  },
  getPersonAction({ commit, state }, id) {
    if (id) {
      const existingPerson = state.persons.find((person) => person.id === id);
      if (existingPerson) {
        commit("setPerson", existingPerson);
      } else {
        return axios
          .get(`${VITE_APP_API_URL}/api/persons/${id}`)
          .then((response) => {
            const person = parseItem(response, 200);
            commit("setPerson", person);
          })
          .catch((error) => console.error(error));
      }
    }
  },
  createNewPersonAction({ commit }) {
    commit("setPerson", newPerson);
  },
};

export default {
  state,
  getters,
  actions,
  mutations,
};
