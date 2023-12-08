<template>

    <div class="recipe-name">
        <h1>{{ name }}</h1>
        <v-icon size="42" color="#E35733" id="heart" @click="toggleFavorite">
            {{ isFavorite ? 'mdi-heart' : 'mdi-heart-outline' }}
        </v-icon>
    </div>
    <p>{{ description }}</p>
    <div class="recipe-summary">
        <div class="category">
            <h2>Categories:</h2>    
            <p>{{ category }}</p>
        </div>
        <div class="time">
            <h2>Time:</h2>
            <p>{{ time }}</p>
        </div>
        <div class="rating">
            <h2>Ratings:</h2>
            <v-rating v-model="rate" id="stars" hover clearable size="27.2"></v-rating>
        </div>
    </div>


</template>

<script>
export default {
    name: "RecipeShortInfo",
    props:[
        'name',
        'description',
        'category',
        'time',
        'rate',
        'isFavorite',
    ],
    methods: {
        toggleFavorite(){
            this.$emit('toggleFavorite')
        }
    }
}
</script>

<style scoped>
    .recipe-summary {
        display: flex;
        justify-content: space-between;
        margin: 5% 0 5% 0;
        /* margin-bottom: 10px; */
        flex-wrap: wrap;
        /* align-items: center; */
    }
    .category {
        margin-right: 20%;
    }
    .time {
        margin-right: 45%;
    }
    .rating {
        margin-top: 3%;
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
        color: #E35733
    }

</style>