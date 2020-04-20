<template>
    <div id="app">
        <router-view/>
        <notifications group="app"></notifications>
    </div>
</template>

<script>

export default {
    name: 'App',
    components: {}
}
</script>
<style>
    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
    }
</style>
