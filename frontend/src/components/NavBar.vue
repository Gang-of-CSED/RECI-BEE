<template>
    <div class="navbar">
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
}
</script>

<style scoped>
.navbar {
    background-color: #FBF7EB;
    height: 116px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    color: #312525;
}
h1 {
    margin-left: 57px;
    font-size: 36px;
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


</style>