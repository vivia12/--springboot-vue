<!-- CustomerModel.vue -->
<template>
  <el-dialog v-model="dialogVisible" title="Customer Details">
    <el-form :model="customerData">
      <el-form-item label="姓名" :label-width="formLabelWidth">
        <el-input v-model="form.fullname" autocomplete="off" />
      </el-form-item>
      <el-form-item label="地址" :label-width="formLabelWidth">
        <el-input v-model="form.address" autocomplete="off" />
      </el-form-item>
      <el-form-item label="电话号码" :label-width="formLabelWidth">
        <el-input v-model="form.phonenumber" autocomplete="off" />
      </el-form-item>
      <el-form-item label="邮箱" :label-width="formLabelWidth">
        <el-input v-model="form.email" autocomplete="off" />
      </el-form-item>
      <el-form-item label="信用卡号" :label-width="formLabelWidth">
        <el-input v-model="form.creditcardnumber" autocomplete="off" />
      </el-form-item>
      <el-form-item label="行驶ID" :label-width="formLabelWidth">
        <el-input v-model="form.drivinglicensenumber" autocomplete="off" />
      </el-form-item>
      <el-form-item label="驾照状态" :label-width="formLabelWidth">
        <el-input v-model="form.licenseissuestate" autocomplete="off" />
      </el-form-item>
      <el-form-item label="驾照过期时间" :label-width="formLabelWidth">
        <el-date-picker
          v-model="form.licenseexpirationdate"
          type="date"
          placeholder="Select date"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="是否为学生" :label-width="formLabelWidth">
        <el-switch
          v-model="form.isstudent"
          active-color="#13ce66"
          inactive-color="#ff4949"
        ></el-switch>
      </el-form-item>
      <el-form-item label="违法记录" :label-width="formLabelWidth">
        <el-input v-model="form.violationstatus" autocomplete="off" />
      </el-form-item>
      <!-- Add other form items as needed for additional Customer entity properties -->
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
    customerData: {
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
    const form = reactive({ ...props.customerData });
    const formLabelWidth = "120px";

    watch(
      () => props.visible,
      (newVal) => {
        dialogVisible.value = newVal;
      }
    );

    watch(
      () => props.customerData,
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
