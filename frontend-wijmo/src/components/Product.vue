<template>
    <div>
        <Number label="제품고유번호" v-model="value.prodNo" :editMode="editMode"/>
        <String label="품번" v-model="value.prodNumber" :editMode="editMode"/>
        <String label="품명" v-model="value.prodName" :editMode="editMode"/>
        <String label="규격" v-model="value.standard" :editMode="editMode"/>
        <ProductType offline label="ProductType" v-model="value.productType" :editMode="editMode" @change="change"/>
        <ManufacturingProcessIdDetailGrid label="ManufacturingProcessIds" offline v-model="value.manufacturingProcessIds" :editMode="editMode" @change="change"/>

        <v-divider class="border-opacity-100 my-divider"></v-divider>
        <v-layout row justify-end>
            <v-btn
                width="64px"
                color="primary"
                @click="save"
            >
                저장
            </v-btn>
        </v-layout>
    </div>
</template>


<script>

import BaseEntity from './base-ui/BaseEntity'

export default {
    name: 'Product',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "products"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>

