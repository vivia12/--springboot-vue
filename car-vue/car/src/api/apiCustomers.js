import axios from 'axios';

const API_URL = 'http://localhost:8080/customers';

export default {
  getCustomers(pageNum = 1, pageSize = 10) {
    return axios.get(`${API_URL}/page`, { params: { pageNum, pageSize } });
  },
  getAllCustomers() {
    return axios.get(`${API_URL}/all`);
  },
  createCustomer(customerData) {
    return axios.post(API_URL, customerData);
  },
  updateCustomer(customerId, customerData) {
    return axios.put(`${API_URL}/${customerId}`, customerData);
  },
  deleteCustomer(customerId) {
    return axios.delete(`${API_URL}/${customerId}`);
  },
  getCustomersOnProbation(){
    return axios.get(`${API_URL}/onProbation`)
  }
};


