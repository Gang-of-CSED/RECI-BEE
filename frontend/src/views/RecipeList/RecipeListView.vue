<template>
    <NavBar/>
    <div class="recipe-list">
        <SideBar/>
        <List/>
    </div>
</template>

<script setup>
import NavBar from '../../components/NavBar.vue';
import SideBar from './components/SideBar.vue';
import List from './components/List.vue';

</script>

<style scoped>
.recipe-list {
    display: flex;
    flex-direction: row;
    height: 100%;
}

</style>