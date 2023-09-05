<template>
    <div>
        <Number label="번호" v-model="value.orderNumber" :editMode="editMode"/>
        <String label="고객품번" v-model="value.customerPartNumber" :editMode="editMode"/>
        <String label="품명" v-model="value.productName" :editMode="editMode"/>
        <Number label="수주량" v-model="value.orderQuantity" :editMode="editMode"/>
        <Date label="수주일" v-model="value.orderDate" :editMode="editMode"/>
        <Date label="납기일" v-model="value.deliveryDate" :editMode="editMode"/>
        <Boolean label="긴급여부" v-model="value.isUrgent" :editMode="editMode"/>
        <Boolean label="확정여부" v-model="value.isConfirmed" :editMode="editMode"/>
        <String label="메모" v-model="value.memo" :editMode="editMode"/>
        <Boolean label="삭제여부" v-model="value.isDeleted" :editMode="editMode"/>
        <Address offline label="납품처" v-model="value.deliveryLocation" :editMode="editMode" @change="change"/>
        <Money offline label="단가" v-model="value.orderAmount" :editMode="editMode" @change="change"/>
        <Money offline label="수주금액" v-model="value.orderAmount" :editMode="editMode" @change="change"/>
        <OrderDetailDetailGrid label="OrderDetails" offline v-model="value.orderDetails" :editMode="editMode" @change="change"/>

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
import Address from './vo/Address.vue';
import Money from './vo/Money.vue';
import Money from './vo/Money.vue';
import BaseEntity from './base-ui/BaseEntity'

export default {
    name: 'ProductOrder',
    mixins:[BaseEntity],
    components:{
        Address,
        Money,
        Money,
    },
    
    data: () => ({
        path: "productOrders"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>

