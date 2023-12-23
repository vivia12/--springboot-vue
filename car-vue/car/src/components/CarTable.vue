<template>
  <div id="add" sty>
    <div id="searchbutton">
      <el-button type="info" @click="openCreateModal">添加车辆</el-button>
    </div>
    <el-input
      v-model="search"
      placeholder="Type to search"
      style="width: 300px"
    />
    <el-input
      v-model="carId"
      placeholder="输入车辆ID"
      style="width: 150px; margin-left: 200px"
    />
    <el-input
      v-model="increaseAmount"
      placeholder="增加租金"
      style="width: 150px"
    />
    <el-button type="primary" @click="handleIncrease">增加租金</el-button>
  </div>

  <div id="cartable">
    <el-table
      v-loading="loading"
      :data="filterTableData"
      style="width: 100%"
      show-header="true"
      border
    >
      <el-table-column align="center" label="ID" prop="carid" width="100px" />
      <el-table-column align="center" label="品牌" prop="brand" />
      <el-table-column align="center" label="型号" prop="model" />
      <el-table-column align="center" label="租赁价格" prop="rentalprice" />
      <el-table-column
        align="center"
        label="乘客容量"
        prop="passengercapacity"
      />
      <el-table-column align="center" label="操作">
        <template #default="scope">
          <el-button size="small" @click="openEditModal(scope.row)"
            >编辑</el-button
          >
          <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.row.carid)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
  <el-pagination
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
    :current-page="pagination.currentPage"
    :page-sizes="[10, 20, 30, 50]"
    :page-size="pagination.pageSize"
    layout="total, sizes, prev, pager, next, jumper"
    :total="pagination.total"
    id="page"
  ></el-pagination>
  <!-- 车辆编辑/创建模态框 -->
  <CarModel
    :visible="modalVisible"
    :car-data="selectedCar"
    @close="closeModal"
    @save="handleSave"
  ></CarModel>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import carApi from "../api/carApi";
import CarModel from "./CarModel.vue";
const carId = ref(""); // 新增的变量，用于存储用户输入的车辆ID
const increaseAmount = ref("");
const search = ref("");
const tableData = ref([]);
const modalVisible = ref(false);
const isEdit = ref(false);
const selectedCar = ref({});
// 分页状态
const pagination = ref({
  currentPage: 1,
  pageSize: 10,
  total: 0,
});
function handleIncrease() {
  const amount = parseFloat(increaseAmount.value);
  const selectedCarId = parseInt(carId.value);

  if (
    !isNaN(selectedCarId) &&
    selectedCarId > 0 &&
    !isNaN(amount) &&
    amount > 0
  ) {
    carApi
      .increase(selectedCarId, amount)
      .then(() => {
        console.log("Rental price increased successfully.");
        loadData(); // 重新加载数据
      })
      .catch((error) => {
        console.error("Error increasing rental price:", error);
      });
  } else {
    console.error("Invalid car ID or amount. Please enter valid values.");
  }
}
// 更新加载数据函数，以包含分页逻辑
async function loadData() {
  try {
    //加载
    const loading = ref(true);
    const currentPage = pagination.value.currentPage;
    const currentPageSize = pagination.value.pageSize;

    const response = await carApi.getCars(currentPage, currentPageSize);

    tableData.value = response.data.records;
    pagination.value.total = response.data.total; // 假设API返回总记录数
    loading = false;
  } catch (error) {
    console.error("Error fetching data:", error);
    // 处理错误
  }
}

// 处理页码改变
function handleCurrentChange(newPage) {
  pagination.value.currentPage = newPage;
  loadData();
}

// 处理每页条目数改变
function handleSizeChange(newSize) {
  pagination.value.pageSize = newSize;
  loadData();
}
// 加载数据渲染的时候
onMounted(async () => {
  await loadData();
});

// 搜索功能
const filterTableData = computed(() => {
  return tableData.value.filter(
    (data) =>
      !search.value ||
      data.brand.toLowerCase().includes(search.value.toLowerCase()) ||
      data.model.toLowerCase().includes(search.value.toLowerCase())
  );
});

//
function openCreateModal() {
  console.log("Opening create modal"); // 添加调试信息
  selectedCar.value = {}; // 清空选中的车辆信息
  isEdit.value = false;
  modalVisible.value = true;
}

function openEditModal(car) {
  selectedCar.value = { ...car };
  isEdit.value = true;
  modalVisible.value = true; // Open the modal
}

function closeModal() {
  modalVisible.value = false; // Close the modal
}

async function handleSave(carData) {
  if (isEdit.value) {
    await carApi.updateCar(carData.carid, carData);
  } else {
    await carApi.createCar(carData);
  }
  await loadData();
  closeModal();
}

// 删除数据
async function handleDelete(carId) {
  await carApi.deleteCar(carId);
  await loadData();
}
</script>

<style scoped>
#page {
  margin-left: 500px;
}
#add {
  margin-bottom: 5px;
}

#add #searchbutton {
  float: left;
  margin-right: 15px;
}

.el-table th {
  background-color: #f2f2f2; /* 表头背景色 */
  color: #333; /* 表头文字颜色 */
  font-weight: bold; /* 文字加粗 */
  border-bottom: 2px solid #ebeef5; /* 底部边框 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 阴影效果 */
  text-align: center; /* 文字居中 */
}
</style>
