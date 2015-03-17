<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<%-- 这就是一个顶级容器，唯一不变的就是聊天框，其他任何元素都通过js获取更换--%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%-- =======start 余. 定义的css文件搬到此 --%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/jsp/topic/css/base.css">
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
<!-- 导航栏动态引用 -->
<jsp:include page="include/navbar2.jsp"></jsp:include>

<div id="container_all">
	<%--页面切换都放这里 --%>
</div>

<!--话题聊天框-->
<div class="topic-box">
	<div class="left">
		<ul class="rec-topic-list">
			<li class="cur">
				<span class="num">3</span>
				<p class="name" title="你多活一天你都干了些什么？">你多活一天你都干了些什么？</p>
				<a href="#" class="iconfont close">&#xe601;</a>
			</li>
			<li>
				<span class="num">3</span>
				<p class="name" title="你多活一天你都干了些什么？">你多活一天你都干了些什么？</p>
				<a href="#" class="iconfont close">&#xe601;</a>
			</li>
			<li>
				<span class="num">3</span>
				<p class="name" title="你多活一天你都干了些什么？">你多活一天你都干了些什么？</p>
				<a href="#" class="iconfont close">&#xe601;</a>
			</li>
			<li>
				<span class="num">3</span>
				<p class="name" title="你多活一天你都干了些什么？">你多活一天你都干了些什么？</p>
				<a href="#" class="iconfont close">&#xe601;</a>
			</li>
		</ul>
	</div>
	<div class="center">
		<div class="title">
			<div class="pic"><img src="images/delete/user-pic2.jpg" alt=""></div>
			<h3 title="话题：心底总会有声音不停提醒“不快乐”。">话题：心底总会有声音不停提醒“不快乐”。</h3>
			<a href="#" class="iconfont close">&#xe607;</a>
		</div>
		<div class="dec">
			<p class="txt">
				<span class="dt">话题描述：</span>
				论你怎样拥抱你想要的生活，心底总会有声音不停提醒“不快乐”你的内心似乎有个黑洞，不停向外索取，让你不停奔忙却忽略了你真正需要的东西.
			</p>
		</div>
		<div class="chat-box">
			<div class="user my">
				<p class="detail">今天用了迪素牛奶瓶男香，橙汁味道</p><div class="user-pic"><img src="images/delete/user-pic2.jpg" alt=""></div>
			</div>
			<div class="user other">
				<p class="detail">今天用了迪素牛奶瓶男香，橙汁味道</p><div class="user-pic"><img src="images/delete/user-pic2.jpg" alt=""></div>
			</div>
			<div class="system">
				-某某加入该对话组-
			</div>
		</div>
		<div class="send-box">
			<textarea name="" id=""></textarea>
			<button class="iconfont send-btn">&#xe604;</button>
		</div>
	</div>
	<div class="toggle" title="收缩">
		<i class="iconfont">&#xe605;</i>
	</div>
	<div class="right">
		<h4>参与人</h4>
		<ul class="user-list">
			<li>
				<div class="user-pic"><img src="images/delete/user-pic2.jpg" alt=""></div>
				<p class="name" title="顽皮的小废话">顽皮的小废话</p>
			</li>
			<li>
				<div class="user-pic"><img src="images/delete/user-pic2.jpg" alt=""></div>
				<p class="name" title="顽皮的小废话">顽皮的小废话</p>
			</li>
			<li>
				<div class="user-pic"><img src="images/delete/user-pic2.jpg" alt=""></div>
				<p class="name" title="顽皮的小废话">顽皮的小废话</p>
			</li>
			<li>
				<div class="user-pic"><img src="images/delete/user-pic2.jpg" alt=""></div>
				<p class="name" title="顽皮的小废话">顽皮的小废话</p>
			</li>
		</ul>
	</div>
</div>
<!-- 隐藏状态 -->
<div class="mintopic-box">
	未读消息 <span class="num">23</span>
	<a href="javascript:;" class="iconfont unfold">&#xe60d;</a>
</div>

<div class="form confirm-box">
	<div class="cont">
		<p class="detail">XXX邀请您参与"XXX"话题</p>
		<div class="btn-area">
			<button class="btn-c">拒绝</button>
			&emsp;
			<button class="btn-c">同意</button>
		</div>
		<a href="javascript:;" class="iconfont close">&#xe601;</a>
	</div>
</div>

<%--start ======= 余. js文件搬到此引用,动态加载页面后，会调用 --%>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery-1.4.4.min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery.lavalamp.min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery-html5Validate-min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery-powerSwitch-min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/common.js"></script>
<%--end   ======= 余. js文件搬到此引用,动态加载页面后，会调用 --%>
<script src="${pageContext.request.contextPath }/jsp/topic/js/ta_pc_chat.js"></script>

<script>
	$(function(){
		//加载话题记忆页面
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
	

</script>


</body>
</html>