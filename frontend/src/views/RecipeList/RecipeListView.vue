<template>
  <div>
    <NavBar />
    <div class="recipe-list">
      <SideBar @filters-updated="filterRecipes" :user="user" />
      <div class="showList">
        <div class="slogan">
          <h6>Unlock The<br>Flavors Of The World</h6>
        </div>
      <SearchBar @sort-event="sortRecipes" @search-event="searchRecipes"/>
        <List :recipiesArray="fltRecipes" :user="user" />
      </div>
    </div>
  </div>
</template>

<script setup >
import axios from 'axios';
import NavBar from '../../components/NavBar.vue';
import SideBar from './components/SideBar.vue';
import SearchBar from './components/SearchBar.vue';
import List from './components/List.vue';
import { ref, onMounted } from 'vue';


const allRecipes = ref([]);
const fltRecipes = ref([]);
const userFavorites = ref([]);
const user = ref(null);


const filterRecipes = (selected) => {
  // Helper function to convert time string to a comparable number (minutes)
  const timeToMinutes = (time) => {
    if (time === "5 - 10 Mins") return [5, 10];
    if (time === "10 - 30 Mins") return [10, 30];
    if (time === "30 - 60 Mins") return [30, 60];
    if (time === "+1 Hour") return [61, Infinity];
    return [0, 0];
  };
  const calorieToRange = (calorie) => {
    if (calorie === "1 - 50") return [1, 50];
    if (calorie === "50 - 150") return [50, 150];
    if (calorie === "150 - 300") return [150, 300];
    if (calorie === "+300") return [301, Infinity];
    return [0, 0];
  };

  const selectedTimeRanges = selected.time && selected.time.length > 0 ? selected.time.map(t => timeToMinutes(t)) : null;


  fltRecipes.value = allRecipes.value.filter(recipe => {

    const categoryMatch = selected.categories && selected.categories.length > 0
      ? recipe.categories.some(category => selected.categories.includes(category))
      : true;

    const cuisineMatch = selected.cuisines && selected.categories.length > 0
      ? recipe.categories.some(cuisine => selected.Cuisines.includes(cuisine))
      : true;

    const timeMatch = selectedTimeRanges
      ? selectedTimeRanges.some(([min, max]) => recipe.time >= min && recipe.time <= max)
      : true;

    const calorieMatch = selectedCalorieRanges
      ? selectedCalorieRanges.some(([min, max]) => recipe.calories >= min && recipe.calories <= max)
      : true;

    const isLiked = selected.liked ? recipe.isFavorite : true;
    const isSaved = selected.saved ? recipe.isFavorite : true;

    // console.log("reciperating",recipe.rate)
    // console.log("selectedrating",selected.rating)

    const ratingMatch = selected.rating === 0 || parseInt(recipe.rate) == parseInt(selected.rating);

    return categoryMatch && timeMatch && isLiked && ratingMatch&& isSaved && calorieMatch && cuisineMatch;
  });

  //   console.log(JSON.stringify(fltRecipes.value, null, 2));
  return fltRecipes.value;
};

const sortRecipes = (sortLogic) => {
  const compare = (a, b) => {
    switch (sortLogic) {
      case 'time':
        return a.time - b.time;
      case 'rate':
        // Assuming higher rating is better
        return b.rate - a.rate;
      case 'calories':
        return a.calories - b.calories;
      default:
        // Default case if an unknown sort logic is passed
        return 0;
    }
  };

  fltRecipes.value.sort(compare);
};

const searchRecipes = (searchWord, searchLogic) => {
 
  if (!searchWord || !searchLogic) return;

  const searchWordLower = searchWord.toLowerCase();

  fltRecipes.value = fltRecipes.value.filter(recipe => {
    if (searchLogic === 'name') {
      return recipe.name.toLowerCase().includes(searchWordLower);
    } else if (searchLogic === 'ingredients') {
      // Assuming 'ingredients' is an array of strings in each recipe
      return recipe.ingredients.some(ingredient =>
        ingredient.toLowerCase().includes(searchWordLower)
      );
    }
    return false;
  });
};
onMounted(() => {
  const token = localStorage.getItem('token');
  if (token) {
    fetch("http://localhost:8080/info", {
      headers: {
        Authorization: `${localStorage.getItem('token')}`
      }
    }).
      then(response => response.json())
      .then(data => {
        console.log(data);
        user.value = data;
        fetchUserFavorite();
      })

  }

  fetchAllRecipes();
  // dummyfetch();
});

const fetchAllRecipes = () => {
  axios.get('http://localhost:8080/recipes')
    .then(response => {
      allRecipes.value = response.data;
      fltRecipes.value = response.data;
      //  console.log(JSON.stringify(allRecipes.value,null, 2));
     
      // resolve(response.data);
    })
    .catch(error => {
      console.error('There was an error!', error);
      // reject(error);
    });
};

const fetchUserFavorite = () => {
  let userId = user.value?.username;
  axios.get('http://localhost:8080/' + userId + '/favorites')
    .then(response => {
      userFavorites.value = response.data;
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
    console.log(userFavorites.value)
    const isFavorite = userFavorites.value.favorites.some(favorite => favorite === recipe.id);
    return { ...recipe, isFavorite };
  });
  fltRecipes.value = allRecipes.value;
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
}
</style>