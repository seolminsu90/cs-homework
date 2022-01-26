<template>
  <div class="article_wrap">
    <article v-if="mode == 'list'">
      <ul>
        <li v-if="questions.length == 0">문의 내역이 없습니다.(10초마다 갱신됩니다.)</li>
        <li v-for="(q,idx) in questions" @click="showDetail(q)" v-bind:key="idx">
          <div class="titlePart">
            <p>
              <span class="title titleNo">문의번호[ {{q.seq}} ]</span>
              <span class="title">{{q.title}}</span>
            </p>
            <p class="stateAndDate">
              <span class="state init">접수완료</span>
              <span class="date">{{q.regDate}}</span>
            </p>
          </div>
        </li>
      </ul>
    </article>
    <article v-if="mode == 'detail'">
      <div class="titlePart">
        <p>
          <span class="title titleNo">문의번호[ {{question.seq}} ]</span>
          <span class="title">{{question.title}}</span>
        </p>
        <p class="stateAndDate">
          <span class="state init">접수완료</span>
          <span class="date">{{question.regDate}}</span>
        </p>
      </div>
      <div class="contentPart">
        <p><span>Q.</span>
        <pre>{{question.content}}</pre>
        </p>
        <button type="button" @click="receive(question.seq)" style="margin-bottom:10px">문의할당</button>
        <button type="button" @click="hideDetail">뒤로가기</button>
      </div>
    </article>
  </div>
</template>

<script>

export default {
  name: 'CounselorQuestionList',
  props: ['cid'],
  mounted(){
    this.getQuestions()
    this.pollingInverval = setInterval(this.getQuestions, 10000)
  },
  destroyed(){
    clearInterval(this.pollingInverval)
  },
  methods: {
    hideDetail(){
      this.question = this.questionInit
      this.mode = 'list'
    },
    showDetail(q){
      this.question = q
      this.mode = 'detail'
    },
    receive(id){
      if (id === null || id === '' || id === undefined){
        return alert('error')
      }

      var $this = this
      var url = 'http://localhost:8090/counselors/questions/' + id + '/receive'
      var config = {
        headers: {
          Authorization: this.$cookies.get('token'),
        },
      }
      this.$axios.post(url, {}, config).then((response) => {
        var result = response.data
        if (result.code === 0){
          console.log('receiveOk')
          alert('할당됬습니다.')
          $this.hideDetail()
          $this.getQuestions()
        } else {
          return alert(result.code + ', ' + result.msg)
        }
      }).catch(err => {
        console.log(err.response)
        if (err.response.data.msg !== undefined){
          alert(err.response.msg)
          $this.navigate('/')
        } else {
          return alert('error')
        }
      })
    },
    getQuestions(){
      var $this = this
      var url = 'http://localhost:8090/counselors/questions'
      var config = {
        headers: {
          Authorization: this.$cookies.get('token'),
        },
      }
      this.$axios.get(url, config).then((response) => {
        var result = response.data
        if (result.code === 0){
          console.log('getListOk')
          $this.questions = result.data
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
      pollingInverval: Function,
      question: {
        content: '',
        customerId: '',
        regDate: null,
        seq: null,
        title: '',
      },
      questionInit: {
        content: '',
        customerId: '',
        regDate: null,
        seq: null,
        title: '',
      },
      questions: [],
      mode: 'list',
    }
  },
}
</script>

<style scoped>
ul{
  height:100%;
  overflow-y:scroll;
}
.contentPart{
  padding:10px;
  width:calc(100% - 20px);
}
.titlePart{
  width:100%;height:40px;
  position:relative;
  border-bottom:1px solid #ccc;
  line-height:40px;
  cursor:pointer;
}
.titlePart .stateAndDate{
  position:absolute;
  top:0px;right:0px;
}
.titlePart .stateAndDate span.date{
  font-size:10px;color:#ccc;
}
.contentPart span.date{
  font-size:20px;color:#ccc;
}
.titlePart .stateAndDate span.state.init{
  color:red;
}
.titlePart .stateAndDate span.state.regist{
  color:orange;
}
.titlePart .stateAndDate span.state.finish{
  color:green;
}
.contentPart{
  height:auto;display:block;
}
.titlePart p {
  margin: 0; font-size:12px;
}
.contentPart p{
  margin: 0; font-size:22px;
}
.contentPart p{
  padding-bottom:5px;
}
.titlePart span {
  font-size:12px;
}
.contentPart span{
  font-size:22px; color: #996600
}
.titlePart span.title{
  font-size:15px;
}
</style>
