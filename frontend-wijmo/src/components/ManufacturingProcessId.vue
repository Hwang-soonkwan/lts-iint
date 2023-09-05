<template>
    <div>
        <BasePicker v-if="editMode" searchApiPath="manufacturingprocesses/search/findBy" searchParameterName=""  idField="processName" nameField="" path="manufacturingprocesses" label="ManufacturingProcessId" v-model="value" @selected="pick" :editMode="editMode" />
        <div v-else style="height:100%">
            <span>{{ value && value.name ? value.name : '' }}</span>
        </div>
    </div>

</template>

<script>
import BaseEntity from './base-ui/BaseEntity'

export default {
    name: 'ManufacturingProcessId',
    mixins:[BaseEntity],
    components:{
    },
    data: () => ({
        path: 'manufacturingprocesses',
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

