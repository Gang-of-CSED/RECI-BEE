<!-- vue template  -->
<template>
  <div class="recipe-detail">
    <NavBar/>
    <div class="wrapper">
      <RecipeInfo :recipe="recipe" class="recipe-info"/>
      <RecipeReview :recipe="recipe" :contributions="contributions" class="recipe-review" />
    </div>
  </div>
</template>

<script>
import NavBar from '../../components/NavBar.vue';
import RecipeInfo from './components/RecipeInfo.vue';
import RecipeReview from './components/RecipeReview.vue';
export default {
  components: {
    NavBar,
    RecipeInfo,
    RecipeReview,
  },
  data() {
    return {
      contributions: [],
      recipe: {},
    }
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      try {
        const contributions = await fetch("http://localhost:3001/contributions");
        this.contributions = await contributions.json(); 
      } catch (error) {
        console.error('Error fetching data:', error);
      }
      try {
        const id = this.$route.params.id;
        const recipe = await fetch("http://localhost:3001/recipes/${id}");
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