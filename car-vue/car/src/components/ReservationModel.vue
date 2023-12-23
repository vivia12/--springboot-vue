<template>
  <el-dialog v-model="dialogVisible" title="Reservation Details">
    <el-form :model="form">
      <el-form-item label="客户ID">
        <el-input v-model="form.customerid"></el-input>
      </el-form-item>
      <el-form-item label="汽车ID">
        <el-input v-model="form.carid"></el-input>
      </el-form-item>
      <el-form-item label="开车地点ID">
        <el-input v-model="form.pickuplocationid"></el-input>
      </el-form-item>
      <el-form-item label="还车地点ID">
        <el-input v-model="form.dropofflocationid"></el-input>
      </el-form-item>
      <el-form-item label="开始日期">
        <el-date-picker
          v-model="form.startdate"
          type="date"
          placeholder="Select date"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="还车日期">
        <el-date-picker
          v-model="form.enddate"
          type="date"
          placeholder="Select date"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="总花费">
        <el-input v-model="form.totalcost"></el-input>
      </el-form-item>
      <el-form-item label="预定状态">
        <el-input v-model="form.reservationstatus"></el-input>
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
