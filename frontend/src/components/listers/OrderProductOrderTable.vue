<template>
    <div>
        <v-data-table
                :headers="headers"
                :items="values"
                :items-per-page="5"
                class="elevation-1"
        ></v-data-table>

        <v-col style="margin-bottom:40px;">
            <div class="text-center">
                <v-dialog
                        v-model="openDialog"
                        width="332.5"
                        fullscreen
                        hide-overlay
                        transition="dialog-bottom-transition"
                >
                    <template v-slot:activator="{ on, attrs }">
                        <v-fab-transition>
                            <v-btn
                                    color="primary"
                                    fab
                                    dark
                                    large
                                    style="position:absolute; bottom: 5%; right: 2%; z-index:99"
                                    @click="openDialog=true;"
                            >
                                <v-icon v-bind="attrs" v-on="on">mdi-plus</v-icon>
                            </v-btn>
                        </v-fab-transition>
                    </template>

                    <OrderProductOrder :offline="offline" class="video-card" :isNew="true" :editMode="true" v-model="newValue" @add="append" v-if="tick"/>
                
                    <v-btn
                            style="postition:absolute; top:2%; right:2%"
                            @click="closeDialog()"
                            depressed 
                            icon 
                            absolute
                    >
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                </v-dialog>
            </div>
        </v-col>
    </div>
</template>

<script>
    const axios = require('axios').default;
    import OrderProductOrder from './../OrderProductOrder.vue';

    export default {
        name: 'OrderProductOrderManager',
        components: {
            OrderProductOrder,
        },
        props: {
            offline: Boolean,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            values: [],
            headers: 
                [
                    { text: "orderNumber", value: "orderNumber" },
                    { text: "customerPartNumber", value: "customerPartNumber" },
                    { text: "productName", value: "productName" },
                    { text: "deliveryLocation", value: "deliveryLocation" },
                    { text: "orderAmount", value: "orderAmount" },
                    { text: "orderQuantity", value: "orderQuantity" },
                    { text: "orderAmount", value: "orderAmount" },
                    { text: "orderDate", value: "orderDate" },
                    { text: "deliveryDate", value: "deliveryDate" },
                    { text: "isUrgent", value: "isUrgent" },
                    { text: "isConfirmed", value: "isConfirmed" },
                    { text: "memo", value: "memo" },
                    { text: "isDeleted", value: "isDeleted" },
                    { text: "orderDetails", value: "orderDetails" },
                ],
            productOrder : [],
            newValue: {},
            tick : true,
            openDialog : false,
        }),
        async created() {
            if(this.offline){
                if(!this.values) this.values = [];
                return;
            }

            var temp = await axios.get(axios.fixUrl('/productorders'))
            temp.data._embedded.productorders.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])
            this.values = temp.data._embedded.productorders;

            this.newValue = {
                'orderNumber': 0,
                'customerPartNumber': '',
                'productName': '',
                'deliveryLocation': {},
                'orderAmount': {},
                'orderQuantity': 0,
                'orderAmount': {},
                'orderDate': '2023-09-05',
                'deliveryDate': '2023-09-05',
                'isUrgent': false,
                'isConfirmed': false,
                'memo': '',
                'isDeleted': false,
                'orderDetails': [],
            }
        },
        methods: {
            closeDialog(){
                this.openDialog = false
            },
            append(value){
                this.tick = false
                this.newValue = {}
                this.values.push(value)
                
                this.$emit('input', this.values);

                this.$nextTick(function(){
                    this.tick=true
                })
            },
        }
    }
</script>

