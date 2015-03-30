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
	<title>登录-寻TA网</title>
	<link rel="stylesheet" href="<%=basePath %>jsp/topic/css/base.css">
	<link rel="stylesheet" href="<%=basePath %>jsp/topic/css/mobilebone-min.css">
	<!--[if lte IE 8]>
		<style>
			/*ie8支持css3
			.header .search,.top-memory .tab-menu,.talk-list .tp,.talk-list .enter,.form .load{behavior:url(js/ie-css3.htc);}*/
		</style>
		<script src="js/html5.js"></script>
	<![endif]-->
</head>
<body onload="YearMonthDay()">
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
		<!-- 第三方登录 -->
		<div class="page dn" id="SignInWith">
			<div class="dialog-box login-1">
				<h3>第三方登录</h3>
				<ul class="quick-login">
					<li><a href="#" title="微信"><img src="<%=basePath %>jsp/topic/images/wx.png">微信</a></li>
					<li><a href="#" title="腾讯QQ"><img src="<%=basePath %>jsp/topic/images/qq.png">QQ</a></li>
					<li><a href="#" title="新浪微博"><img src="<%=basePath %>jsp/topic/images/sina.png">新浪</a></li>
					<li class="justify-fix">&nbsp;</li>
				</ul>
				<div class="tc f16">
					<a href="#Login">已有账号，直接登录</a>
				</div>
			</div>
		</div>
		<!-- 直接登录 -->
		<div class="page out" id="Login" data-params="root=$&amp;callback=login&amp;fallback=loginOut">
			<div class="dialog-box d2">
				<div class="login-2 l form">
					<h3>登录</h3>
					<form action="#FillInfo" data-preventDefault="validate_false">
						<ul>
							<li class="item first">
								<label for="UserName" class="label-a">用户名</label>
								<input type="text" id="UserName" class="text-b wta" placeholder="邮箱/用户名" data-min="1" required>
							</li>
							<li class="item mb10">
								<label for="PassWord" class="label-a">密&emsp;码</label>
								<input type="password" id="PassWord" class="text-b wta" data-min="1" required>
							</li>
							<li class="item-b">
								<label class="cbox" for="RememberPw">
									<i class="check"></i>
									<i class="iconfont checked">&#xe600;</i>
									<input type="checkbox" id="RememberPw" class="dn">记住密码
								</label> 
								<a href="#" class="a1">忘记密码？</a>
							</li>
							<li><input type="submit" class="btn-a wta" value="登&emsp;录"></li>
						</ul>
					</form>
				</div>
				<div class="login-1 l">
					<h3>第三方登录</h3>
					<ul class="quick-login">
						<li><a href="#" title="微信"><img src="<%=basePath %>jsp/topic/images/wx.png">微信</a></li>
						<li><a href="#" title="腾讯QQ"><img src="<%=basePath %>jsp/topic/images/qq.png">QQ</a></li>
						<li><a href="#" title="新浪微博"><img src="<%=basePath %>jsp/topic/images/sina.png">新浪</a></li>
						<li class="justify-fix">&nbsp;</li>
					</ul>
				</div>
			</div>
		</div>
		<!-- 基本资料填写 -->
		<div class="page out" id="FillInfo" data-params="root=$&amp;callback=fillinfo&amp;fallback=fillinOut">
			<div class="dialog-box d2">
				<div class="login-3 l form">
					<h3 style="position:relative;left:50%;">基本资料填写<span class="f14"></span></h3>
					<div class="item">
						<p style="margin-bottom:25px;font-size:17px;font-weight:normal;text-shadow:0 0;">一.以标签表达个人兴趣,匹配同趣之人:</p>
						<span class="dt">个人兴趣</span>
						<div class="dd">
							<div class="interests">
								<div class="cont mb10" id="user_input_tags">
									<!-- <a href="#" class="tag">测试<i class="iconfont del">&#xe601;</i></a> -->
								</div>
								<lable class="placeholder">填写或选择感兴趣的标签,以，隔开</lable>
								<div class="mb10">
									输入标签：<input type="text" class="text-c">
									<button class="btn-b w80" type="button" id="AddTagBtn">确定</button>
								</div>
