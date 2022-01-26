<template>
  <section class="box_wrap">
    <div class="box" @click="navigate('/customer')">
      <h1 class="hcenter">고객</h1>
      <h2>문의하기</h2>
    </div>
    <div class="box" @click="navigate('/counselor')">
      <h1 class="hcenter">상담원</h1>
      <h2>답변하기</h2>
    </div>
  </section>
</template>

<script>
export default {
  name: 'Main',
  data () {
    return {
    }
  }
}
</script>

<style scoped>
</style>
