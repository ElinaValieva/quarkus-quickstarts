<template>
    <div>
        <UserComponent/>
        <PostsComponent v-bind:posts="posts"/>
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
