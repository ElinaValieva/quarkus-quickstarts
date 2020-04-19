<template>
    <div>
        <form @submit.prevent="login" class="base-form">
            <div class="input-field">
                <img src="../../assets/images/man-user.svg" alt="username"/>
                <label>
                    <input class="form-control" type="text" placeholder="username" v-model="username" required/>
                </label>
            </div>
            <div class="input-field">
                <img src="../../assets/images/lock.svg" alt="password"/>
                <label>
                    <input class="form-control" type="password" placeholder="password" v-model="password" required/>
                </label>
            </div>
            <button class="btn btn-success form-button">LOGIN</button>
            <a class="form-link">
                <router-link to="/registration">Sing up?</router-link>
            </a>
        </form>
    </div>
</template>

<script>
export default {
    name: "LoginComponent",
    data() {
        return {
            username: null,
            password: null
        }
    },
    methods: {
        login: function () {
            this.$store.dispatch('login', {
                username: this.username,
                password: this.password
            }).then(() => {
                this.getUserInfo();
                this.$router.push('/blog');
            }).catch(error => alert(JSON.stringify(error.message)));
        },
        getUserInfo: function () {
            this.$store.dispatch('getUserInfo').catch(error => alert(JSON.stringify(error.message)));
        }
    }
}
</script>

<style src="../../assets/css/login-form.css"></style>
