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
import NavBar from '../../components/NavBar.vue';
import LandingPageDetails from './components/LandingPageDetails.vue';
import Footer from '../../components/Footer.vue';

</script>

<style scoped>
.wrapper {
    display: flex;
}

.Nav {
    position: fixed;
    width: 100%;
    z-index: 10;
}
</style>