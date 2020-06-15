import Vue from 'vue'
import Router from 'vue-router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import LoginComponent from "../components/LoginComponent";
import RegistrationComponent from "../components/RegistrationComponent";
import CreatePostComponent from "../components/CreatePostComponent";
import BlogComponent from "../components/BlogComponent";
import GetPostComponent from "../components/GetPostComponent";

Vue.use(Router);
Vue.use(VueAxios, axios);

let router = new Router({
    mode: 'history',
    routes: [{
        path: '*',
        name: 'LoginComponent',
        component: LoginComponent
    }, {
        path: '/login',
        name: 'LoginComponent',
        component: LoginComponent
    }, {
        path: '/registration',
        name: 'RegistrationComponent',
        component: RegistrationComponent
    }, {
        path: '/blog/post',
        name: 'CreatePostComponent',
        component: CreatePostComponent,
        meta: {requiresAuth: true}
    }, {
        path: '/blog',
        name: 'BlogComponent',
        component: BlogComponent,
        meta: {requiresAuth: true}
    }, {
        path: '/blog/post',
        name: 'GetPostComponent',
        component: GetPostComponent,
        props: true,
        meta: {requiresAuth: true}
    }]
});


router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        if (localStorage.getItem('vuex') == null || localStorage.getItem('vuex').token == null) {
            next({
                path: '/login',
                query: {redirect: to.fullPath}
            })
        } else {
            next()
        }
    } else {
        next() // всегда так или иначе нужно вызвать next()!
    }
});

export default router

