<script>
import apiCustomers from "../api/apiCustomers";
export default {
  data() {
    return {
      customersOnProbation: [],
      isListVisible: false,
      disabled: true,
    };
  },
  methods: {
    async getCustomersOnProbation() {
      try {
        const response = await apiCustomers.getCustomersOnProbation();
        this.customersOnProbation = response.data;
        this.isListVisible = true; // Show the list when data is fetched
        this.disabled = false;
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    },
  },
};
</script>

<template>
  <div id="card">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <el-button type="success" round @click="getCustomersOnProbation"
            >展示三年内存在行驶违规的学生</el-button
          >
        </div>
      </template>
      <el-table
        row-class-name="warning-row"
        :data="customersOnProbation"
        v-if="isListVisible && customersOnProbation.length > 0"
        border
      >
        <el-table-column label="姓名" prop="fullName"></el-table-column>
        <el-table-column label="邮箱" prop="email"></el-table-column>
        <el-table-column label="地址" prop="address"></el-table-column>
      </el-table>
      <template #footer>
        <el-button
          v-if="isListVisible"
          type="primary"
          @click="isListVisible = !isListVisible"
        >
          隐藏列表
        </el-button>
        <el-button
          v-else
          type="primary"
          @click="isListVisible = !isListVisible"
          :disabled="disabled"
        >
          显示列表
        </el-button>
      </template>
    </el-card>
  </div>
</template>
