<script>
import locationApi from "../api/locationApi";
export default {
  data() {
    return {
      popularLocaions: [],
      isListVisible: false,
      disabled: true,
    };
  },
  methods: {
    async getPopularLocations() {
      try {
        const response = await locationApi.getPopular();
        this.popularLocaions = response.data;
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
          <el-button type="success" round @click="getPopularLocations"
            >展示租赁地点的欢迎程度</el-button
          >
        </div>
      </template>
      <el-table
        row-class-name="warning-row"
        :data="popularLocaions"
        v-if="isListVisible && popularLocaions.length > 0"
        border
      >
        <el-table-column label="地点ID" prop="locationId"></el-table-column>
        <el-table-column
          label="详细地址"
          prop="streetAddress"
        ></el-table-column>
        <el-table-column label="城市" prop="city"></el-table-column>
        <el-table-column label="州" prop="state"></el-table-column>
        <el-table-column
          label="Number of Rentals"
          prop="numberOfRentals"
        ></el-table-column>
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
