<!-- vue template  -->
<template>
  <div class="recipe-detail">
    <NavBar/>
    <div class="wrapper">
      <RecipeInfo/>
      <RecipeReview/>

    </div>
  </div>
</template>

<script setup>
import NavBar from '../../components/NavBar.vue';
import RecipeInfo from './components/RecipeInfo.vue';
import RecipeReview from './components/RecipeReview.vue';
</script>

<style scoped>
.wrapper {
  display: flex;
  width: 100%;
  height: 100%;
}
.recipe-detail {
  height: auto;
}

</style>