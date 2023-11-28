<template>
    <div class="side-bar">
        <div class="top">
            <h2>Filters</h2>
            <button @click="clearAll">Clear All</button>
        </div>
        <hr id="bolder">
        <ul>
            <li>
                <h3>Ratings</h3>
                <hr>
                <v-rating v-model="rating" id="stars" hover clearable size="27.2"></v-rating>
            </li>
            <li>
                <h3>Categories</h3>
                <hr>
                <div class="filter">
                    <v-checkbox density="compact" v-model="categories" hide-details class="labels" label="Breakfast" value="Breakfast"></v-checkbox>
                    <v-checkbox density="compact" v-model="categories" hide-details class="labels" label="Lunch" value="Lunch"></v-checkbox>
                    <v-checkbox density="compact" v-model="categories" hide-details class="labels" label="Dinner" value="Dinner"></v-checkbox>
                    <v-checkbox density="compact" v-model="categories" hide-details class="labels" label="Snacks" value="Snacks"></v-checkbox>
                    <v-checkbox density="compact" v-model="categories" hide-details class="labels" label="Dessert" value="Dessert"></v-checkbox>
                    <v-checkbox density="compact" v-model="categories" hide-details class="labels" label="Vegan" value="Vegan"></v-checkbox>
                </div>
            </li>
            <li>
                <h3>Time</h3>
                <hr>
                <div class="filter">
                    <v-checkbox density="compact" v-model="time" hide-details class="labels" label="5 - 10 Mins" value="5 - 10 Mins"></v-checkbox>
                    <v-checkbox density="compact" v-model="time" hide-details class="labels" label="10 - 30 Mins" value="10 - 30 Mins"></v-checkbox>
                    <v-checkbox density="compact" v-model="time" hide-details class="labels" label="30 - 60 Mins" value="30 - 60 Mins"></v-checkbox>
                    <v-checkbox density="compact" v-model="time" hide-details class="labels" label="+1 Hour" value="+1 Hour"></v-checkbox>
                </div>
            </li>
            <li>
                <h3>Liked</h3>
                <hr>
                <div class="filter">
                    <v-checkbox density="compact" v-model="liked" hide-details class="labels" label="Show Liked Recipes" value="Liked"></v-checkbox>
                </div>
            </li>
        </ul>
    </div>
</template>
<script>
export default {
  data() {
    return {
      rating: 0,
      categories: [],
      time: [],
      liked: false,
    };
  },
  methods: {
    clearAll() {
      this.rating = 0;
      this.categories = [];
      this.time = [];
      this.liked = false;
    },
  },
};
</script>

<style scoped>
#stars {
    margin-top: 11px;
}
h2 {
    font-size: 32px;
    font-weight: 700;
    margin: 0;
}
h3 {
    font-size: 20px;
    font-weight: 700;
    margin-top: 16px;
}
#bolder {
    border: 2px solid #FBF7EB;
    margin-bottom: 20px;
}
hr {
    margin-bottom: 0;
}
.side-bar {
        display: flex;
        flex-direction: column;
        padding: 0 57px;
        flex-basis: 360px;
        flex-grow: 0;
        flex-shrink: 0;
        background-color: #E35733;
        color: #FBF7EB;
    }
    .top {
        display: flex;
        justify-content: space-between;
        flex-direction: row;
        align-items: center;
        width: 100%;
        margin-top: 76px;
    }
    button {
        font-size: 16px;
    }
    ul {
        list-style: none;
        padding: 0;
        margin: 0;
    }
    .labels {
        color: #FBF7EB;
        opacity: 1;
        font-weight: 500;
        height: 19px;
        font-size: 16px;
        margin-bottom: 7px;
    }
    .filter {
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        margin-bottom: 23px;
    }
    
</style>
