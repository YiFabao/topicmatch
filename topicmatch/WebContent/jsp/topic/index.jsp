<%@page import="so.xunta.entity.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
so.xunta.utils.LogUtils logutil = new so.xunta.utils.LogUtils();
logutil.traceLog(request, "首页");

String path = request.getContextPath();
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/"; 
User user = (User)session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<%-- 这就是一个顶级容器，唯一不变的就是聊天框，其他任何元素都通过js获取更换--%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="pragma" content="no-cache">  
<meta http-equiv="cache-control" content="no-cache">  
<meta http-equiv="expires" content="0">     
<title>xunta.so</title>
<%pageContext.setAttribute("baseUrl",request.getContextPath());%>
<link rel="shortcut icon" href="${baseUrl }/assets/xunta.ico" type="image/x-icon">
<link rel="icon" href="${baseUrl }/assets/xunta.ico" type="image/x-icon">
<%-- =======start 余. 定义的css文件搬到此 --%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/jsp/topic/css/base.css">
	<!--[if lte IE 8]>
		<style>
			/*ie8支持css3
			.header .search,.top-memory .tab-menu,.talk-list .tp,.talk-list .enter,.form .load{behavior:url("${pageContext.request.contextPath }/jsp/topic/js/ie-css3.htc");}*/
		</style>
		<script src="${pageContext.request.contextPath }/jsp/topic/js/html5.js"></script>
	<![endif]-->
 	<style>
/* 		.content,.main{position: absolute;bottom: 0;left:0;right:0;}
		.content{top:120px;}
		.main{top:0;overflow-y:auto;} */
	</style>
</head>

<body>
<!-- 导航栏动态引用 -->
<jsp:include page="include/navbar2.jsp"></jsp:include>

<div id="container_all" ">
	<%--页面切换都放这里 --%>
</div>

<!--话题聊天框-->
<div class="topic-box"  style="bottom: -620px;">
	<div class="left">
		<ul class="rec-topic-list">
<!-- 			<li class="cur">
				<span class="num">3</span>
				<p class="name" title="你多活一天你都干了些什么？">你多活一天你都干了些什么？</p>
				<a href="#" class="iconfont close">&#xe601;</a>
			</li>
			<li>
				<span class="num">3</span>
				<p class="name" title="你多活一天你都干了些什么？">你多活一天你都干了些什么？</p>
				<a href="#" class="iconfont close">&#xe601;</a>
			</li>  -->
		</ul>
	</div>
	<div class="center" topicId="1">
		<div class="title">
			<div class="pic"><!-- <img src="images/delete/user-pic2.jpg" alt=""> --></div>
			<h3 title="话题：心底总会有声音不停提醒“不快乐”。"><!-- 话题：心底总会有声音不停提醒“不快乐”。 --></h3>
			<a href="javascript:chat_box_close()" class="iconfont close">&#xe607;</a>
		</div>
		<div class="dec">
			<p class="txt">
				<!-- <span class="dt">话题描述：</span>
				论你怎样拥抱你想要的生活，心底总会有声音不停提醒“不快乐”你的内心似乎有个黑洞，不停向外索取，让你不停奔忙却忽略了你真正需要的东西. -->
			</p>
		</div>
		<div class="chat-box">
			<!-- <div class="user my">
				<p class="detail">今天用了迪素牛奶瓶男香，橙汁味道</p><div class="user-pic"><img src="images/delete/user-pic2.jpg" alt=""></div>
			</div>
			<div class="user other">
				<p class="detail">今天用了迪素牛奶瓶男香，橙汁味道</p><div class="user-pic"><img src="images/delete/user-pic2.jpg" alt=""></div>
			</div>
			<div class="system">
				-某某加入该对话组-
			</div> -->
		</div>
		<div class="send-box">
			<textarea name="" id="msg_input_box"></textarea>
			<button class="iconfont send-btn" onclick="createMessage(0,null)">&#xe604;</button>
		</div>
	</div>
	<div class="toggle" title="收缩"  onclick="topic_member_toggle(this)">
		<i class="iconfont">&#xe606;</i>
	</div>
	<div class="right" topicId="999">
		<h4 id="joinMemNum">参与人</h4>
		<ul class="user-list">
			<!--  <li　userId="111">
				<div class="user-pic"><img src="images/delete/user-pic2.jpg" alt=""></div>
				<p class="name" title="张三">张三</p>
			</li>
			<li userId="333">
				<div class="user-pic"><img src="images/3.jpg" alt=""></div>
				<p class="name" title="王二麻子">王二麻子</p>
			</li> -->
		</ul>
	</div>
</div>

<!-- 隐藏状态 -->
<div class="mintopic-box">　
	未读消息 <span class="num">0</span>
	<a href="javascript:;" class="iconfont unfold">&#xe60d;</a>
</div>

<!-- 多余 -->
<!-- <div class="form confirm-box">
	<div class="cont">
		<p class="detail">XXX邀请您参与"XXX"话题</p>
		<div class="btn-area">
			<button class="btn-c">拒绝</button>
			&emsp;
			<button class="btn-c">同意</button>
		</div>
		<a href="javascript:;" class="iconfont close">&#xe601;</a>
	</div>
</div> -->
<form action="<%=basePath %>servlet/login" method="post" id="hidden_form">
	<input type="hidden" name="xunta_username" id="hidden_username" value="<%=user.getNickname()%>"/>
	<input type="hidden" name="password" id="hidden_password" value="admin"/>
</form>

<%--start ======= 余. js文件搬到此引用,动态加载页面后，会调用 --%>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery-1.4.4.min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery.lavalamp.min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery-html5Validate-min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery-powerSwitch-min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/common.js"></script>
<%--end   ======= 余. js文件搬到此引用,动态加载页面后，会调用 --%>

