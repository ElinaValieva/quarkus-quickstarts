<template>
    <div>
        <UserComponent/>
        <div class="main-wrapper">
            <section class="cta-section theme-bg-light py-5">
                <div class="container text-center">
                    <h2>DevBlog - Life is for sharing</h2>
                    <form class="signup-form form-inline justify-content-center pt-3">
                        <div class="form-group">
                            <label class="sr-only" for="semail">Your email</label>
                            <input type="email" id="semail" name="semail1" class="form-control mr-md-1 semail"
                                   placeholder="Post ..">
                        </div>
                        <button type="submit" class="btn btn-primary">Search</button>
                    </form>
                </div>
            </section>
            <PostsComponent v-bind:posts="posts"/>
        </div>
    </div>
</template>

<script>
import PostsComponent from "./performed/RetrievePostsComponent";
import UserComponent from "./performed/UserComponent";

export default {
    name: "BlogComponent",
    components: {
        PostsComponent, UserComponent
    },
    data() {
        return {
            posts: []
        }
    },
    mounted() {
        this.posts = this.loadPosts();
    },
    methods: {
        loadPosts() {
            this.$store.dispatch('getPosts').catch(error => alert(JSON.stringify(error.message)));
            return this.$store.getters.postDetails;
        }
    }
}
</script>
<style src="../assets/css/theme.css"></style>
