import Vue from 'vue'
import VueRouter from 'vue-router'
import Announcements from "pages/Announcements.vue";

Vue.use(VueRouter)

const routes = [
    { path: '/', component: Announcements }
]

export default new VueRouter({
    mode: 'history',
    routes
})
