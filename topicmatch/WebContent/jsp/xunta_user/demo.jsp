<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/"; 
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>uploadPreview演示</title>
    <script src="<%=basePath %>jsp/static/js/uploadPreview.js" type="text/javascript"></script>
    <script>
       window.onload = function () { 
            new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
        }
    </script>
</head>
<body>
    <div id="imgdiv"><img id="imgShow" width="100" height="100"  style="border-radius:48px"/></div>
    <form name="myform" action="<%=basePath %>servlet/fileupload" enctype="multipart/form-data" method="post">
   		 <input type="file" id="up_img" name="myfile"/>
   		 用户名:<input type="text" name="username" value="${param.username }"><br>
   		 密码:<input type="text" name="password" value="${param.password }"><br>
   		 <input type="submit" value="完成注册">
    </form>
</body>
</html>
