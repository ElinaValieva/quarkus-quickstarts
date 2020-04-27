<template>
    <div>
        <UserInfoComponent/>
        <div>
            <div class="main-wrapper">
                <section class="cta-section theme-bg-light py-5">
                    <div class="container text-center">
                        <h1>{{title}}</h1>
                        <form>
                            <div class="form-group">
                                <label class="sr-only" for="title">Title</label>
                                <input type="text" id="title" v-model="title" class="form-control mr-md-1"
                                       placeholder="Title">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="tags">Tags</label>
                                <input type="text" id="tags" v-model="tags" class="form-control mr-md-1"
                                       placeholder="# Tags">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="text">Tags</label>
                                <textarea id="text" v-model="text" class="form-control mr-md-1" rows="20"></textarea>
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
            submitted: false
        }
    },
    methods: {
        createPost: function () {
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
