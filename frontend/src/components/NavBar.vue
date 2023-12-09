<template>
    <div class="navbar">
        <div><h1>RECI-BEE</h1></div>
        <ul>
            <li><a href="#">Browse Catalogue</a></li>
            <li><a href="#">Dish Of The Day</a></li>
            <li><a href="#">Random Dish</a></li>
            <li><a href="#">About Us</a></li>
            <li>
                <h3 v-if="logged">{{ username }}</h3>
                <div v-else class="log">
                    <button class="login">LOG IN</button>
                    <button class="signup">SIGN UP</button>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    data(){
        return{
            name: 'NavBar',
            username:"Abdelrhman Deif",
            logged: false,
        }
    }
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
.log{
    display: flex;
    justify-content: space-between;
    margin-right: 5px;
   

}
.login, .signup{
    margin-right: 17px;
    width: 105.26px;
    height: 35.09px;
    font-size: 16px;
    font-weight: 700;
    line-height: 19px;
    letter-spacing: 0em;
    text-align: center;
    border-radius: 10px;
}
.login{
    border: #E35733 1px solid;
    color: #E35733;
}
.signup{
    background-color:#E35733;
    color: #FBF7EB;

}

.login:hover, .signup:hover{
    opacity: 70%;
}
.login:active, .signup:active{
    scale: 110%;
}

</style>