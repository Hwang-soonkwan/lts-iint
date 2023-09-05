<template>

    <v-card outlined>
        <v-card-title>
            CreateManufacturingProcess
        </v-card-title>

        <v-card-text>
            <String label="ProcessName" v-model="value.processName" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="createManufacturingProcess"
            >
                CreateManufacturingProcess
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
        name: 'CreateManufacturingProcessCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.processName = '';
        },
        watch: {
        },
        methods: {
            createManufacturingProcess() {
                this.$emit('createManufacturingProcess', this.value);
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

