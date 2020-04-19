<template>
    <div>
        <form @submit.prevent="login">
            <div class="input-field">
                <img src="../assets/images/man-user.svg"/>
                <input class="form-control" type="text" placeholder="username" v-model="username" required/>
            </div>
            <div class="input-field">
                <img src="../assets/images/lock.svg"/>
                <input class="form-control" type="password" placeholder="password" v-model="password" required/>
            </div>
            <button class="btn btn-success">LOGIN</button>
            <a href="/registration">Sing up?</a>
        </form>
    </div>
</template>

<script>
export default {
    name: "LoginComponent",
    data() {
        return {
            username: '',
            password: ''
        }
    },
    methods: {
        login: function () {
        }
    }
}
</script>

<style>
    body {
        background: #28a745;
    }

    .input-field {
        margin-top: 5%;
        padding: 3%;
        border-bottom: 2px solid #28a745;
        display: inline-block;
        width: 100%;
    }

    .input-field img {
        width: 7%;
        margin-bottom: 1%;
    }

    .input-field input {
        width: 80%;
        border: none;
        display: inline-block;
    }

    form {
        background: white;
        width: 30%;
        margin-left: 35%;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
        border-radius: 15px;
        margin-top: 10%;
        padding: 3%;
    }

    button {
        width: 50%;
        margin: 5% 25%;
    }

    a {
        width: 20%;
        margin: 10% 40%;
        font-size: 16px;
        text-decoration: none;
    }

    .form-control:focus {
        -webkit-box-shadow: none;
        box-shadow: none;
    }
</style>
