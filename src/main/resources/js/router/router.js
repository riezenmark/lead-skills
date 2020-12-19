import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    /*{ path: '/admin', component: Admin },
    { path: '/admin/users', component: UserList },
    { path: '/admin/makers', component: Makers },
    { path: '/add', component: CarAdding },
    { path: '/update/:id', component: CarUpdating },
    { path: '/', component: Cars },
    { path: '/admin/users/:id', component: UserUpdating },*/
]

export default new VueRouter({
    mode: 'history',
    routes
})
