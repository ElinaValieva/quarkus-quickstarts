<template>
    <div>
        <form @submit.prevent="register">
            <div class="input-field">
                <img src="../assets/images/man-user.svg"/>
                <input class="form-control" type="text" placeholder="username" v-model="username" required/>
            </div>
            <div class="input-field">
                <img src="../assets/images/user-name.svg"/>
                <input class="form-control" type="text" placeholder="name" v-model="name" required/>
            </div>
            <div class="input-field">
                <img src="../assets/images/user-name.svg"/>
                <input class="form-control" type="text" placeholder="last name" v-model="lastName" required/>
            </div>
            <div class="input-field">
                <img src="../assets/images/lock.svg"/>
                <input class="form-control" type="password" placeholder="password" v-model="password" required/>
            </div>
            <button class="btn btn-success" type="submit">SING IN</button>
            <a href="/login">Sing in?</a>
        </form>
    </div>
</template>

<script>
export default {
    name: "RegistrationComponent",
    data() {
        return {
            username: '',
            name: '',
            lastName: '',
            password: ''
        }
    },
    methods: {
        register: function () {
        }
    }
}
</script>

<style src="../assets/css/login-form.css"></style>
