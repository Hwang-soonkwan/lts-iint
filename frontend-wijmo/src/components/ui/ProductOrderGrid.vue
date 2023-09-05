<template>
    <div style="max-height:80vh;">
        <div class="hierarchy">
            <div>제품주문 &nbsp; ></div>
            <div>&nbsp; 주문</div>
        </div>
        <div class="gs-bundle-of-buttons" style="max-height:10vh;">
            <v-btn @click="addNewRow" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Actor Name')">
                <v-icon small>mdi-plus-circle-outline</v-icon>등록
            </v-btn>
            <v-btn  @click="editSelectedRow" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Actor Name')">
                <v-icon small>mdi-pencil</v-icon>수정
            </v-btn>
            <v-btn @click="updateProductOrderDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Actor Name')">
                <v-icon small>mdi-minus-circle-outline</v-icon>주문 업데이트
            </v-btn>
            <v-dialog v-model="updateProductOrderDialog" width="500">
                <UpdateProductOrderCommand
                    @closeDialog="updateProductOrderDialog = false"
                    @updateProductOrder="updateProductOrder"
                ></UpdateProductOrderCommand>
            </v-dialog>
            <v-btn @click="productComplateDialog = true" class="contrast-primary-text" small color="primary" >
                <v-icon small>mdi-minus-circle-outline</v-icon>생산완료
            </v-btn>
            <v-dialog v-model="productComplateDialog" width="500">
                <ProductComplateCommand
                    @closeDialog="productComplateDialog = false"
                    @productComplate="productComplate"
                ></ProductComplateCommand>
            </v-dialog>
            <v-btn @click="deleteSelectedRows" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Actor Name')">
                <v-icon small>mdi-minus-circle-outline</v-icon>삭제
            </v-btn>
            <excel-export-button class="contrast-primary-text" :exportService="this.exportService" :getFlex="getFlex" />
        </div>
        <OrderSearch @search="search"></OrderSearch>


        <!-- the grid -->
        <wj-flex-grid
            ref="flexGrid"
            :key="value.length"
            :autoGenerateColumns="false"
            :allowAddNew="false"
            :allowDelete="true"
            :allowPinning="'SingleColumn'"
            :newRowAtTop="false"
            :showMarquee="true"
            :selectionMode="'MultiRange'"
            :validateEdits="false"
            :itemsSource="value"
            :initialized="flexInitialized"
            :selectionChanged="onSelectionChanged"
            style="margin-top:10px; max-height:65vh;"
            class="wj-felx-grid"
        >
            <wj-flex-grid-filter :filterColumns="['RowHeader','orderNumber','customerPartNumber','productName','deliveryLocation','orderAmount','orderQuantity','orderAmount','orderDate','deliveryDate','isUrgent','isConfirmed','memo','isDeleted','orderDetails',]" />
            <wj-flex-grid-cell-template cellType="RowHeader" v-slot="cell">{{cell.row.index + 1}}</wj-flex-grid-cell-template>
            <wj-flex-grid-column binding="orderNumber" header="번호" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="customerPartNumber" header="고객품번" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="productName" header="품명" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="deliveryLocation" header="납품처" width="2*" :isReadOnly="true" align="center">
                <wj-flex-grid-cell-template cellType="Cell" v-slot="cell">   
                    <Address v-model="cell.item.deliveryLocation" :editMode="editMode"></Address>
                </wj-flex-grid-cell-template>
            </wj-flex-grid-column>
            <wj-flex-grid-column binding="orderAmount" header="단가" width="2*" :isReadOnly="true" align="center">
                <wj-flex-grid-cell-template cellType="Cell" v-slot="cell">   
                    <Money v-model="cell.item.orderAmount" :editMode="editMode"></Money>
                </wj-flex-grid-cell-template>
            </wj-flex-grid-column>
            <wj-flex-grid-column binding="orderQuantity" header="수주량" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="orderAmount" header="수주금액" width="2*" :isReadOnly="true" align="center">
                <wj-flex-grid-cell-template cellType="Cell" v-slot="cell">   
                    <Money v-model="cell.item.orderAmount" :editMode="editMode"></Money>
                </wj-flex-grid-cell-template>
            </wj-flex-grid-column>
            <wj-flex-grid-column binding="orderDate" header="수주일" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="deliveryDate" header="납기일" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="isUrgent" header="긴급여부" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="isConfirmed" header="확정여부" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="memo" header="메모" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="isDeleted" header="삭제여부" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="productId" header="제품마스터" width="2*" :isReadOnly="true" align="center">
                <wj-flex-grid-cell-template cellType="Cell" v-slot="cell">   
                    <ProductId v-model="cell.item.productId" :editMode="editMode"></ProductId>
                </wj-flex-grid-cell-template>
            </wj-flex-grid-column>
        </wj-flex-grid>
        <OrderDetailDetailGrid label="OrderDetails" offline v-if="selectedRow" v-model="selectedRow.orderDetails"/>
        <v-col>
            <v-dialog
                v-model="openDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <template>
                    <v-card>
                        <v-toolbar
                            color="primary"
                            class="elevation-0"
                            height="50px"
                        >
                            <div style="color:white; font-size:17px; font-weight:700;">주문 등록</div>
                            <v-spacer></v-spacer>
                            <v-icon
                                color="white"
                                small
                                @click="closeDialog()"
                            >mdi-close</v-icon>
                        </v-toolbar>
                        <v-card-text>
                            <ProductOrder :offline="offline"
                                :isNew="!itemToEdit"
                                :editMode="true"
                                v-model="newValue"
                                @add="append"
                                @edit="edit"
                            />
                        </v-card-text>
                    </v-card>
                </template>
            </v-dialog>
        </v-col>
        <v-col style="margin-bottom:40px;">
            <div class="text-center">
                <v-dialog
                    width="332.5"
                    fullscreen
                    hide-overlay
                    transition="dialog-bottom-transition"
                >
                    <v-btn
                        style="position:absolute; top:2%; right:2%"
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
import BaseGrid from '../base-ui/BaseGrid'

export default {
    name: 'productOrderGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'productOrders',
        updateProductOrderDialog: false,
        productComplateDialog: false,
    }),
    watch: {
        newValue: {
            deep:true,
            handler:function(){
                if(!this.newValue){
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
                }
            }
        }
    },
    methods:{
        updateProductOrder(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "updateProductOrder", params)
                this.$EventBus.$emit('show-success','UpdateProductOrder 성공적으로 처리되었습니다.')
            }catch(e){
                this.$EventBus.$emit('show-error', e);
            }
        },
        productComplate(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "productComplate", params)
                this.$EventBus.$emit('show-success','ProductComplate 성공적으로 처리되었습니다.')
            }catch(e){
                this.$EventBus.$emit('show-error', e);
            }
        },
    }
}
</script>