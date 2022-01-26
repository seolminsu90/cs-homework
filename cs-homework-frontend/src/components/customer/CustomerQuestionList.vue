<template>
  <div class="article_wrap">
    <article v-if="mode == 'list'">
      <ul>
        <li v-if="questions.length == 0">문의 내역이 없습니다.</li>
        <li v-for="(q,idx) in questions" @click="showDetail(q)" v-bind:key="idx">
          <div class="titlePart">
            <p>
              <span class="title titleNo">문의번호[ {{q.seq}} ]</span>
              <span class="title">{{q.title}}</span>
            </p>
            <p class="stateAndDate">
              <span class="state init">접수완료</span>
              <span class="date">{{q.createDate}}</span>
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
          <!--<span class="state regist">담당자지정</span>
          <span class="state finish">답변완료</span>-->
          <span class="date">{{question.createDate}}</span>
        </p>
      </div>
      <div class="contentPart">
        <p><span>Q.</span>
        <pre>{{question.content}}</pre>
        </p>
        <!--<p><span></span>
        <pre>{{question.content}}</pre>
        </p>-->
        <button type="button" @click="hideDetail">뒤로가기</button>
      </div>
    </article>
  </div>
</template>

<script>
import CustomerQuestionDetail from '@/components/customer/CustomerQuestionDetail'

export default {
  name: 'CustomerQuestionList',
  props: ['cid'],
  components: {
    'customerQuestionDetail': CustomerQuestionDetail,
  },
  mounted(){
    this.getQuestions()
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
    getQuestions(){
      var $this = this
      var url = 'http://localhost:8090/customers/questions'
      var params = {
        params: {
          customerId: this.cid,
        },
      }
      this.$axios.get(url, params).then((response) => {
        var result = response.data
        if (result.code === 0){
          console.log('getListOk')
          $this.questions = result.data
        } else {
          return alert(result.code + ', ' + result.msg)
        }
      }).catch(err => {
        console.log(err)
        if (err.response.status === 400){
          alert('Id 정보가 없습니다.')
          $this.navigate('/')
        }
      })
    },
  },
  data(){
    return {
      question: {
        'content': '',
        'customerId': '',
        'regDate': null,
        'seq': null,
        'title': '',
      },
      questionInit: {
        'content': '',
        'customerId': '',
        'regDate': null,
        'seq': null,
        'title': '',
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
