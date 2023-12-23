import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import axios from 'axios';
import './assets/global.css';
import * as ElementPlusIconsVue from '@element-plus/icons-vue';

const app = createApp(App);

// Configure base URL for Axios
axios.defaults.baseURL = 'http://localhost:8080/';

app.use(ElementPlus);
app.use(router);

// 注册所有 Element Plus Icons
for (const iconName in ElementPlusIconsVue) {
  app.component(iconName, ElementPlusIconsVue[iconName]);
}

app.mount('#app');
