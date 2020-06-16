<template>
    <div>
        <form @submit.prevent="register" class="base-form">
            <div class="input-field">
                <img src="../assets/images/man-user.svg" alt="username"/>
                <label>
                    <input class="form-control" type="text" placeholder="username" v-model="username" required/>
                </label>
            </div>
            <div class="input-field">
                <img src="../assets/images/user-name.svg" alt="name"/>
                <label>
                    <input class="form-control" type="text" placeholder="name" v-model="name" required/>
                </label>
            </div>
            <div class="input-field">
                <img src="../assets/images/user-name.svg" alt="lastName"/>
                <label>
                    <input class="form-control" type="text" placeholder="last name" v-model="lastName" required/>
                </label>
            </div>
            <div class="input-field">
                <img src="../assets/images/lock.svg" alt="password"/>
                <label>
                    <input class="form-control" type="password" placeholder="password" v-model="password" required/>
                </label>
            </div>
            <button class="btn btn-success form-button" :disabled="submitted">SING IN</button>
            <a class="form-link">
                <router-link to="/login">Sing in?</router-link>
            </a>
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
            password: '',
            submitted: false
        }
    },
    methods: {
        register: function () {
            this.submitted = true;
            this.$store.dispatch('register', {
                userName: this.username,
                password: this.password,
                firstName: this.name,
                lastName: this.lastName
            }).then(() => {
                this.submitted = false;
                this.$router.push('/login');
            }).catch(error => {
                this.submitted = false;
                this.$swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: error.message
                });
            });
        }
    }
}
</script>
<style src="../assets/css/login-form.css"></style>
