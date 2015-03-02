<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<%-- 这就是一个顶级容器，唯一不变的就是聊天框，其他任何元素都通过js获取更换--%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%-- =======start 余. 定义的css文件搬到此 --%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/jsp/topic_ta_pc/css/base.css">
</head>

<body>
<!-- 导航栏动态引用 -->
<jsp:include page="include/navbar2.jsp"></jsp:include>

<div id="container_all">
	<%--页面切换都放这里 --%>
</div>

<%--start ======= 余. js文件搬到此引用,动态加载页面后，会调用 --%>
<script src="${pageContext.request.contextPath }/jsp/topic_ta_pc/js/jquery-1.4.4.min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic_ta_pc/js/jquery-powerSwitch-min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic_ta_pc/js/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic_ta_pc/js/jquery.lavalamp.min.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic_ta_pc/js/common.js"></script>
<%--end   ======= 余. js文件搬到此引用,动态加载页面后，会调用 --%>

<script>
	//加载话题记忆页面
	$.post(contextPath+"/jsp/topic_ta_pc/topics_memory.jsp",null,function(res,status){
		$("#container_all").empty();
		$("#container_all").append(res);
	});
	
</script>
</body>
</html>