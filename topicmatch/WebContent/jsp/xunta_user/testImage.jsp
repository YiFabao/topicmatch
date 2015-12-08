<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="java.io.InputStream"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/"; 
%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

	<form action="<%=basePath %>/servlet/userLoginService?cmd=uploadImageTest" enctype="multipart/form-data" method="post">
		<a href="javascript:$('#up_img').click()">选择图片
			<input type="File" name="img" onchange="upLoadImage()" style="display:none" id="up_img"><br/>
		</a>
	</form>
	<image src ="<%=request.getContextPath() %>/image?picId=${requestScope.picUrl }" width="80" height="80">
</body>
<script src="${pageContext.request.contextPath }/jsp/topic/js/jquery-1.4.4.min.js"></script>
<script>
	function upLoadImage(){
		console.log("上传图片");
		$("form").submit();
	}
</script>
</html> 

<!-- <style>
input{border:1px solid #333333;color:#666666;background:#eeeeee;font:normal 12px Tahoma;height:18px}
</style>
<form method="post" action="" enctype="multipart/form-data">
文件选择：<input id="f_file" style="border:1px solid #0000ff;"><br>
按钮：<input type="button" onmouseover="fclick(t_file)" value="" style="background-image:url('fasong.jpg'); width:60px; height:19px;"><br/>
上传：<input name="upload" type="file" style="position:absolute;filter:alpha(opacity=0);width:30px;" id="t_file" onchange="f_file.value=this.value" hidefocus>
<br>
<input type="submit" value="提交">
</form>
<script>
function fclick(obj){
  with(obj){
    style.posTop=event.srcElement.offsetTop;
    var x=event.x-offsetWidth/2;
    if(x<event.srcElement.offsetLeft)x=event.srcElement.offsetLeft;
    if(x>event.srcElement.offsetLeft+event.srcElement.offsetWidth-offsetWidth)x=event.srcElement.offsetLeft+event.srcElement.offsetWidth-offsetWidth;
    style.posLeft=x;
  }
} -->