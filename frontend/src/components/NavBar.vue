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
                    <button class="login" onclick="document.getElementById('Login').style.display='block'">LOG IN</button>
                        <div id="Login" class="formC">
                        <form class="formC-content animate" action="/login" method="post">
                            <div class="container">
                            <p>sign In</p>
                            <input class="text" type="text" placeholder="username/email" name="username" required>
                            <input class="psw" type="password" placeholder="password" name="psw" required>
                            <button class="sbt" type="submit">Enter</button>
                            </div>
                        </form>
                        </div>

                        <button class="signup" onclick="document.getElementById('Signup').style.display='block'">SIGN UP</button>
                        <div id="Signup" class="formC">
                        <form class="formC-content2 animate" action="/signup" method="post">
                            <div class="container">
                            <p>sign Up</p>
                            <input class="text" type="text" placeholder="username" name="username" required>
                            <input class="text" type="text" placeholder="email" name="email" required>
                            <input class="psw" type="password" placeholder="password" name="psw" required>
                            <input class="psw" type="password" placeholder="confirm password" name="cpsw" required>
                            <button class="sbt" type="submit">Enter</button>
                            </div>
                        </form>
                        </div>

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
    },
    mounted(){
        var formC = document.getElementById('Login');
        var formC2= document.getElementById('Signup');
        window.onclick = function(event) {
            if (event.target == formC) {
                formC.style.display = "none";
            }
            if (event.target == formC2) {
                formC2.style.display = "none";
            }

        }
    },
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
    /* border: 1px solid black; */

    margin: 0 0 0 57px;
    font-size: 36px;
    font-weight: 700;
}
h3 {
    /* border: 1px solid black; */

    /* border: 1px solid black; */

    margin-left: 19px;
    font-size: 20px;
}
ul {
    /* border: 1px solid rgb(199, 37, 137); */

    /* border: 1px solid rgb(199, 37, 137); */

    display: flex;
    align-items: center;
    list-style: none;
    margin: 0 25.5px 0 0;
    padding: 0;
}
li {
    /* border: 1px solid rgb(0, 0, 0); */

    /* border: 1px solid rgb(0, 0, 0); */

    margin: 0 20.5px;
    font-size: 15px;
}
a {
    /* border: 1px solid rgb(207, 23, 23); */

    /* border: 1px solid rgb(207, 23, 23); */

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

@media (min-width:0px) {
    .navbar {
        flex-direction: row; 
        height: auto;
        /* justify-content: center; Center items horizontally */
       /* border: 1px solid rgb(252, 32, 32); */

        /* padding: 10px 0;  */
    }
    h1{
        padding: 0; 
        margin: 0px 0vw 0px 4vw;
        font-size: 2.5vw; 
    }
    h3{
        padding: 0; 
        margin: 0 1vw 0 0;
        font-size: 1.6vw; 
    }
    ul {

        flex-direction: row; 
        flex-wrap: nowrap;
        margin: 3vw 0px 3vw  10vw; 
        
    }
    li {
        display: flex;

        /* border: 1px solid rgb(252, 32, 32); */
         
        margin: 0 1.5vw;
    }
    a {
       /* padding: 30px; */
       display: inline-block;

       /* padding-bottom: 1vw; */
        font-size: 1.25vw;
    }
}

input[type=text], input[type=password] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    box-sizing: border-box;
}

.sbt{
    background-color: #E35733;
    color: #FBF7EB;
    margin:12% 37%;
    border-radius: 10px;
    cursor: pointer;
    width: 83px;
    height: 42px;
    font-size: 16px;
    font-weight: 300;
    line-height: 19px;
    letter-spacing: 0em;
    text-align: center;
}

.sbt:hover {
    opacity: 0.8;
}
.sbt:active{
    scale: 110%;
}

.container {
    padding: 16px;
}

.container p{
    margin: 8% auto ;
    font-size: 48px;
    font-weight: 700;
    line-height: 58px;
    letter-spacing: 0em;
    text-align: center;
    color: #FBF7EB;
}

.text, .psw{
    width: 273px;
    height: 64px;
    border-radius: 10px;
    box-shadow: 0 2px 0px rgba(0, 0, 0, 0.7);
    background-color: #FBF7EB;
}

.formC {
    display: none; 
    position: fixed; 
    z-index: 1; 
    left: 0;
    top: 0;
    width: 100%; 
    height: 100%; 
    background-color: rgba(0,0,0,0.4); 
    padding-top: 60px;
}

.formC-content, .formC-content2{
    border-radius: 15px;
}
.formC-content{
    margin: 5% auto 15% auto; 
    background-color: #FBBC3B;
    width: 360px; 
    height: 400px;
}
.formC-content2{
    margin: 3% auto 10% auto; 
    background-color: #B3D0E8;
    width: 360px; 
    height: 569px;
}

.animate {
  -webkit-animation: animatezoom 0.6s;
  animation: animatezoom 0.6s
}

@-webkit-keyframes animatezoom {
  from {-webkit-transform: scale(0)} 
  to {-webkit-transform: scale(1)}
}
  
@keyframes animatezoom {
  from {transform: scale(0)} 
  to {transform: scale(1)}
}



@media (min-width:0px) {
    .navbar {
        flex-direction: row; 
        height: auto;
        /* justify-content: center; Center items horizontally */
       /* border: 1px solid rgb(252, 32, 32); */

        /* padding: 10px 0;  */
    }
    h1{
        padding: 0; 
        margin: 0px 0vw 0px 4vw;
        font-size: 2.5vw; 
    }
    h3{
        padding: 0; 
        margin: 0 1vw 0 0;
        font-size: 1.6vw; 
    }
    ul {

        flex-direction: row; 
        flex-wrap: nowrap;
        margin: 3vw 0px 3vw  10vw; 
        
    }
    li {
        display: flex;

        /* border: 1px solid rgb(252, 32, 32); */
         
        margin: 0 1.5vw;
    }
    a {
       /* padding: 30px; */
       display: inline-block;

       /* padding-bottom: 1vw; */
        font-size: 1.25vw;
    }
}

</style>