<!-- 								<p class="tag-list">
									<span class="mr15">您可能感兴趣的标签:</span>
									<a href="#" class="tag"><span class="add">+</span>Duang</a>
									<a href="#" class="tag"><span class="add">+</span>标签</a>
									<a href="#" class="tag"><span class="add">+</span>Duang</a>
									<a href="#" class="tag"><span class="add">+</span>标签</a>
									<a href="#" class="tag"><span class="add">+</span>Duang</a>
									<a href="#" class="tag"><span class="add">+</span>标签</a>
									<a href="#" class="tag"><span class="add">+</span>Duang</a>
									<a href="#" class="tag"><span class="add">+</span>标签</a>
								</p>
								<div class="tr"><a href="#" class="a1 mr20">换一换</a></div> -->
							</div>
							<div class="fix">
							<!-- 	<a href="#Login" class="l f16 lh34">已有账号，直接登录</a> -->
							<!-- 	<button class="btn-b r wtb" data-url="#Reg" id="reg_tag">下一步</button> -->
								<button class="btn-b r wtb"  id="reg_tag">下一步</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 创建账户 -->
		<div class="page out" id="Reg" data-params="root=$&amp;callback=reg&amp;fallback=regOut">
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
								<a href="#" class="hove-code" id="getValidateCode">获取验证码</a>
							</div>
						</div>
						<div class="opear">
							<button class="btn-d wtb" id="bind_local_account">下一步</button>
						</div>
					</form>
					
					<div class="r">
						<p class="tip" style="margin-bottom:100px;">本地帐号将关联您的第三方登，<br>录帐号. 在第三方登录失效时,<br>可直接用本地帐号登录.<br>如果”毅然跳过”, 以后可在”昵<br>称->个人资料”中设置.</p>
						<button class="btn-b wtb" data-url="#ComReg">毅然跳过</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 完善资料 -->
		<div class="page out" id="ComReg" data-params="root=$&amp;callback=comreg&amp;fallback=comregoOut">
			<div class="dialog-box d2">
				<div class="login-4 form">
					<h3 style="position:relative;left:40%;">基本资料填写<span class="f14"></span></h3>
					<p style="margin-bottom:25px;font-size:17px;font-weight:normal;text-shadow:0 0;">三.继续完善 or 完成注册？</p>
					<form  id="ComRegForm" data-preventDefault="validate_false" >
						<div class="l">
							<div class="item">
								<label class="dt dtt">头&emsp;&emsp;像</label>
								<div class="dd">
									<span class="pic-area">
										<div id="imgdiv"><img id="imgShow" style="width:70px;height:70px;"/></div>
									</span>
									
									<a href="javascript:up_img.click();"  class="f14 a1">
										本地上传<input type="file" id="up_img" name="myfile" style="display:none" required/>
									</a>
									<br><small style="position:relative;left:100px;top:-20px">(头像文件不大于1M)</small>
								</div>
							</div>
							<div class="item">
								<label for="NC" class="dt">昵&emsp;&emsp;称</label>
								<div class="dd">
									<input type="text" id="NC" class="text-c wtb" data-min="1" name="nickname">
								</div>
							</div>
							<div class="item">
								<label class="dt">性&emsp;&emsp;别</label>
								<div class="dd pt5">
									<label for="Man" class="radio ml20 mr50 checked-r"><input type="radio" id="Man" name="sex" class="dn" value="m" checked="checked"><i></i>男</label>
									<label for="Woman" class="radio"><input type="radio" id="Woman" name="sex" class="dn" value="w"><i></i>女</label>
								</div>
							</div>
							<div class="item pt14">
								<label class="dt">出生日期</label>
								<div class="dd pt5">
									<div class="d-select">
										<div class="ter">
											<label></label>
											<select name=year onchange="yy(this.value)">
											<option value=""></option>
											</select>
										</div>
									</div>
									年
									<div class="d-select">
										<div class="ter">
											<label></label>
											<select name=month onchange="mm(this.value)">
												<option value=""></option>
											</select>
										</div>
									</div>
									月
									<div class="d-select">
										<div class="ter">
											<label></label>
											<select name=day>
												<option value=""></option>
											</select>
										</div>
									</div>
									日
								</div>
							</div>
							<div class="opear">
								<button class="btn-e wtb" type="button" onclick="backforward()">上一步</button>
							</div>
						</div>
						<div class="r-2">
							<div class="item">
								<label for="NC" class="dt">常&ensp;住&ensp;地</label>
								<div class="dd">
									<input type="text" id="NC" class="text-c wtb" data-min="1" name="address">
								</div>
							</div>
							<div class="item"  style="margin-bottom:29px;">
								<label for="NC" class="dt">邮&emsp;&emsp;箱</label>
								<div class="dd">
									<input type="text" id="NC" class="text-c wtb" data-min="1" name="email">
								</div>
							</div>
							<div class="carry-out">
								<p class="red f18">以后仍可在个人中心进行修改。</p>
								<button class="btn-b wtb" id="completeReg">完成注册</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
