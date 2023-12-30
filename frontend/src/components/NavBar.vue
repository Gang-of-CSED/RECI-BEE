<template>
    <div class="navbar">
        <div>
            <a href="/home"><h1>RECI-BEE</h1></a>
        </div>
        <ul>
            <li><a href="/recipes">Browse Catalogue</a></li>
            <li><a href="/recipe/1">Dish Of The Day</a></li>
            <li><a :href="randomDishLink()">Random Dish</a></li>
            <li><a href="/home">About Us</a></li>
            <li>
                <h3 v-if="logged">{{ username }}</h3>
                <div v-else class="log">
                    <button class="login" onclick="document.getElementById('Login').style.display='block'">LOG IN</button>
                    <div id="Login" class="formC">
                        <form class="formC-content animate">
                            <div class="container">
                                <p>sign In</p>
                                <input v-model="loginForm.username" class="text" type="text" placeholder="username"
                                    name="username" required>
                                <div class="input-errors" v-for="error of v$.loginForm.username.$errors" :key="error.$uid">
                                    <div class="error-msg">{{ error.$message }}</div>
                                </div>
                                <input v-model="loginForm.password" class="psw" type="password" placeholder="password"
                                    name="psw" required>
                                <div class="input-errors" v-for="error of v$.loginForm.password.$errors" :key="error.$uid">
                                    <div class="error-msg">{{ error.$message }}</div>
                                </div>
                                <button class="sbt" @click="handleLogIn">Enter</button>
                            </div>
                        </form>
                    </div>

                    <button class="signup" onclick="document.getElementById('Signup').style.display='block'">SIGN
                        UP</button>
                    <div id="Signup" class="formC">
                        <form class="formC-content2 animate">
                            <div class="container">
                                <p>sign Up</p>

                                <input v-model="signupForm.username" class="text" type="text" placeholder="username"
                                    name="username" required>
                                <div class="input-errors" v-for="error of v$.signupForm.username.$errors" :key="error.$uid">
                                    <div class="error-msg">{{ error.$message }}</div>
                                </div>
                                <input v-model="signupForm.name" class="text" type="text" placeholder="name" name="name"
                                    required>
                                <div class="input-errors" v-for="error of v$.signupForm.name.$errors" :key="error.$uid">
                                    <div class="error-msg">{{ error.$message }}</div>
                                </div>
                                <input v-model="signupForm.password" class="psw" type="password" placeholder="password"
                                    name="psw" required>
                                <div class="input-errors" v-for="error of v$.signupForm.password.$errors" :key="error.$uid">
                                    <div class="error-msg">{{ error.$message }}</div>
                                </div>
                                <!-- <input class="psw" type="password" placeholder="confirm password" name="cpsw" required> -->
                                <button class="sbt" @click="handleSignUp">Enter</button>
                            </div>
                        </form>
                    </div>
                    <v-snackbar v-model="snackbar" :timeout="2000">
                        {{snackbarText}}

                        <template v-slot:actions>
                            <v-btn color="blue" variant="text" @click="snackbar = false">
                                Close
                            </v-btn>
                        </template>
                    </v-snackbar>

                </div>
            </li>
        </ul>
    </div>
</template>

