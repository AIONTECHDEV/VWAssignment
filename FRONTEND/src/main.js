import Vue from "vue";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import VueSimpleAlert from "vue-simple-alert";
import Vuelidate from "vuelidate";
import App from "./App.vue";
import router from "./router";
import HighchartsVue from "highcharts-vue";

Vue.use(HighchartsVue);
Vue.use(VueSimpleAlert);
Vue.use(Vuelidate);
Vue.config.productionTip = false;
new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
