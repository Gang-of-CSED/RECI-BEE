<template>
  <div>
    <NavBar />
    <div class="recipe-list">
      <SideBar @filters-updated="filterRecipes" :user="user"/>
      <div class="showList">
        <div class="slogan">
          <h6>Unlock The<br>Flavors Of The World</h6>
        </div>
        <List :recipiesArray="fltRecipes" :user="user"/>
      </div>
    </div>
  </div>
</template>

<script setup >
import axios from 'axios';
import NavBar from '../../components/NavBar.vue';
import SideBar from './components/SideBar.vue';
import List from './components/List.vue';
import { ref, onMounted } from 'vue';


const allRecipes = ref([]);
const fltRecipes = ref([]);
const userFavorites = ref([]);


const filterRecipes = (selected) => {
  // Helper function to convert time string to a comparable number (minutes)
  const timeToMinutes = (time) => {
    if (time === "5 - 10 Mins") return [5, 10];
    if (time === "10 - 30 Mins") return [10, 30];
    if (time === "30 - 60 Mins") return [30, 60];
    if (time === "+1 Hour") return [61, Infinity];
    return [0, 0];
  };

  const selectedTimeRanges = selected.time && selected.time.length > 0 ? selected.time.map(t => timeToMinutes(t)) : null;


  fltRecipes.value = allRecipes.value.filter(recipe => {

    const categoryMatch = selected.categories && selected.categories.length > 0
      ? recipe.categories.some(category => selected.categories.includes(category))
      : true;

    const timeMatch = selectedTimeRanges
      ? selectedTimeRanges.some(([min, max]) => recipe.time >= min && recipe.time <= max)
      : true;

    const isLiked = selected.liked ? recipe.isFavorited : true;

    // console.log("reciperating",recipe.rate)
    // console.log("selectedrating",selected.rating)

    const ratingMatch = selected.rating === 0 || parseInt(recipe.rate) == parseInt(selected.rating);

    return categoryMatch && timeMatch && isLiked && ratingMatch;
  });

  //   console.log(JSON.stringify(fltRecipes.value, null, 2));
  return fltRecipes.value;
};

onMounted(() => {

    fetchAllRecipes();
    fetchUserFavorite();
    // dummyfetch();
  }); 

  const fetchAllRecipes=()=>{
    axios.get('http://localhost:8080/recipes')
              .then(response => {
                allRecipes.value= response.data;
                fltRecipes.value=response.data;
                //  console.log(JSON.stringify(allRecipes.value,null, 2));

                // resolve(response.data);
              })
              .catch(error => {
                console.error('There was an error!', error);
                // reject(error);
              });
  };

  const fetchUserFavorite=()=>{
    let userId=1;
    axios.get('http://localhost:8080/'+userId+'/favorites')
              .then(response => {
                userFavorites.value= response.data;
                matchFavorites();
              })
              .catch(error => {
                console.error('There was an error!', error);

              });
  };
  

  //dummy fetching for testing 
  // const dummyfetch=()=>{

  //     fetch('http://localhost:3000/favorites')
  //     .then((response) => response.json()) 
  //     .then((data) => {
  //       userFavorites.value = data;
  //       matchFavorites();

  //     //  console.log(JSON.stringify(userFavorites.value,null, 2));
  //     })
  //     .catch((err) => console.error('Error fetching shapes:', err));
  //   };




  const matchFavorites = () => {
  allRecipes.value = allRecipes.value.map(recipe => {

    const isFavorite = userFavorites.value.some(favorite => favorite.recipe_id === recipe.id);
    return { ...recipe, isFavorite };
  });
    fltRecipes.value=allRecipes.value;
      //  console.log(JSON.stringify(fltRecipes.value,null, 2));

};
   

</script>

<style scoped>
.recipe-list {
  display: flex;
  flex-direction: row;
  height: 100%;
  background: #FBF7EB;
}

.showList {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-content: flex-start;
}

.slogan {
  position: relative;
  padding-right: 1%;
  min-width: 75vw;
  display: flex;
  justify-content: flex-end;
  /* Align text to the right horizontally */
  align-items: center;
  /* Center vertically */
  background: #FBBC3B;
  min-height: 12.153vw;
}

.slogan h6 {
  text-align: right;
  color: #FBF7EB;
  font-style: normal;
  font-size: 3.33vw;
  font-weight: 700;
  line-height: 1;
  /* Adjust the line height to control spacing between lines */

  margin-right: 2.45vw;
}</style>