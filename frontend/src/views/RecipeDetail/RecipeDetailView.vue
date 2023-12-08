<!-- vue template  -->
<template>
  <div class="recipe-detail">
    <NavBar/>
    <div class="wrapper">
      <RecipeInfo class="recipe-info"/>
      <RecipeReview class="recipe-review"/>
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
      items: []
    }
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      try {
        const response = await fetch('/path/to/data.json');
        const data = await response.json();
        this.items = data;
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
}
.recipe-detail {
  height: auto;
  background: #FBF7EB;
}

</style>