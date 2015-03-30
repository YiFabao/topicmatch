<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<section class="content" >
	<div class="main">
		<c:if test="${requestScope.user!=null}">
		<div class="form login-4 acc-set">	
			<form id="ComRegForm">
				<div class="item">
					<label class="dt-c" for="UserNameR">用户名&nbsp;:</label>
					<div class="dd-c lh36">&emsp;${user.xunta_username}</div>
				</div>
				<div class="item">
					<label for="PassWordR" class="dt-c">密&emsp;&emsp;码</label>
					<div class="dd-c">
						<input name="password" type="password" id="PassWordR" class="text-c wtb pwd" data-min="6" value="${user.password}" required disabled>
						<a href="javascript:;" class="edit">编辑</a>
					</div>
				</div>
				<div class="item">
					<label for="PassWordRC" class="dt-c">确认密码</label>
					<div class="dd-c">
						<input name="rePassword" type="password" id="PassWordRC" class="text-c wtb pwdcheck" data-min="6" value="${user.password}" required disabled>
						<a href="javascript:;" class="edit">编辑</a>
					</div>
				</div>
				<div class="item">
					<span class="dt-c">标签</span>
					<div class="dd-c">
						<div class="interests">
							<div class="cont mb10" id="user_input_tags">
								<c:if test="${requestScope.tags!=null}">
									<c:forEach var="tag" items="${requestScope.tags}" varStatus="status">
										<a href="javascript:;" class="tag" tagId="${tag.id}">${tag.tagname}<i class="iconfont del">&#xe601;</i></a>
									</c:forEach>
								</c:if>
								<input name="newTags" id="newTags" type="hidden"/>
							</div>
							<lable class="placeholder" style="display:none">填写或选择感兴趣的标签,以，隔开</lable>
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
					</div>
				</div>
				<div class="item">
					<label class="dt-c dtt">头像</label>
					<div class="dd-c">
						<span class="pic-area">
							<div id="imgdiv"><img id="imgShow" style="width:70px;height:70px;" src="<%=request.getContextPath() %>/image?picId=${sessionScope.user.imageUrl}" alt=""/></div>
						</span>
						<!-- <a href="javascript:up_img.click();"  class="f14 a1"> -->
						<a href="#" class="f14 a1" onclick="upload()">
							本地上传<input type="hidden" id="imgName" name="imgName"/>
						</a>
						<br><small style="position:relative;left:100px;top:-20px">(头像文件不大于1M)</small>
						
					</div>
				</div>
				<div class="item">
					<label for="NC" class="dt-c">昵称</label>
					<div class="dd-c">
						<input name="nickname" type="text" id="NC" class="text-c wtb" data-min="1" value="${user.nickname}">
					</div>
				</div>
				<div class="item pt14">
					<label class="dt-c">出生日期</label>
					<div class="dd-c pt5">
						<div class="d-select">
							<div class="ter">
								<label id="yearLabel"></label>
								<select name=year onchange="yy(this.value)">
								<option value=""></option>
								</select>
							</div>
						</div>
						年
						<div class="d-select">
							<div class="ter">
								<label id="monthLabel"></label>
								<select name=month onchange="mm(this.value)">
									<option value=""></option>
								</select>
							</div>
						</div>
						月
						<div class="d-select">
							<div class="ter">
								<label id="dayLabel"></label>
								<select name=day>
									<option value=""></option>
								</select>
							</div>
						</div>
						日
					</div>
				</div>
				<div class="item">
					<label for="city" class="dt-c">常驻城市</label>
					<div class="dd-c">
						<input name="address" type="text" id="city" class="text-c wtb" data-min="1" value="${user.address}" disabled>
						<a href="javascript:;" class="edit">编辑</a>
					</div>
				</div>
				<div class="item">
					<label for="email" class="dt-c">电子邮箱</label>
					<div class="dd-c">
						<input name="email" type="email" id="email" class="text-c wtb" data-min="1" value="${user.email}" disabled>
						<a href="javascript:;" class="edit">编辑</a>
					</div>
				</div>
				<!-- <div class="item mb60">
					<label for="moblie" class="dt-c">绑定手机</label>
					<div class="dd-c">
						<input type="tel" id="moblie" class="text-c wtb" data-min="1">
						<a href="javascript:;" class="edit">编辑</a>
					</div>
				</div> -->
				<!-- <div class="login-1">
					<h3>第三方帐号绑定</h3>
					<ul class="quick-login">
						<li><a href="#" title="微信"><img src="images/wx.png">微信</a></li>
						<li><a href="#" title="腾讯QQ"><img src="images/qq.png">QQ</a></li>
						<li><a href="#" title="新浪微博"><img src="images/sina.png">新浪</a></li>
						<li class="justify-fix">&nbsp;</li>
					</ul>
				</div> -->
				<div class="dd-c">
					<button class="btn-d wta" id="submitModify">确 定</button>
				</div>
			</form>
			<form id="picForm" enctype="multipart/form-data">
				<span id="fileSpan">
					<input type="file" id="up_img" name="myfile" style="display:none" required/>
				</span>
			</form>
			<input type="hidden" id="picExceed" value="false"></input>
		</div>
		</c:if>
	</div>
