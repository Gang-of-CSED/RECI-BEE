<template>
   <div class="list" @mousemove="refresh">
     <!-- <div class="slogan"><h6>Unlock The<br>Flavors Of The World</h6></div> -->

     <div class="recipies">
         <Card v-for="card in recipiesSubArray" :key="card.name" :card-data="card" @toggle-favorite="toggleFavorite" @toggle-save="toggleSave" class="cards" :user="user" />
     </div>
     <div class="pagin-bar">
        <button class="pagin-btn" @click="getPre"> &lt; </button>
        <span v-for="(item, index) in new Array(pageNums)" :key="index">
            <button class="pagin-btn" @click="goToPage(index+1)" :style="{ backgroundColor: index + 1 === currentPage ? '#FBBC3B' : '#E35733' }">{{ index+1 }}</button>
        </span>
        <button class="pagin-btn" @click="getNext"> &gt; </button>
        <span> recipes in one page:
          <select id="inPage" name="choices" v-model="nums" @change="changeNums">
              <option value="8">8</option>
              <option value="16">16</option>
              <option value="32">32</option>
              <option value="64">64</option>
              <option value="128">128</option>
          </select>
          </span>

    </div>

     
   </div>
</template>

<script>

import Card from './Card.vue'; 
import axios from 'axios';

export default {
  name: 'List.vue',
  components: {
    Card,
  },
  props: {
    recipiesArray:Array,
    user:Object,
    },
  data() {
    return {
      // recipiesArray: [],
      recipiesSubArray: [],
      pageNums: 0,
      currentPage: 1,
      nums: 8,
      start: 0, 
      end: 10,
      begin: true,
    };
  },
  methods: {

    toggleFavorite(card) {
        const index = this.recipiesArray.indexOf(card);
      
        this.recipiesArray[index].isFavorite=!this.recipiesArray[index].isFavorite;
        console.log(this.recipiesArray)
      
        if(this.recipiesArray[index].isFavorite){
          this.sendStateToBack(this.user.username,'favorite',index);
        }
        else{
          this.sendStateToBack(this.user.username,'unfavorite',index);
        }

    },
    toggleSave(card) {
      const index = this.recipiesArray.indexOf(card);
    
      this.recipiesArray[index].isSave=!this.recipiesArray[index].isSave;
      console.log(this.recipiesArray)
    
      if(this.recipiesArray[index].isSave){
        this.sendStateToBack(this.user.username,'save',index);
      }
      else{
        this.sendStateToBack(this.user.username,'unsave',index);
      }
    },
    sendStateToBack(userId,favoriteState,recipeId){
      // console.log('http://localhost:8080/'+userId+'/'+favoriteState+'/'+recipeId);
      axios.put('http://localhost:8080/'+userId+'/'+favoriteState+'/'+recipeId)
              .then(response => {
                console.log(response.data);
              })
              .catch(error => {
                console.error('There was an error!', error);
              });
    },
    getNext() {
      if(this.end >= this.recipiesArray.length){return;}
      this.start = this.end;
      this.end = Math.min(this.end + this.nums, this.recipiesArray.length);
      this.currentPage += 1;
      this.updateSubArray();
    },
    getPre() {
      this.start = Math.max(0, this.start - this.nums);
      this.end = Math.min(this.start + this.nums, this.recipiesArray.length);
      if(this.currentPage > 1)this.currentPage -= 1;
      this.updateSubArray();
    },
    updateSubArray() {
      this.recipiesSubArray = this.recipiesArray.slice(this.start, this.end);
      this.pageNums = this.calcPageNums();
    },
    goToPage(p){
      this.currentPage = p;
      this.start = Math.min((p - 1) * this.nums, this.recipiesArray.length);
      this.end = Math.min(this.start + this.nums, this.recipiesArray.length);
      this.updateSubArray();
    },
    calcPageNums(){
      return  this.pageNums = Math.ceil(this.recipiesArray.length / this.nums);
    },
    changeNums(){
        this.goToPage(1);
        this.nums = parseInt(this.nums);
        console.log(this.nums);
    },
    refresh(){
      if(this.begin){
        this.begin = false;
        this.changeNums();
      }
    },
   

  },
  mounted(){
  //   // fetch('http://localhost:3000/recipiesArray')
  //   // .then(response => response.json()) // Convert the response to JSON
  //   // .then(data => {
  //   //   this.recipiesArray = data;
  //   // })
  //   // .catch(err => console.error("Error fetching recipes:", err));
  },
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
.pagin-btn{
    padding: 8px;
    margin: 2px;
    border-radius: 20px;
    font-size: 1em;
    cursor: pointer;
    background-color: #E35733;
    color: #FBF7EB;
    width: auto;
}
.pagin-bar{
  margin-bottom: 10%;
}
#inPage{
  width: auto;
  height: auto;
  margin-left: 10px;
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