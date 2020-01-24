<template>
    <div id="secure">
        <h1>Welcome to your online banking {{this.$parent.mockAccount.username}}</h1>    
        <h1>Secure Area</h1>
        <p>
            This is a secure area
        </p>
    </div>
</template>

<script>
    export default {
        name: 'Secure',
        data() {
            return {};
        },
         methods: {
        }
    }
</script>

<style scoped>
    #secure {
        background-color: #FFFFFF;
        border: 1px solid #CCCCCC;
        padding: 20px;
        margin-top: 10px;
    }
</style>