<script setup>
import { ref, computed, onMounted } from "vue";
import apiService from "../api/apiService";
import UserModal from "./UserModel.vue"; // 确保你有一个UserModal组件

const search = ref("");
const tableData = ref([]);
const modalVisible = ref(false);
const isEdit = ref(false);
const selectedUser = ref({});

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

    const response = await apiService.getUsers(currentPage, currentPageSize);

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
      data.name.toLowerCase().includes(search.value.toLowerCase())
  );
});

//
function openCreateModal() {
  console.log("Opening create modal"); // 添加调试信息
  selectedUser.value = {}; // 清空选中的用户信息
  isEdit.value = false;
  modalVisible.value = true;
}

function openEditModal(user) {
  selectedUser.value = { ...user };
  isEdit.value = true;
  modalVisible.value = true; // Open the modal
}

function closeModal() {
  modalVisible.value = false; // Close the modal
}

async function handleSave(userData) {
  if (isEdit.value) {
    await apiService.updateUser(userData.id, userData);
  } else {
    await apiService.createUser(userData);
  }
  await loadData();
  closeModal();
}

// 删除数据
async function handleDelete(userId) {
  await apiService.deleteUser(userId);
  await loadData();
}
</script>

<template>
  <div id="add">
    <div id="searchbutton">
      <el-button type="info" @click="openCreateModal">添加管理员</el-button>
    </div>
    <el-input
      v-model="search"
      placeholder="Type to search"
      style="width: 300px"
    />
  </div>
  <div id="usertable">
    <el-table
      v-loading="loading"
      :data="filterTableData"
      style="width: 100%"
      show-header="true"
      border
    >
      <el-table-column align="center" label="id" prop="id" width="100px" />
      <el-table-column align="center" label="name" prop="name" />
      <el-table-column align="center" label="password" prop="password" />
      <el-table-column align="center" label="email" prop="email" />
      <el-table-column align="center" label="操作">
        <template #default="scope">
          <el-button size="small" @click="openEditModal(scope.row)"
            >Edit</el-button
          >
          <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.row.id)"
            >Delete</el-button
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
  >
  </el-pagination>
  <!-- 用户编辑/创建模态框 -->
  <UserModal
    :visible="modalVisible"
    :user-data="selectedUser"
    @close="closeModal"
    @save="handleSave"
  ></UserModal>
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
/* #usertable {
  width: 90%;
  margin: 0 auto;
} */
</style>