<script src="${pageContext.request.contextPath }/jsp/topic/js/ta_pc_chat.js"></script>
<script src="${pageContext.request.contextPath }/jsp/static/js/uploadPreview.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/websocket.js"></script>
<%-- ======= 房 开发话题记忆模块引用独立文件js --%>
<script src="${pageContext.request.contextPath }/jsp/topic/js/topic_memory_fang.js"></script>

<script>

	var contextPath = "${pageContext.request.contextPath}";
	var myselfId = "${sessionScope.user.id}";
	//var myname = "${sessionScope.user.xunta_username}";
	var myname = "${sessionScope.user.nickname}";
	var userImageUrl = "${sessionScope.user.imageUrl}";
	console.log("自己的图像："+userImageUrl);

	$(function(){
		//加载话题推荐页面
		$.post("<%=request.getContextPath()%>/jsp/topic/include/recommended_topics.jsp",null,function(res,status){
			$("#container_all").empty();
			$("#container_all").append(res);
		});
	});
	
	$("#httj").click(function(){
		console.log("话题推荐");
		$.post("<%=request.getContextPath()%>/jsp/topic/include/recommended_topics.jsp",null,function(res,status){
			$("#container_all").empty();
			$("#container_all").append(res);
		});
	});

	$("#fqht").click(function(){
		console.log("发起话题");
		 $.post("<%=request.getContextPath()%>/jsp/topic/include/sponsored_topic.jsp",null,function(res,status){
			$("#container_all").empty();
			$("#container_all").append(res);
		}); 
	});
	$("#htjy").click(function(){
		console.log("话题记忆");
		$.post("<%=request.getContextPath()%>/jsp/topic/include/topics_memory.jsp",null,function(res,status){
			$("#container_all").empty();
			$("#container_all").append(res);
		});
	});
	
	$("#p_info").click(function(){
		console.log("个人信息设置");
		<%-- $.post("<%=request.getContextPath()%>/jsp/topic/include/account_settings.jsp",null,function(res,status){
			$("#container_all").empty();
			$("#container_all").append(res);
		}); --%>
		var parameters={
				userId:myselfId
		};
		$.post("${pageContext.request.contextPath}/servlet/userLoginService?cmd=getUserInfo",parameters,function(res,status){
			$("#container_all").empty();
			$("#container_all").append(res);
		});
	});
	
	function searchTopic(){
		console.log("搜索……");
		var search_word=$("#search_word").val().trim();//用户填写的搜索词
		if(!search_word)
		{
			alert("请输入要搜索的关键字");
			return;
		}
		console.log(search_word);
		var parameters={
				search_word:search_word
		};
		//涉及到分页,自行设计
		$.post("${pageContext.request.contextPath}/servlet/topic_service?cmd=htss",parameters,function(res,status){
			$("#container_all").empty();
			$("#container_all").append(res);
			$("#search_word").val("");
		});
	}
	$("#search_btn").click(function(){
		searchTopic()
	});
	$("#search_word").keypress(function(event){
		//console.log(event.keyCode?event.keyCode:event.which);
	    var keycode = (event.keyCode ? event.keyCode : event.which);
	    if(keycode == '13'){
	    	console.log("话题搜索");
	    	var c = $("#search_word");
	    	if(c.val()==null||""==c.val()){
	    		return;
	    	}
	    	searchTopic();
	    }
	});
	
	function keypress(obj,event){
		//console.log(obj);
		var keyCode = (event.keyCode ? event.keyCode : event.which);
		if(keyCode=='13'){
	    	createMessage(0,null);
	    	return false;
	    }
	}
/* 	//发送消息按钮添加点击或按回车键发送消息
	 $("#msg_input_box").keypress(function(event){
		    if(event.keyCode==13){
		    	createMessage(0,null);
		    	return false;
		    }
	 }); */
	
	//创建websocket
	createWebsocketConnect(myselfId);
	// websocket状态发生变化时触发
	var state_fang = "0";
	window.webimStateChange = function(state) {
		console.log("websocket状态:"+state);
		if (state == "ok") {
			console.log("websocket创建成功");
		} else if (state = "no") {
			if (state_fang == "0") {
				msgManagerReady = false;
				console.log("websocket异常,尝试从新连接websocket");
				setTimeout(createWS, 6000);
				state_fang = "1";
			} else if (state_fang == "1") {
				msgManagerReady = false;
				console.log("websocket异常,尝试从新连接websocket");
				setTimeout(createWS, 8000);
				state_fang = "2";
			} else if (state_fang == "2") {
				msgManagerReady = false;
				console.log("websocket异常,尝试从新连接websocket");
				setTimeout(createWS, 10000);
				state_fang = "3";
			} else if (state_fang = "3") {
				// 告知用户让其 手动 选择 连接
				console.log("尝试从新连接websocket第三次异常，告知用户检查网络环境，手动请求连接websocket服务器");
				state_fang = "0";
			}
		} 
	}; 
	//重新加载
	function createWS(){
		//createWebsocketConnect("${sessionScope.user.id}");
		window.location.reload();
	}
	
	//点击别的地方关闭聊天框
	$(document).click(function(event){
		newMessageRemind.clear();
		var pointX = event.pageX;
		var pointY = event.pageY;
		var left = $(".topic-box").position().left;
		var top = $(".topic-box").position().top;
		console.log(pointX+"  "+pointY);
		console.log("left:"+left+"  top:"+top);
		if(!(pointX>=left&&pointY>=top)){
			if((event.target.tagName)!="A"&&(event.target.tagName)!="BUTTON"&&(event.target.tagName)!="IMG"&&($(event.target).attr("class")!="btn")){
				chat_box_close();//关闭聊天窗
			}
		}
	});
	window.history.forward(1);
</script>


</body>
</html>