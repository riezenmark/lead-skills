import Vue from 'vue'
import VueRouter from 'vue-router'
import Announcements from "pages/Announcements.vue"
import Announcement from "pages/Announcement.vue"
import Timetable from "pages/Timetable.vue"

Vue.use(VueRouter)

const routes = [
    { path: '/', component: Announcements },
    { path: '/announcement/:id', component: Announcement },
    { path: '/timetable', component: Timetable }
]

export default new VueRouter({
    mode: 'history',
    routes
})