<script src="<%=basePath %>jsp/topic/js/jquery-1.4.4.min.js"></script>
<script src="<%=basePath %>jsp/topic/js/jquery.placeholder-1.0.js"></script>
<script src="<%=basePath %>jsp/topic/js/jquery-powerSwitch-min.js"></script>
<script src="<%=basePath %>jsp/topic/js/jquery.easing.min.js"></script>
<script src="<%=basePath %>jsp/topic/js/jquery.lavalamp.min.js"></script>
<script src="<%=basePath %>jsp/topic/js/mobilebone-min.js"></script>
<script src="<%=basePath %>jsp/topic/js/jquery-html5Validate-min.js"></script>
<script src="<%=basePath %>jsp/topic/js/common.js"></script>
<script src="<%=basePath %>jsp/static/js/uploadPreview.js"></script>

<script>
//阻止表单提交	
var validate_false = function(form) {
    return true;    
};
	Mobilebone.callback = function(page_in, page_out) {
	if(page_in) {
	    //遍历连接
	    $("*").each(function(){
	    	var url = $(this).attr('data-url')
	    	if(url){
	    		$(this).click(function(){
	    			window.location.href=url
	    		})
	    	}
	    });
	};
}
//二次密码验证
function checkPwd(even){
	var pwd = $(even+" .pwd"), pwdCheck = $(even+" .pwdcheck");
	pwd.bind("keyup", function() {
	    if ($.html5Validate.isEmpty(this)) {
	        pwdCheck.attr("disabled", "disabled");    
	    } else {
	        pwdCheck.removeAttr("disabled");
	    }
	});
	$(even).html5Validate(function() {
		var self = this;
		// 前后密码一致的验证
	    if (pwd.val() != pwdCheck.val()) {
	        pwdCheck.testRemind("前后密码不一致").get(0).select();    
	    } else {
			self.submit(); 
		}
	});
}
$.testRemind.css = {
    fontSize: "16px"	
};
var p = $(".login-3 .interests .placeholder")
if($('.login-3 .cont').text()!=""){
	p.hide()
}
$('.login-3 .cont').focus(function(){
	$(this).append("<a></a>")
})
$('.login-3 .cont').keyup(function(){
	p.hide()
	if($(this).text()==""){
		p.show()
	}else if($(this).text()=="," || $(this).text()=="，"){

	}
})
//操作标签相关
$('.interests .tag .del').live('click',function(){
	$(this).parent('.tag').remove();
})
$('#AddTagBtn').click(function(){
	var c = $(this).prev();
	if(c.val()==null||""==c.val().trim()){
		return;
	}
	$('.login-3 .cont').append('<a href="#" class="tag">'+ c.val()+'<i class="iconfont del"></i></a>&nbsp;&nbsp;')
	c.val("");
});

