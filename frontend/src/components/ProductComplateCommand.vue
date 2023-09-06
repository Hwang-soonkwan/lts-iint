<template>
    <v-card outlined>
        <v-card-title>
            ProductComplate
        </v-card-title>

        <v-card-text>
            <Date label="ProductDt" v-model="value.productDt" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="productComplate"
            >
                ProductComplate
            </v-btn>
            
            <v-btn
                    color="primary"
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
        name: 'ProductComplateCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.productDt = '2023-09-05';
        },
        watch: {
        },
        methods: {
            productComplate() {
                this.$emit('productComplate', this.value);
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

