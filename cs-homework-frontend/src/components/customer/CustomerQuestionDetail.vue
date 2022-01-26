<template>
  <div class="article_wrap">
    <article>
      <ul>
        <div class="titlePart">
          <p>
            <span class="title titleNo">문의번호[ {{quetion.seq}} ]</span>
            <span class="title">{{quetion.title}}</span>
          </p>
          <p class="stateAndDate">
            <span class="state init">접수완료</span>
            <span class="date">{{quetion.createDate}}</span>
          </p>
        </div>
        <div class="contentPart">
        </div>
      </ul>
    </article>
  </div>
</template>

<script>
export default {
  name: 'CustomerQuestionDetail',
  props: ['question'],
  mounted(){
  },
  methods: {
  },
  data(){
    return {
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
  border-bottom:1px solid #ccc;
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
  font-size:10px;color:#ccc;
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
.titlePart p,.contentPart p{
  margin: 0; font-size:12px;
}
.contentPart p{
  padding-bottom:5px;
}
.titlePart span, .contentPart span{
  font-size:12px;
}
.titlePart span.title{
  font-size:15px;
}
</style>
