// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from '@/router/index'
import store from '@/store/store'
import axios from 'axios'
import VueCookies from 'vue-cookies'
// import moment from 'vue-moment'

Vue.config.productionTip = false
Vue.prototype.$axios = axios
Vue.config.devtools = true
Vue.use(VueCookies)
Vue.$cookies.config('7d')
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*'

Vue.mixin({
  methods: {
    navigate(path){
      this.$router.push(path)
    },
    parseJwt(token){
      try {
        var base64Url = token.split('.')[1]
        var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/')
        var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c){
          return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)
        }).join(''))

        return JSON.parse(jsonPayload)
      } catch (e){
        return null
      }
    },
    errResponse(err){
      if (err.response === undefined) return alert('unknown error')

      let msg = err.response.data !== undefined ? err.response.data.msg : ''
      switch (err.response.status){
        case 401:{
          alert('인증이 실패했습니다. \n' + msg)
          return
        }
        case 403:{
          alert('권한이 없습니다. 필요한 권한을 할당해야합니다.\n' + msg)
          this.navigate('/')
          return
        }
        case 400:{
          return alert('파라미터가 잘못됬습니다..\n' + msg)
        }
        case 500:{
          return alert('내부 서버 오류\n' + msg)
        }
      }
    },
    logOut(){
      this.deleteCookie('crawler-access-key')
      this.$store.commit('logout')
      this.navigate('/')
    },
    deleteCookie(name){
      document.cookie = name + '=; expires=Thu, 01 Jan 1999 00:00:10 GMT;'
    },
    checkCustomerId(){
      if (this.$store.state.customerId == '') this.navigate('/')
    },
  },
})

new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
  store,
  created: function(){
    var token = this.getCookie('crawler-access-key')
    if (token != null){
      this.$store.commit('loginAfter', token)
    }
  },
  methods: {
    getCookie(name){
      var v = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)')
      return v ? v[2] : null
    },
  },
})
