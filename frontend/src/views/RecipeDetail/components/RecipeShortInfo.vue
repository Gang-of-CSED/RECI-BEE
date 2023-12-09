<template>
    <div class="recipeShortInfo">
        <div class="recipe-name">
            <h1 v-html="recipe.name"></h1>
            <v-icon size="42" color="#E35733" id="heart" @click="toggleFavorite">
                {{ isFavorite ? 'mdi-heart' : 'mdi-heart-outline' }}
            </v-icon>
        </div>
    <p v-html="recipe.description"></p>
    <div class="category-time">
        <div class="categories">
            <h2>Categories:</h2>    
            <p v-for="category in recipe.categories" class="category"> {{ category }}</p>
        </div>
        <div class="time">
            <h2>Time:</h2>
            <p v-html="recipe.time"></p>
        </div>
    </div>
    <div class="rating">
        <h2>Ratings:</h2>
        <v-rating v-model="recipe.rate" id="stars" hover clearable size="27.2" :readonly="true"></v-rating>
    </div>
</div>
    

</template>

<script>
import axios from 'axios';
export default {
    name: "RecipeShortInfo",
    props:['recipe'],
    data(){
        return{
            isFavorite: false,
            user: null,
        }
    },
    mounted(){
        const token = localStorage.getItem('token');
        if(token){
            fetch("http://localhost:8080/info", {
                headers: {
                    Authorization: `${localStorage.getItem('token')}`
                }
            }).
            then(response => response.json())
            .then(data => {
                console.log(data);
                this.user = data;
                let username=data.username;
                let recipeId=this.$route.params.id;
                fetch(`http://localhost:8080/${username}/favorite/${recipeId}`)
                .then(response => response.text())
                .then(data => {
                    console.log(data);
                    // convert to boolean
                    this.isFavorite = data === 'true';
                })
            })
        }
    },
    methods: {
        toggleFavorite(){
            console.log("toggleFavorite")
            this.isFavorite =!this.isFavorite
            let favoriteState = this.isFavorite ? 'favorite' : 'unfavorite';
            let recipeId = this.$route.params.id;
            axios.put('http://localhost:8080/'+this.user.username+'/'+favoriteState+'/'+recipeId)
              .then(response => {
                console.log(response.data);
              })
              .catch(error => {
                console.error('There was an error!', error);
              });
        }
    }
}
</script>

<style scoped>
    .category {
        display: flex;
        flex-direction: row;
    }
    .recipeShortInfo {
        margin-bottom: 10%;
        border-bottom:#312525 1px solid;
    }
    .category-time{
        display: flex;
        justify-content: space-between;
    }
    .time {
        margin-right: 50%;
    } 
    .rating h2{
        margin-bottom: 0;
    }
    .rating {
        margin: 2% 0 6% 0;
    }
    .recipe-name {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 10px;
        border-bottom: 1px solid #312525;
    }

    p {
        font-size: 14px;
        font-weight: 300;
        line-height: 17px;
        letter-spacing: 0em;
        text-align: left;
        margin-bottom: 10%;
    }
    h1 {
        color: #312525;
        font-size: 48px;
        font-weight: 700;
        line-height: 58px;
        letter-spacing: 0em;
        text-align: left;
        margin: 2% 0 2% 0;
    }
    h2 {
        color: #312525;
        font-size: 16px;
        font-weight: 700;
        line-height: 19px;
        letter-spacing: 0em;
        text-align: left;
        margin-bottom: 5%;
    }
    #stars {
        color: #E35733;
    }

</style>