<script scoped>
import { useVuelidate } from '@vuelidate/core'
import { required, minLength, maxLength, alpha, alphaNum } from '@vuelidate/validators'
export default {
    setup() {
        return { v$: useVuelidate() }
    },
    data() {
        return {
            name: 'NavBar',
            username: "Abdelrhman Deif",
            logged: false,
            signupForm: {
                username: '',
                name: '',
                password: '',
                // cpassword:''
            },
            loginForm: {
                username: '',
                password: ''
            },
            snackbar: false,
            snackbarText: ''

        }
    },
    validations() {
        return {
            signupForm: {
                username: { required, minLength: minLength(3), maxLength: maxLength(16), alphaNum },
                name: { required, minLength: minLength(3), maxLength: maxLength(16), alpha },
                password: { required, minLength: minLength(8), maxLength: maxLength(16) },
                // cpassword: { required }
            },
            loginForm: {
                username: { required, minLength: minLength(3), maxLength: maxLength(16), alphaNum },
                password: { required, minLength: minLength(8), maxLength: maxLength(16) },
            }
        }
    },
    methods: {
        randomDishLink() {
            const randomRecipeNumber = Math.floor(Math.random() * 30);
            return `/recipe/${randomRecipeNumber}`;
        },
        async handleSignUp(e) {
            e.preventDefault();
            console.log(this.signupForm)
            const result = await this.v$.signupForm.$validate()
            console.log("result", result);
            if (result) {
                fetch(`http://localhost:8080/register`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        username: this.signupForm.username,
                        name: this.signupForm.name,
                        password: this.signupForm.password,
                    }),
                })
                    .then(response => response.json())
                    .then((data) => {
                        console.log("Dataaaa",data)
                        document.getElementById('Signup').style.display = 'none';
                        if (!data) {
                            this.snackbar = true
                            this.snackbarText = "Username already exists"
                            return
                        }
                        
                        fetch(`http://localhost:8080/login`, {
                            method: 'POST',
                            // send data as form not as stringified JSON
                            headers: {
                                'Content-Type': 'application/json',
                            },
                            body: JSON.stringify({
                                username: this.signupForm.username,
                                password: this.signupForm.password,
                            }), // body data type must match "Content-Type" header

                        })
                            .then(response => response.text())
                            .then(async (data) => {
                                console.log(data)
                                document.getElementById('Login').style.display = 'none';
                                localStorage.setItem('token', data);
                                this.$router.replace({ name: 'recipe-list' });
                                if(this.$route.name == 'recipe-list')
                                    this.$router.go();
                            })
                    }).catch(err => {
                        this.snackbar = true
                        this.snackbarText = "Username already exists"
                    })
            }
        },
        async handleLogIn(e) {
            e.preventDefault();
            const result = await this.v$.loginForm.$validate()
            console.log("result", result);
            console.log("valliiidd", this.v$.loginForm.username.$errors)
            console.log("password", this.v$.loginForm.password.$errors)
            if (result) {
                fetch(`http://localhost:8080/login`, {
                    method: 'POST',
                    // send data as form not as stringified JSON
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        username: this.loginForm.username,
                        password: this.loginForm.password,
                    }), // body data type must match "Content-Type" header

                })
                    .then(response => response.text())
                    .then(async (data) => {
                        console.log("login", data)
                        if (data) {
                            document.getElementById('Login').style.display = 'none';
                            localStorage.setItem('token', data);
                            this.$router.push({ name: 'recipe-list' });
                            if(this.$route.name == 'recipe-list')
                                this.$router.go();
                        }else{
                            this.snackbar = true
                            this.snackbarText = "Wrong username or password"
                        }
                    })
            }
        }
    },
    mounted() {
        console.log(localStorage.getItem('token'))
        if (localStorage.getItem('token')) {
            fetch("http://localhost:8080/info", {
                headers: {
                    Authorization: `${localStorage.getItem('token')}`
                }
            }).then(response => response.json())
                .then(data => {
                    console.log("navbar", data)
                    this.username = data.username
                    this.logged = true;
                }).catch(err => {
                    console.log(err)
                })

        }
        var formC = document.getElementById('Login');
        var formC2 = document.getElementById('Signup');
        window.onclick = function (event) {
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

    margin: 0 20.5px;
    font-size: 15px;
}

a {
    /* border: 1px solid rgb(207, 23, 23); */

    /* border: 1px solid rgb(207, 23, 23); */

    text-decoration: none;
    color: #312525;
}

.log {
    display: flex;
    justify-content: space-between;
    margin-right: 5px;


}

.login,
.signup {
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

.login {
    border: #E35733 1px solid;
    color: #E35733;
}

.signup {
    background-color: #E35733;
    color: #FBF7EB;

}

.login:hover,
.signup:hover {
    opacity: 70%;
}

.login:active,
.signup:active {
    scale: 110%;
}

.log {
    display: flex;
    justify-content: space-between;
    margin-right: 5px;


}

.login,
.signup {
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

.login {
    border: #E35733 1px solid;
    color: #E35733;
}

.signup {
    background-color: #E35733;
    color: #FBF7EB;

}

.login:hover,
.signup:hover {
    opacity: 70%;
}

.login:active,
.signup:active {
    scale: 110%;
}

.input-errors {
    color: red;
    font-size: 12px;
    margin: 0 0 0 10px;
}

@media (min-width:0px) {
    .navbar {
        flex-direction: row;
        height: auto;
        /* justify-content: center; Center items horizontally */
        /* border: 1px solid rgb(252, 32, 32); */

        /* padding: 10px 0;  */
    }

    h1 {
        padding: 0;
        margin: 0px 0vw 0px 4vw;
        font-size: 2.5vw;
    }

    h3 {
        padding: 0;
        margin: 0 1vw 0 0;
        font-size: 1.6vw;
    }

    ul {

        flex-direction: row;
        flex-wrap: nowrap;
        margin: 3vw 0px 3vw 10vw;

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

input[type=text],
input[type=password] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    box-sizing: border-box;
}

.sbt {
    background-color: #E35733;
    color: #FBF7EB;
    margin: 12% 37%;
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

.sbt:active {
    scale: 110%;
}

.container {
    padding: 16px;
}

.container p {
    margin: 8% auto;
    font-size: 48px;
    font-weight: 700;
    line-height: 58px;
    letter-spacing: 0em;
    text-align: center;
    color: #FBF7EB;
}

.text,
.psw {
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
    background-color: rgba(0, 0, 0, 0.4);
    padding-top: 60px;
}

.formC-content,
.formC-content2 {
    border-radius: 15px;
}

.formC-content {
    margin: 5% auto 15% auto;
    background-color: #FBBC3B;
    width: 360px;
    height: 400px;
}

.formC-content2 {
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
    from {
        -webkit-transform: scale(0)
    }

    to {
        -webkit-transform: scale(1)
    }
}

@keyframes animatezoom {
    from {
        transform: scale(0)
    }

    to {
        transform: scale(1)
    }
}
</style>