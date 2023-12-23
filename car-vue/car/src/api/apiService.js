import axios from 'axios';

const API_URL = 'http://localhost:8080/user';

export default {
  getUsers(pageNum = 1, pageSize = 10) {
    return axios.get(`${API_URL}/page`, { params: { pageNum, pageSize } });
  },
  getAllUsers() {
    return axios.get(`${API_URL}/all`);
  },
  createUser(userData) {
    return axios.post(API_URL, userData);
  },
  updateUser(userId, userData) {
    return axios.put(`${API_URL}/${userId}`, userData);
  },
  deleteUser(userId) {
    return axios.delete(`${API_URL}/${userId}`);
  }
}
