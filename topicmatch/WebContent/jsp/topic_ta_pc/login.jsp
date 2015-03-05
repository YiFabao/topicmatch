<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/"; 
	String qq_accesstoken=request.getParameter("access_token");
	String code = request.getParameter("code");
	if(qq_accesstoken!=null&&!"".equals(qq_accesstoken)){
		request.getRequestDispatcher("/servlet/qq_login").forward(request,response);
	}
	if(code!=null&&!"".equals(code)){
		request.getRequestDispatcher("/servlet/weiboLogin").forward(request,response);
	}
%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<title>登录-寻TA网</title>
	<link rel="stylesheet" href="css/base.css">
	<link rel="stylesheet" href="css/mobilebone-min.css">
	<!--[if lte IE 8]>
		<style>
			/*ie8支持css3
			.header .search,.top-memory .tab-menu,.talk-list .tp,.talk-list .enter,.form .load{behavior:url(js/ie-css3.htc);}*/
		</style>
		<script src="js/html5.js"></script>
	<![endif]-->
</head>
<body onLoad="checkCookie()">
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
					<li><a href="#">话题记忆</a></li>
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
	<div class="login">
		<!-- 第三方登录 -->
		<div class="page" id="SignInWith" style="display:none">
			<div class="dialog-box login-1">
				<h3>第三方登录</h3>
				<ul class="quick-login">
					<li><a href="#" title="微信"><img src="images/wx.png">微信</a></li>
					<li><a href="#" title="腾讯QQ"><img src="images/qq.png">QQ</a></li>
					<li><a href="#" title="新浪微博"><img src="images/sina.png">新浪</a></li>
					<li class="justify-fix">&nbsp;</li>
				</ul>
				<div class="tc f16">
					<a href="#Login">已有账号，直接登录</a>
				</div>
			</div>
		</div>
		<!-- 直接登录 -->
		<div class="page out" id="Login" style="display:block">
			<div class="dialog-box d2">
				<div class="login-2 l form">
					<h3>登录</h3>
					<font color="red" id="errorMsg" style="display:none">用户名或密码错误</font>
					<form action="" data-preventDefault="validate_false" method="post">
						<ul>
							<li class="item first">
								<label for="UserName" class="label-a">用户名</label>
								<input type="text" id="UserName" name="xunta_username" class="text-b wta" placeholder="邮箱/用户名" data-min="1" required>
							</li>
							<li class="item">
								<label for="PassWord" class="label-a">密&emsp;码</label>
								<input type="password" id="PassWord" name="password" class="text-b wta" data-min="1" required>
							</li>
							<li>记住密码 <a href="#">忘记密码？</a></li>
							<li><input type="button" class="btn-a" value="登录" id="btn_login"></li>
						</ul>
						
					</form>
				</div>
				<div class="login-1 l">
					<h3>第三方登录</h3>
					<ul class="quick-login">
						<li><a href="#" title="微信"><img src="images/wx.png">微信</a></li>
						<li><a href="#" title="腾讯QQ" id="qq_login"><img src="images/qq.png">QQ</a></li>
						<li><a href="#" title="新浪微博" id="weibo_login"><img src="images/sina.png">新浪</a></li>
						<li class="justify-fix">&nbsp;</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
<script src="js/jquery-1.4.4.min.js"></script>
<script src="js/jquery.placeholder-1.0.js"></script>
<script src="js/jquery-powerSwitch-min.js"></script>
<script src="js/jquery.easing.min.js"></script>
<script src="js/jquery.lavalamp.min.js"></script>
<script src="js/mobilebone-min.js"></script>
<script src="js/jquery-html5Validate-min.js"></script>
<script src="js/common.js"></script>
<script>
	 //阻止表单提交	
var validate_false = function(form) {
	console.log("阻止表单提交");
    return false;    
};
//qq登录
$("#qq_login").click(function(){
	window.location="<%=basePath %>servlet/authorization";
});
//微博登录
$("#weibo_login").click(function(){
	var redirect_uri = "http://xunta.so/jsp/xunta_user/jsp_token.jsp";
	var  url = "https://api.weibo.com/oauth2/authorize?client_id=3793162942&response_type=code&redirect_uri="+redirect_uri;
	window.location=url;
});
//直接登录
$("#btn_login").click(function(){
	var userName=$("#UserName").val();
	var password=$("#PassWord").val();
	console.log(userName+"|"+password);
	$(this).attr("disabled",false);
	$.post("<%=basePath %>servlet/login",{xunta_username:userName,password:password},function(res,state){
		console.log(res);
		if(res=="failure"){
			console.log("用户名或密码错误");
			$("#errorMsg").css("display","block");
			$("#btn_login").removeAttr("disabled");
		}
		else if(res=="success")
		{
			console.log("登录成功");
			window.location.replace("<%=basePath %>jsp/topic/index.jsp");
		}
	});
});

//用户修改错语密码时去除登录用户名或密码出错消息提示
$("#UserName,#PassWord").focus(function(){
	$("#errorMsg").css("display","none");
});

//判断用户是否注册或登录过
 function getCookie(c_name)
 {
     if (document.cookie.length>0)
     {
         c_start=document.cookie.indexOf(c_name + "=")
         if (c_start!=-1)
         {
             c_start=c_start + c_name.length+1
             c_end=document.cookie.indexOf(";",c_start)
             if (c_end==-1) c_end=document.cookie.length
             return unescape(document.cookie.substring(c_start,c_end))
         }
     }
     return ""
 }

 function setCookie(c_name,value,expiredays)
 {
     var exdate=new Date()
     exdate.setDate(exdate.getDate()+expiredays)
     document.cookie=c_name+ "=" +escape(value)+ ((expiredays==null) ? "" : "; expires="+exdate.toGMTString())
 }

 function checkCookie()
 {
     aigine_login_state=getCookie('aigine_login_state')
     if (aigine_login_state!=null && aigine_login_state!="")
     {
         //有cookie记录
         //alert('Welcome again '+aigine_login_state+'!')
         console.log("有cookie记录");
         window.location.href="#Login";
     }
     else
     {
    	 console.log("没有cookie记录");
    	 console.log("第三方登录");
    	 window.location.href="#SignInWith";
/*          aigine_login_state=prompt('Please enter your aigine_login_sate:',"");//获取cookie

         if (aigine_login_state!=null && aigine_login_state!="")
         {
             console.log("add cookie");
             setCookie('aigine_login_state',aigine_login_state,365);
         } */
     }
 }

 function deleteCookie(){
     console.log("delete cookie");
     document.cookie = "aigine_login_state=; expires="+new Date();
 }

</script>
</body>
</html>