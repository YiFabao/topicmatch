<%@page import="so.xunta.utils.LogUtils"%>
<%@page import="so.xunta.entity.User"%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%LogUtils logutil = new LogUtils();logutil.traceLog(request,"发起话题页面");%>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<style>
			.resultEmpty{font-size: 18px;height: 48px;text-align: center;border-radius: 10px;width: 494px;border:none;cursor: pointer;clear: both;background-color: #f3f3f3;display: block;margin-left: auto;margin-right: auto;}
			.resultEmpty:hover{background-color: #fff}
		</style>
	</head>
	<section class="content">
		<div class="main" id="sponsored">
			<div class="form spons-topic">
				<h2>发起话题</h2>
				<form>
					<div class="item">
						<label for="topic" class="dt-b">话题:</label>
						<div class="dd-b"><input type="text" id="topic" class="text-d" data-max="25" required onKeyUp="showLen(this,25);"></div>
						<span class="tip" id="TopicwordNum">还可以输入25字</span>
					</div>
					<div class="item detail">
						<label for="detail" class="dt-b">话题描述:</label>
						<div class="dd-b">
							<div class="cont">
								<textarea name="" id="detail" cols="100%" rows="6" class="tarea" required data-max="5000" onKeyUp="showLen(this,5000);"></textarea>
							</div>
						</div>
						<span class="tip" id="DetailwordNum" style="bottom:-25px;right:80px">还可以输入5000字</span>
					</div>
					<div class="opear"><input type="button" class="btn-f" value="发布" id="btn_publish"></div>
				</form>
				<div class="most-man">

						<%-- <c:if test="${requestScope.matchedTopicList!=null}">
						<h3 class="title">最匹配的"话题人"</h3>
						<ul class="talk-list">
							<c:forEach var="matched_topic" items="${requestScope.matchedTopicList}" varStatus="status">
							<c:if test="${matched_topic.userId!=sessionScope.user.id }">
							<li class="tp" userId="${matched_topic.userId }">
								<div class="hd">
									<a href="#" class="user-pic"><img src="images/user-default-pic.png" alt="齐天大圣"></a>
									<div class="info">
										<p class="nc">齐天大圣</p>
										<p class="signature">即使世界末日也要和爱的人手牵手在天台看日光。</p>
										<div>
											<i class="iconfont mark">&#xe610;</i>
											<ul class="tag-lists">
												<li>90后</li>
												<li>不靠谱</li>
											</ul>
										</div>
										<ul class="fix other">
											<li>发起相关话题数<span class="num">${matched_topic.relativeTopicList.size() }</span></li>
											<li>&emsp;&emsp;参与相关话题数<span class="num">23</span></li>
										</ul>
									</div>
									<a href="#" class="enter" userId="${matched_topic.userId }" userName="${matched_topic.userName }">邀请</a>
								</div>
							</li>
							</c:if>
							</c:forEach>
						</ul>
						</div>
						</c:if> --%>
						
						<c:if test="${requestScope.matchedTopicList!=null}">
						<h3 class="title">最匹配的"话题人"</h3>
						<ul class="talk-list">
							<c:forEach var="matched_topic" items="${requestScope.matchedTopicList}" varStatus="status">
							<c:if test="${matched_topic.userId!=sessionScope.user.id}">
							<li class="tp" userId="${matched_topic.userId }">
								<div class="hd">
									<a href="#" class="user-pic"><img src="<%=request.getContextPath() %>/image?picId=${matched_topic.imgUrl}" alt=""></a>
									<div class="info">
										<p class="nc">${matched_topic.name}</p>
										<p class="signature">${matched_topic.signature}</p>
										<div>
											<i class="iconfont mark">&#xe610;</i>
											<ul class="tag-lists">
											<c:forEach var="tag" items="${matched_topic.tagList}" varStatus="status">	
												<li>${tag}</li>
											</c:forEach>
											</ul>
											<!-- <ul class="tag-lists">
												<li>90后</li>
												<li>不靠谱</li>
											</ul> -->
										</div>
										<ul class="fix other">
											<li>发起相关话题数<span class="num">${matched_topic.p_num}</span></li>
											<li>&emsp;&emsp;参与相关话题数<span class="num">${matched_topic.j_num}</span></li>
										</ul>
									</div>
									<a href="#" class="enter" userId="${matched_topic.userId }" userName="${matched_topic.name }" onclick="topicInvite(mytopicId,myselfId,this);">邀请</a>
								</div>
							</li>
							</c:if>
							</c:forEach>
						</ul>
						</c:if>
						<c:if test="${requestScope.matchedTopicList==null && requestScope.my_topicId!=null}">
							<h3 class="title">最匹配的"话题人"</h3>
							<button class="resultEmpty">暂无匹配话题人</button>
							<br/>
						</c:if>
						</div>
						<!-- <li class="tp">
							<div class="hd">
								<a href="#" class="user-pic"><img src="images/user-default-pic.png" alt="齐天大圣"></a>
								<div class="info">
									<p class="nc">齐天大圣</p>
									<p class="signature">即使世界末日也要和爱的人手牵手在天台看日光。</p>
									<div>
										<i class="iconfont mark">&#xe610;</i>
										<ul class="tag-lists">
											<li>90后</li>
											<li>不靠谱</li>
										</ul>
									</div>
									<ul class="fix other">
										<li>发起相关话题数<span class="num">23</span></li>
										<li>&emsp;&emsp;参与相关话题数<span class="num">23</span></li>
									</ul>
								</div>
								<a href="#" class="enter">邀请</a>
							</div>
						</li> -->
			

			</div>
		</div>
	</section>
	<div id="Tip"><span class="txt"></span></div>
<script>

	var topicContent = "";
	var detailContent = "";
	var mytopicId = "${requestScope.my_topicId}";
	console.log("我刚发起topicId:"+mytopicId);
	 $("form").html5Validate(function() {
		var self = this;
			//self.submit(); 
	 });
	 
	 $(function(){
	 	$('.most-man  .talk-list .tp:even').addClass("even-b")
		console.log($("#sponsored")[0]);
		$("#sponsored").css("height",($(document).height()-120)+"px");
		
		
		if(!isNull(mytopicId))
	 	{
	 		/* showSuccess('话题发布成功');
	    	$('#disppear_alert').fadeOut(1000); */
	    	Tip("话题发布成功");
	    	//刚发起话题后，回到该页，重新填充表单内容
			$('#topic').val('${requestScope.topicName}');
			$('#topic').keyup();
			$('#detail').val('${requestScope.topicContent}');
			$('#detail').keyup();
			
			$('#topic').css("background-color", "#F0F0F0").attr("disabled",true);
			$('#detail').css("background-color", "#F0F0F0").attr("disabled",true);
			$("#btn_publish").css("color", "#d0d0d0").attr("disabled",true);
	 	} 
	 })
	 
	 //发起话题
	 $("#btn_publish").click(function(){
		
		var topic_name = $("#topic").val().trim();
		var topic_content = $("#detail").val().trim();
		var userId ="${sessionScope.user.id}";
		//var userName ="${(empty sessionScope.user.xunta_username)?sessionScope.thirdParty:sessionScope.user.xunta_username}";
		var userName = "${sessionScope.user.nickname}";
		var userLogoUrl ="${sessionScope.user.imageUrl}";
		console.log("topic_name:"+topic_name);
		console.log("topic_content:"+topic_content);
		console.log("userId:"+userId);
		console.log("userName:"+userName);
		console.log("userLogoUrl:"+userLogoUrl);
		
		if(topic_name=="")
		{
			//alert("话题　及　话题描述不能为空");
			Tip("话题 及 话题描述不能为空");
			return;
		}
		topicContent = "";
 		detailContent = "";
		
		var parameters={
				userId:userId,
				userName:userName,
				userLogoUrl:userLogoUrl,
				topicName:topic_name,
				topicContent:topic_content
		};
 		$.post("${pageContext.request.contextPath}/servlet/topic_service?cmd=fqht",parameters,function(res,status){
			$("#container_all").empty();
			$("#container_all").append(res);
		}); 
 		
		//$("#form1").submit();
	});
	 
	function topicInvite(topic_id,user_id,node){
		var invite_user_id = $(node).attr("userId");
		var invite_user_name = $(node).attr("userName");
		console.log("invite_user_id  :  "+invite_user_id);
		//console.log(topic_id+"  "+user_id+"  "+invite_user_id+"  "+invite_user_name);
		$(node).text("已发出邀请");
		console.log("客户端发送话题邀请");
		var parameters={
				"topicId":topic_id,
				"fromUserId":user_id,
				"to_userId":invite_user_id,
				"to_userName":invite_user_name
		};
 		$.post("${pageContext.request.contextPath}/servlet/topic_service?cmd=invite",parameters,function(res,status){
 			console.log(res);
 			if(res == "ok"){
 				//请求成功
 				console.log("客户端话题邀请请求成功")
 				inviteUser(invite_user_id, topic_id,user_id);
 			}else{
 				//请求失败。。。
 			}
		}); 
	}
	
	//计算当前输入的字符数，仿照新浪微博，中文占一个字符，英文和数字占半个
	 function getCharLength(obj,num)
     {
         var iLength = 0;
         for(var i = 0;i<obj.value.length;i++)  //遍历字符串中的每个字符
         {
             if(obj.value.charCodeAt(i) >255)   //如果当前字符的编码大于255
             {
                 iLength += 1;    //所占字符数加1
             }
             else
             {
                 iLength += 0.5;   //否则所占字节数加1
             }
             
         }
         if(Math.ceil(iLength)<=(num+0.5))
         {
        	 if(num==25)
        	 {
        		topicContent = obj.value;
        		console.log(topicContent);
        	 }
        	 else
        	 {
        		detailContent = obj.value;
        	 }
         }
         return Math.ceil(iLength);//返回字符所占字节数
     }
	 
	function showLen(obj,num){
		_this = $(obj);
		var id=_this.attr('id');
		
		var charLength = getCharLength(obj,num);
		if(id=='topic')
		{
	    	document.getElementById('TopicwordNum').innerHTML = '还可以输入'+(num-charLength) +'字';
	    	if(num-charLength<0)
	    	{
	    		document.getElementById('TopicwordNum').innerHTML = '还可以输入0字';
	    		document.getElementById('TopicwordNum').style.color = "red";
	    		obj.value = "";
	    		obj.value = topicContent;
	    	}
	    	if(num-charLength>0)
	    		document.getElementById('TopicwordNum').style.color ="#7a7a7a";
		}
		else
		{
			document.getElementById('DetailwordNum').innerHTML = '还可以输入'+(num-charLength) +'字';
			if(num-charLength<0)
			{
				document.getElementById('DetailwordNum').innerHTML = '还可以输入0字';
	    		document.getElementById('DetailwordNum').style.color = "red"; 
	    		obj.value = "";
	    		obj.value = detailContent;
			}
			if(num-charLength>0)
	    		document.getElementById('DetailwordNum').style.color ="#7a7a7a";
		}
	}

	function isNull( str ){ 
		if ( str == "" ) return true; 
		var regu = "^[ ]+$"; 
		var re = new RegExp(regu); 
		return re.test(str); 
	};
</script>
</html>