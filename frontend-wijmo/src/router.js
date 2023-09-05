
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import ProductManager from "./components/ui/ProductGrid"

import ManufacturingProcessManager from "./components/ui/ManufacturingProcessGrid"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/products',
                name: 'ProductManager',
                component: ProductManager
            },

            {
                path: '/manufacturingProcesses',
                name: 'ManufacturingProcessManager',
                component: ManufacturingProcessManager
            },



    ]
})
