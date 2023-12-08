<template>
    <div class="user-container">
        <div class="name-rate">
            <h1>{{ this.userName }}</h1>
            <v-rating v-model="this.rate" id="stars" hover clearable size="27.2"></v-rating>
        </div>
        <p>{{ this.comment }}</p>
    </div>
</template>

<script>
export default {
    name: 'RecipeComment',
    props: [
        'userName',
        'comment',
        'rate'
        ],
}
</script>

<style scoped>
    .user-container {
        color: #312525;
        display: flex;
        flex-direction: column;
        align-content: center;
        justify-content: left;
        border-bottom: 1px solid #31252580;
        padding: 0 0 2% 0;
        margin-bottom: 26px;
    }
    .name-rate {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        padding: 0 0 1% 0;
    }

    #stars {
        color: #E35733
    }

    h1{
        font-size: 16px;
        font-weight: 700;
        line-height: 19px;
        letter-spacing: 0em;
        text-align: left;
    }
    p{
        color: #312525;
        font-size: 14px;
        font-weight: 300;
        line-height: 17px;
        letter-spacing: 0em;
        text-align: left;
    }
</style>