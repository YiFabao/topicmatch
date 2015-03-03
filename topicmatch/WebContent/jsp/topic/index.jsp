<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<!-- 这就是一个顶级容器，唯一不变的就是聊天框，其他任何元素都通过js获取更换 -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jsp/topic/css/navbar.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jsp/topic/css/navbar_msg_tab.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/jsp/topic/css/chat_box.css">
<style>

</style>

</head>
<body>
<!-- 导航栏 -->
<jsp:include page="include/navbar.jsp"></jsp:include>
<div id="container_all">
	
</div>
<script src="${pageContext.request.contextPath }/assets/javascripts/jquery-1.10.2.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/nav_bar_msg_tab.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/navbar.js"></script>
<script src="${pageContext.request.contextPath }/jsp/topic/js/include_chat.js"></script>
<script>
 	//默认显示 话题记忆
	console.log("户点击话题记忆");
	$.post(contextPath+"/jsp/topic/htjy.jsp",null,function(res,status){
		$("#container_all").empty();
		$("#container_all").append(res);
	});

    function getCookie(c_name)
    {
        if (document.cookie.length>0)
        {
            c_start=document.cookie.indexOf(c_name + "=")
            if (c_start!=-1)
            {
                c_start=c_start + c_name.length+1
                c_end=document.cookie.indexOf(";",c_start)
                if (c_end==-1) c_end=document.cookie.length
                return unescape(document.cookie.substring(c_start,c_end))
            }
        }
        return ""
    }

    function setCookie(c_name,value,expiredays)
    {
        var exdate=new Date()
        exdate.setDate(exdate.getDate()+expiredays)
        document.cookie=c_name+ "=" +escape(value)+ ((expiredays==null) ? "" : ";path=/; expires="+exdate.toGMTString());
    }

    function checkCookie()
    {
        aigine_login_state=getCookie('aigine_login_state')
        if (aigine_login_state!=null && aigine_login_state!="")
        {
            //有cookie记录
            //alert('Welcome again '+aigine_login_state+'!')
            console.log("欢迎"+aigine_login_state+"回来!上次登录时间为:"+getCookie("aigine_login_lastdatetime"));
        }
        else
        {
            //aigine_login_state=prompt('Please enter your aigine_login_sate:',"");//获取cookie
            //aigine_login_state="${sessionScope.user.xunta_username}";//获取cookie
			console.log("页面失效了");
            document.innerHTML="";
  			window.location="${pageContext.request.contextPath }/jsp/xunta_user/login.jsp";
        }
    }

/*     function deleteCookie(){
       	alert("删除cookie")
        var date=new Date(); 
		//将date设置为过去的时间 
		date.setTime(date.getTime()-10000); //过去的一个时间
        document.cookie = "aigine_login_state=; expires="+date.toGMTString();
    } */
	//ssetInterval(checkCookie(),1000);
</script>
</body>
</html>