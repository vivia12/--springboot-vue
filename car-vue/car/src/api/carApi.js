import axios from 'axios';

const API_URL = 'http://localhost:8080/cars';

export default {
  getCars(pageNum = 1, pageSize = 10) {
    return axios.get(`${API_URL}/page`, { params: { pageNum, pageSize } });
  },
  getAllCars() {
    return axios.get(`${API_URL}/all`);
  },
  createCar(carData) {
    return axios.post(API_URL, carData);
  },
  updateCar(carId, carData) {
    return axios.put(`${API_URL}/${carId}`, carData);
  },
  deleteCar(carId) {
    return axios.delete(`${API_URL}/${carId}`);
  },
  getCarsByPassengers(requiredPassengers) {
    return axios.get(`${API_URL}/by-passengers`, {params: {requiredPassengers }});
  },
  getTrend(){
    return axios.get(`${API_URL}/trend`);
  },

increase(carId, amount) {
  return axios.post(`${API_URL}/increase`, null, { 
    params: { carid: carId, increaseAmount: amount }
  });
}


};
