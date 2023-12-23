import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Dashboard from '../views/dashboard.vue'
import Usertable from '../components/UserTable.vue'
import CarTable from '../components/CarTable.vue'
import CustomerTable from '../components/CustomerTable.vue'
import LocationTable from '../components/LocationTable.vue'
import Function from '../components/Function.vue'

import ReservationTable from '../components/ReservationTable.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login,
      redirect: '/login',
      children:[
        {
          path: '/login',
          name: 'login',
          component: Login,
        },
      ]
    },
   
    {
      path: '/dashboard',
      name: 'dashboard',
      component: Dashboard,
      redirect: '/dashboard/user', // Default child path when visiting /dashboard
      children:[
        {
          path: 'user', // Removed the leading slash
          name: 'user',
          component: Usertable
        },
        {
          path: 'car', // Removed the leading slash
          name: 'car',
          component: CarTable
        },
        {
          path: 'custom', // Removed the leading slash
          name: 'custom',
          component: CustomerTable
        },
        {
          path: 'location', // Removed the leading slash
          name: 'location',
          component: LocationTable
        },
        {
          path: 'Reservation', // Removed the leading slash
          name: 'Reservation',
          component: ReservationTable
        },
        {
          path: 'function', // Removed the leading slash
          name: 'Function',
          component: Function
        }
      ]
    },
    // ... other routes if any
  ]
})

export default router
