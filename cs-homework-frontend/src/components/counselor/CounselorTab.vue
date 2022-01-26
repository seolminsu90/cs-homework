<template>
  <div class="tab">
    <div>
      <span class="id">{{cid}}</span>
      <span @click="clearCounselor">로그아웃</span>
    </div>
    <ul>
      <li :class="{on:tab === 1}" @click="emitToParent($event, 1)">미답변 문의</li>
      <li :class="{on:tab === 2}" @click="emitToParent($event, 2)">할당 내역</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'CounselorTab',
  props: ['cid', 'tab'],
  data(){
    return {
    }
  },
  methods: {
    clearCounselor(){
      this.$store.commit('logout')
      this.$cookies.remove('token')
      this.navigate('/')
    },
    emitToParent(e, _no){
      console.log('tab' + _no)
      this.$emit('tabToParent', _no)
    },
  },
}
</script>

<style scoped>
</style>
