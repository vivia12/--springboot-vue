<script>
import carApi from "../api/carApi"; // 导入你的 axios 接口服务

export default {
  data() {
    return {
      trends: [],
      isListVisible: false, // 用于存储符合条件的车辆数据
      disabled: true,
    };
  },
  methods: {
    getTread() {
      carApi
        .getTrend()
        .then((response) => {
          this.trends = response.data;
          this.isListVisible = true;
          this.disabled = false;
        })
        .catch((error) => {
          console.error("Error fetching data:", error);
        });
    },
  },
};
</script>
<template>
  <div id="card">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <el-button type="success" round @click="getTread"
            >展示租赁流行</el-button
          >
        </div>
      </template>
      <!-- 符合条件的车辆列表 -->
      <el-table
        v-if="isListVisible"
        :data="trends"
        border
        style="width: 100%"
        stripe
      >
        <el-table-column label="品牌" prop="make"></el-table-column>
        <el-table-column label="型号" prop="model"></el-table-column>
        <el-table-column label="是否学生" prop="isstudent">
          <template #default="scope">
            <el-tag
              :type="scope.row.isstudent ? 'success' : 'info'"
              disable-transitions
            >
              {{ scope.row.isstudent ? "是" : "否" }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="租赁次数" prop="timesRented"></el-table-column>
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
