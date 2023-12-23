<script setup>
import { RouterView } from "vue-router";

// 定义 props
const props = defineProps(["isCollapse"]);
</script>

<template>
  <el-scrollbar>
    <el-menu
      router
      default-active="/dashboard/user"
      :collapse="isCollapse"
      :collapse-transition="false"
      style="height: 100vh; background-color: whitesmoke"
      active-text-color="#ffc402"
    >
      <el-sub-menu index="/dashboard">
        <template #title>
          <el-icon><House /></el-icon>
          <span> 管理员面板</span>
        </template>
        <el-menu-item-group>
          <template #title>admin</template>
          <el-menu-item index="/dashboard/user">管理员列表</el-menu-item>
        </el-menu-item-group>
      </el-sub-menu>
      <el-sub-menu index="2">
        <template #title>
          <el-icon><Van /></el-icon>
          <span>车辆管理</span>
        </template>
        <el-menu-item-group>
          <template #title>car</template>
          <el-menu-item index="/dashboard/car">车辆列表</el-menu-item>
          <el-menu-item index="/dashboard/location">车辆租赁地点</el-menu-item>
        </el-menu-item-group>
      </el-sub-menu>
      <el-sub-menu index="3">
        <template #title>
          <el-icon><User /></el-icon>
          <span>客户管理</span>
        </template>
        <el-menu-item-group>
          <template #title>custom</template>
          <el-menu-item index="/dashboard/custom">客户数据</el-menu-item>
          <el-menu-item index="/dashboard/reservation"
            >预定订单信息</el-menu-item
          >
        </el-menu-item-group>
      </el-sub-menu>
      <el-sub-menu index="4">
        <template #title>
          <el-icon
            ><el-icon><Menu /></el-icon
          ></el-icon>
          <span>功能列表</span>
        </template>
        <el-menu-item-group>
          <template #title>function</template>
          <el-menu-item index="/dashboard/function">快速查询</el-menu-item>
        </el-menu-item-group>
      </el-sub-menu>
    </el-menu>
  </el-scrollbar>
</template>
