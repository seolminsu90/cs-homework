<template>
  <section class="box_wrap">
    <div class="bigbox">
      <customerTab @tabToParent="changeTab" :cid="this.customerId" :tab="tab"></customerTab>
      <customerQuestion @tabToParent="changeTab" v-if="tab === 1" :cid="this.customerId"></customerQuestion>
      <customerQuestionList v-if="tab === 2" :cid="this.customerId"></customerQuestionList>
    </div>
  </section>
</template>

<script>
import CustomerTab from '@/components/customer/CustomerTab'
import CustomerQuestion from '@/components/customer/CustomerQuestion'
import CustomerQuestionList from '@/components/customer/CustomerQuestionList'

export default {
  name: 'CustomerMain',
  components: {
    'customerTab': CustomerTab,
    'customerQuestion': CustomerQuestion,
    'customerQuestionList': CustomerQuestionList,
  },
  mounted(){
    var $this = this
    this.customerId = $this.$store.state.customerId
    this.checkCustomerId()
  },
  methods: {
    checkCustomerId(){
      if (this.customerId == null){
        alert('Id 정보가 없습니다.')
        this.navigate('/')
      }
    },
    changeTab(value){
      this.tab = value
    },
  },
  data(){
    return {
      customerId: null,
      tab: 1,
    }
  },
}
</script>

<style scoped>
textarea{
  height:270px
}
</style>
