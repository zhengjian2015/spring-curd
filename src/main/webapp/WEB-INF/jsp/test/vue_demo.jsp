<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test vue</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/js/vue.min.js"></script>
<div id="app">
	<p>{{message}}</p>
	<a v-bind:href="url">百度</a>
	<a v-on:click="doSomething">动作</a>
	<input type="text" v-model="message" />
</div>
<script type="text/javascript">

new Vue({
	el:"#app",
	data:{
		message:"Hello Vue!",
		url:"http://www.baidu.com"
	},
	methods:{
		doSomething:function(){
			console.log('doSomething...');
		}
	}
});
</script>
</html>