<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>第二步：填写密码 </h1>
	<form action="demo.jsp" method="post">
		密码：<input type="password" name="password"/>
		<br/>
		用户名：<input type="text" name="username" value="${param.username }">
		<input type="submit" value="下一步">
	</form>
</body>
</html></html>