import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    customerId: null,
    counselorId: null,
  },
  mutations: {
    setCounselorId(state, payload){
      state.counselorId = payload
    },
    setCustomerId(state, payload){
      state.customerId = payload
    },
    logout(state){
      state.counselorId = null
      state.customerId = null
    },
  },
})
