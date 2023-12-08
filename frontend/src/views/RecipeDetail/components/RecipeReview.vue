<template>
    <div class="recipe-review">
        <!-- RecipeReview -->
        <div class="show-comments">
            <div v-for="recipeInfo in recipesInfo" :key="recipeInfo" class="comment">
                <RecipeShortInfo :name="recipeInfo.name" :description="recipeInfo.description" :isFavorite="isFavorite" :rate="recipeInfo.rate" :time="recipeInfo.time" :category="recipeInfo.category" />
            </div>
        </div>



        <div class="new-comment">
            <h1>Comments</h1>
            <v-rating v-model="newComment.rate" id="stars" hover clearable size="27.2"></v-rating>
        </div>
        <input v-model="newComment.comment" id="add-comment" type="text" placeholder="Add a comment" @keyup.enter="addComment"/>
        <!-- <button @click="addComment">Add Comment</button> -->


        <div class="show-comments">
            <div v-for="comment in comments" :key="comment" class="comment">
                <RecipeComment :userName="comment.userName" :comment="comment.comment" :rate="comment.rate" />
            </div>
        </div>

</div>

</template>

<script>

import RecipeComment from './RecipeComment.vue';
import RecipeShortInfo from './RecipeShortInfo.vue';

export default{
    name: 'RecipeReview',
    components:{
        RecipeComment,
        RecipeShortInfo,
    },
    data(){
        return{
            recipesInfo: [{
                name: 'Bashamel Pasta',
                description: 'Macarona Bechamel, as it\'s known in the Middle East, is a Bechamel pasta bake that takes the win for the best pasta casserole ever! It\'s an Egyptian dish that is loved by the family and its pure comfort food. I mean, butter, flour, pasta, sauce... what else do you expect.',
                category: 'breakfast, lunch, dinner',
                time: '+1 Hours',
                rate: 4,
                isFavorite: true,
            }],
            comments: [
                {
                    userName: 'mourad',
                    rate : 3,
                    comment: 'jdhjdhjhdjhekekkekkekkekekkekkekkekkekkekke',
                },
                {
                    userName: 'mourad',
                    rate : 3,
                    comment: 'jdhjdhjhdjhekekkekkekkekekkekkekkekkekkekke',
                }
        ],
            newComment: {userName:"", rate:0, comment:""},
        }
    },
    methods: {
        addComment(){
            this.comments.push({
                userName: this.newComment.userName,
                rate: this.newComment.rate,
                comment: this.newComment.comment,
            });
            this.newComment.userName = "";
            this.newComment.rate = 0;
            this.newComment.comment = "";
        }
    }
}

</script>

<style scoped>
/* .recipe-review {
    
} */
#add-comment {
    background: #3125251A;
    border-bottom: 1px solid #31252580;
    width: 100%;
    height: 10%;
    top: 610px;
    left: 777px;
    border: 0px, 0px, 1px, 0px;
    margin-bottom: 7.5%;
}
#add-comment::placeholder {
    color: #312525;
    font-size: 14px;
}
.new-comment {
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