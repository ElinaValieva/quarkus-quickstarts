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
                </div><!--//container-->
            </section>
            <PostsComponent v-bind:posts="posts"/>
        </div>
    </div>
</template>

<script>
import PostsComponent from "./RetrievePostsComponent";
import UserComponent from "./UserComponent";

export default {
    name: "SearchComponent",
    components: {
        PostsComponent, UserComponent
    },
    data() {
        return {
            post: {"id": 1, "title": "Hello world", "text": "Hi all ...", "tags": "#hello", "date": "12 Apr"},
            posts: [
                {"id": 1, "title": "Hello world", "text": "Hi all ...", "tags": "#hello", "date": "12 Apr"},
                {"id": 2, "title": "Hello world [2]", "text": "Hi all ...[2]", "tags": "#[2]", "date": "25 Apr"}],
        }
    }
}
</script>
<style src="../assets/css/theme.css"></style>
