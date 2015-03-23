<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh">
	<section class="content">
		<div class="main">
			<table class="table-r" >
				<colgroup>
					<col width="230">
					<col width="415">
					<col width="150">
				</colgroup>
				
			<%-- <c:if test="${requestScope.topicList!=null}">
				<c:forEach var="searched_topic" items="${requestScope.topicList}"
					varStatus="status">
					<tbody>
						<tr>
							<td class="border"><img src="${searched_topic.logo_url}"
								alt="" class="user-pic"> <a href="#" class="nc">${searched_topic.userName}</a>
							</td>
							<td class="border"><span class="dt">标题：</span>
								<div class="dd">
									<p class="name">
										<a href="#">${searched_topic.topicName}</a>
									</p>
									<p class="txt">${searched_topic.topicContent}</p>
								</div></td>
							<td class="tc">${searched_topic.join_people_num}人</td>
							<td class="tc">${searched_topic.createTime}</td>
						</tr>
						<tr class="bt">
							<td colspan="4"></td>
						</tr>
					</tbody>
				</c:forEach>
			</c:if> --%>
			<c:if test="${requestScope.topicsHashMap!=null}">
				<c:forEach var="searched_topicsHashMap" items="${requestScope.topicsHashMap}"
					varStatus="status">
					 <%-- key值： ${searched_topicsHashMap.key}<br>
     				value大小：${searched_topicsHashMap.value.size()}<br> --%>
     				<c:forEach var="searched_userTopic" items="${searched_topicsHashMap.value}"
					varStatus="innerstatus">
						<tbody>
							<tr>
								<%-- <c:if test="${innerstatus.first}">
									<td class="border" rowspan="${searched_topicsHashMap.value.size()}">
										<img src="${searched_userTopic.logo_url}"
										alt="" class="user-pic"> <a href="#" class="nc">${searched_userTopic.userName}</a>
									</td>
								</c:if>  --%>
								<c:choose>  
								   <c:when test="${innerstatus.first}">
								   		<td class="border">
											<%-- <img src="${searched_userTopic.logo_url}" --%>
											<img src="images/delete/user-pic2.jpg"
											alt="" class="user-pic"> <a href="#" class="nc">${searched_userTopic.userName}</a>
										</td>
								   </c:when>  
								   <c:otherwise>
								   		<td class="border">
										</td>
								   </c:otherwise>  
								</c:choose>  
								<td class="border"><span class="dt">标题：</span>
									<div class="dd">
										<p class="name">
											<a href="#">${searched_userTopic.topicName}</a>
										</p>
										<p class="txt">${searched_userTopic.topicContent}</p>
									</div></td>
								<td class="tc">${searched_userTopic.join_people_num}人</td>
								<td class="tc">${searched_userTopic.createTime}</td>
							</tr>
							<tr class="bt">
								<td colspan="4"></td>
							</tr>
						</tbody>
					
					</c:forEach>
				</c:forEach>
			</c:if>
			
				<!-- <tbody>
					<tr>
						<td class="border" rowspan="2">
							<img src="images/delete/user-pic2.jpg" alt="" class="user-pic">
							<a href="#" class="nc">落叶的小王子</a>
						</td>
						<td class="border bb">
							<span class="dt">标题：</span>
							<div class="dd">
								<p class="name">
									<a href="#">PC和移动端双屏互动平台的讨论</a>
								</p>
								<p class="txt">关于最新的互联网咨询，支持在线学习并且跟大家讨论“PC和移动端双屏互动”,</p>
							</div>
						</td>
						<td class="tc" rowspan="2">500人</td>
						<td class="tc" rowspan="2">2天前</td>
					</tr>
					<tr>
						<td class="border">
							<span class="dt">标题：</span>
							<div class="dd">
								<p class="name">
									<a href="#">PC和移动端双屏互动平台的讨论</a>
								</p>
								<p class="txt">关于最新的互联网咨询，支持在线学习并且跟大家讨论“PC和移动端双屏互动”,</p>
							</div>
						</td>
					</tr>
					<tr class="bt"><td colspan="4"></td></tr>
				</tbody> -->
			</table>
			<!-- 分页 -->
			<!-- <div class="page-topic">
				<a href="#" class="iconfont prev">&#xe609;</a>
				<span class="cur">1/1</span>
				<a href="#" class="iconfont next">&#xe608;</a>
			</div> -->
		</div>
	</section>
	<script>
		/* var search_word=$("#search_word").val();//用户填写的搜索词
		console.log(search_word);
		var parameters={
				search_word:search_word
		};
		$.post("${pageContext.request.contextPath}/servlet/topic_service?cmd=htss",parameters,function(res,status){
			$("#container_all").empty();
			$("#container_all").append(res);
		}); */
		
		var currentPage = 1;//当前第几页
		var pageSum = 1;//总页数
		var pageNum = 5;//每页多少
		var data_index_array = new Array();
		
		/* var searchRes = "${requestScope.topicList}"; */
		//page 从1开始的页数
		/* function getThPageData(page) {
			console.log("当前页:" + currentPage + "  总页数:" + pageSum + "  每页多个条:"
					+ pageNum);

			if (page < 1) {
				return null;
			}
			var ret = {};
			var cur_page = currentPage;//当前第几页
			var pn = pageNum;//每一页多少数据
			//1页==> 1 -- pageNum*1
			//2页==> pageNum*(2-1)+1 --pageNum*2
			//**
			//
			//判断是否是最后一页
			if (page == pageSum) {
				var from_index = pn * (page - 1);//0起始的下标
				var to_index = data_index_array.length - 1;
				console.log("from_index:" + from_index);
				console.log("to_index:" + to_index);
				//打印第 page 页的数据
				for (var i = from_index; i <= to_index; i++) {
					console.log(data_index_array[i] + "==>"
							+ recommendedPeopleData[data_index_array[i]]);
					ret[data_index_array[i]] = recommendedPeopleData[data_index_array[i]];
				}
			} else {

				var from_index = pn * (page - 1);//0起始的下标
				var to_index = pn * page - 1;
				console.log("from_index:" + from_index);
				console.log("to_index:" + to_index);
				//打印第 page 页的数据
				for (var i = from_index; i <= to_index; i++) {
					console.log(data_index_array[i] + "==>"
							+ recommendedPeopleData[data_index_array[i]]);
					ret[data_index_array[i]] = recommendedPeopleData[data_index_array[i]];
				}
			}

			return ret;
		} */

/* 		//翻页
		$(".iconfont.prev").click(function() {
			if (currentPage > 1) {
				currentPage = currentPage - 1;
			} else {
				console.log("已经是第一页");
				return;
			}
			console.log("上一页:" + currentPage);
			var param = getThPageData(currentPage);
			do_postForTopicData(param);
			$(".page-topic .cur").text(currentPage + "/" + pageSum);
		});

		$(".iconfont.next").click(function() {
			if (currentPage < pageSum) {
				currentPage = currentPage + 1;
			} else {
				console.log("已经是最后一页");
				return;
			}
			console.log("下一页" + currentPage);
			var param = getThPageData(currentPage);
			do_postForTopicData(param);
			$(".page-topic .cur").text(currentPage + "/" + pageSum);
		}); */
	</script>
</html>