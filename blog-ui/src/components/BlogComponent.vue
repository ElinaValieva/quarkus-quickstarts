<template>
    <div>
        <UserComponent/>
        <div class="main-wrapper bg-white">
            <section class="cta-section theme-bg-light py-5">
                <div class="container text-center">
                    <h2>DevBlog - Life is for sharing</h2>
                    <form class="signup-form form-inline justify-content-center pt-3">
                        <div class="form-group">
                            <label class="sr-only" for="semail">Your email</label>
                            <input type="text" id="semail" name="semail1" class="form-control mr-md-1 semail"
                                   placeholder="Post .." v-model="filtered">
                        </div>
                    </form>
                </div>
            </section>
            <PostsComponent v-bind:posts="filteredPost"/>
        </div>
    </div>
</template>

<script>
import PostsComponent from "./RetrievePostsComponent";
import UserComponent from "./UserComponent";

export default {
    name: "BlogComponent",
    components: {
        PostsComponent, UserComponent
    },
    data() {
        return {
            posts: this.loadPosts(),
            filtered: ''
        }
    },
    computed: {
        filteredPost: function () {
            if (this.filtered !== '') {
                return (this.filtered.startsWith('#')) ?
                    this.filterByTags(this.filtered) :
                    this.filterByTitle(this.filtered);
            }
            return this.posts;
        }
    },
    methods: {
        loadPosts() {
            this.$store.dispatch('getPosts').catch(error => {
                this.$swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: error.message
                });
            });
            return this.$store.getters.postDetails;
        },
        filterByTags(tag) {
            return this.posts.filter(post => post.tags !== undefined && post.tags.includes(tag));
        },
        filterByTitle(title) {
            return this.posts.filter(post => post.title.includes(title));
        }
    }
}
</script>

<style src="../assets/css/theme.css"></style>
