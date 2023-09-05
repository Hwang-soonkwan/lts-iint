
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import BasicProductManager from "./components/listers/BasicProductCards"
import BasicProductDetail from "./components/listers/BasicProductDetail"

import ProgressManufacturingProcessManager from "./components/listers/ProgressManufacturingProcessCards"
import ProgressManufacturingProcessDetail from "./components/listers/ProgressManufacturingProcessDetail"

import OrderProductOrderManager from "./components/listers/OrderProductOrderCards"
import OrderProductOrderDetail from "./components/listers/OrderProductOrderDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/basics/products',
                name: 'BasicProductManager',
                component: BasicProductManager
            },
            {
                path: '/basics/products/:id',
                name: 'BasicProductDetail',
                component: BasicProductDetail
            },

            {
                path: '/progresses/manufacturingProcesses',
                name: 'ProgressManufacturingProcessManager',
                component: ProgressManufacturingProcessManager
            },
            {
                path: '/progresses/manufacturingProcesses/:id',
                name: 'ProgressManufacturingProcessDetail',
                component: ProgressManufacturingProcessDetail
            },

            {
                path: '/orders/productOrders',
                name: 'OrderProductOrderManager',
                component: OrderProductOrderManager
            },
            {
                path: '/orders/productOrders/:id',
                name: 'OrderProductOrderDetail',
                component: OrderProductOrderDetail
            },



    ]
})
