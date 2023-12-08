<template>
    <div class="recipe-review">
        <!-- RecipeReview -->
        <div class="show-recipeShortInfo">
            <div v-for="recipe in recipeList" :key="recipe" class="contribution">
                <RecipeShortInfo :recipe="recipe" />
            </div>
        </div>



        <div class="new-contribution">
            <h1>Comments</h1>
            <v-rating v-model="newContribution.rate" id="stars" hover clearable size="27.2"></v-rating>
        </div>
        <input v-model="newContribution.comment" id="add-contribution" type="text" placeholder="Add a comment" @keyup.enter="addContribution"/>


        <div class="show-contributions">
            <div v-for="contribution in contributions" :key="contribution" class="contribution">
                <RecipeContribution :contribution="contribution" />
            </div>
        </div>

</div>

</template>

<script>

import RecipeContribution from './RecipeContribution.vue';
import RecipeShortInfo from './RecipeShortInfo.vue';

export default{
    name: 'RecipeReview',
    props: ['recipe', 'contributionList'],
    components:{
        RecipeContribution,
        RecipeShortInfo,
    },
    data(){
        return{
            recipeList: [{
                name: 'Bashamel Pasta',
                description: 'Macarona Bechamel, as it\'s known in the Middle East, is a Bechamel pasta bake that takes the win for the best pasta casserole ever! It\'s an Egyptian dish that is loved by the family and its pure comfort food. I mean, butter, flour, pasta, sauce... what else do you expect.',
                category: 'breakfast, lunch, dinner',
                time: '+1 Hours',
                rate: 4,
                isFavorite: true,
            }],
            contributions: [
                {
                    userName: 'Ahmad El Sayed:',
                    rate : 4,
                    comment: 'What a wonderful recipe. i would give it 20/20.',
                },
                {
                    userName: 'Abdelrahman Bassam:',
                    rate : 5,
                    comment: 'I love this recipe, my son really enjoyed making it! One of his favourite.',
                },
                {
                    userName: 'Mourad Mahgoub:',
                    rate : 4,
                    comment: 'Absolutely a hit with my children!',
                },
                {
                    userName: 'Abdelrahman Osama:',
                    rate : 3,
                    comment: 'This is one of my favorite dishes! This recipe is so easy and the dish turns out delicious. Highly recommend!',
                },
                {
                    userName: 'Deif:',
                    rate : 4,
                    comment: 'We’ve been looking for a macarona bechamel recipe that really has the flavor of home for months now! This is definitely up their with the best recipes we’ve tried.',
                },
        ],
            newContribution: {userName:'', rate:0, comment:''},
        }
    },
    methods: {
        addContribution(){
            if(this.newContribution.comment.length > 0 && this.newContribution.rate > 0){
                this.contributions.push({
                userName: this.newContribution.userName,
                rate: this.newContribution.rate,
                comment: this.newContribution.comment,
                });
                this.newContribution.userName = "";
                this.newContribution.rate = 0;
                this.newContribution.comment = "";
            }
        }
    }
}

</script>

<style scoped>
/* .recipe-review {
    
} */
#add-contribution {
    background: #3125251A;
    border-bottom: 1px solid #31252580;
    width: 100%;
    height: 10%;
    top: 610px;
    left: 777px;
    border: 0px, 0px, 1px, 0px;
    margin-bottom: 7.5%;
}
#add-contribution::placeholder {
    color: #312525;
    font-size: 14px;
}
.new-contribution {
    display: flex;
    justify-content: space-between;
    align-content: center;
    margin-bottom: 10px;
}
#stars{
    color: #E35733
}
h1{
    font-size: 16px;
    font-weight: 700;
    line-height: 19px;
    letter-spacing: 0em;
    text-align: left;

}
</style>