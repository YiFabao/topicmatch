<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
	<section class="content">
		<div class="main">
			<div class="form spons-topic">
				<h2>发起话题</h2>
				<form>
					<div class="item">
						<label for="topic" class="dt-b">话题:</label>
						<div class="dd-b"><input type="text" id="topic" class="text-d" data-max="30" required></div>
						<span class="tip">30个字</span>
					</div>
					<div class="item detail">
						<label for="detail" class="dt-b">话题描述:</label>
						<div class="dd-b">
							<div class="cont">
								<textarea name="" id="detail" cols="100%" rows="6" class="tarea" required data-max="5000"></textarea>
							</div>
						</div>
						<span class="tip">3250/5000个字</span>
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
						
						<h3 class="title">最匹配的"话题人"</h3>
						<c:if test="${requestScope.matchedTopicList!=null}">
						<ul class="talk-list">
							<c:forEach var="matched_topic" items="${requestScope.matchedTopicList}" varStatus="status">
							<c:if test="${matched_topic.userId!=sessionScope.user.id}">
							<li class="tp" userId="${matched_topic.userId }">
								<div class="hd">
									<a href="#" class="user-pic"><img src="images/user-default-pic.png" alt="齐天大圣"></a>
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
									<a href="#" class="enter" userId="${matched_topic.userId }" userName="${matched_topic.name }" onclick="topicInvite(mytopicId,myselfId);">邀请</a>
								</div>
							</li>
							</c:if>
							</c:forEach>
						</ul>
						</div>
						</c:if>
						
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
	
<script>
	var mytopicId = "${requestScope.my_topicId}";
	console.log("我刚发起topicId:"+mytopicId);
	 $("form").html5Validate(function() {
		var self = this;
			//self.submit(); 
	 });
	 
	 $(function(){
	 	$('.most-man  .talk-list .tp:even').addClass("even-b")
	 })
	 
	 //发起话题
	 $("#btn_publish").click(function(){
		
		var topic_name = $("#topic").val();
		var topic_content = $("#detail").val();
		var userId ="${sessionScope.user.id}";
		var userName ="${sessionScope.user.xunta_username}";
		var userLogoUrl ="${sessionScope.user.imageUrl}";
		console.log("topic_name:"+topic_name);
		console.log("topic_content:"+topic_content);
		console.log("userId:"+userId);
		console.log("userName:"+userName);
		console.log("userLogoUrl:"+userLogoUrl);
		
		if(topic_name=="")
		{
			alert("话题　及　话题描述不能为空");
			return;
		}
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
	 
	function topicInvite(topic_id,user_id){
		$(".enter").text("已发出邀请");
		var invite_user_id = $(".enter").attr("userId");
		var invite_user_name = $(".enter").attr("userName");
		//console.log(topic_id+"  "+user_id+"  "+invite_user_id+"  "+invite_user_name);
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
 				console.log("话题邀请请求成功")
 				inviteUser(invite_user_id, topic_id,user_id);
 			}else{
 				//请求失败。。。
 			}
		}); 
	}
</script>
</html>