<template>
   <div class="list">

     <!-- <div class="slogan"><h6>Unlock The<br>Flavors Of The World</h6></div> -->

     <div class="recipies">
         <Card v-for="card in recipiesArray" :key="card.name" :card-data="card" @toggle-favorite="toggleFavorite" class="cards" />
     </div>

   </div>
</template>

<script>

import Card from './Card.vue'; 

export default {
  name: 'List.vue',
  components: {
    Card, 
  },
  props: {
    recipiesArray:Array,
    },
  data() {
    return {
      // recipiesArray: [],
    };
  },
  methods: {

    toggleFavorite(card) {
        const index = this.recipiesArray.indexOf(card);
      
        this.recipiesArray[index].isFavorited=!this.recipiesArray[index].isFavorited;
    }

  },
  // mounted(){
  //   // fetch('http://localhost:3000/recipiesArray')
  //   // .then(response => response.json()) // Convert the response to JSON
  //   // .then(data => {
  //   //   this.recipiesArray = data;
  //   // })
  //   // .catch(err => console.error("Error fetching recipes:", err));
  // }
};
</script>
<style scoped>
/* .list{
    display: flex;
    width: 1000;
} */
   .recipies{
    /* padding:3% ; */
    padding-top: 5%;
    padding-left: 5%;
    flex-wrap: wrap;
    display: flex;
    /* max-height: 550px;   */
    overflow-y: auto; /* Enable vertical scrolling when content overflows */
    padding-bottom:7% ;
      /* width: 1000;   */
    
}
.cards{
  margin: 0.8333vw;
  /* flex: 0 0 calc(25% - 24px);  */
  /* flex: 0 0 225;  */
  box-sizing: border-box; 
 
}
/* .slogan{
  
  display: flex;
 
  justify-content: flex-end; 
  align-items: center; 
  height: 175px;
  background:#FBBC3B;
  
}
.slogan h6 {
  text-align: right;
  color: #FBF7EB;
  font-style: normal;
  font-size: 48px;
  font-weight: 700;

  margin-right:30px ;
} */
</style>