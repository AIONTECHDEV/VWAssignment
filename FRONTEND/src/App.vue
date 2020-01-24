<template>
    <div id="app">
      <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div id="nav" class="collapse navbar-collapse">
          <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                      <router-link v-if="authenticated" to="/login" v-on:click.native="sessionlive()" replace class="nav-link"> Company </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link v-if="authenticated" to="/home" v-on:click.native="sessionlive()" replace class="nav-link"> Home </router-link>
                    </li>
                    <li class="nav-item active">
                        <router-link v-if="authenticated" to="/transfer" v-on:click.native="sessionlive()" replace class="nav-link"> Transfer </router-link>
                    </li>
                   
           </ul>           
        </div>
        <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
        <ul class="navbar-nav ml-auto">
             <li class="nav-item">
                <router-link v-if="authenticated" to="/login" v-on:click="logout()" replace class="nav-link "> Logout </router-link>
            </li>
        </ul>
    </div>
      </nav> 
        <router-view @authenticated="setAuthenticated" />
    </div>
</template>

<script>
import "./assets/logo.png";
    export default {
        name: 'App',
        data() {
            return {
                authenticated: false,
                imagen:"./assets/logo.png",
                mockAccount: {
                    username: "volkswagen",
                    password: "Adminvw-1"
                }
            }
        },
        mounted() {
            if(!this.authenticated) {
                this.$router.replace({ name: "login" });
            }
        },
        methods: {
            setAuthenticated(status) {
                this.authenticated = status;
            },
            logout() {
                this.authenticated = false;
            },
            sessionlive() {
                this.authenticated = true;
            }
        }
    }
</script>

<style>
</style>