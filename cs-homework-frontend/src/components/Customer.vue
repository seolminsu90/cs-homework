<template>
  <section class="box_wrap">
    <div class="box">
      <h3 class="hcenter">고객 아이디를 입력해주세요.</h3>
      <input type="text" v-model.trim="customer.id" placeholder="아이디 입력" maxlength="20"/>
      <button type="button" @click="customerNext">다음</button>
    </div>
  </section>
</template>

<script>
export default {
  name: 'Customer',
  data(){
    return {
      customer: {
        id: 'KakaoCS',
      },
    }
  },
  methods: {
    customerNext(){
      if (this.customer.id.length < 4){
        return alert('4자이상 입력해주세요.')
      }
      this.$store.commit('setCustomerId', this.customer.id, {root: true})
      this.navigate('/customer/main')
    },
  },
}
</script>

<style scoped>
</style>
