<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="header">
	<div class="head">
		<h1 class="logo"><a href="/">寻TA网</a></h1>
		<div class="form search">
			<input type="text" class="text">
			<input type="submit" class="iconfont submit" value="&#xe602;">
		</div>
		<nav class="nav">
			<ul class="fix">
				<li><a href="#">话题推荐</a></li>
				<li class="current"><a href="#">话题记忆</a></li>
				<li><a href="#">发起话题</a></li>
			</ul>
		</nav>
		<div class="user">
			<a href="#" class="admin">登录</a>
			<a href="#">注册</a>
			<a class="news" href="#">
				<i class="iconfont">&#xe603;</i>
				<span class="dunk">15</span>
			</a>
		</div>
	</div>
</header>

<script>
	//全局环境变量
	var contextPath ="${pageContext.request.contextPath}";
	var currentUserId ="${sessionScope.user.id}";
	var currentUserName="${sessionScope.user.xunta_username}";
	var signout=document.getElementsByClassName("signOut")[0];
	signout.addEventListener("click",function(){
		console.log("退出登录,删除记录登录状态的　cookie");
		window.location.href="${pageContext.request.contextPath}/servlet/topic_service?cmd=exit";
	});
</script>