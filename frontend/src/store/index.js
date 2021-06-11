import Vue from 'vue';
import Vuex from 'vuex';
import { getApts } from '../api/apt.js';
import { deleteQna } from '../api/qna.js';
import { getHistory } from '../api/user.js';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    user: null,
    userHistory: [],
    apts: [],
    markers: [],
    clickedApt: null,
    qnaDetail: {},
  },
  getters: {
    getUser(state) {
      if (state.user && state.user.userid) {
        return state.user;
      }
      const userFromLocalStorage = localStorage.getItem('seoulapt_user');
      if (userFromLocalStorage) {
        state.user = JSON.parse(userFromLocalStorage);
        return state.user;
      }
      return null;
    },
  },
  mutations: {
    SET_APTS(state, apts) {
      state.apts = null;
      state.apts = apts;
    },
    SET_CLICKED_APT(state, apt) {
      state.clickedApt = null;
      state.clickedApt = apt;
    },
    SET_QNA_DETAIL(state, qna) {
      state.qnaDetail = qna;
    },
    SET_USER(state, user) {
      if (user) {
        localStorage.setItem('seoulapt_user', JSON.stringify(user));
        state.user = user;
      } else {
        localStorage.removeItem('seoulapt_user');
        state.user = null;
      }
    },
    SET_USER_HISTORY(state, history) {
      state.userHistory = history;
    },
    SET_MARKERS(state, markers) {
      state.markers = markers;
    },
    PUSH_MARKER(state, marker) {
      state.markers.push(marker);
    },
    CLEAR_MARKERS(state) {
      state.markers = [];
    },
  },
  actions: {
    fetchApts({ commit }, gugun) {
      return new Promise((resolve, reject) => {
        getApts(gugun)
          .then(({ data }) => {
            commit('SET_APTS', data);
            resolve();
          })
          .catch((err) => {
            console.error(err);
            reject();
          });
      });
    },
    deleteQnaById({ commit }, qnaboardid) {
      deleteQna(qnaboardid)
        .then(({ data }) => {
          console.log(data);
          commit('SET_QNA_DETAIL', null);
        })
        .catch((err) => {
          console.error(err);
        });
    },
    fetchUserHistory({ commit }, userid) {
      getHistory(userid).then(({ data }) => {
        commit('SET_USER_HISTORY', data);
      });
    },
  },
});
