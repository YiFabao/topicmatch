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
		<%pageContext.setAttribute("baseUrl",request.getContextPath());%>
	<link rel="shortcut icon" href="${baseUrl }/assets/xunta.ico" type="image/x-icon">
	<link rel="icon" href="${baseUrl }/assets/xunta.ico" type="image/x-icon">
	<link rel="stylesheet" href="<%=basePath %>jsp/topic/css/base.css">
	<!--[if lte IE 8]>
		<style>
			/*ie8支持css3
			.header .search,.top-memory .tab-menu,.talk-list .tp,.talk-list .enter,.form .load{behavior:url(${pageContext.request.contextPath }/jsp/topic/js/ie-css3.htc);}*/
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
		<!-- 基本资料填写 -->
		<div class="dialog-box d2">
			<div class="login-3 l form">
				<h3 style="position:relative;left:50%;">基本资料填写<span class="f14"></span></h3>
					<div class="item">
						<p style="margin-bottom:25px;font-size:17px;font-weight:normal;text-shadow:0 0;">一.以标签表达个人兴趣,匹配同趣之人:</p>
						<span class="dt">个人兴趣</span>
					<div class="dd">
						<div class="interests">
							<div class="cont mb10" disabled style="overflow-y:auto" id="tags_box">
<!-- 								<a href="#" class="tag">文学<i class="iconfont del">&#xe601;</i></a>
								<a href="#" class="tag">地理<i class="iconfont del">&#xe601;</i></a>
								<a href="#" class="tag">整治<i class="iconfont del">&#xe601;</i></a>
								<a href="#" class="tag">语文<i class="iconfont del">&#xe601;</i></a>
								<a href="#" class="tag">数学<i class="iconfont del">&#xe601;</i></a>
								<a href="#" class="tag">文学<i class="iconfont del">&#xe601;</i></a>
								<a href="#" class="tag">地理<i class="iconfont del">&#xe601;</i></a>
								<a href="#" class="tag">整治<i class="iconfont del">&#xe601;</i></a>
								<a href="#" class="tag">语文<i class="iconfont del">&#xe601;</i></a> -->
							</div>
							<lable class="placeholder">填写或选择感兴趣的标签,以，隔开</lable>
							<div class="mb10">
								输入标签：<input type="text" class="text-c">
								<button class="btn-b w80" type="button" id="AddTagBtn">确定</button>
							</div>
							<!-- <p class="tag-list">
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
							<!-- <a href="#Login" class="l f16 lh34">已有账号，直接登录</a> -->
							<button class="btn-b r wtb" data-url="#Reg" id="reg_tag">下一步</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 提示 -->
	<div id="Tip"><span class="txt"></span></div>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery-1.4.4.min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery.placeholder-1.0.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery-powerSwitch-min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery.lavalamp.min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery-html5Validate-min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/common.js"></script>
<script>
$(function(){
    //标签相关
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
	//点击确定
	$('#AddTagBtn').click(function(){
		var c = $(this).prev();
		addTag(c);
	});
	//回车
	$("div.mb10 .text-c").keypress(function(event){
	    var keycode = (event.keyCode ? event.keyCode : event.which);
	    if(keycode == '13'){
	    	var c = $(this);
	    	addTag(c);
	    }
	});
	
	Array.prototype.in_array = function(e)
	{
		for(i=0;i<this.length;i++)
		{
		if(this[i] == e)
		return true;
		}
		return false;
	}
	Array.prototype.indexOf = function(val) {
		for (var i = 0; i < this.length; i++) {
		if (this[i] == val) return i;
		}
		return -1;
	};
	Array.prototype.remove = function(val) {
		var index = this.indexOf(val);
		if (index > -1) {
			this.splice(index, 1);
		}
	};
	var _tag_array=new Array();
	function addTag(c){
		if(_tag_array.in_array(c.val().trim())){
			Tip("标签写填重复");
			c.val("");
			return;
		}else{
			_tag_array.push(c.val().trim());
		}
		if(c.val()==null||""==c.val().trim()){
			c.val("");
			return;
		}
		$('.login-3 .cont').append('<a href="#" class="tag" style="margin-left:5px;">'+ c.val().trim()+'<i class="iconfont del"></i></a>')
		$("#tags_box").scrollTop($("#tags_box")[0].scrollHeight); //滚动条置底
		c.val("");
	}
	//标签删除
	$('.interests .tag .del').live('click',function(){
		var i =  $(this).parents(".interests")
		//console.log();
		console.log($(this).parent('.tag').find("i").empty());
		var tag_todel = $(this).parent('.tag').text().trim();
		console.log(tag_todel);
		_tag_array.remove(tag_todel);
		
		$(this).parent('.tag').remove();
		if(i.find(".cont").html() == " ")
			i.find(".placeholder").show()
	});
	
	//用户填写完标签后，下一步
	$("#reg_tag").click(function(){
		console.log("点击下一步提交表单……");
		var tags_array=new Array();
	 	var atags=$("#tags_box a");
	 	atags.each(function(index,element){
	 		//console.log("element   :  " + element.innerHTML.toString());
	 		tags_array.push(element.innerHTML.toString());
	 	});
	 	
	 	var parameters = {
	 		tags : tags_array.toString(),
	 		userId : "${sessionScope.user.id}",
	 		cmd : 'tag'
	 	};
	 	
	 	$.post("<%=basePath%>servlet/userLoginService",parameters,function(res,status){
	 		if(res=="ok")
	 		{
	 			window.location="<%=basePath%>servlet/userLoginService?cmd=checkAccountBindOrJump";
	 		}else{
	 			console.log(res);
	 			alert("提交表单出错");
	 		}
	 	});
	});
})
</script>
</body>
</html>