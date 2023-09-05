<template>

    <v-card outlined>
        <v-card-title>
            UpdateProductOrder
        </v-card-title>

        <v-card-text>
            <String label="OrderNumber" v-model="value.orderNumber" :editMode="editMode"/>
            <String label="CustomerPartNumber" v-model="value.customerPartNumber" :editMode="editMode"/>
            <String label="ProductName" v-model="value.productName" :editMode="editMode"/>
            <Address offline label="DeliveryLocation" v-model="value.deliveryLocation" :editMode="editMode" @change="change"/>
            <Money offline label="UnitPrice" v-model="value.unitPrice" :editMode="editMode" @change="change"/>
            <Number label="OrderQuantity" v-model="value.orderQuantity" :editMode="editMode"/>
            <Money offline label="OrderAmount" v-model="value.orderAmount" :editMode="editMode" @change="change"/>
            <Date label="OrderDate" v-model="value.orderDate" :editMode="editMode"/>
            <Date label="DeliveryDate" v-model="value.deliveryDate" :editMode="editMode"/>
            <Boolean label="IsUrgent" v-model="value.isUrgent" :editMode="editMode"/>
            <Boolean label="IsConfirmed" v-model="value.isConfirmed" :editMode="editMode"/>
            <String label="Memo" v-model="value.memo" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="updateProductOrder"
            >
                UpdateProductOrder
            </v-btn>
            
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'UpdateProductOrderCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.orderNumber = '';
            this.value.customerPartNumber = '';
            this.value.productName = '';
            this.value.deliveryLocation = {};
            this.value.unitPrice = {};
            this.value.orderQuantity = 0;
            this.value.orderAmount = {};
            this.value.orderDate = '2023-09-05';
            this.value.deliveryDate = '2023-09-05';
            this.value.isUrgent = false;
            this.value.isConfirmed = false;
            this.value.memo = '';
        },
        watch: {
        },
        methods: {
            updateProductOrder() {
                this.$emit('updateProductOrder', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

