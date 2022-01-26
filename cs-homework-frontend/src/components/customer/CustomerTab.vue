<template>
  <div class="tab">
    <div>
      <span class="id">{{cid}}</span>
      <span @click="clearCustomerId">변경</span>
    </div>
    <ul>
      <li :class="{on:tab === 1}" @click="emitToParent($event, 1)">문의하기</li>
      <li :class="{on:tab === 2}" @click="emitToParent($event, 2)">문의내역</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'CustomerTab',
  props: ['cid', 'tab'],
  methods: {
    clearCustomerId(){
      this.$store.commit('logout')
      this.navigate('/')
    },
    emitToParent(e, no){
      this.$emit('tabToParent', no)
    },
  },
  data(){
    return {
    }
  },
}
</script>

<style scoped>
</style>
