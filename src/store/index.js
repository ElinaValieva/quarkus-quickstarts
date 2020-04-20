import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import VuexPersist from 'vuex-persist'

Vue.use(Vuex);

axios.defaults.baseURL = 'http://localhost:8090/blog';

export const pathSettings = {
    login: '/login',
    register: '/register',
    createPost: '/posts/post',
    getUserInfo: '/user',
    getPosts: '/posts/all'
};

const vuexLocalStorage = new VuexPersist({
    key: 'vuex',
    storage: window.localStorage
});

export default new Vuex.Store({
    plugins: [vuexLocalStorage.plugin],
    state: {
        token: null,
        user: {"username": "undefined", "firstName": "undefined", "lastName": "undefined"},
        posts: []
    },
    getters: {
        userInfo(state) {
            return state.user
        },
        postDetails(state) {
            return state.posts
        }
    },
    mutations: {
        retrieveToken(state, token) {
            state.token = token
        },
        retrieveUserInfo(state, user) {
            state.user = user;
        },
        retrievePosts(state, posts) {
            state.posts = posts;
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
        },
        createPost(context, data) {
            return new Promise((resolve, reject) => {
                axios
                    .post(pathSettings.createPost, data, {
                        headers: {'Authorization': 'Bearer ' + this.state.token}
                    })
                    .then(response => resolve(response))
                    .catch(error => reject(error));
            })
        },
        getUserInfo(context) {
            return new Promise((resolve, reject) => {
                axios
                    .get(pathSettings.getUserInfo, {
                        headers: {'Authorization': 'Bearer ' + this.state.token}
                    })
                    .then(response => context.commit('retrieveUserInfo', response.data))
                    .catch(error => reject(error));
            })
        },
        getPosts(context) {
            return new Promise((resolve, reject) => {
                axios
                    .get(pathSettings.getPosts, {
                        headers: {'Authorization': 'Bearer ' + this.state.token}
                    })
                    .then(response => context.commit('retrievePosts', response.data))
                    .catch(error => reject(error));
            })
        }
    }
})
