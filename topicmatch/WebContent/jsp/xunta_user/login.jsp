<!DOCTYPE html>
<html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/"; 
	String qq_accesstoken=request.getParameter("access_token");
	String code = request.getParameter("code");
	if(qq_accesstoken!=null&&!"".equals(qq_accesstoken)){
		request.getRequestDispatcher("/servlet/qq_login?accesstoken="+qq_accesstoken).forward(request,response);
	}
	if(code!=null&&!"".equals(code)){
		request.getRequestDispatcher("/servlet/weiboLogin?cmd="+code).forward(request,response);
	}
%>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="icon" href="favicon.ico">
		<script type="text/javascript" src="<%=basePath %>assets/javascripts/jquery-1.10.2.js"></script>
		<!-- 寻他logo -->

		<title>XunTa 用户登录首页</title>

		<!-- Bootstrap core CSS -->
		<link href="<%=basePath %>assets/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
	</head>

	<body>
	
		<div class="container">
			<h2>用户登 录</h2>
			${requestScope.errorMsg }
			<form method="post" action="<%=basePath %>servlet/login">
				<input type="text" name="xunta_username" value="${xunta_username}"><br/>
				<input type="password" name="password"><br/>
				<input type="checkbox" name="checkbox">记住我<br/>
				<button type="submit">登  录</button>
			</form>
			<button class="register">注 册</button>
			<br>
			<div>
				<img id="qq_login" src="<%=basePath %>assets/images/QQ/Connect_logo_3.png"/>
				<img id="weibo_login" src="<%=basePath %>assets/images/weibo/xinlang.png"/> 
			</div>
		</div>
		
	<script type="text/javascript">

		$("#qq_login").click(function(){
			window.location="<%=basePath %>servlet/authorization";
		});
		$("#weibo_login").click(function(){
			var redirect_uri = "http://xunta.so/jsp/xunta_user/jsp_token.jsp";
			var  url = "https://api.weibo.com/oauth2/authorize?client_id=3793162942&response_type=code&redirect_uri="+redirect_uri;
			window.location=url;
		});
		var register_node=document.getElementsByClassName("register")[0];
		register_node.addEventListener("click",function(){
			window.location="<%=request.getContextPath()%>/jsp/xunta_user/register.jsp";
		});
	
	
	</script>

	</body>
</html>
