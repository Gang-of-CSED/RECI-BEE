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
  width: 40px; 
  height: 40px; 
  transform: translate(-50%, -50%); /* Center the star in the corner */
}
.Card{
  display: flex;
  align-items: center;
  flex-direction: column;
  margin: 20px;
  position: relative;
  background: #FBF7EB;
  border: 1.5px solid #312525;
  width: 225px;
  height: 225px;
  border-radius:15px;
  border-bottom:5px solid #312525;
  }
.recipeImg{
  margin-top:12px ;
  width: 205px;
  height: 150px;
  overflow: hidden;
  border-radius:10px;
}
  .custom-image {
    width: 100%;
    height: 100%;
    object-fit: cover; 
  }
.recipefooter{
  height: 50px;
  width: 205px;
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
  font-size: 16px;

  text-align: left;
  width: 85%;
  color: #312525;
}
.heart {
  position: relative;
  width: 26px; /* Set the desired width */
  height: 22px; /* Set the desired height */
  margin-top: 0px;
}


</style>
