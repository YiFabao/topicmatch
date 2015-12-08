<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/"; 
	String access_token = request.getParameter("access_token");
	String code = request.getParameter("code");
	String weixin_code = request.getParameter("weixin_code");
	if(access_token!=null&&!"".equals(access_token))
	{
		response.sendRedirect(request.getContextPath()+"/servlet/qq_login?access_token="+access_token);
	}else if(code!=null&&!"".equals(code)){
		response.sendRedirect(request.getContextPath()+"/servlet/weiboLogin?code="+code);
	}else if(weixin_code!=null&&!"".equals(weixin_code)){
		response.sendRedirect(request.getContextPath()+"/servlet/weixinLogin?weixin_code="+weixin_code);
	}
	so.xunta.utils.LogUtils logutil = new so.xunta.utils.LogUtils();
	//logutil.traceLog(request, "本地账户登录页面");
%>


<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta name="renderer" content="webkit">
	<title>登录-寻TA网</title>
	<%pageContext.setAttribute("baseUrl",request.getContextPath());%>
	<link rel="shortcut icon" href="${baseUrl }/assets/xunta.ico" type="image/x-icon">
	<link rel="icon" href="${baseUrl }/assets/xunta.ico" type="image/x-icon">
	<link rel="stylesheet" href="<%=basePath %>jsp/topic/css/base.css">
	<!--[if lte IE 8]>
		<style>
			/*ie8支持css3
			.header .search,.top-memory .tab-menu,.talk-list .tp,.talk-list .enter,.form .load{behavior:url("${pageContext.request.contextPath }/jsp/topic/js/ie-css3.htc");}*/
		</style>
		<script src="${pageContext.request.contextPath }/jsp/topic/js/html5.js"></script>
	<![endif]-->
</head>
<body>
<header class="header">
		<div class="head">
			<h1 class="logo"><a href="/">寻TA网</a></h1>
			<div class="form search">
				<input type="text" class="text" disabled>
				<input type="submit" class="iconfont submit" value="&#xe602;" disabled>
			</div>
			<nav class="nav">
				<ul class="fix">
					<li><a href="#" >话题推荐</a></li>
					<li><a href="#" >话题记忆</a></li>
					<li><a href="#" >发起话题</a></li>
				</ul>
			</nav>
			<div class="user">
				<a href="#" class="admin" disabled>未登录</a>
			<!-- 	<a href="#">注册</a> -->
				<a class="news" href="#" style="line-height:48px;">
					<i class="iconfont">&#xe603;</i>
			<!-- 		<span class="dunk"></span> -->
				</a>
			</div>
		</div>
	</header>

	<div class="login">
		<!-- 直接登录 -->
		<div class="dialog-box d2">
			<div class="login-2 l form">
				<h3>登录</h3>
				<c:if test="${requestScope.errorMsg!=null}">
					<font color="red" id="errorMsg">${requestScope.errorMsg }</font>
				</c:if>
				<form action="<%=basePath %>servlet/login" method="post">
					<input type="hidden" name="from" value="/jsp/xunta_user/login2.jsp">
					<ul>
						<li class="item first">
							<label for="UserName" class="label-a">用户名</label>
							<input name="xunta_username" type="text" id="UserName" class="text-b wta" placeholder="邮箱/用户名" data-min="1" required>
						</li>
						<li class="item mb10">
							<label for="PassWord" class="label-a">密&emsp;码</label>
							<input type="password" name="password" id="PassWord" class="text-b wta" data-min="1" required>
						</li>
						<li class="item-b">
							<label class="cbox" for="RememberPw">
								<i class="check"></i>
								<i class="iconfont checked">&#xe600;</i>
								<input type="checkbox" id="RememberPw" class="dn">记住密码
							</label> 
							<a href="#" class="a1">忘记密码？</a>
						</li>
						<li><input type="submit" class="btn-a wta" value="登&emsp;录" onclick="return checkPwdNull();"></li>
					</ul>
				</form>
			</div>
			<div class="login-1 l">
				<h3>第三方登录</h3>
					<ul class="quick-login">
						<li><a href="#" title="微信" class="weixin_login" ><img src="<%=basePath %>jsp/topic/images/wx.png">微信</a></li>
						<li><a href="#" title="腾讯QQ" class="qq_login" ><img src="<%=basePath %>jsp/topic/images/qq.png">QQ</a></li>
						<li><a href="#" title="新浪微博" class="weibo_login"><img src="<%=basePath %>jsp/topic/images/sina.png">新浪</a></li>
						<li class="justify-fix">&nbsp;</li>
					</ul>
			</div>
		</div>
	</div>
<div id="Tip"><span class="txt"></span></div>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery-1.4.4.min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery.placeholder-1.0.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery-powerSwitch-min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery.lavalamp.min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery-html5Validate-min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/common.js"></script>
<script>
$("form").html5Validate(function() {
	var self = this;
		self.submit(); 
});
//密码全为空字符是不准提交
function checkPwdNull()
{
	var pwd = $('#PassWord').val().trim();
	console.log("checkPwd");
	if(!pwd||pwd=="")
	{
		//alert("密码不能全为空字符");
		Tip("密码不能全为空字符");
		return false;
	}
	return true;
}
//用户修改错语密码时去除登录用户名或密码出错消息提示
$("#UserName,#PassWord").focus(function(){
	$("#errorMsg").css("display","none");
});

$(function(){
	//qq登录
	$(".qq_login").click(function(){
		var redirect_url = "http://xunta.so/jsp/xunta_user/qq_token.jsp";
		var url = "https://graph.qq.com/oauth2.0/authorize?response_type=token&client_id=101100198&redirect_uri="+ redirect_url + "&scope=do_like,get_user_info,get_info,add_t";
		window.location=url;
	});
	//微博登录
	$(".weibo_login").click(function(){
		var redirect_uri = "http://xunta.so/servlet/weiboLogin";
		var  url = "https://api.weibo.com/oauth2/authorize?client_id=3793162942&response_type=code&redirect_uri="+redirect_uri;
		window.location=url;
	});
	//微信登录
	$(".weixin_login").click(function(){
		var redirect_uri = "http://xunta.so/servlet/weixinLogin";
		var  url = "https://open.weixin.qq.com/connect/qrconnect?appid=wx0ad98a24caca02ca&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_login&state=d967dc101ad34ff81062309e2be96b46#wechat_redirect";
		window.location=url;
	});
});
</script>
</body>
</html>