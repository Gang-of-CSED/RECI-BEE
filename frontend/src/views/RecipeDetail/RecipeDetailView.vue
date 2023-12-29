<!-- vue template  -->
<template>
  <div class="recipe-detail">
    <NavBar/>
    <div class="wrapper">
      <RecipeInfo :recipe="recipe" class="recipe-info"/>
      <RecipeReview :recipe="recipe"  class="recipe-review" />
    </div>
    <Footer />
  </div>
</template>

<script>
import NavBar from '../../components/NavBar.vue';
import RecipeInfo from './components/RecipeInfo.vue';
import RecipeReview from './components/RecipeReview.vue';
import Footer from '../../components/Footer.vue';
export default {
  components: {
    NavBar,
    RecipeInfo,
    RecipeReview,
    Footer,
  },
  data() {
    return {
      recipe: {},
    }
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      const id = this.$route.params.id;
      try {
        const recipe = await fetch(`http://localhost:8080/recipe/${id}`);
        this.recipe = await recipe.json();
      } catch (error) { 
        console.error('Error fetching data:', error);
      }
    }
  },
}
    
</script>

<style scoped>
.wrapper {
  display: flex;
  flex-direction: row;
  width: 100%;
  height: 100%;
}
.recipe-info {
  flex: 1;
  padding: 1%;

}
.recipe-review {
  flex: 1;
  padding: 1%;
  margin-top: 2%;
}
.recipe-detail {
  /* height: auto; */
  background: #FBF7EB;
}

</style>