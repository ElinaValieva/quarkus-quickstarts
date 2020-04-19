<template>
    <div>
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
            <section class="blog-list px-3 py-5 p-md-5">
                <div class="container" v-for="post in posts" v-on:click.prevent :key="post.id">
                    <div class="item mb-5">
                        <div class="media">
                            <img class="mr-3 img-fluid post-thumb d-none d-md-flex"
                                 src="../assets/images/blog-post-thumb.jpg" alt="image">
                            <div class="media-body">
                                <h3 class="title mb-1"><a href="blog-post.html">{{post.title}}</a></h3>
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
    </div>
</template>

<script>
export default {
    name: "PostComponent",
    props: {
        posts: Array
    }
}
</script>

<style scoped>

</style>
