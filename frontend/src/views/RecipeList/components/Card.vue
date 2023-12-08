<template>

  <div class="Card" @click="redirectToCard">
  <!-- <div class="Card"> -->
         <img v-if="cardData.isTheRandom" class="randomDish" :src="randomDishPath" />
    
     
     <div class="recipeImg">
         <img class="custom-image" :src="imagePath" />
     </div>
     

     <div class="recipefooter">

        <div class="recipeName"> {{cardData.name}}</div>  
         <img class="heart" :src="cardData.isFavorited ? favPath : notFavPath" alt="Heart" @click="toggleFavorite" />
  
     </div>

  </div>

</template>

<script>
export default {
  name: 'Card',
  props: {
    cardData: Object, 
  },

  data() {
      return {
      imagePath:null,      
      notFavPath:require('@/assets/notFav.svg'),
      favPath:require('@/assets/fav.svg'),
      randomDishPath:require('@/assets/randomDish.svg'),
      };
  } ,

  mounted(){
   this.imagePath = "http://localhost:8080"+this.cardData.cover;
  },

  methods: {

      toggleFavorite() {
        event.stopPropagation();
        this.$emit('toggle-favorite', this.cardData);
      },

      // redirectToCard() {
      // const cardUrl = `http://localhost:8080/${this.cardData.id}`;
      // window.location.href = cardUrl;
      // }
       redirectToCard() {
        // Use Vue Router to navigate to the Details component with the card's ID
        this.$router.push({ name: 'recipe-detail', params: { id: this.cardData.id } });
      }
  },   
}
</script>

<style scoped>
.randomDish{
  position: absolute;
  top: 0; 
  left: 0; 
  width: 2.78vh; 
  height: 4.4445vh; 
  transform: translate(-50%, -50%); /* Center the star in the corner */
}
.Card{
  display: flex;
  align-items: center;
  flex-direction: column;
  /* margin: 20px; */
  position: relative;
  background: #FBF7EB;
  border: 1.5px solid #312525;
  /* border: 1.5% solid #312525;; */
  width: 15.625vw;
  height: 15.625vw;
  border-radius:10%;
  border-bottom:5px solid #312525;
  }
.recipeImg{
  margin-top:0.69445vw ;
  width: 14.236vw;
  height:  10.4vw;
  overflow: hidden;
  /* border-radius:0.695vw; */
  /* border-radius:10px; */
  /* padding-top: 62.5%; 100% / 1.6 = 62.5% */
  border-radius: 10%;
}
  .custom-image {
    width: 100%;
    height: 100%;
    object-fit: cover; 
  }
.recipefooter{
  height: 4.445vw;
  width: 14.236vw;
  justify-content: center; /* Center horizontally */
  align-items: center; 
  /* margin: 15px 7px; */
  /* border:1px solid #312525; */
  display: flex;
}
.recipeName{
  /* font-family: 'Inter'; */
  font-style: normal;
  font-weight: 700;
  font-size: 1.111vw;

  text-align: left;
  width: 85%;
  color: #312525;
}
.heart {
  position: relative;
  width: 1.806vw; /* Set the desired width */
  height: 2.45vh; /* Set the desired height */
  margin-top: 0px;
}
@media (max-width:426px) {
  /* .Card{

  border-bottom:3.5px solid #312525;
  } */
  /* .recipeImg{
    margin-top:0.5vh ;

  } */
}
@media (max-width:426px) {
  .Card{

    border: 1px solid rgba(49, 37, 37, 0.4);
      border-bottom:3px solid #312525;
  }
}

</style>
