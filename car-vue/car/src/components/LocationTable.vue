<script setup>
import { ref, computed, onMounted } from "vue";
import locationApi from "../api/locationApi";
import LocationModel from "./LocationModel.vue";

const search = ref("");
const tableData = ref([]);
const modalVisible = ref(false);
const isEdit = ref(false);
const selectedLocation = ref({});

// 分页状态
const pagination = ref({
  currentPage: 1,
  pageSize: 10,
  total: 0,
});

// 更新加载数据函数，以包含分页逻辑
async function loadData() {
  try {
    //加载
    const loading = ref(true);
    const currentPage = pagination.value.currentPage;
    const currentPageSize = pagination.value.pageSize;

    const response = await locationApi.getLocations(
      currentPage,
      currentPageSize
    );

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
      data.locationCode.toLowerCase().includes(search.value.toLowerCase()) ||
      data.address.toLowerCase().includes(search.value.toLowerCase()) ||
      data.city.toLowerCase().includes(search.value.toLowerCase()) ||
      data.state.toLowerCase().includes(search.value.toLowerCase()) ||
      data.zipCode.toLowerCase().includes(search.value.toLowerCase())
  );
});

//
function openCreateModal() {
  console.log("Opening create modal"); // 添加调试信息
  selectedLocation.value = {}; // 清空选中的位置信息
  isEdit.value = false;
  modalVisible.value = true;
}

function openEditModal(location) {
  selectedLocation.value = { ...location };
  isEdit.value = true;
  modalVisible.value = true; // Open the modal
}

function closeModal() {
  modalVisible.value = false; // Close the modal
}

async function handleSave(locationData) {
  if (isEdit.value) {
    await locationApi.updateLocation(locationData.locationId, locationData);
  } else {
    await locationApi.createLocation(locationData);
  }
  await loadData();
  closeModal();
}

// 删除数据
async function handleDelete(locationId) {
  await locationApi.deleteLocation(locationId);
  await loadData();
}
</script>

<template>
  <div id="add">
    <div id="searchbutton">
      <el-button type="info" @click="openCreateModal">添加位置</el-button>
    </div>
    <el-input
      v-model="search"
      placeholder="Type to search"
      style="width: 300px"
    />
  </div>
  <div id="locationtable">
    <el-table
      v-loading="loading"
      :data="filterTableData"
      style="width: 100%"
      show-header="true"
      border
    >
      <el-table-column
        align="center"
        label="ID"
        prop="locationId"
        width="100px"
      />
      <el-table-column align="center" label="位置代码" prop="locationCode" />
      <el-table-column align="center" label="地址" prop="address" />
      <el-table-column align="center" label="城市" prop="city" />
      <el-table-column align="center" label="州" prop="state" />
      <el-table-column align="center" label="邮政编码" prop="zipCode" />
      <el-table-column align="center" label="操作">
        <template #default="scope">
          <el-button size="small" @click="openEditModal(scope.row)"
            >编辑</el-button
          >
          <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.row.locationId)"
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
  <!-- 位置编辑/创建模态框 -->
  <LocationModel
    :visible="modalVisible"
    :location-data="selectedLocation"
    @close="closeModal"
    @save="handleSave"
  ></LocationModel>
</template>

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
