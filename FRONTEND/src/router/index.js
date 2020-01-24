import Vue from "vue";
import Router from "vue-router";
import LoginComponent from "../views/login.vue";
import SecureComponent from "../views/secure.vue";
import HomeComponent from "../views/Home.vue";
import TransferComponent from "../views/transfer.vue";
import Vuelidate from "vuelidate";

Vue.use(Router);
Vue.use(Vuelidate);

export default new Router({
  routes: [
        {
            path: '/',
            redirect: {
                name: "login"
            }
        },
        {
            path: "/login",
            name: "login",
            component: LoginComponent
        },
        {
            path: "/secure",
            name: "secure",
            component: SecureComponent
        },
        {
          path: "/home",
          name: "home",
          component: HomeComponent
       },
       {
        path: "/transfer",
        name: "transfer",
        component: TransferComponent
     }

    ]
})