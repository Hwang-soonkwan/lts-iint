<template>
    <v-card outlined>
        <v-card-title>
            ProductOrder # {{item._links.self.href.split("/")[item._links.self.href.split("/").length - 1]}}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="OrderNumber" v-model="item.orderNumber" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="CustomerPartNumber" v-model="item.customerPartNumber" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="ProductName" v-model="item.productName" :editMode="editMode" @change="change" />
            </div>
            <Address offline label="DeliveryLocation" v-model="item.deliveryLocation" :editMode="false" @change="change" />
            <Money offline label="OrderAmount" v-model="item.orderAmount" :editMode="false" @change="change" />
            <div>
                <Number label="OrderQuantity" v-model="item.orderQuantity" :editMode="editMode" @change="change" />
            </div>
            <Money offline label="OrderAmount" v-model="item.orderAmount" :editMode="false" @change="change" />
            <div>
                <Date label="OrderDate" v-model="item.orderDate" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Date label="DeliveryDate" v-model="item.deliveryDate" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Boolean label="IsUrgent" v-model="item.isUrgent" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Boolean label="IsConfirmed" v-model="item.isConfirmed" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Memo" v-model="item.memo" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Boolean label="IsDeleted" v-model="item.isDeleted" :editMode="editMode" @change="change" />
            </div>
            <OrderDetailManager offline label="OrderDetails" v-model="item.orderDetails" :editMode="false" @change="change" />
        </v-card-text>

        <v-card-actions>
            <v-btn text color="deep-purple lighten-2" large @click="goList">List</v-btn>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="edit"
                    v-if="!editMode"
            >
                Edit
            </v-btn>
            <v-btn
                    color="primary"
                    text
                    @click="save"
                    v-else
            >
                Save
            </v-btn>
            <v-btn
                    color="primary"
                    text
                    @click="remove"
                    v-if="!editMode"
            >
                Delete
            </v-btn>
            <v-btn
                    color="primary"
                    text
                    @click="editMode = false"
                    v-if="editMode"
            >
                Cancel
            </v-btn>
        </v-card-actions>
    </v-card>
</template>


<script>
    const axios = require('axios').default;

    export default {
        name: 'OrderProductOrderDetail',
        components:{},
        props: {
        },
        data: () => ({
            item: null,
            editMode: false,
        }),
        async created() {
            var me = this;
            var params = this.$route.params;
            var temp = await axios.get(axios.fixUrl('/productOrders/' + params.id))
            if(temp.data) {
                me.item = temp.data
            }
        },
        methods: {
            goList() {
                var path = window.location.href.slice(window.location.href.indexOf("/#/"), window.location.href.lastIndexOf("/#"));
                path = path.replace("/#/", "/");
                this.$router.push(path);
            },
            edit() {
                this.editMode = true;
            },
            async remove(){
                try {
                    if (!this.offline) {
                        await axios.delete(axios.fixUrl(this.item._links.self.href))
                    }

                    this.editMode = false;

                    this.$emit('input', this.item);
                    this.$emit('delete', this.item);

                } catch(e) {
                    console.log(e)
                }
            },
        },
    };
</script>
