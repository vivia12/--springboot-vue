<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { ElMessageBox } from "element-plus";

const user = ref(localStorage.getItem("username"));
const router = useRouter();
const todashboard = () => {
  router.push("/dashboard");
};

const handleLogout = () => {
  // Show a confirmation prompt
  ElMessageBox.confirm("确定要退出登录吗?", "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  }).then(() => {
    // User clicked "确定", perform logout and navigate to the login page
    localStorage.removeItem("username");
    router.push("/login");
  });
};
const emit = defineEmits(["docollapse"]);
const collapse = () => {
  console.log("点击了伸缩");

  emit("docollapse");
};
</script>

<template>
  <div style="float: left; font-size: 25px" id="icon">
    <el-icon @click="collapse"><Fold /></el-icon>
  </div>
  <div class="toolbar">
    <div class="profile-picture">
      <el-avatar
        src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
      />
    </div>
    <el-dropdown>
      <div class="el-dropdown-link">
        <span id="user-name">{{ user }}</span>
        <el-icon color="white" id="icon"><ArrowDown /></el-icon>
      </div>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item @click="todashboard">个人中心</el-dropdown-item>
          <el-dropdown-item @click="handleLogout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>

<style scoped>
.toolbar {
  height: 60px;
  float: right;
  margin-right: 20px;
  display: flex;
  align-items: center;
}

.profile-picture {
  margin-right: 15px;
}

#user-name {
  color: white;
  font-weight: bold;
}

.el-dropdown-linkf {
  line-height: 60px;
}

#icon {
  color: white;
  z-index: 99;
  line-height: 60px;
}
</style>
