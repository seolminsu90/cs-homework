<template>
  <div class="article_wrap">
    <article>
      <p>
        <span>문의 제목 (Id. {{cid}})</span>
      </p>
      <p>
        <input maxlength="50" v-model="question.title" type="text"/>
      </p>
      <p>
        <span>문의 내용 </span>
      </p>
        <p>
        <textarea maxlength="100" v-model="question.content" style="height:270px;"></textarea>
      </p>
      <button type="button" @click="writeQuestion">확인</button>
    </article>
  </div>
</template>

<script>
export default {
  name: 'CustomerQuestion',
  props: ['cid'],
  methods: {
    writeQuestion(){
      var $this = this
      this.question.customerId = this.cid
      this.$axios.post('http://localhost:8090/customers/questions', this.question).then((response) => {
        var result = response.data
        if (result.code === 0){
          alert('접수되었습니다.')
          $this.question = $this.questionInit
          $this.$emit('tabToParent', 2)
        } else {
          return alert(result.code + ', ' + result.msg)
        }
      }).catch(err => {
        $this.errResponse(err)
      })
    },
  },
  data(){
    return {
      questionInit: {
        title: '',
        content: '',
        customerId: '',
      },
      question: {
        title: '',
        content: '',
        customerId: '',
      },
    }
  },
}
</script>

<style scoped>
article{
  overflow-y:scroll;
  position:relative;
}
.x-button{
  position:absolute;
  cursor:pointer;
  top:0px;right:20px;
  width:60px;height:30px;font-size:30px;
  line-height:30px;
  font-weight:bold;
}

article {
    -ms-overflow-style: none; /* for Internet Explorer, Edge */
    scrollbar-width: none; /* for Firefox */
    overflow-y: scroll;
}

article::-webkit-scrollbar {
    display: none; /* for Chrome, Safari, and Opera */
}

.content {
    -ms-overflow-style: none; /* for Internet Explorer, Edge */
    scrollbar-width: none; /* for Firefox */
    overflow-y: scroll;
}

.content::-webkit-scrollbar {
    display: none; /* for Chrome, Safari, and Opera */
}

input, textarea{
  width:100%;
  border:1px solid #ccc;
  padding:0px;
  height:35px;
  resize:none;
  fond-size:20px;
}

pre{
  padding:0;margin:0;
  margin-bottom:10px;
}

textarea{
  height:100px;
}
.content{
  border:1px solid #ccc;
  height:130px;
  overflow-y:scroll;
}
</style>
