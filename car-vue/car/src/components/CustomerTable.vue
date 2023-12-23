<script setup>
import { ref, computed, onMounted } from "vue";
import apiCustomers from "../api/apiCustomers";
import CustomerModel from "./CustomerModel.vue";

const search = ref("");
const tableData = ref([]);
const modalVisible = ref(false);
const isEdit = ref(false);
const selectedCustomer = ref({});

const pagination = ref({
  currentPage: 1,
  pageSize: 10,
  total: 0,
});

async function loadData() {
  try {
    const loading = ref(true);
    const currentPage = pagination.value.currentPage;
    const currentPageSize = pagination.value.pageSize;

    const response = await apiCustomers.getCustomers(
      currentPage,
      currentPageSize
    );

    tableData.value = response.data.records;
    pagination.value.total = response.data.total;
    loading.value = false;
  } catch (error) {
    console.error("Error fetching data:", error);
  }
}

function handleCurrentChange(newPage) {
  pagination.value.currentPage = newPage;
  loadData();
}

function handleSizeChange(newSize) {
  pagination.value.pageSize = newSize;
  loadData();
}

onMounted(async () => {
  await loadData();
});

const filterTableData = computed(() => {
  return tableData.value.filter(
    (data) =>
      !search.value ||
      data.fullname.toLowerCase().includes(search.value.toLowerCase())
  );
});

function openCreateModal() {
  console.log("Opening create modal");
  selectedCustomer.value = {};
  isEdit.value = false;
  modalVisible.value = true;
}

function openEditModal(customer) {
  selectedCustomer.value = { ...customer };
  isEdit.value = true;
  modalVisible.value = true;
}

function closeModal() {
  modalVisible.value = false;
}

async function handleSave(customerData) {
  if (isEdit.value) {
    await apiCustomers.updateCustomer(customerData.customerid, customerData);
  } else {
    await apiCustomers.createCustomer(customerData);
  }
  await loadData();
  closeModal();
}

async function handleDelete(customerId) {
  await apiCustomers.deleteCustomer(customerId);
  await loadData();
}
</script>

<template>
  <el-backtop :right="100" :bottom="100" />
  <div id="add">
    <div id="searchbutton">
      <el-button type="info" @click="openCreateModal">添加客户</el-button>
    </div>
    <el-input
      v-model="search"
      placeholder="Type to search"
      style="width: 300px"
    />
  </div>
  <div id="customertable">
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
        prop="customerid"
        width="100px"
        fixed
      />
      <el-table-column align="center" label="姓名" prop="fullname" />
      <el-table-column align="center" label="地址" prop="address" />
      <el-table-column align="center" label="电话号码" prop="phonenumber" />
      <el-table-column align="center" label="邮箱" prop="email" />
      <el-table-column
        align="center"
        label="信用卡号"
        prop="creditcardnumber"
      />
      <el-table-column
        align="center"
        label="行驶证号码"
        prop="drivinglicensenumber"
      />
      <el-table-column
        align="center"
        label="行驶证状态"
        prop="licenseissuestate"
      />
      <el-table-column
        align="center"
        label="行驶证过期时间"
        prop="licenseexpirationdate"
      />

      <el-table-column align="center" label="是否为学生" prop="isstudent">
        <template #default="scope">
          <el-tag
            :type="scope.row.isstudent ? 'success' : 'info'"
            disable-transitions
          >
            {{ scope.row.isstudent ? "是" : "否" }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column
        align="center"
        label="交通违法记录"
        prop="violationstatus"
      >
        <template #default="scope">
          <span v-if="scope.row.violationstatus">
            {{ scope.row.violationstatus }}
          </span>
          <span v-else> None </span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="操作" width="150px">
        <template #default="scope">
          <el-button size="small" @click="openEditModal(scope.row)"
            >Edit</el-button
          >
          <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.row.customerid)"
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
  <!-- 客户编辑/创建模态框 -->
  <CustomerModel
    :visible="modalVisible"
    :customer-data="selectedCustomer"
    @close="closeModal"
    @save="handleSave"
  ></CustomerModel>
  <!-- 分割线 -->
  <el-divider style="margin-top: 40px">
    <el-icon><star-filled /></el-icon>
  </el-divider>

  <div style="height: 500px"></div>
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
  background-color: #f2f2f2;
  color: #333;
  font-weight: bold;
  border-bottom: 2px solid #ebeef5;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  text-align: center;
}
</style>
