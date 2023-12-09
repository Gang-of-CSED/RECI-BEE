<template>
    <div class="navbar">
        <div>
            <h1>RECI-BEE</h1>
        </div>
        <ul>
            <li><a href="#">Browse Catalogue</a></li>
            <li><a href="#">Dish Of The Day</a></li>
            <li><a href="#">Random Dish</a></li>
            <li><a href="#">About Us</a></li>
            <li>
                <h3>{{ user.name }}</h3>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'NavBar',
    data() {
        return {
            user: {
                "name":"Ahmad Hassan"
            },
        };
    },
    mounted() {
        // get user name from user token
        const token = localStorage.getItem('token');
        if (token) {
            axios.get('http://localhost:8080/info', {
                headers: {
                    Authorization: `${token}`,
                },
            })
                .then(response => {
                    this.user = response.data;
                    console.log(JSON.stringify(this.user, null, 2));
                })
                .catch(error => {
                    console.error('There was an error!', error);
                });
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
    /* border: 1px solid black; */

    margin: 0 0 0 57px;
    font-size: 36px;
    font-weight: 700;
}

h3 {
    /* border: 1px solid black; */

    margin-left: 19px;
    font-size: 20px;
}

ul {
    /* border: 1px solid rgb(199, 37, 137); */

    display: flex;
    justify-content: space-between;
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

    text-decoration: none;
    color: #312525;
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
}</style>