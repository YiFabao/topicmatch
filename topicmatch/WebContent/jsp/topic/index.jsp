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