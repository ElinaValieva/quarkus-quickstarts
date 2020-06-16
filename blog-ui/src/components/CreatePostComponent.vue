<template>
    <div>
        <UserInfoComponent/>
        <div>
            <div class="main-wrapper">
                <section class="cta-section theme-bg-light py-5">
                    <div class="container-fluid text-center">
                        <h1 style="padding-top: 3%">{{title}}</h1>
                        <form>
                            <div class="error text-danger font-weight-bold" v-if="!$v.title.required">Title is required
                            </div>
                            <div class="form-group" :class="{ 'form-group--error': $v.title.$error}">
                                <label class="sr-only" for="title">Title</label>
                                <input type="text" id="title"
                                       v-model.trim="$v.title.$model"
                                       class="form-control mr-md-1"
                                       placeholder="Title">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="tags">Tags</label>
                                <input type="text" id="tags"
                                       v-model="tags"
                                       class="form-control mr-md-1"
                                       placeholder="# Tags">
                            </div>
                            <div class="error text-danger font-weight-bold" v-if="!$v.text.required">Text is required
                            </div>
                            <div class="error text-danger font-weight-bold" v-if="!$v.text.minLength">
                                Text must have at least {{textLength}} letters.
                            </div>
                            <div class="form-group" :class="{ 'form-group--error': $v.text.$error }">
                                <label class="sr-only" for="text">Text</label>
                                <textarea id="text"
                                          v-model.trim="$v.text.$model"
                                          class="form-control mr-md-1"
                                          rows="19"></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary" v-on:click="createPost" v-on:click.prevent
                                    :disabled="submitted">
                                Create post
                            </button>
                        </form>
                    </div>
                </section>
            </div>
        </div>
    </div>
</template>

<script>
import UserInfoComponent from "./UserComponent";
import {minLength, required} from "vuelidate/src/validators";

export default {
    name: "PostBlogComponent",
    components: {
        UserInfoComponent
    },
    data() {
        return {
            title: 'DevBlog - Life is for sharing',
            text: null,
            tags: null,
            submitted: false,
            textLength: 10
        }
    },
    validations: {
        text: {
            required,
            minLength: minLength(10)
        },
        title: {
            required
        }
    },
    methods: {
        createPost: function () {
            this.$v.$touch();
            if (this.$v.$invalid)
                return;

            this.submitted = true;
            this.$store.dispatch('createPost', {
                title: this.title,
                text: this.text,
                tags: this.tags
            }).then(() => {
                this.$swal.fire({
                    icon: 'success',
                    title: 'Good job!',
                    text: 'Post successfully published'
                });
                this.submitted = false;
            }).catch(error => {
                this.$swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: error.message
                });
                this.submitted = false;
            });
        }
    }
}
</script>

<style src="../assets/css/theme.css"></style>
