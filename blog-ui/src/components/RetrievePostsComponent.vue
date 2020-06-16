<template>
    <div class="p-4 mb-5">
        <section class="blog-list px-3 py-5 p-md-5">
            <div class="container" v-for="post in posts" v-on:click.prevent :key="post.id">
                <div class="item mb-5">
                    <div class="media">
                        <img class="mr-3 img-fluid post-thumb d-none d-md-flex"
                             src="../assets/images/blog-post-thumb.jpg" alt="image">
                        <div class="media-body">
                            <h3 class="title mb-1">
                                <router-link v-bind:to="{ name: 'GetPostComponent', params: { post: post }}">
                                    {{post.title}}
                                </router-link>
                            </h3>
                            <div class="meta mb-1">
                                <span class="date">Published {{post.date}}</span>
                                <span class="comment">{{post.tags}}</span>
                            </div>
                            <div class="intro">{{post.text}}</div>
                            <a class="more-link" href="blog-post.html">Read more &rarr;</a>
                        </div><!--//media-body-->
                    </div><!--//media-->
                </div><!--//item-->
            </div>
        </section>
    </div><!--//main-wrapper-->
</template>

<script>
export default {
    name: "PostComponent",
    props: {
        posts: Array
    }
}
</script>
