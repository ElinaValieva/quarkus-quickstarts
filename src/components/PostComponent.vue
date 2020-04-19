<template>
    <div>
        <InfoComponent/>
        <div class="main-wrapper">
            <article class="blog-post px-3 py-5 p-md-5">
                <div class="container">
                    <header class="blog-post-header">
                        <h2 class="title mb-2">{{post.title}}</h2>
                        <div class="meta mb-3"><span class="date">Published {{post.date}}</span>
                            <span class="comment">{{post.tags}}</span>
                        </div>
                    </header>
                    <div class="blog-post-body">
                        <figure class="blog-banner">
                            <img alt="image" class="img-fluid" src="../assets/images/blog-post-banner.jpg">
                        </figure>
                        <p>{{post.text}}</p>
                    </div>
                </div><!--//container-->
            </article>
        </div>
    </div>
</template>

<script>
import InfoComponent from "./UserComponent";

export default {
    name: "PostInfoComponent",
    components: {
        InfoComponent
    },
    props: {
        post: Object
    }
}
</script>

<style scoped>

</style>
