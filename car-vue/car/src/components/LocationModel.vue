<template>
  <el-dialog v-model="dialogVisible" title="Location Details">
    <el-form :model="locationData">
      <el-form-item label="Location Code" :label-width="formLabelWidth">
        <el-input v-model="form.locationCode" autocomplete="off" />
      </el-form-item>
      <el-form-item label="Address" :label-width="formLabelWidth">
        <el-input v-model="form.address" autocomplete="off" />
      </el-form-item>
      <el-form-item label="City" :label-width="formLabelWidth">
        <el-input v-model="form.city" autocomplete="off" />
      </el-form-item>
      <el-form-item label="State" :label-width="formLabelWidth">
        <el-input v-model="form.state" autocomplete="off" />
      </el-form-item>
      <el-form-item label="Zip Code" :label-width="formLabelWidth">
        <el-input v-model="form.zipCode" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="onConfirm">Confirm</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import { ref, reactive, watch, toRefs } from "vue";

export default {
  props: {
    visible: Boolean,
    locationData: {
      type: Object,
      default: () => ({}),
    },
    isEdit: {
      type: Boolean,
      default: false,
    },
  },
  emits: ["update:visible", "save"],
  setup(props, { emit }) {
    const dialogVisible = ref(props.visible);
    const form = reactive({ ...props.locationData });
    const formLabelWidth = "120px";

    watch(
      () => props.visible,
      (newVal) => {
        dialogVisible.value = newVal;
      }
    );

    watch(
      () => props.locationData,
      (newVal) => {
        Object.assign(form, newVal);
      }
    );

    const onConfirm = () => {
      emit("save", form);
      dialogVisible.value = false;
    };

    watch(dialogVisible, (newVal) => {
      emit("update:visible", newVal);
    });

    return {
      form,
      dialogVisible,
      formLabelWidth,
      onConfirm,
    };
  },
};
</script>

<style scoped>
.dialog-footer {
  text-align: right;
}
</style>
