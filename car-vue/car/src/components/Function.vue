<script setup>
import CustomersOnProbation from "./CustomersOnProbation.vue";
import GetCarsByPassengers from "./GetCarsByPassengers.vue";
import PopularLocation from "./PopularLocation.vue";
import Trend from "./Trend.vue";
</script>
<template>
  <div>
    <!-- 展示违规学生 -->
    <CustomersOnProbation />
    <GetCarsByPassengers />
    <PopularLocation />
    <Trend />
  </div>
</template>
<style scoped>
#card {
  float: left;
  margin: 30px 10px;
  width: calc(50% - 20px);
  box-sizing: border-box;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.el-table .warning-row {
  background-color: #ffdab9 !important;
}

/* 添加一些边距和样式到查询按钮 */
.el-button[type="primary"] {
  margin-left: 10px;
}

/* 调整表格样式 */
.el-table {
  width: 100%;
}

/* 增加一些底部边距 */
.el-card {
  margin-bottom: 20px;
}
</style>
