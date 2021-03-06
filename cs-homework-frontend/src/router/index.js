import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/components/Main'

import Customer from '@/components/Customer'
import CustomerMain from '@/components/CustomerMain'

import Counselor from '@/components/Counselor'
import CounselorMain from '@/components/CounselorMain'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Main,
    },
    {
      path: '/customer',
      name: 'Customer',
      component: Customer,
    },
    {
      path: '/customer/main',
      name: 'CustomerMain',
      component: CustomerMain,
    },
    {
      path: '/counselor',
      name: 'Counselor',
      component: Counselor,
    },
    {
      path: '/counselor/main',
      name: 'CounselorMain',
      component: CounselorMain,
    },
  ],
})
