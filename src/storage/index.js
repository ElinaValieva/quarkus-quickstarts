import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import VuexPersist from 'vuex-persist'

Vue.use(Vuex);

axios.defaults.baseURL = 'http://localhost:8090/blog';

export const pathSettings = {
    login: '/login',
    register: '/register',
    messages: '/messages/',
    friends: '/friends/',
    message: '/message',
    auth: '/auth',
    token: 'access_token',
    id: 'user_id'
};

const vuexLocalStorage = new VuexPersist({
    key: 'vuex',
    storage: window.localStorage
});

export default new Vuex.Store({
    plugins: [vuexLocalStorage.plugin],
    state: {
        token: null
    },
    getters: {},
    mutations: {
        retrieveToken(state, token) {
            state.token = token
        }
    },
    actions: {
        register(context, data) {
            return new Promise((resolve, reject) => {
                axios
                    .post(pathSettings.register, data)
                    .then(response => resolve(response))
                    .catch(error => reject(error));
            })
        },
        login(context, data) {
            return new Promise((resolve, reject) => {
                axios
                    .post(pathSettings.login, data)
                    .then(response => {
                        const token = response.data;
                        context.commit('retrieveToken', token);
                        resolve(response)
                    })
                    .catch(error => reject(error));
            })
        }
    }
})
