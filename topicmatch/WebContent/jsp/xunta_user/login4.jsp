<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/"; 
%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta name="renderer" content="webkit">
	<title>登录-寻TA网</title>
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
		<!-- 创建账户 -->
		<div class="dialog-box d2">
			<div class="login-4 form">
					<h3 style="position:relative;left:40%;">基本资料填写<span class="f14"></span></h3>
					<p style="margin-bottom:25px;font-size:17px;font-weight:normal;text-shadow:0 0;">二.创建本地账号</p>
					<form  class="l" data-preventDefault="validate_false" >
						<div class="item">
							<label class="dt" for="UserNameR">用&ensp;户&ensp;名</label>
							<div class="dd">
								<input type="text" class="text-c wtb" id="UserNameR" placeholder="手机号/邮箱/用户名" data-min="1" required>
							</div>
						</div>
						<div class="item">
							<label for="PassWordR" class="dt">密&emsp;&emsp;码</label>
							<div class="dd">
								<input type="password" id="PassWordR" class="text-c wtb pwd" data-min="6" required>
							</div>
						</div>
						<div class="item">
							<label for="PassWordRC" class="dt">确认密码</label>
							
							<div class="dd">
								<input type="password" id="PassWordRC" class="text-c wtb pwdcheck" data-min="6" required disabled>
							</div>
						</div>
						<div class="item pt14">
							<label for="Code" class="dt">验&ensp;证&ensp;码</label>
							<div class="dd">
								<input type="text" id="Code" class="text-c wtb" required>
								<!-- <a href="#" class="hove-code" id="getValidateCode">获取验证码</a> -->
								<br/>
								<img src="<%=basePath%>servlet/validateCodeServlet" width=80 height=30 style="margin-top:10px;" class="validateImage" onclick="changeValiCode(this)"/><small><i>点击图片换验证码</i></small><br />
							</div>
						</div>
						<div class="opear" style="position:absolute;bottom:40px;left:25px;">
							<button class="btn-d wtb" type="button" id="bind_local_account">下一步</button>
						</div>
					</form>
					<div class="r">
						<p class="tip" style="margin-bottom:130px;">本地帐号将关联您的第三方登，<br>录帐号. 在第三方登录失效时,<br>可直接用本地帐号登录.<br>如果”毅然跳过”, 以后可在”昵<br>称->个人资料”中设置.</p>
						<button class="btn-b wtb" id="ComReg">毅然跳过</button>
					</div>
				</div>
		</div>
	</div>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery-1.4.4.min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery.placeholder-1.0.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery-powerSwitch-min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery.lavalamp.min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery-html5Validate-min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/common.js"></script>
<script>
checkPwd("form")

/* var isUnique = false;

//ajax验证用户名是否存在
function checkNameUnique()
{
	var username = $('#UserNameR').val().trim();
	if(isNull(username)||username=="手机号/邮箱/用户名")
	{
		$('#UserNameR').testRemind("用户名不能为空").get(0).select();  
		return;
		//return false;
	}
	$.post("${pageContext.request.contextPath}/servlet/userLoginService?cmd=checkNameUnique",username,function(res,status){
		if(res=="no")
		{
			$('#UserNameR').testRemind("用户已存在").get(0).select();
			isUnique = false;
		}
		else
		{
			isUnique = true;
		}
	});	
}

$('#UserNameR').focus(function(){
	isUnique = true;
});

$('#UserNameR').blur(function(){
	checkNameUnique();
}); */

//切换验证码
function changeValiCode(obj)
{
	$(obj).attr("src","<%=basePath%>servlet/validateCodeServlet?"+Math.random());	
}
//绑定本地账户，表单提交
$("#bind_local_account").click(function(){
	console.log("绑定账号后用户提交...");
	//获取填写的用户名
	var userNameR=$("#UserNameR").val();
	//获取填写的密码 
	var passWordR=$("#PassWordR").val();
	var passWordRC=$("#PassWordRC").val();
	//获取填写的验证码
	var validateCodeR=$("#Code").val();
	console.log("用户输入====>用户名："+userNameR);
	console.log("用户输入====>密码 ："+userNameR);
	console.log("用户输入====>验证码 ： ："+validateCodeR);
	var flag = checkForm(userNameR,passWordR,passWordRC,validateCodeR);
	if(flag)
	{
		console.log("验证通过...");
		$.post("<%=basePath%>servlet/userLoginService",{
			cmd:"bindlocalaccount",
  			userNameR:userNameR,
  			passwordR:passWordR,
  			validateCodeR:validateCodeR
		},function(res,state){
			if(res=="ok")
			{
				window.location="${pageContext.request.contextPath}/jsp/xunta_user/login5.jsp";
			}else{
				console.log(res);
				//alert(res);
				$('#Code').testRemind("验证码输入错误").get(0).select();  
				$(".validateImage").attr("src","<%=basePath%>servlet/validateCodeServlet?"+Math.random());
			}
		});
	}else{
		console.log("验证不通过...");
		//$(".validateImage").attr("src","<%=basePath%>servlet/validateCodeServlet?"+Math.random());
	}
});

//验证基本资料填写之绑定本地账户密码的表单
function checkForm(userNameR,passwordR,passWordRC,validateCodeR){
	var flag=true;
	//用户名是否为空
	if(isNull(userNameR)||userNameR=="手机号/邮箱/用户名")
	{
		console.log("用户名为空");
		$('#UserNameR').testRemind("用户名不能为空").get(0).select();  
	}
	if(!isEqual(passwordR,passWordRC))
	{
		console.log("两次密码输入不相同");
		$('#PassWordRC').testRemind("两次密码输入不相同").get(0).select(); 
		flag = false;
	}
	if(passwordR.length<6){
		console.log("密码长度小于6位");
		$('#PassWordR').testRemind("密码长度小于6位").get(0).select();  
		flag=false;
	}
	if(isNull(validateCodeR))
	{
		$('#Code').testRemind("验证码为空").get(0).select();  
		flag=false;
	}
	/* if(!isUnique)
	{
		$('#UserNameR').testRemind("用户已存在").get(0).select();
		flag=false;
	} */
	return flag;
	function isNull( str ){ 
		if ( str == "" ) return true; 
		var regu = "^[ ]+$"; 
		var re = new RegExp(regu); 
		return re.test(str); 
	};
	
	function isEqual(str1,str2){
		return str1==str2;
	};
};

$("#ComReg").click(function(){
	//跳到servlet再跳到login5.jsp
	window.location="${pageContext.request.contextPath}/servlet/userLoginService?cmd=jumpBindAccountStep";
});


</script>
</body>
</html>