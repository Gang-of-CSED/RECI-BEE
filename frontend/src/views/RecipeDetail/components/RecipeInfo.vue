<template>
    <div class="recipe-info">
        <div class="recipeImg">
            <img class="Img" :src="recipe.cover" alt="recipe" />
        </div>
        <div class="Ingredients">
            <div class="intText">Ingredients:</div>
            <div class="t1" v-html="recipe.ingredients"></div>
        </div>
        <div class="breakLine">
            <hr>
        </div>
        <div class="Steps">
            <div class="stepText">Steps:</div>
            <div class="t2" v-html="recipe.steps"></div>
        </div>
    </div>
</template>

<script>
    export default {
        props:['recipe'],
    }
</script>

<style scoped>
.recipe-info {
    width: 50%;
    height: 100%;
    background: #FBF7EB;
;
}
.recipeImg{
    width: 90%;
    height: 362px;
    border: 1px #FBF7EB solid;
    border-radius: 15px;
    margin-top: 50px;
    margin-left: 5%;

}
.Img{
    width: 100%;
    height: 100%;
    overflow: hidden;
}

.Ingredients{
    width: 90%;
    height: auto;
    border: #FBF7EB 1px solid;
    margin-top: 37px;
    margin-left: 5%;

}

.breakLine{
    width: 90%;
    height: 1px;
    border:#312525 1px solid;
    margin-top: 46px;
    margin-left: 5%;
}

.Steps{
    width: 90%;
    
    border: #FBF7EB 1px solid;
    margin-top: 37px;
    margin-left: 5%;
    margin-bottom: 54px;
}

.intText,.stepText{
    color: #312525;
    font-size: 18px;
    line-height: 19.36px;
    font-weight: 700;
    margin-bottom: 22px;
}

.t1,.t2{
    color: #312525;
    font-size: 14px;
    line-height: 19px;
    letter-spacing: 0.098em;
    text-align: left;
    font-weight: 400;

}
</style>