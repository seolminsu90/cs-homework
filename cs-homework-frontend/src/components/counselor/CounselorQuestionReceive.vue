<template>
  <div class="article_wrap">
    <article v-if="mode == 'list'">
      <ul>
        <li v-if="questions.length == 0">문의 내역이 없습니다.</li>
        <li v-for="(q,idx) in questions" @click="showDetail(q)" v-bind:key="idx">
          <div class="titlePart">
            <p>
              <span class="title titleNo">문의번호[ {{q.questionSeq}} ]</span>
              <span class="title">{{q.title}}</span>
            </p>
            <p class="stateAndDate">
              <span v-if="q.checkState === 0" class="state regist">담당자지정</span>
              <span v-else-if="q.checkState === 1" class="state finish">답변완료</span>
              <span class="date">{{q.regDate}}(문의일시)</span>
            </p>
          </div>
        </li>
      </ul>
    </article>
    <article v-if="mode == 'detail'">
      <div class="titlePart">
        <p>
          <span class="title titleNo">문의번호[ {{questionReceive.questionSeq}} ]</span>
          <span class="title">{{questionReceive.title}}</span>
        </p>
        <p class="stateAndDate" v-if="questionReceive.checkState === 0">
          <span class="state regist">담당자지정</span>
          <span class="date">{{questionReceive.createDate}}(할당일)</span>
        </p>
        <p class="stateAndDate" v-else-if="questionReceive.checkState === 1">
          <span class="state finish">답변완료</span>
          <span class="date">{{questionReceive.responseDate}}(답변일)</span>
        </p>
      </div>
      <div class="contentPart">
        <p><span>Q.</span>
          <pre>{{questionReceive.questionContent}}</pre>
        </p>
        <p style="margin:20px 0"><span>A.</span>
          <pre v-if="questionReceive.checkState === 1">{{questionReceive.responseContent}}</pre>
          <input maxlength="100" v-else-if="questionReceive.checkState === 0" v-model="content" type="text" placeholder="답변입력"/>
        </p>

        <button v-if="questionReceive.checkState === 0" type="button" @click="answer(questionReceive.questionSeq)" style="margin-bottom:10px">답변등록</button>
        <button type="button" @click="hideDetail">뒤로가기</button>
      </div>
    </article>
  </div>
</template>

<script>

export default {
  name: 'CounselorQuestionReceive',
  mounted(){
    this.getQuestions()
  },
  methods: {
    hideDetail(){
      this.questionReceive = this.questionReceiveInit
      this.mode = 'list'
    },
    showDetail(q){
      this.questionReceive = q
      console.log(q)
      this.mode = 'detail'
    },
    answer(id){
      if (id === null || id === '' || id === undefined){ return alert('error') }

      if (this.content.length === 0){ return alert('답변을 입력해주세요.') }

      var $this = this
      var url = 'http://localhost:8090/counselors/questions/' + id + '/response'
      var config = {
        headers: {
          Authorization: this.$cookies.get('token'),
        },
      }
      var body = {
        content: this.content,
      }
      this.$axios.put(url, body, config).then((response) => {
        var result = response.data
        if (result.code === 0){
          console.log('answerOk')
          alert('등록되었습니다.')
          $this.questionReceive = $this.questionReceiveInit
          $this.content = null
          $this.hideDetail()
          $this.getQuestions()
        } else {
          return alert(result.code + ', ' + result.msg)
        }
      }).catch(err => {
        $this.errResponse(err)
      })
    },
    getQuestions(){
      var $this = this
      var url = 'http://localhost:8090/counselors/questions-own'
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
        $this.errResponse(err, function(){
          $this.navigate('/')
        })
      })
    },
  },
  data(){
    return {
      questionReceive: {
        questionSeq: null,
        counselorId: '',
        content: '',
        responseContent: '',
        customerId: '',
        regDate: null,
        seq: null,
        title: '',
        counselorName: '',
        createDate: null,
        responseDate: null,
      },
      questionReceiveInit: {
        questionSeq: null,
        counselorId: '',
        content: '',
        responseContent: '',
        customerId: '',
        regDate: null,
        seq: null,
        title: '',
        counselorName: '',
        createDate: null,
        responseDate: null,
      },
      content: '',
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