</section>
<!-- <script src="js/jquery-1.4.4.min.js"></script>
<script src="js/jquery.easing.min.js"></script>
<script src="js/jquery.lavalamp.min.js"></script>
<script src="js/jquery-html5Validate-min.js"></script>
<script src="js/jquery-powerSwitch-min.js"></script>
<script src="js/common.js"></script> -->
<script src="js/jquery.form.js"></script>
<script>
	 checkPwd("#Reg form")
	 $(function(){
		 YearMonthDay();// 加载日期选择组件
		//上传图片预览
		 new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
		 autoSelected();
		// $("#picExceed").val("false");
		 $("#imgName").val("");
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
	 
	 function upload()
	 {
		 document.getElementById("up_img").click();
	 }
	 
	 //提交表单
	 $("#submitModify").click(function(){
		 //获取标签
		 var tags_array=new Array();
		 var atags=$("#user_input_tags a");
		 atags.each(function(index,element){
		 	//console.log("element   :  " + element.innerHTML.toString());
		 	tags_array.push(element.innerHTML.toString());
		 });
		 if(tags_array.length<=0)
		 {
			 alert("请至少填入一个标签!");
			 return;
			 return false;
		 }
		 var pwd=$("#PassWordR").val();
		 var repwd=$("#PassWordRC").val();
		 if(pwd!=repwd)
		 {
			 alert("两次输入的密码不一致!");
			 return;
			 return false;
		 }
		/*  if($("#picExceed").val().trim()=="true")
		 {
			 alert("头像文件不能大于1M");
			 return;
			 return false;
		 } */
		 var newTags = $("#newTags");
		 newTags.val(tags_array.toString());
		 
		 $("#ComRegForm").html5Validate(function() {
				var self = this;
			
				$(this).attr("method","post");
				$(this).attr("enctype","multipart/form-data");
				$(this).attr("action","${pageContext.request.contextPath}/servlet/userLoginService?cmd=complete_reg");
				console.log("完成修改");
				self.submit(); 
			});
	 });
	 
	 //添加标签
	 $('#AddTagBtn').click(function(){
			var c = $(this).prev();
			if(c.val()==null||""==c.val().trim()){
				return;
			}
			$('.interests .cont').append('<a href="#" class="tag">'+ c.val()+'<i class="iconfont del">&#xe601;</i>')
			c.val("");
			
	});
	
	 //点击编辑按钮
	$('.edit').click(function(){
		var c = $(this).prev();
		//c.removeAttribute("disabled");
		c.attr('disabled', '');
	})
	
	$('#up_img').live('change',function(){
		//$("#picExceed").val("false");
		//$("#picForm").submit();
		$('#picForm').ajaxSubmit({  
             type: 'post', 
             data: $('#picForm').serialize(),
             url: "${pageContext.request.contextPath}/servlet/userLoginService?cmd=imgCheck",
             success: function(data){  
                 
                 if(data=="exceed")
                 {
                      alert("头像文件不能大于1M");
                      //$("#picExceed").val("true");
                      document.getElementById("imgShow").src="${pageContext.request.contextPath}/image?picId=${sessionScope.user.imageUrl}";
                      //清空上传域
                      $("#imgName").val("");
                      //document.getElementById("fileSpan").innerHTML='<input type="file" id="up_img" name="myfile" style="display:none" required/>';
                      return;
                 }
                 if(data=="illegal login")
                 {
                	 alert("非法登录");
                	 return;
                 }
                 if(data=="not multipart")
                 {
                	 alert("非multipart请求");
                	 return;
                 }
                 else
                 {
                	 $("#imgName").val(data);
                 }
             },  
             error: function(XmlHttpRequest, textStatus, errorThrown){  
                 alert( "error");  
             }  
         });
	});
	/* var isIE = /msie/i.test(navigator.userAgent) && !window.opera;         
  	function fileSize() {     
      	var target = document.getElementById("up_img");
	    var fileSize = 0;          
	    if (isIE && !target.files) {      
	      var filePath = target.value;      
	      var fileSystem = new ActiveXObject("Scripting.FileSystemObject");         
	      var file = fileSystem.GetFile (filePath);      
	      fileSize = file.Size;     
	    } else {     
	     fileSize = target.files[0].size;      
	     }    
	     var size = fileSize / 1024;     
	     alert(size);
	     console.log(size);
	     if(size>10000){   
	      alert("附件不能大于10M");   
	     }       
	} */

</script>
</html>
