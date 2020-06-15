<template>
    <div>
        <header class="header text-center">
            <h1 class="blog-name pt-lg-4 mb-0">{{user.username}}'s Blog</h1>
            <nav class="navbar navbar-expand-lg navbar-dark">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navigation"
                        aria-controls="navigation" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div id="navigation" class="collapse navbar-collapse flex-column">
                    <div class="profile-section pt-3 pt-lg-0">
                        <img class="profile-image mb-3 rounded-circle mx-auto" src="../assets/images/profile.png"
                             alt="image">
                        <div class="bio mb-3">Hi, my name is {{user.firstName}} {{user.lastName}}. Briefly introduce
                            yourself here. You can also provide a link to the about page.
                        </div><!--//bio-->
                        <ul class="social-list list-inline py-3 mx-auto">
                            <li class="list-inline-item"><a href="#"><i class="fab fa-twitter fa-fw"></i></a></li>
                            <li class="list-inline-item"><a href="#"><i class="fab fa-linkedin-in fa-fw"></i></a></li>
                            <li class="list-inline-item"><a href="#"><i class="fab fa-github-alt fa-fw"></i></a></li>
                            <li class="list-inline-item"><a href="#"><i class="fab fa-stack-overflow fa-fw"></i></a>
                            </li>
                            <li class="list-inline-item"><a href="#"><i class="fab fa-codepen fa-fw"></i></a></li>
                        </ul><!--//social-list-->
                        <hr>
                    </div><!--//profile-section-->

                    <ul class="navbar-nav flex-column text-left">
                        <li class="nav-item active">
                            <a class="nav-link"><i class="fas fa-home fa-fw mr-2"></i>
                                <router-link to="/blog" style="color: white">Blog Home</router-link>
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link">
                                <i class="fas fa-bookmark fa-fw mr-2"></i>
                                <router-link to="/blog/post" style="color: white">Create post</router-link>
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
    </div>
</template>

<script>
export default {
    name: "InfoComponent",
    computed: {
        user() {
            return this.$store.getters.userInfo;
        }
    }
}
</script>
