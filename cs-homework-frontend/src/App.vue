<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>

export default {
  name: 'App',
}
</script>

<style>

* {
  font-family: 'NanumBarunGothic', sans-serif !important;
}
@font-face {
  font-family:'NanumBarunGothic';
  src: url('assets/fonts/NanumBarunGothic.ttf') format('truetype');
  font-weight: 400;
}
@font-face {
  font-family:'NanumBarunGothic';
  src: url('assets/fonts/NanumBarunGothicLight.ttf') format('truetype');
  font-weight: 300;
}
@font-face {
  font-family:'NanumBarunGothic';
  src: url('assets/fonts/NanumBarunGothicBold.ttf') format('truetype');
  font-weight: 700;
}

html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p,
  blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn,
  em, img, ins, kbd, q, s, samp, small, strike, strong, sub, sup, tt, var,
  b, u, i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend,
  table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas,
  details, embed, figure, figcaption, footer, header, hgroup, menu, nav,
  output, ruby, section, summary, time, mark, audio, video {
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  font: inherit;
  vertical-align: baseline;
}
article, aside, details, figcaption, figure, footer, header, hgroup,
  menu, nav, section {
  display: block;
}
body {
  line-height: 1;
}
body, html {
  width:100%;
  height:100%;
  background-color: #F2DC01;
}
ol, ul {
  list-style: none;
}
blockquote, q {
  quotes: none;
}
blockquote:before, blockquote:after, q:before, q:after {
  content: '';
  content: none;
}

table {
  border-collapse: collapse;
  border-spacing: 0;
}
#app{
  width:100%; height:100%;
}
button{
  border:none;
}
input {
  border:none;
  font-size:0.9em;
}
textarea:focus, input:focus{
    outline: none;
}
*:focus {
    outline: none;
}

.box_wrap{
  width: 100%;
  height: 100%;
  text-align:center;
  margin: 0 auto;
}
.box{
  width:300px;height:270px;border-radius:35px;
  background:#fff;
  display:inline-block;
  margin: calc(50vh - 150px) 10px;
  padding: 10px;
}
.box h1{
  font-weight:bold;
  font-size:50px;
  height:70px;
  letter-spacing: 3px;
}

.box .hcenter{
  margin:calc(45% - 35px) 0 0 0;
}

.box h2{
  font-weight:bold;
  font-size:30px;
  letter-spacing: 3px;
  color: #666;
}

.box h3{
  font-weight:bold;
  font-size:20px;
  letter-spacing: 3px;
  color: #666;
  text-align:left;
}

.box input{
  border-bottom: 2px solid #ccc;
  width:100%;
  margin:10px 0;
  height:30px;
  padding:0 !important;
}

button{
  cursor:pointer;
  display: block;
  width: 100%;
  height: 50px;
  padding: 0;
  border: 0;
  border-radius: 4px;
  font-size: 15px;
  color: #191919;
  background-color: #F2DC01;
}

.bigbox{
  width:900px;height:500px;border-radius:35px;
  background:#fff;
  display:inline-block;
  margin: calc(50vh - 250px) 10px;
  padding: 10px;
  position:relative;
}
.bigbox .tab{
  position:absolute;top:0px;left:0px;
  width:250px;height:calc(100% - 10px);
  margin:5px 0;
  border-right:2px solid #eee;
  text-align:left;
  text-indent:20px;
}
.bigbox .tab div{
  position: absolute;
  top:5px;left:5px;
}
.bigbox .tab span{
  font-size:12px;
  font-weight:bold;
  cursor:pointer;
}
.bigbox .tab span.id{
  color:#c34;
}
.bigbox .tab ul{
  padding-top: 50px;
}
.bigbox .tab li{
  cursor:pointer;
  padding:20px 0;
  font-size:20px;
}
.bigbox .tab li.on{
  background:#eed;
}
.bigbox .article_wrap{
  position:absolute;top:0px;left:254px;
  display:inline-block;height:100%;
  padding-top: 50px;
  width:650px;
}
article{
  text-align:left;
  padding:10px 20px;
  height:450px;
}
article p {
  font-size:20px;
  margin-bottom:10px;
}
article span {
  font-size:20px;
}

ul {
    -ms-overflow-style: none; /* for Internet Explorer, Edge */
    scrollbar-width: none; /* for Firefox */
    overflow-y: scroll;
}

ul::-webkit-scrollbar {
    display: none; /* for Chrome, Safari, and Opera */
}
</style>
