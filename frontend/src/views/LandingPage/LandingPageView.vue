<template>
    <div class="LandingPage">
        <NavBar class="Nav" />
        <div class="wrapper">
            <LandingPageDetails />
        </div>
        <Footer />
    </div>
</template>

<script setup>
import Footer from '../../components/Footer.vue';
import NavBar from '../../components/NavBar.vue';
import LandingPageDetails from './components/LandingPageDetails.vue';

</script>

<style scoped>
.wrapper {
    display: flex;
}

.Nav {
    position: fixed;
    width: 100%;
    z-index: 10;
    box-shadow: 0px 5px 10px 0px #aaa8a150;
}
</style>