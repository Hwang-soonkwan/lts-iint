
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import ProductManager from "./components/ui/ProductGrid"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/products',
                name: 'ProductManager',
                component: ProductManager
            },



    ]
})
