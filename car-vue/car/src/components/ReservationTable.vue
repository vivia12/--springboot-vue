<script setup>
import { ref, computed, onMounted } from "vue";
import reservationApi from "../api/reservationapi";
import ReservationModel from "@/components/ReservationModel.vue";

const search = ref("");
const reservations = ref([]);
const modalVisible = ref(false);
const isEdit = ref(false);
const selectedReservation = ref({});

// Pagination state
const pagination = ref({
  currentPage: 1,
  pageSize: 10,
  total: 0,
});

// Load reservations data
onMounted(async () => {
  await loadData();
});

const loadData = async () => {
  try {
    const currentPage = pagination.value.currentPage;
    const currentPageSize = pagination.value.pageSize;

    const response = await reservationApi.getReservations(
      currentPage,
      currentPageSize
    );

    reservations.value = response.data.records;
    pagination.value.total = response.data.total;
  } catch (error) {
    console.error("Error fetching data:", error);
  }
};

// Handle page size change
const handleSizeChange = (newSize) => {
  pagination.value.pageSize = newSize;
  loadData();
};

// Handle current page change
const handleCurrentChange = (newPage) => {
  pagination.value.currentPage = newPage;
  loadData();
};

// Filtered reservations based on search input
const filteredReservations = computed(() => {
  return reservations.value.filter(
    (data) =>
      !search.value ||
      data.reservationstatus.toLowerCase().includes(search.value.toLowerCase())
  );
});

// Open create modal
const openCreateModal = () => {
  selectedReservation.value = {};
  isEdit.value = false;
  modalVisible.value = true;
};

// Open edit modal
const openEditModal = (reservation) => {
  selectedReservation.value = { ...reservation };
  isEdit.value = true;
  modalVisible.value = true;
};

// Close modal
const closeModal = () => {
  modalVisible.value = false;
};

// Handle save (create/update) reservation
const handleSave = async (reservationData) => {
  if (isEdit.value) {
    await reservationApi.updateReservation(
      reservationData.reservationid,
      reservationData
    );
  } else {
    await reservationApi.createReservation(reservationData);
  }
  await loadData();
  closeModal();
};

// Handle delete reservation
const handleDelete = async (reservationId) => {
  await reservationApi.deleteReservation(reservationId);
  await loadData();
};
</script>

<template>
  <div>
    <div id="add">
      <div id="searchbutton">
        <el-button type="info" @click="openCreateModal">添加预约</el-button>
      </div>
      <el-input
        v-model="search"
        placeholder="Type to search"
        style="width: 300px"
      />
    </div>

    <el-table :data="filteredReservations" style="width: 100%" border>
      <el-table-column
        label="预定ID"
        prop="reservationid"
        width="100px"
      ></el-table-column>
      <el-table-column
        label="客户ID"
        prop="customerid"
        width="100px"
      ></el-table-column>
      <el-table-column
        label="汽车ID"
        prop="carid"
        width="100px"
      ></el-table-column>
      <el-table-column
        label="开车地点ID"
        prop="pickuplocationid"
        width="100px"
      ></el-table-column>
      <el-table-column
        label="还车地点ID"
        prop="dropofflocationid"
        width="100px"
      ></el-table-column>
      <el-table-column label="开始日期" prop="startdate"></el-table-column>
      <el-table-column label="还车日期" prop="enddate"></el-table-column>
      <el-table-column label="总花费" prop="totalcost"></el-table-column>
      <el-table-column
        label="预定状态"
        prop="reservationstatus"
      ></el-table-column>
      <el-table-column align="center" label="操作" width="200px">
        <template #default="scope">
          <el-button size="small" @click="openEditModal(scope.row)"
            >Edit</el-button
          >
          <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.row.reservationid)"
          >
            Delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>

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

    <!-- ReservationModel component -->
    <ReservationModel
      :visible="modalVisible"
      :reservation-data="selectedReservation"
      @close="closeModal"
      @save="handleSave"
    ></ReservationModel>
    <div style="height: 300px"></div>
  </div>
</template>

<style scoped>
#add {
  margin-bottom: 5px;
}
#page {
  margin-left: 400px;
}
#add #searchbutton {
  float: left;
  margin-right: 15px;
}
</style>
