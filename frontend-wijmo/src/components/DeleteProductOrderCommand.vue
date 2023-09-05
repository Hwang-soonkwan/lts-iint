<template>

    <v-card outlined>
        <v-card-title>
            DeleteProductOrder
        </v-card-title>

        <v-card-text>
            <String label="OrderNumber" v-model="value.orderNumber" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="deleteProductOrder"
            >
                DeleteProductOrder
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
        name: 'DeleteProductOrderCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.orderNumber = '';
        },
        watch: {
        },
        methods: {
            deleteProductOrder() {
                this.$emit('deleteProductOrder', this.value);
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

