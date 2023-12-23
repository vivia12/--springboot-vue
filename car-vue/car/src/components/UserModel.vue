<template>
  <el-dialog v-model="dialogVisible" title="User Details">
    <el-form :model="userData">
      <el-form-item label="name" :label-width="formLabelWidth">
        <el-input v-model="form.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="password" :label-width="formLabelWidth">
        <el-input v-model="form.password" autocomplete="off" />
      </el-form-item>
      <el-form-item label="email" :label-width="formLabelWidth">
        <el-input v-model="form.email" autocomplete="off" />
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
    userData: {
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
    const form = reactive({ ...props.userData });
    const formLabelWidth = "120px";

    watch(
      () => props.visible,
      (newVal) => {
        dialogVisible.value = newVal;
      }
    );

    watch(
      () => props.userData,
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
