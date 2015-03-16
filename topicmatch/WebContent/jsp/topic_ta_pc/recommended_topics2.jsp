<%@page import="so.xunta.entity.User"%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	User user = (User)session.getAttribute("user");
	if(user==null){
		user = new User();
		user.setId(1L);
		user.setXunta_username("测试账号");
		user.setImageUrl(request.getContextPath()+"/jsp/topic/images/1.jpg");
		session.setAttribute("user", user);
	} 
%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<title>话题推荐-寻TA网</title>
	<link rel="stylesheet" href="css/base.css">
	<!--[if lte IE 8]>
		<style>
			/*ie8支持css3
			.header .search,.top-memory .tab-menu,.talk-list .tp,.talk-list .enter,.form .load{behavior:url(js/ie-css3.htc);}*/
		</style>
		<script src="js/html5.js"></script>
	<![endif]-->	
	<style>
		.content,.main{position: absolute;bottom: 0;left:0;right:0;}
		.content{top:120px;}
		.main{top:0;overflow-y:auto;}
	</style>
</head>
<body>
	<header class="header">
		<div class="head">
			<h1 class="logo"><a href="/">寻TA网</a></h1>
			<div class="form search">
				<input type="text" class="text">
				<input type="submit" class="iconfont submit" value="&#xe602;">
			</div>
			<nav class="nav">
				<ul class="fix">
					<li class="current"><a href="#">话题推荐</a></li>
					<li><a href="topics_memory.html">话题记忆</a></li>
					<li><a href="#">发起话题</a></li>
				</ul>
			</nav>
			<div class="user">
				<!-- ==未登录 显示内容==
				<a href="#" class="admin">登录</a>
				<a href="#">注册</a>
				-->
				<div class="h user-area">
					<i class="user-pic"><img src="images/delete/user_pic.jpg" alt=""></i>
					<span class="grade">T17</span>
					<div class="pop">
						<i class="link"></i>
						<div class="t">
							<p class="name">齐天大圣</p>
							<ul class="menu-list">
								<li><a href="#"><i class="iconfont">&#xe60a;</i>个人信息</a></li>
								<li><a href="#"><i class="iconfont">&#xe60c;</i>账号设置</a></li>
								<li><a href="#"><i class="iconfont">&#xe60b;</i>退出</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="h news-area">
					<i class="news" href="#">
						<i class="iconfont">&#xe603;</i>
						<span class="dunk">15</span>
					</i>
					<div class="pop">
						<i class="link"></i>
						<div class="t">
							<ul class="fix tab-list">
								<li>
									<a href="javascript:;" class="tab-on" data-rel="topicsRequest">话题请求<i class="new">·</i></a>
								</li>
								<li><a href="javascript:;" data-rel="systemMessages">系统消息<i class="new">·</i></a></li>
							</ul>
							<ul class="tab-content">
								<li class="tab-panel" id="topicsRequest">
									<ul class="news-list">
										<li class="panel">
											<p class="txt">某某moumou 邀请你参与话题#我们去过的地方#</p>
											<time>2015-3-15&emsp;12:24:13</time>
											<ul class="fix opera">
												<li class="btn">
													<button class="yes">同意</button>
												</li>
												<li class="btn">
													<button class="no">拒绝</button>
												</li>
											</ul>
										</li>
										<li class="panel">
											<p class="txt">某某moumou 邀请你参与话题#我们去过的地方#</p>
											<time>2015-3-15</time>
											<ul class="fix opera">
												<li class="btn vh">
													<button class="yes"></button>
												</li>
												<li class="btn">
													已同意
												</li>
											</ul>
										</li>
									</ul>
								</li>
								<li class="tab-panel dn" id="systemMessages">
									<ul class="news-list">
										<li class="panel">
											<p class="txt">网站已经全面上线公测，如果你有任何问题都可以与我们联系！</p>
											<time>2015-3-15&emsp;12:24:13</time>
										</li>
										<li class="panel">
											<p class="txt">网站已经全面上线公测，如果你有任何问题都可以与我们联系！</p>
											<time>2015-3-15&emsp;12:24:13</time>
										</li>
									</ul>
								</li>
							</ul>
							
						</div>
					</div>
				</div>
			</div>			
			<!-- 已登录 -->
		</div>
	</header>

</body>
</html>