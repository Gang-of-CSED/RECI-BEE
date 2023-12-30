<template>
    <div class="footer">
        <div><h1>RECI-BEE</h1></div>
        <div class="ll"></div>
        <ul class="info">
            <li><a href="/home">About Us</a></li>
            <li><a href="/home">Features</a></li>
            <li><a href="/home">FAQ</a></li>
            <!-- <li><a href="#">Contact Us</a></li>
            <li class="socialMedia">
                <img class="Img" :src="imageSrcX" alt="X" />
                <img class="Img" :src="imageSrcF" alt="Facebook" />
                <img class="Img" :src="imageSrcI" alt="instagram" />
            </li> -->
        </ul>
    </div>
</template>

<script>
export default {
    data(){
        return{
            name: 'footer',
            imageSrcX: require('@/assets/v3.png'),
            imageSrcF: require('@/assets/v2.png'),
            imageSrcI: require('@/assets/v1.png'),
        }
    }
}
</script>


<style scoped>
.footer {
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
ul {
    display: flex;
    align-items: center;
    list-style: none;
}

li {
    margin: 0 20.5px;
    font-size: 15px;
}
a {
    text-decoration: none;
    color : #312525;
}
.socialMedia{
    display: flex;
    margin-left: 360px;
}

.Img{
    margin-right: 15px;
    margin-right: 15px;
}

.ll{
    height: 48px;
    border: 1px #898989 solid;
}



</style>