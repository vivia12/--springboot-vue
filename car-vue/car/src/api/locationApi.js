import axios from 'axios';

const API_URL = 'http://localhost:8080/locations';

export default {
  getLocations(pageNum = 1, pageSize = 10) {
    return axios.get(`${API_URL}/page`, { params: { pageNum, pageSize } });
  },
  createLocation(locationData) {
    return axios.post(API_URL, locationData);
  },
  updateLocation(locationId, locationData) {
    return axios.put(`${API_URL}/${locationId}`, locationData);
  },
  deleteLocation(locationId) {
    return axios.delete(`${API_URL}/${locationId}`);
  },
  getAllLocations() {
    return axios.get(`${API_URL}/all`);
  },
  getPopular(){
    return axios.get(`${API_URL}/popular`);
  }
};
