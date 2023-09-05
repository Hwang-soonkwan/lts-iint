<template>

    <div>
        <div class="detail-title">
        OrderDetail
        </div>
        <v-col>
            <Number label="Qty" v-model="value.qty" :editMode="editMode"/>
            <ProductId offline label="ProductId" v-model="value.productId" :editMode="editMode" @change="change"/>
        </v-col>

        <v-card-actions v-if="inList">
            <slot name="actions"></slot>
        </v-card-actions>
    </div>
</template>

<script>
import BaseEntity from './base-ui/BaseEntity'

export default {
    name: 'OrderDetail',
    mixins:[BaseEntity],
    components:{
    },
    data: () => ({
        path: 'OrderDetails',
    }),
    props: {
    },
    watch: {
        value(val){
            this.value = val;
            this.change();
        },
    },
    async created(){
        if (this.value && this.value.id !== undefined) {
            this.value = await this.repository.findById(this.value.id)
        }
    },
    methods: {
        pick(val){
            this.value = val;
            this.change();
        },
    }
}
</script>

