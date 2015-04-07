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
	<link rel="stylesheet" href="<%=basePath %>jsp/topic/css/base.css">
	<!--[if lte IE 8]>
		<style>
			/*ie8支持css3
			.header .search,.top-memory .tab-menu,.talk-list .tp,.talk-list .enter,.form .load{behavior:url("${pageContext.request.contextPath }/jsp/topic/js/ie-css3.htc");}*/
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
		<!-- 完善资料 -->
	<div class="page out" id="ComReg">
			<div class="dialog-box d2">
				<div class="login-4 form">
					<h3 style="position:relative;left:40%;">基本资料填写<span class="f14"></span></h3>
					<p style="margin-bottom:25px;font-size:17px;font-weight:normal;text-shadow:0 0;">三.继续完善 or 完成注册？</p>
					<form  id="ComRegForm">
						<div class="l">
							<div class="item">
								<label class="dt dtt">头&emsp;&emsp;像</label>
								<div class="dd">
									<span class="pic-area">
										<div id="imgdiv"><img id="imgShow" style="width:70px;height:70px;" /></div>
									</span>
									<a href="#"  class="f14 a1" onclick="selectImage()">
										本地上传<input type="file" id="up_img" name="myfile" style="display:none"  onchange="upLoadImage()"/>
									</a>
									<br><small style="position:relative;left:100px;top:-20px">(头像文件不大于1M)</small>
								</div>
							</div>
							<div class="item">
								<label for="NC" class="dt">昵&emsp;&emsp;称</label>
								<div class="dd">
									<input type="text" id="NC" class="text-c wtb" data-min="1" name="nickname" value="${requestScope.nickname }">
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
											<label id="yearLabel">${requestScope.year }</label>
											<select name=year onchange="yy(this.value)">
											<option value="${requestScope.year }"></option>
											</select>
										</div>
									</div>
									年
									<div class="d-select">
										<div class="ter">
											<label id="monthLabel">${requestScope.month }</label>
											<select name=month onchange="mm(this.value)">
												<option value="${requestScope.month }"></option>
											</select>
										</div>
									</div>
									月
									<div class="d-select">
										<div class="ter">
											<label id="dayLabel">${requestScope.day }</label>
											<select name=day>
												<option value="${requestScope.day }"></option>
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
								<label for="address" class="dt">常&ensp;住&ensp;地</label>
								<div class="dd">
									<input type="text" id="address" class="text-c wtb" data-min="1" name="address" value="${requestScope.address }">
								</div>
							</div>
							<div class="item"  style="margin-bottom:29px;">
								<label for="email" class="dt">邮&emsp;&emsp;箱</label>
								<div class="dd">
									<input type="text" id="email" class="text-c wtb" data-min="1" name="email" value="${requestScope.email }">
								</div>
							</div>
						</div>
					</form>
					<div class="carry-out">
						<p class="red f18">以后仍可在个人中心进行修改。</p>
						<button class="btn-b wtb" id="completeReg">完成注册</button><br><br>
						<button class="btn-b wtb" id="ignore">跳过</button>
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
/* $("form").html5Validate(function() {
	var self = this;
		self.submit(); 
}); */

//上传图片预览
//new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
//续续完善或注册 上一步

function backforward(){
	console.log("上一步");
	window.location="${pageContext.request.contextPath}/jsp/xunta_user/login4.jsp";
}
function upLoadImage(){
	
	$("#ComRegForm").attr("action","<%=basePath %>servlet/userLoginService?cmd=uploadImageTest")
			.attr("method","post")
			.attr("enctype","multipart/form-data")
	console.log("上传图片");
	$("#ComRegForm").submit();
}

function selectImage(){
	document.getElementById("up_img").click();
}



var imageTooBig = true;
//完成注册
$("#completeReg").click(function(){
	//验证图片的大小
	if(!imageTooBig){
		Tip("图片大小不能超过1M,请重新选择一下图片啊");
		return;
	}
	//验证昵称是否完整
	var nickname = $("#NC").val();
	//性别　
	var sex = $("").val();
	//出生日期
	var year=$("#year_data").text();
	var month = $("#monthLabel").text();
	var day = $("#dayLabel");
	//常住地
	var address = $("#address").text();
	//邮箱
	var email = $("#email").val();
	
	console.log("性别:"+sex);
	console.log("年："+year);
	console.log("月:"+month);
	console.log("日:"+day);
	console.log();
	
	

	//alert("完成注册");
	//console.log($("#ComRegForm")[0]);
	//$("#ComRegForm").attr("action","<%=basePath %>servlet/userLoginService?cmd=comReg").attr("method","post");
	//$("#ComRegForm").submit();
});

$(function(){
	
	//判断图像是否选择
	if("${requestScope.picUrl }"==""){
		//判断是否选择的图片大于1M
		if("${requestScope.errorImage}"=="1M"){
			console.log("图片过大");
			imageTooBig=false;
			Tip("选择的图片超过1M,请重新选择");
		}
		
	}else{
		console.log("用户选择图片");
		$("#imgShow").attr("src",src="<%=request.getContextPath() %>/image?picId=${requestScope.picUrl }");
	}
	
	 YearMonthDay();// 加载日期选择组件
	 autoSelected();
	 $("#imgName").val("");
	//将模拟的单选按钮与实际的选择按钮调成一致
	if("${requestScope.sex}"=="m"){
		$("#Man").attr("checked","checked");
		$("#Woman").attr("checked",false);
		console.log($("label[for='Man']")[0]);
		$("label[for='Man']").addClass("checked-r").siblings().removeClass("checked-r");
	}else if("${requestScope.sex}"=="w"){
		$("#Man").attr("checked",false);
		$("#Woman").attr("checked","checked");
		$("label[for='Woman']").addClass("checked-r").siblings().removeClass("checked-r");
	}
});

//下拉列表自动选中
function autoSelected()
{
	 var fo=document.getElementById("ComRegForm");
	 var year = fo.year;
	 var month = fo.month;
	 var day = fo.day;
	 
	 var year_data = "${requestScope.year}";
	 var month_data = "${requestScope.month}";
	 var day_data = "${requestScope.day}";
	 if(year_data!=null)
	 {
		 for (var i = 0; i < year.options.length; i++) {
            if (year.options[i].value == year_data) {
                year.options[i].selected = true;
                document.getElementById("yearLabel").innerHTML=year_data;
                break;
            }
        }
		 for (var i = 0; i < month.options.length; i++) {
            if (month.options[i].value == month_data) {
                month.options[i].selected = true;
                document.getElementById("monthLabel").innerHTML=month_data;
                break;
            }
        }
		 for (var i = 0; i < day.options.length; i++) {
            if (day.options[i].value == day_data) {
                day.options[i].selected = true;
                document.getElementById("dayLabel").innerHTML=day_data;
                break;
            }
        }
	 }
}
</script>
</body>
</html>