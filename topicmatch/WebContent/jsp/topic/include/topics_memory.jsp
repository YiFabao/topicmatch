<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
	<section class="content">
		<div class="main">
			<div class="form top-memory">
				<ul class="tab-menu">
					<li><a href="#launch" class="selected" onclick="getTopicMemory(myselfId,'p','0');">我发起的话题</a></li>
					<li><a href="#participate" onclick="getTopicMemory(myselfId,'j','0');">我参与的话题</a></li>
				</ul>
				<ul class="fix tab-box talk-list">
					<li id="launch" class="item launch">
						<ul class="column l">
						</ul>
						<ul class="column r">
						</ul>
						<button class="load" onclick="loadTopicMemory(myselfId,'p',load);"><img src="images/loading.gif" alt="">加载中...</button>
					</li>
					<li id="participate" class="dn item pate">
						<ul class="fix" id="pateUl">
							
						</ul>
						<div class="bt">
							<button class="load" id="loadMoreBtn" onclick="loadTopicMemory(myselfId,'j',load);"><img src="images/loading.gif" alt="">加载中...</button>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</section>
<script>
	$(".tab-menu a").powerSwitch({
	    classAdd: "selected"
	}).eq(0).trigger("click");
	
	
	//滑动加载
	$(document).scrollTop() //获取垂直滚动条到顶部的距离
	$(document).height()//整个网页的高度
	$(window).height()//浏览器窗口的高度
	$(window).scrollTop() + $(window).height()  >= $(document).height()
	$(function(){
	  $(window).scroll(function() {
		  //当内容滚动到底部时加载新的内容
		  if ($(this).scrollTop() + $(window).height() + 20 >= $(document).height() && $(this).scrollTop() > 20) {
			  //ajax 加载
			  LoadPage();
			  alert("滑动加载事件");
		  }
	  });
	});
	
	
	
</script>
