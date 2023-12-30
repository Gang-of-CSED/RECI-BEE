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
                <v-rating v-model="rating" id="stars" hover clearable size="2.1vw" @click="emitFilters"></v-rating>
            </li>
            <li>
                <h3>Cuisines</h3>
                <hr>
                <div class="filter">
                    <v-checkbox density="compact" v-model="cuisines" hide-details class="labels" value="Arabian"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">Arabian</span>
                        </template>
                    </v-checkbox>
                    <v-checkbox density="compact" v-model="cuisines" hide-details class="labels" value="European"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">European</span>
                        </template>
                    </v-checkbox>
                    <v-checkbox density="compact" v-model="cuisines" hide-details class="labels" value="American"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">American</span>
                        </template>
                    </v-checkbox>
                    <v-checkbox density="compact" v-model="cuisines" hide-details class="labels" value="Asian"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">Asian </span>
                        </template>
                    </v-checkbox>
                   
             
                </div>
            </li>
            <li>
                <h3>Categories</h3>
                <hr>
                <div class="filter">
                    <v-checkbox density="compact" v-model="categories" hide-details class="labels" value="Breakfast"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">Breakfast</span>
                        </template>
                    </v-checkbox>
                    <v-checkbox density="compact" v-model="categories" hide-details class="labels" value="Lunch"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">Lunch</span>
                        </template>
                    </v-checkbox>
                    <v-checkbox density="compact" v-model="categories" hide-details class="labels" value="Dinner"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">Dinner</span>
                        </template>
                    </v-checkbox>
                    <v-checkbox density="compact" v-model="categories" hide-details class="labels" value="Snacks"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">Snacks</span>
                        </template>
                    </v-checkbox>
                    <v-checkbox density="compact" v-model="categories" hide-details class="labels" value="Dessert"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">Dessert</span>
                        </template>
                    </v-checkbox>
                    <v-checkbox density="compact" v-model="categories" hide-details class="labels" value="Drinks"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">Drinks</span>
                        </template>
                    </v-checkbox>
                </div>
            </li>
            <li>
                <h3>Diet</h3>
                <hr>
                <div class="filter" @click="emitFilters">
                    <v-checkbox density="compact" v-model="diets" hide-details class="labels" value="Keto"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">Keto </span>
                        </template>
                    </v-checkbox>
                    <v-checkbox density="compact" v-model="diets" hide-details class="labels" value="Sugar-free"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">Sugar-free </span>
                        </template>
                    </v-checkbox>
                    <v-checkbox density="compact" v-model="diets" hide-details class="labels" value="Vegetarian"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">Vegetarian</span>
                        </template>
                    </v-checkbox>
                   
                </div>
            </li>
            <li>
                <h3>Calories</h3>
                <hr>
                <div class="filter" @click="emitFilters">
                    <v-checkbox density="compact" v-model="calories" hide-details class="labels" value="1 - 50"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">1 - 50 </span>
                        </template>
                    </v-checkbox>
                    <v-checkbox density="compact" v-model="calories" hide-details class="labels" value="50 - 150"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">50 - 150 </span>
                        </template>
                    </v-checkbox>
                    <v-checkbox density="compact" v-model="calories" hide-details class="labels" value="150 - 300"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">150 - 300</span>
                        </template>
                    </v-checkbox>
                    <v-checkbox density="compact" v-model="calories" hide-details class="labels" value="+300"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">+300</span>
                        </template>
                    </v-checkbox>
                </div>
            </li>
            <li>
                <h3>Time</h3>
                <hr>
                <div class="filter" @click="emitFilters">
                    <v-checkbox density="compact" v-model="time" hide-details class="labels" value="5 - 10 Mins"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">5 - 10 Mins</span>
                        </template>
                    </v-checkbox>
                    <v-checkbox density="compact" v-model="time" hide-details class="labels" value="10 - 30 Mins"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">10 - 30 Mins</span>
                        </template>
                    </v-checkbox>
                    <v-checkbox density="compact" v-model="time" hide-details class="labels" value="30 - 60 Mins"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">30 - 60 Mins</span>
                        </template>
                    </v-checkbox>
                    <v-checkbox density="compact" v-model="time" hide-details class="labels" value="+1 Hour"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">+1 Hour</span>
                        </template>
                    </v-checkbox>
                </div>
            </li>
            <!-- show liked only if there is user -->

            <li v-if="user">
                <h3>Liked</h3>
                <hr>
                <div class="filter">
                    <v-checkbox density="compact" v-model="liked" hide-details class="labels" value="Liked"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">Show Liked Recipes</span>
                        </template>
                    </v-checkbox>
                    <!-- checkbox for saves -->
                    <v-checkbox density="compact" v-model="saved" hide-details class="labels" value="Saved"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">Show Saved Recipes</span>
                        </template>
                    </v-checkbox>
                </div>
            </li>
            <!-- <li v-if="user">
                <h3>Saved</h3>
                <hr>
                <div class="filter">
                    <v-checkbox density="compact" v-model="saved" hide-details class="labels" value="Saved"
                        @click="emitFilters">
                        <template v-slot:label>
                            <span class="lol">Show Saved Recipes</span>
                        </template>
                    </v-checkbox>
                </div>
            </li> -->
        </ul>
    </div>
