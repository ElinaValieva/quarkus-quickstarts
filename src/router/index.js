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

export default new Router({
    mode: 'history',
    routes: [{
        path: '/login',
        name: 'LoginComponent',
        component: LoginComponent
    }, {
        path: '/registration',
        name: 'RegistrationComponent',
        component: RegistrationComponent
    }, {
        path: '/blog/posts',
        name: 'CreatePostComponent',
        component: CreatePostComponent
    }, {
        path: '/blog',
        name: 'BlogComponent',
        component: BlogComponent
    }, {
        path: '/blog/post',
        name: 'GetPostComponent',
        component: GetPostComponent
    }
    ]
})

