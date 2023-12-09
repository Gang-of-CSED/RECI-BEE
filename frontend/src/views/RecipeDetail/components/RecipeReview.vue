<template>
    <div class="recipe-review">
        <div class="show-recipeShortInfo">
                <RecipeShortInfo :recipe="recipe" />
        </div>

        <div class="new-contribution">
            <h1>Comments</h1>
            <v-rating v-model="newContribution.rating" id="stars" hover clearable size="27.2"></v-rating>
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
    props: ['recipe'],
    components:{
        RecipeContribution,
        RecipeShortInfo,
    },
    data(){
        return{
            contributions: [],
            newContribution: {user:'user', rating:0, comment:''},
        }
    },
    mounted() {
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
                this.newContribution.user = data.username;
            })
        }
        this.fetchContributions();
    },
    methods: {
        async fetchContributions(){
            const id = this.$route.params.id 
            try {
                this.contributions  = await (await fetch(`http://localhost:8080/comments/${id}`)).json();
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        },
        addContribution(){
            if(this.newContribution.comment.length > 0 && this.newContribution.rating > 0){
                const id = this.$route.params.id 
                fetch(`http://localhost:8080/comments`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        recipeId: id,
                        user: this.newContribution.user,
                        rating: this.newContribution.rating,
                        comment: this.newContribution.comment,
                    }),
                })
                .then(async () => {
                    this.contributions = await (await fetch(`http://localhost:8080/comments/${id}`)).json();
                })
                this.newContribution.user = "user";
                this.newContribution.rating = 0;
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
    height: 100px;
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