</template>
<script>
export default {
    data() {
        return {
            rating: 0,
            categories: [],
            cuisines: [],
            calories: [],
            diets: [],
            time: [],
            liked: false,
        };
    },
    methods: {
        clearAll() {
            // console.log()
            this.rating = 0;
            this.categories = [];
            this.cuisines = [];
            this.diets = [];
            this.calories = [];
            this.time = [];
            this.liked = false;
            this.saved = false;
            this.emitFilters();
        },
        emitFilters() {
            setTimeout(() => {
                //   console.log(JSON.stringify({ 
                //     rating: this.rating,
                //     categories: this.categories,
                //     time: this.time,
                //     liked: this.liked,
                //   }, null, 2));

                this.$emit('filters-updated', {
                    rating: this.rating,
                    categories: this.categories,
                    cuisines: this.cuisines,
                    calories: this.calories,
                    diets: this.diets,
                    time: this.time,
                    liked: this.liked,
                });
            },0);
        },
    },
    props:{
        user:{
            type:Object,
            required:true
        }
    }
};
</script>

<style scoped>
#stars {
    width: 10px;
    margin-top: 0.6vh;
}

h2 {
    font-size: 2.223vw;
    font-weight: 700;
    margin: 0;
}

h3 {
    font-size: 1.3889vw;
    font-weight: 700;
    margin-top: 0.8727vh;
}

#bolder {
    border: 0.12vh solid #FBF7EB;
    margin-bottom: 1vh;
}

hr {
    margin-bottom: 0;
}

.side-bar {
    display: flex;
    flex-direction: column;
    padding: 0 4vw;
    /* flex-basis: 360px; */
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
    /* margin-top: 76px; */
    margin-top: 5.95vh;
}

button {
    font-size: 1.111vw;
}

ul {
    list-style: none;
    padding: 0;
    margin: 0;
}

.labels {
    color: #fbf7eb;
    opacity: 3;
    font-weight: 500;
    height: 1.4vh;
    font-size: 1.1vw;
    margin-bottom: 1.5vh;
}

.filter {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    margin-bottom: 1.80vh;
    width: 17vw;
}

#stars {
    width: 17vw;
    margin-top: 0.6vh;
    font-size: 1.2vw;
}

.lol {
    font-size: 1.2vw;
}

/* ::v-deep .v-rating__wrapper {
        width: 5vw;
      justify-content: space-around;
    }
    ::v-deep .v-rating .v-icon {
      font-size: 2.5vw !important; 
      margin-right: 0.2vw !important; 
    }
    ::v-deep .v-label {
     color: #ffffff;
      font-size: 1.5vw !important;
    } */
/* #stars {
    
    margin: 0 ;
    font-size:1vw;
    width:17vw ;
}


h2 {
    font-size: 2.22vw;
    font-weight: 700;
    margin: 0;
}
h3 {
    font-size: 1.4vw;
    font-weight: 700;
    margin-top: 1.78vh;
}
#bolder {
    border: 0.2vh solid #FBF7EB;
    margin-bottom: 2.23vh;
}
hr {

    margin-bottom: 0;
}
.side-bar {
        display: flex;
        flex-direction: column;
        padding: 0 3.95vw;
        flex-basis: 25vw;
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
        margin-top:4.44vh;
    }
    button {
        font-size: 1.11vw;
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
        height: 2.12vh;
        font-size: 1.11vw; 
        margin-bottom: 0.778vh;
    }

    .filter {
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        margin-bottom: 2.56vh;
    }
   
   */</style>
