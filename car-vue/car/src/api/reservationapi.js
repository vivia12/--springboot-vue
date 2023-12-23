import axios from 'axios';

const API_URL = 'http://localhost:8080/reservations';

export default {
  getReservations(pageNum = 1, pageSize = 10) {
    return axios.get(`${API_URL}/page`, { params: { pageNum, pageSize } });
  },
  createReservation(reservationData) {
    return axios.post(API_URL, reservationData);
  },
  updateReservation(reservationId, reservationData) {
    return axios.put(`${API_URL}/${reservationId}`, reservationData);
  },
  deleteReservation(reservationId) {
    return axios.delete(`${API_URL}/${reservationId}`);
  },
  getAllReservations() {
    return axios.get(`${API_URL}/all`);
  },
};
