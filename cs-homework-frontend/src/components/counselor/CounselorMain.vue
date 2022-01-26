<template>
  <section class="box_wrap">
    <div class="bigbox">
      <counselorTab @tabToParent="changeTab" :cid="this.counselorId" :tab="tab"></counselorTab>
      <counselorQuestionList v-if="tab == 1"></counselorQuestionList>
      <counselorQuestionReceive v-if="tab == 2"></counselorQuestionReceive>
    </div>
  </section>
</template>

<script>
import CounselorTab from '@/components/counselor/CounselorTab'
import CounselorQuestionList from '@/components/counselor/CounselorQuestionList'
import CounselorQuestionReceive from '@/components/counselor/CounselorQuestionReceive'

export default {
  name: 'CounselorMain',
  components: {
    'counselorTab': CounselorTab,
    'counselorQuestionList': CounselorQuestionList,
    'counselorQuestionReceive': CounselorQuestionReceive,
  },
  mounted(){
    var $this = this
    this.counselorId = $this.$store.state.counselorId
    this.checkCounselorId()
  },
  methods: {
    changeTab(value){
      this.tab = value
    },
    checkCounselorId(){
      var jwtClaim = this.parseJwt(this.$cookies.get('token'))
      if (jwtClaim === null){ this.navigate('/') }
    },
  },
  data(){
    return {
      tab: 1,
      counselorId: null,
    }
  },
}
</script>

<style scoped>
textarea{
  height:270px
}
</style>
