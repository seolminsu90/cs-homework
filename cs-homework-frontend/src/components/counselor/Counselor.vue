<template>
  <section class="box_wrap">
    <div class="box">
      <h3 class="" style="margin-top:40px">아이디를 입력해주세요.</h3>
      <input
        v-model="counselor.id"
        type="text"
        placeholder="아이디 입력 (ex. Counselor1)"
        maxlength="20"
      />
      <h3 class="">비밀번호를 입력해주세요.</h3>
      <input
        v-model="counselor.pwd"
        type="password"
        placeholder="비밀번호 입력 (ex. 123123)"
        maxlength="20"
      />
      <button type="button" @click="counselorLogin">로그인</button>
      <p>
        <input maxlength="5" v-model="counselor.name" type="text" style="width:50px;text-align:center" placeholder="이름">
        <span @click="counselorCreate">을 추가하고 <span style="cursor:pointer;color:#559922">신규생성</span></span>
      </p>
    </div>
  </section>
</template>

<script>
export default {
  name: 'Counselor',
  methods: {
    counselorCreate(){
      if (this.counselor.id.length < 4 || this.counselor.pwd.length < 4){ return alert('아이디 또는 패스워드는 4자 이상이어야 합니다.') }
      if (this.counselor.name.length < 1){ return alert('이름을 입력하세요') }
      var $this = this
      this.$axios
        .post('http://localhost:8090/counselors', this.counselor)
        .then(response => {
          var result = response.data
          $this.counselor = $this.counselorInit
          if (result.code === 0){
            alert('생성 성공.')
          } else {
            return alert(result.code + ', ' + result.msg)
          }
        })
        .catch(err => {
          this.counselor.pwd = ''
          $this.errResponse(err)
        })
    },
    counselorLogin(){
      if (this.counselor.id.length < 4 || this.counselor.pwd.length < 4){ return alert('아이디 또는 패스워드는 4자 이상이어야 합니다.') }

      var $this = this
      this.$axios
        .post('http://localhost:8090/counselors/login', this.counselor)
        .then(response => {
          var result = response.data
          if (result.code === 0){
            alert('로그인 성공.')

            $this.$cookies.set('token', result.data.token)
            $this.$store.commit('setCounselorId', result.data.id, {
              root: true,
            })
            $this.navigate('/counselor/main')
          } else {
            this.counselor.pwd = ''
            return alert(result.code + ', ' + result.msg)
          }
        })
        .catch(err => {
          this.counselor.pwd = ''
          $this.errResponse(err)
        })
    },
  },
  data(){
    return {
      counselorInit: {
        id: '',
        pwd: '',
        name: '',
      },
      counselor: {
        id: 'Counselor1',
        pwd: '123123',
        name: '',
      },
    }
  },
}
</script>

<style scoped></style>
