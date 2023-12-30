<template>
    
   <v-toolbar class="searchBar">
        <v-row  class="searchBar">
              
                
                <v-col cols="3" class="barElement">
                    <v-select
                    v-model="sortLogic"
                    :items="['Rate','Calories','Time']"
                    label="Sort by"
                    class="mx-2"
                    clearable
                    ></v-select>
                </v-col>
                
                <v-col cols="3" class="barElement">
                    <v-select
                    v-model="searchLogic"
                    :items="['Name', 'Ingredients']"
                    label="Search by"
                    class="mx-2"
                    
                    ></v-select>
                </v-col>
                
                <v-col cols="2" class="barElement" v-show="searchLogic?.includes('Name')">
                    <v-text-field
                    v-model="searchWord"
                    placeholder="Name"
                    @input="searchRecipes"
                    ></v-text-field>
                </v-col>
                <v-col cols="2"  class="barElement" v-show="searchLogic?.includes('Ingredients')">
                    <v-text-field
                    v-model="searchWord"
                    @input="searchRecipes"
                    placeholder="Ingredients"
                    ></v-text-field>
                </v-col>
                
                
                <div class="clearButton" v-show="sortLogic || searchWord">
                    <v-btn icon @click="clear">
                        <v-icon size="35">mdi-close</v-icon>
                    </v-btn>
                </div>
            </v-row>
        </v-toolbar>
        
       
 
  </template>
  
  <script>
  export default {
    name: 'SearchComponent',
    data() {
      return {
        searchWord: '',
        searchLogic: null,
        sortLogic:null,
        showMenu: false,
      };
    },
    methods: {
        clear() {
            this.searchWord= '',
            this.searchLogic= null,
            this.sortLogic=null,
            this.showMenu= false
            this.searchRecipes(),
            this.sortRecipes()
      },
      searchRecipes() {
      // Emit the search word and type to the parent
      if(this.searchLogic==null)
          this.searchLogic=''
      this.$emit('search-event',null,this.searchWord,this.searchLogic.toLowerCase() );
    },
    sortRecipes() {
      // Emit the sort logic to the parent
       if(this.sortLogic==null)
          this.sortLogic=''
      this.$emit('sort-event', this.sortLogic.toLowerCase());
    }
    },
    watch: {
       
        sortLogic(newValue, oldValue) {
        if (newValue !== oldValue) {
            this.sortRecipes();
        }
        }
    },
    mounted() {
     
    }
  };
  </script>
  
  <style>
  .pa-4 {
    width: 600px;
    height: 600px;
    background: #FBF7EB;
  }
  .menuButton{
    display: flex;
    background: red;
  }
  .clearButton{
    margin-top: 2.3vh;
    background: #FBF7EB;

  }
  .barElement{
    margin: top 10px; ;
   padding-top: 10px;
  }
  .searchBar{
    background: #f6f2e6;
    margin-top: 2vh;
    margin-left: 4.5vw;
    border:1px solid rgb(110, 39, 39);
  }
  </style>
  