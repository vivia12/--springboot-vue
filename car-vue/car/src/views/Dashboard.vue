<script setup>
import { ref } from "vue";
import AsideMenu from "../components/AsideMenu.vue";
import HeaderSection from "../components/HeaderSection.vue";

// 初始化 isCollapse 为 false 或 true 根据您的需求
const isCollapse = ref(false);

const docollapse = () => {
  // 修改 isCollapse 的值
  isCollapse.value = !isCollapse.value;
  console.log(isCollapse);
};
</script>

<template>
  <el-container class="layout-container-demo" style="height: 100vh">
    <!-- 侧边栏 -->
    <el-aside width="auto" class="sidebar">
      <AsideMenu :isCollapse="isCollapse"></AsideMenu>
    </el-aside>
    <el-container style="display: flex; flex-direction: column">
      <!-- 头部 -->
      <el-header id="custom-header">
        <HeaderSection @docollapse="docollapse"></HeaderSection>
      </el-header>
      <!-- 主体 -->
      <el-main class="custom-main">
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<style scope>
.layout-container-demo .el-header {
  position: relative;
}

.layout-container-demo .el-menu {
  border-right: none;
}
.layout-container-demo .el-main {
  padding: 0;
}

#custom-header {
  height: 60px;
  border-bottom: 1px solid #ebeef5;
  font-size: 14px;
  background-color: #333;
}

.custom-main {
  /* 主内容区样式 */
  width: 100%;
  height: 100%;
  padding: 20px;
  box-sizing: border-box;
  margin-top: 20px;
}
</style>
