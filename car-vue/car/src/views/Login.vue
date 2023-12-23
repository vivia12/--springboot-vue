<script setup>
import { ref } from "vue";
import axios from "axios";
import { ElForm, ElFormItem, ElInput, ElButton } from "element-plus";
import { useRouter } from "vue-router";

const loginForm = ref({
  username: "",
  password: "",
});

const router = useRouter();

const login = async () => {
  try {
    console.log(loginForm.value);
    const response = await axios.post("user/login", loginForm.value, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    const token = response.data.token;
    // Save token to localStorage or Vuex store for future requests
    localStorage.setItem("token", token);

    // Save username to localStorage
    localStorage.setItem("username", loginForm.value.username);

    // Redirect to the dashboard
    router.push("/dashboard");
  } catch (error) {
    console.error("Login failed", error);
  }
};

const handleEnterKey = (event) => {
  if (event.key === "Enter") {
    login();
  }
};
</script>

<template>
  <div id="login">
    <el-form v-model="loginForm" :model="loginForm" label-position="top">
      <h3>Atlanta Car Share</h3>

      <el-form-item label="Username" prop="username">
        <el-input
          v-model="loginForm.username"
          @keyup.enter="handleEnterKey"
        ></el-input>
      </el-form-item>
      <el-form-item label="Password" prop="password">
        <el-input
          type="password"
          v-model="loginForm.password"
          show-password
          @keyup.enter="handleEnterKey"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="login">Login</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style scoped>
#login {
  width: 25%;
  margin: 50px auto;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  background-color: whitesmoke;
  text-align: center;
}
h3 {
  font-size: 30px;
  font-weight: bolder;
}
.el-form-item {
  margin-bottom: 20px;
}

.el-button {
  margin: 0 auto;
  width: 100px;
  border: none;
  background-color: #409eff;
  color: white;
}

.el-input {
  border: 1px solid #ddd;
  border-radius: 5px;
}

label {
  color: #555;
  font-weight: bold;
}

/* Responsive Design */
@media (max-width: 600px) {
  #login {
    width: 80%;
    margin-top: 20px;
    padding: 10px;
  }
}
</style>