$("div.mb10 .text-c").keypress(function(event){
    var keycode = (event.keyCode ? event.keyCode : event.which);
    if(keycode == '13'){
    	var c = $(this);
    	if(c.val()==null||""==c.val()){
    		return;
    	}
    	$('.login-3 .cont').append('<a href="#" class="tag">'+ c.val()+'<i class="iconfont del"></i></a>&nbsp;&nbsp;')
    	c.val("");
    }
});



$.extend({
	//登录
	login:function(){
		$("#Login form").html5Validate(function() {
			var self = this;
				self.submit(); 
		});
	},
	loginOut:function(){
		$("#validateRemind").remove();

	},
	//注册
	reg:function(){
		checkPwd("#Reg form");},
	regOut:function(){
		$("#validateRemind").remove();

	},
	//完善注册
	comreg:function(){
		$("#ComReg form").html5Validate(function() {
			var self = this;
		
			$(this).attr("method","post");
			$(this).attr("enctype","multipart/form-data");
			$(this).attr("action","<%=basePath %>servlet/userLoginService?cmd=complete_reg");
			console.log("完成注册");
		
			self.submit(); 
		});
	},
	comregOut:function(){
		$("#validateRemind").remove();
	}
})

//用户填写完标签后，下一步
$("#reg_tag").click(function(){
	console.log("点击下一步提交表单……");
	var tags_array=new Array();
 	var atags=$("#user_input_tags a");
 	atags.each(function(index,element){
 		//console.log("element   :  " + element.innerHTML.toString());
 		tags_array.push(element.innerHTML.toString());
 	});
 	
 	var parameters = {
 		tags : tags_array.toString(),
 		userId : "${sessionScope.user.id}"==""?'9999':"${sessionScope.user.id}",
 		cmd : 'tag'
 	};
 	$.post("<%=basePath%>servlet/userLoginService",parameters,function(res,status){
 		if(res=="ok")
 		{
 			//检查是否有xunta_username和password
 			var xunta_username="${sessionScope.user.xunta_username}";
 			var password = "${sessionScope.user.password}";
 			console.log("存在该用户");
 			if(xunta_username&&password){//存在用户名和密码直接跳到主页
 				window.location.replace("<%=basePath %>jsp/topic/index.jsp");
 			}else{
 				console.log("准备跳到下一步");
 				window.location="#Reg";
 				window.location.reload();
 			}
 		}
 		else{
 			console.log(res);
 		}
 	});
});

//点击获取验证码
$("#getValidateCode").click(function(){
	console.log("点击获取验证码...");
	var parent = $(this).parent();
	$(this).remove();
	parent.append("<br/><img src=\"<%=basePath%>servlet/validateCodeServlet\" width=80 height=30 style=\"margin-top:10px;\"class=\"validateImage\"/><small><i>点击图片换验证码</i></small><br />");
	//点击验证码切换
	$(".validateImage").click(function(){
		console.log("点击验证码");
		$(this).attr("src","<%=basePath%>servlet/validateCodeServlet?"+Math.random());
	});
});

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
				window.location.href="#ComReg";
			}else{
				console.log(res);
				alert(res);
			}
		});
	}else{
		console.log("验证不通过...");
	}
	  
});
//验证基本资料填写之绑定本地账户密码的表单
function checkForm(userNameR,passwordR,passWordRC,validateCodeR){
	var flag=true;
	//用户名是否为空
	if(isNull(userNameR)||userNameR=="手机号/邮箱/用户名")
	{
		console.log("用户名为空");
		flag = false;
	}
	if(!isEqual(passwordR,passWordRC))
	{
		console.log("两次密码输入不相同");
		flag = false;
	}
	if(passwordR.length<6){
		console.log("密码长度小于6位");
		flag=false;
	}
	if(isNull(validateCodeR))
	{
		console.log("验证码为空");
		flag=false;
	}
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

//上传图片预览
 new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
//续续完善或注册 上一步
function backforward(){
	window.location="#Reg";
}

</script>
</body>
</html>