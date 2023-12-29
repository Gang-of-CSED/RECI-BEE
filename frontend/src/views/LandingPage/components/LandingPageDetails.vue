<template>
    <div class="LandingPage">
        <div class="Page1">
            <div class="rec1">
                <div class="text1">
                    <div class="subtext1">
                        <p>
                            <span class="whiteText">Unlimited Inspiration <br>for </span><span class="redText">Every
                                Meal.</span>
                            <span class="discover"><br>Discover new flavors and find your favorites. Browse<br>over 100
                                recipes from around the world right at your<br> fingertips.
                                Filter by cuisine, ingredient or diet to find<br>the perfect dish for any meal.</span>
                        </p>
                        <p class="discover">

                        </p>
                    </div>
                    <div class="btn1">
                        <button class="catalogue">catalogue</button>
                    </div>
                </div>
                <div class="img1">
                    <img class="Img" :src="imageSrc1" alt="book" />
                </div>
            </div>
        </div>
        <div class="Page2">
            <img class="ImgCheif" :src="imageSrc2" alt="cheif" />
            <div class="content">
                <img class="Img" :src="imageSrc3" alt="???" />
                <p class="p1">What Will We Eat Today?</p>
                <p class="p2">
                    Unlock daily culinary inspiration with recibee!
                    Explore our featured 'Dish<br>of the Day' or embrace adventure with a random recipe
                    and infuse your<br>kitchen with exciting flavors.
                </p>
                <div class="btns">
                    <button class="rc">random recipe</button>
                    <button class="rd">recipe of the day</button>
                </div>
            </div>

        </div>
        <div class="Page3">
            <div class="content1">
                <img class="ImgHeart" :src="imageSrc4" alt="heart" />
                <p class="p1">save all your favorite<br>dishes in one place!</p>
                <p class="p2">
                    Organize, save, and enjoy! Keep all your favorite<br>recipes in one place.
                    Build custom collections,<br>never forget a cherished recipe. Your profile,<br>your culinary adventure.
                </p>
                <div class="btns1">
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
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { useVuelidate } from '@vuelidate/core'
import { required, minLength, maxLength, alpha, alphaNum } from '@vuelidate/validators'
export default {
    setup() {
        return { v$: useVuelidate() }
    },
    data() {
        return {
            imageSrc1: require('@/assets/p1.png'),
            imageSrc2: require('@/assets/p2.png'),
            imageSrc3: require('@/assets/p3.png'),
            imageSrc4: require('@/assets/p4.png'),
            signupForm: {
                username: '',
                name: '',
                password: '',
                // cpassword:''
            },
            loginForm: {
                username: '',
                password: ''
            }



        };
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
    mounted() {
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
    methods: {
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
                        console.log(data)
                        document.getElementById('Signup').style.display = 'none';
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
                                this.$router.go();
                            })
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
                            this.$router.go();
                        }
                    })
            }
        }
    },
};
</script>

<style scoped>
* {
    margin: 0;
    padding: 0;
    height: auto;
    width: 100%;
}

.Page1 {
    background-color: #FBBC3B;
    height: 784px;
    width: 100%;
    padding-top: 13%;
}

.rec1 {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 406px;
    width: 85%;
    margin: auto;
}

.text1 {
    height: 327px;
    width: 50%;
    height: 80%;
    left: 0;
    right: 103px;
    margin-top: 1%;
}

.subtext1 {
    height: 75%;
    width: 100%;
}

.subtext1 .whiteText {
    font-size: 48px;
    font-weight: 800;
    color: #FBF7EB;
    line-height: 58.09px;

}

.subtext1 .redText {
    font-size: 48px;
    font-weight: 800;
    color: #E35733;
}

.discover {
    font-size: 18px;
    font-weight: 400px;
    color: #FBF7EB;
    letter-spacing: 0.05rem;
}

.btn1 {
    height: 25%;
    width: 40%;
    padding: auto;
}

.catalogue {
    height: 80%;
    width: 80%;
    font-size: 24px;
    background-color: #FBF7EB;
    color: #FBBC3B;
    border-radius: 10px;
    margin-top: 5%;
    font-size: 24px;
    font-weight: 700;
    line-height: 29px;
    letter-spacing: 0em;
    text-align: center;


}

.catalogue:hover {
    opacity: 70%;
}

.catalogue:active {
    scale: 110%;
}

.img1 {
    margin-left: 30px;
    max-width: 660px;
    max-height: 406px;
}

.Page2 {
    background-color: #FBF7EB;
    height: 900px;
    width: 100%;
}

.content .p1 {
    font-size: 48px;
    font-weight: 800;
    line-height: 44px;
    letter-spacing: 0em;
    text-align: center;
    margin-top: 5px;
    margin-bottom: 15px;
    color: #312525;
    ;
}

.content .p2 {
    font-size: 14px;
    font-weight: 500;
    line-height: 23px;
    letter-spacing: 0em;
    text-align: center;
    color: #312525;
}

.content {
    display: inline-block;
    margin-left: 46%;
    margin-top: 15%;
    width: auto;
    height: 45%;
    align-items: center;
}

.content img {
    margin-left: 35%;
    height: 171px;
    width: 213px;
}

.btns {
    display: flex;
    margin-top: 20px;
    margin-left: 8%;
}

.btns .rc,
.rd {
    margin-left: 7.5%;
    width: 200px;
    height: 50px;
    border-radius: 10px;
    background: #B3D0E8;
    color: #FBF7EB;
    font-size: 16px;
    font-weight: 700;
    line-height: 19px;
    letter-spacing: 0em;
    text-align: center;


}

.ImgCheif {
    width: 520px;
    height: 424px;
    position: absolute;
    margin-top: 14%;
    margin-left: 7.5%;
}

.rd:hover,
.rc:hover,
.login:hover,
.signup:hover {
    opacity: 70%;
}

.rd:active,
.rc:active,
.login:active,
.signup:active {
    scale: 110%;
}

.Page3 {
    background-color: #E35733;
    height: 784px;
    width: 100%;
    background-image: url('@/assets/p5.png');
    background-position: bottom;
    background-size: 100% 52%;
    padding-left: 5%;
    padding-right: 5%;
    position: relative;
    z-index: 5;
}

.content1 .p1 {
    font-size: 48px;
    font-weight: 700;
    line-height: 55px;
    letter-spacing: 0em;
    text-align: center;
    margin-top: 5px;
    margin-bottom: 15px;
    color: #FBF7EB;
}

.content1 .p2 {
    font-size: 16px;
    font-weight: 400;
    line-height: 23px;
    letter-spacing: 0em;
    text-align: center;
    color: #FBF7EB;
}

.content1 {
    display: inline-block;
    margin-left: 31%;
    margin-top: 15%;
    width: auto;
    height: 45%;
    align-items: center;
}

.content1 img {
    margin-left: 42.5%;
    height: 69px;
    width: 81px;
}

.btns1 {
    display: flex;
    margin-top: 20px;
    margin-left: 15%;
}

.login,
.signup {
    width: 150px;
    height: 50px;
    border-radius: 10px;
    font-size: 16px;
    font-weight: 700;
    line-height: 19px;
    letter-spacing: 0em;
    text-align: center;

}

.login {
    background-color: #FBF7EB;
    color: #B3D0E8;
    margin-right: 7%;
}

.signup {
    background-color: #B3D0E8;
    color: #FBF7EB;
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

.input-errors {
    color: red;
    font-size: 12px;
    margin: 0 0 0 10px;
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
}</style>