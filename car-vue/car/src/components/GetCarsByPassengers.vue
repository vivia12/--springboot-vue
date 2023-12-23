<template>
  <div id="card">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <!-- 输入乘客数量的输入框 -->
          <el-input
            v-model="passengerCount"
            placeholder="请输入乘客数量"
            style="width: 200px; margin-right: 5px"
            clearable
          ></el-input>

          <!-- 查询按钮 -->
          <el-button type="primary" @click="searchCars">查询</el-button>
        </div>
      </template>
      <!-- 符合条件的车辆列表 -->
      <el-table
        v-if="isListVisible"
        :data="cars"
        border
        style="width: 100%"
        stripe
      >
        <el-table-column label="汽车ID" prop="carid"></el-table-column>
        <el-table-column label="制造商" prop="brand"></el-table-column>
        <el-table-column label="型号" prop="model"></el-table-column>
        <el-table-column label="租赁价格" prop="rentalprice"></el-table-column>
        <el-table-column
          label="乘客容量"
          prop="passengercapacity"
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
        </el-button></template
      >
    </el-card>
  </div>
</template>

<script>
import carApi from "../api/carApi"; // 导入你的 axios 接口服务

export default {
  data() {
    return {
      passengerCount: "", // 用于接收用户输入的乘客数量
      showCars: false, // 用于控制是否显示车辆列表
      cars: [],
      isListVisible: false, // 用于存储符合条件的车辆数据
      disabled: true,
    };
  },
  methods: {
    searchCars() {
      if (this.passengerCount == "") {
        alert("输入不能为空");
        console.log("用户输入为空");
      } else {
        carApi
          .getCarsByPassengers(this.passengerCount)
          .then((response) => {
            this.cars = response.data;
            this.showCars = true;
            this.isListVisible = true;
            this.disabled = false;
          })
          .catch((error) => {
            console.error("Error fetching data:", error);
          });
      }
    },
  },
};
</script>
