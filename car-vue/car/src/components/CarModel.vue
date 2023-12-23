<template>
  <el-dialog v-model="dialogVisible" title="Car Details">
    <el-form :model="carData">
      <el-form-item label="品牌" :label-width="formLabelWidth">
        <el-input v-model="form.brand" autocomplete="off" />
      </el-form-item>
      <el-form-item label="型号" :label-width="formLabelWidth">
        <el-input v-model="form.model" autocomplete="off" />
      </el-form-item>
      <el-form-item label="租赁价格" :label-width="formLabelWidth">
        <el-input v-model="form.rentalprice" autocomplete="off" />
      </el-form-item>
      <el-form-item label="乘客容量" :label-width="formLabelWidth">
        <el-input v-model="form.passengercapacity" autocomplete="off" />
      </el-form-item>
      <!-- Add other form items as needed for Car entity properties -->
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
    carData: {
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
    const form = reactive({ ...props.carData });
    const formLabelWidth = "120px";

    watch(
      () => props.visible,
      (newVal) => {
        dialogVisible.value = newVal;
      }
    );

    watch(
      () => props.carData,
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
