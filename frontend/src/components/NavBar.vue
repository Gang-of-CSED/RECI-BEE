<template>
    <div :class="{ 'hidden': scrolledDown, 'fixed': shouldFixNavbar }" class="navbar">
        <div><h1>RECI-BEE</h1></div>
        <ul>
            <li><a href="#">Browse Catalogue</a></li>
            <li><a href="#">Dish Of The Day</a></li>
            <li><a href="#">Random Dish</a></li>
            <li><a href="#">About Us</a></li>
            <li><h3>AbdElRahman Osama</h3></li>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'NavBar',
  data() {
    return {
      scrolledDown: false,
      lastScrollY: 0,
      scrollThreshold: 250,
    };
  },
  mounted() {
    window.addEventListener('scroll', this.handleScroll);
  },
  beforeDestroy() {
    window.removeEventListener('scroll', this.handleScroll);
  },
  computed: {
    shouldFixNavbar() {
        
      return !this.scrolledDown && window.scrollY > this.scrollThreshold;
    },
  },
  methods: {
    handleScroll() {
      const scrolledDown = window.scrollY > this.lastScrollY && window.scrollY > 116;
      this.scrolledDown = scrolledDown;
      this.lastScrollY = window.scrollY;
    },
  },
};
</script>


<style scoped>
.navbar {
    transition: transform 0.5s ease-in-out;
    background-color: #FBF7EB;
    height: 116px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    color: #312525;
}
h1 {
    margin: 0 0 0 57px;
    font-size: 36px;
    font-weight: 700;
}
h3 {
    margin-left: 19px;
    font-size: 20px;
}
ul {
    display: flex;
    justify-content: space-between;
    align-items: center;
    list-style: none;
    margin: 0 25.5px 0 0;
    padding: 0;
}
li {
    margin: 0 20.5px;
    font-size: 15px;
}
a {
    text-decoration: none;
    color : #312525;
}
.hidden {
  transform: translateY(-100%);
}
.fixed {
  position: fixed;
  top: 0;
  width: 100%;
  z-index: 1000;
}
</style>