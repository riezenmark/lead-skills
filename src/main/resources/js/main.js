import Vue from 'vue'
import '@babel/polyfill'
import router from './router/router'
import VueResource from 'vue-resource'
import App from 'pages/App.vue'
import store from "store/store";
import vuetify from 'plugin/vuetify'

Vue.use(VueResource)
new Vue({
    el: '#app',
    router,
    store,
    vuetify,
    render: a => a(App)
})
