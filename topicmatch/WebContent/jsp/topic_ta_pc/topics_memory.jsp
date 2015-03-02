<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<title>话题记忆-寻TA网</title>
<!-- 	<link rel="stylesheet" href="css/base.css"> -->
	<!--[if lte IE 8]>
		<script src="js/html5.js"></script>
	<![endif]-->
</head>
<body>
<!-- 	<header class="header">
		<div class="head">
			<h1 class="logo"><a href="/">寻TA网</a></h1>
			<div class="form search">
				<input type="text" class="text">
				<input type="submit" class="iconfont submit" value="&#xe602;">
			</div>
			<nav class="nav">
				<ul class="fix">
					<li><a href="#">话题推荐</a></li>
					<li class="current"><a href="#">话题记忆</a></li>
					<li><a href="#">发起话题</a></li>
				</ul>
			</nav>
			<div class="user">
				<a href="#" class="admin">登录</a>
				<a href="#">注册</a>
				<a class="news" href="#">
					<i class="iconfont">&#xe603;</i>
					<span class="dunk">15</span>
				</a>
			</div>
		</div>
	</header> -->
	<section class="content">
		<div class="main">
			<div class="form top-memory">
				<ul class="tab-menu">
					<li><a href="#launch" class="selected">我发起的话题</a></li>
					<li><a href="#participate">我参与的话题</a></li>
				</ul>
				<ul class="fix tab-box talk-list">
					<li id="launch" class="item launch">
						<ul class="column l">
							<li class="tp">
								<div class="hd">
									<p class="name"><a href="#" title="最近在用的香水-still">「保险」到底保险吗？-still</a></p>
								</div>
								<div class="bd">
									<a href="#" class="user-pic"><img src="images/user-default-pic.png" alt="齐天大圣"></a>
									<p class="txt">1、保险业的本质就是保险公司和客户之间对赌，而保险公司押的是大概率，用户永远押的都是小概率事件。从整体上分析，客户的总体收益永远是亏损的，对不对？<br> 2、有没有什么法律或者规定来限制保险公司用拆东墙补西墙的方法来兑现用户承诺的呢？<br>3、保险这个东西，在人民生活中普及才不过几年，那遥远的多少年以后的利益怎么去保证？是不是如果有严重通胀、战争等情况，这些都化为乌有？</p>
									<div class="last-time">最后回复：3天前&emsp;<time>16:24:35</time></div>
								</div>
								<div class="operating">
									发起时间：<time>2015-02-02  09:12</time>
									<a href="#" class="enter">进入</a>
								</div>
							</li>
							<li class="tp">
								<div class="hd">
									<p class="name"><a href="#" title="最近在用的香水-still">最近在用的香水-still</a></p>
								</div>
								<div class="bd">
									<a href="#" class="user-pic"><img src="images/delete/pic1.jpg" alt="齐天大圣"></a>
									<p class="txt">很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。</p>
									<div class="last-time">最后回复：3天前&emsp;<time>16:24:35</time></div>
								</div>
								<div class="operating">
									发起时间：<time>2015-02-02  09:12</time>
									<a href="#" class="enter">进入</a>
								</div>
							</li>
						</ul>
						<ul class="column r">
							<li class="tp">
								<div class="hd">
									<p class="name"><a href="#" title="最近在用的香水-still">最近在用的香水-still</a></p>
								</div>
								<div class="bd">
									<a href="#" class="user-pic"><img src="images/delete/pic1.jpg" alt="齐天大圣"></a>
									<p class="txt">很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。</p>
									<div class="last-time">最后回复：3天前&emsp;<time>16:24:35</time></div>
								</div>
								<div class="operating">
									发起时间：<time>2015-02-02  09:12</time>
									<a href="#" class="enter">进入</a>
								</div>
							</li>
							<li class="tp">
								<div class="hd">
									<p class="name"><a href="#" title="最近在用的香水-still">最近在用的香水-still</a></p>
								</div>
								<div class="bd">
									<a href="#" class="user-pic"><img src="images/user-default-pic.png" alt="齐天大圣"></a>
									<p class="txt">很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。</p>
									<div class="last-time">最后回复：3天前&emsp;<time>16:24:35</time></div>
								</div>
								<div class="operating">
									发起时间：<time>2015-02-02  09:12</time>
									<a href="#" class="enter">进入</a>
								</div>
							</li>
						</ul>
						<button class="load"><img src="images/loading.gif" alt="">加载中...</button>
					</li>
					<li id="participate" class="dn item pate">
						<ul class="fix" id="pateUl">
							<li class="column tp">
								<div class="hd">
									<a href="#" class="user-pic"><img src="images/user-default-pic.png" alt="齐天大圣"></a>
									<p class="name"><a href="#" title="最近在用的香水-still">「保险」到底保险吗？-still</a></p>
									<a href="#" class="enter">进入</a>
								</div>
								<div class="bd">
									来自: <span class="area">希达</span>
									<time>2015-02-16  <b class="hour">16:24:35</b></time>
									<p class="txt">1、保险业的本质就是保险公司和客户之间对赌，而保险公司押的是大概率，用户永远押的都是小概率事件。从整体上分析，客户的总体收益永远是亏损的，对不对？<br> 2、有没有什么法律或者规定来限制保险公司用拆东墙补西墙的方法来兑现用户承诺的呢？<br>3、保险这个东西，在人民生活中普及才不过几年，那遥远的多少年以后的利益怎么去保证？是不是如果有严重通胀、战争等情况，这些都化为乌有？</p>
								</div>
								<div class="date">
									<time>2月10日-2015年</time>
									<p class="man">234人参与</p>
								</div>
							</li>
							<li class="column tp">
								<div class="hd">
									<a href="#" class="user-pic"><img src="images/delete/pic1.jpg" alt="齐天大圣"></a>
									<p class="name"><a href="#" title="最近在用的香水-still">最近在用的香水-still</a></p>
									<a href="#" class="enter">进入</a>
								</div>
								<div class="bd">
									来自: <span class="area">希达</span>
									<time>2015-02-16  <b class="hour">16:24:35</b></time>
									<p class="txt">很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。<br>ps：我在搜集各种香水瓶子。</p>
								</div>
								<div class="date">
									<time>2月10日-2015年</time>
									<p class="man">234人参与</p>
								</div>
							</li>
							<li class="column tp">
								<div class="hd">
									<a href="#" class="user-pic"><img src="images/user-default-pic.png" alt="齐天大圣"></a>
									<p class="name"><a href="#" title="最近在用的香水-still">「保险」到底保险吗？-still</a></p>
									<a href="#" class="enter">进入</a>
								</div>
								<div class="bd">
									来自: <span class="area">希达</span>
									<time>2015-02-16  <b class="hour">16:24:35</b></time>
									<p class="txt">1、保险业的本质就是保险公司和客户之间对赌，而保险公司押的是大概率，用户永远押的都是小概率事件。从整体上分析，客户的总体收益永远是亏损的，对不对？<br> 2、有没有什么法律或者规定来限制保险公司用拆东墙补西墙的方法来兑现用户承诺的呢？<br>3、保险这个东西，在人民生活中普及才不过几年，那遥远的多少年以后的利益怎么去保证？是不是如果有严重通胀、战争等情况，这些都化为乌有？</p>
								</div>
								<div class="date">
									<time>2月10日-2015年</time>
									<p class="man">234人参与</p>
								</div>
							</li>
							<li class="column tp">
								<div class="hd">
									<a href="#" class="user-pic"><img src="images/delete/pic1.jpg" alt="齐天大圣"></a>
									<p class="name"><a href="#" title="最近在用的香水-still">最近在用的香水-still</a></p>
									<a href="#" class="enter">进入</a>
								</div>
								<div class="bd">
									来自: <span class="area">希达</span>
									<time>2015-02-16  <b class="hour">16:24:35</b></time>
									<p class="txt">很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。<br>ps：我在搜集各种香水瓶子。</p>
								</div>
								<div class="date">
									<time>2月10日-2015年</time>
									<p class="man">234人参与</p>
								</div>
							</li>
						</ul>
						<div class="bt">
							<button class="load" id="loadMoreBtn"><img src="images/loading.gif" alt="">加载中...</button>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</section>
	<footer class="footer">
		
	</footer>
<!-- <script src="js/jquery-1.4.4.min.js"></script>
<script src="js/jquery-powerSwitch-min.js"></script>
<script src="js/jquery.easing.min.js"></script>
<script src="js/jquery.lavalamp.min.js"></script>
<script src="js/common.js"></script> -->

<script>
	$(".tab-menu a").powerSwitch({
	    classAdd: "selected"
	}).eq(0).trigger("click");
	function eachTackList(){
		$("#participate li:odd").addClass("even")
		$("#participate li:even").addClass("odd")
		$("#participate li").eq(-1).addClass("last-1")
		$("#participate li").eq(-2).addClass("last-2")
		$("#participate li").eq(-3).addClass("last-3")
		$("#participate li").eq(-4).addClass("last-4")
		$("#participate li").eq(-5).addClass("last-5")
	}
	eachTackList()
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
		  }
	  });
	});
	var html1 ='<li class="tp"><div class="hd"><p class="name"><a href="#" title="最近在用的香水-still1">最近在用的香水1-still</a></p></div><div class="bd"><a href="#" class="user-pic"><img src="images/delete/pic1.jpg" alt="齐天大圣"></a><p class="txt">很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。</p><div class="last-time">最后回复：3天前 <time>16:24:35</time></div></div><div class="operating">发起时间：<time>2015-02-02  09:12</time><a href="#" class="enter">进入</a></div></li>';
	var html2 ='<li class="tp"><div class="hd"><p class="name"><a href="#" title="最近在用的香水-still2">最近在用的香水2-still</a></p></div><div class="bd"><a href="#" class="user-pic"><img src="images/delete/pic1.jpg" alt="齐天大圣"></a><p class="txt">很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。</p><div class="last-time">最后回复：3天前 <time>16:24:35</time></div></div><div class="operating">发起时间：<time>2015-02-02  09:12</time><a href="#" class="enter">进入</a></div></li>';
	var html3 ='<li class="tp"><div class="hd"><p class="name"><a href="#" title="最近在用的香水-still3">最近在用的香水3-still</a></p></div><div class="bd"><a href="#" class="user-pic"><img src="images/delete/pic1.jpg" alt="齐天大圣"></a><p class="txt">很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。</p><div class="last-time">最后回复：3天前 <time>16:24:35</time></div></div><div class="operating">发起时间：<time>2015-02-02  09:12</time><a href="#" class="enter">进入</a></div></li>';
	var html4 ='<li class="tp"><div class="hd"><p class="name"><a href="#" title="最近在用的香水-still4">最近在用的香水4-still</a></p></div><div class="bd"><a href="#" class="user-pic"><img src="images/delete/pic1.jpg" alt="齐天大圣"></a><p class="txt">很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。</p><div class="last-time">最后回复：3天前 <time>16:24:35</time></div></div><div class="operating">发起时间：<time>2015-02-02  09:12</time><a href="#" class="enter">进入</a></div></li>';
	var html5 ='<li class="tp"><div class="hd"><p class="name"><a href="#" title="最近在用的香水-still5">最近在用的香水5-still</a></p></div><div class="bd"><a href="#" class="user-pic"><img src="images/delete/pic1.jpg" alt="齐天大圣"></a><p class="txt">很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。</p><div class="last-time">最后回复：3天前 <time>16:24:35</time></div></div><div class="operating">发起时间：<time>2015-02-02  09:12</time><a href="#" class="enter">进入</a></div></li>';
	var html6 ='<li class="tp"><div class="hd"><p class="name"><a href="#" title="最近在用的香水-still6">最近在用的香水6-still</a></p></div><div class="bd"><a href="#" class="user-pic"><img src="images/delete/pic1.jpg" alt="齐天大圣"></a><p class="txt">很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。</p><div class="last-time">最后回复：3天前 <time>16:24:35</time></div></div><div class="operating">发起时间：<time>2015-02-02  09:12</time><a href="#" class="enter">进入</a></div></li>';
	var html7 ='<li class="tp"><div class="hd"><p class="name"><a href="#" title="最近在用的香水-still7">最近在用的香水7-still</a></p></div><div class="bd"><a href="#" class="user-pic"><img src="images/delete/pic1.jpg" alt="齐天大圣"></a><p class="txt">很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。</p><div class="last-time">最后回复：3天前 <time>16:24:35</time></div></div><div class="operating">发起时间：<time>2015-02-02  09:12</time><a href="#" class="enter">进入</a></div></li>';
	var html8 ='<li class="tp"><div class="hd"><p class="name"><a href="#" title="最近在用的香水-still8">最近在用的香水8-still</a></p></div><div class="bd"><a href="#" class="user-pic"><img src="images/delete/pic1.jpg" alt="齐天大圣"></a><p class="txt">很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。</p><div class="last-time">最后回复：3天前 <time>16:24:35</time></div></div><div class="operating">发起时间：<time>2015-02-02  09:12</time><a href="#" class="enter">进入</a></div></li>';
	var html9 ='<li class="tp"><div class="hd"><p class="name"><a href="#" title="最近在用的香水-still9">最近在用的香水9-still</a></p></div><div class="bd"><a href="#" class="user-pic"><img src="images/delete/pic1.jpg" alt="齐天大圣"></a><p class="txt">很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。</p><div class="last-time">最后回复：3天前 <time>16:24:35</time></div></div><div class="operating">发起时间：<time>2015-02-02  09:12</time><a href="#" class="enter">进入</a></div></li>';
	var html10 ='<li class="tp"><div class="hd"><p class="name"><a href="#" title="最近在用的香水-still10">最近在用的香水10-still</a></p></div><div class="bd"><a href="#" class="user-pic"><img src="images/delete/pic1.jpg" alt="齐天大圣"></a><p class="txt">很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。</p><div class="last-time">最后回复：3天前 <time>16:24:35</time></div></div><div class="operating">发起时间：<time>2015-02-02  09:12</time><a href="#" class="enter">进入</a></div></li>';
	var array_html=new Array();
	array_html.push(html1);
	array_html.push(html2);
	array_html.push(html3);
	array_html.push(html4);
	array_html.push(html5);
	array_html.push(html6);
	array_html.push(html7);
	array_html.push(html8);
	array_html.push(html9);
	array_html.push(html10);

	
	var htmlB = '<li class="column tp"><div class="hd"><a href="#" class="user-pic"><img src="images/delete/pic1.jpg" alt="齐天大圣"></a><p class="name"><a href="#" title="最近在用的香水-still">最近在用的香水-still</a></p><a href="#" class="enter">进入</a></div><div class="bd">来自: <span class="area">希达</span><time>2015-02-16  <b class="hour">16:24:35</b></time><p class="txt">很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。<br>ps：我在搜集各种香水瓶子。</p></div><div class="date"><time>2月10日-2015年</time><p class="man">234人参与</p></div></li>'
	
	var i=0;
	var j=0;
	function LoadPage(){
		var who;
		var loadBtn=$(".load");
		//测试加载3条
		if(array_html.length==0){
			loadBtn = $('#launch .load')
			loadBtn.addClass("disabled").attr("disabled")
			loadBtn.html("暂无更多话题内容")
			return false;
		}if(j==100){
			loadBtn = $('#participate .load')
			loadBtn.addClass("disabled").attr("disabled")
			loadBtn.html("暂无更多话题内容")
			return false;
		}else if(loadBtn.hasClass("disabled")){
			return false;
		}else{
			//我发起的话题动态加载
			var loadBtn = $('#launch .load')
			if($('#launch').is(":visible")){
				var nums=array_html.length;
				for(var i=0;i<nums;i++){
					//by fabao.yi
					//此处要判断左右两列的高度，添加到矮的的一边
					var left_clientTop=$('#launch .l').height();
					var right_clientTop=$('#launch .r').height();
					//console.log("左边的高度是:"+left_clientTop);
					//console.log("右边的高度是:"+right_clientTop);
					if(left_clientTop>right_clientTop){
						//添加到右边
						$('#launch .r').append(array_html[i])
					}
					else{
						//添加到左边
						$('#launch .l').append(array_html[i])
					}
				}
				//$('#launch .l,#launch .r').append(html)
			}
			//我参与的话题动态加载
			if($('#participate').is(":visible")){
				j=j+1;
				$('#participate>ul').append(htmlB)
				eachTackList()
			}
		}
	}
	
	/**
	*Author by:fabao.yi
	*History:
	*	2015/3/2 11:41:用于创建　我发起的话题　中的话题项
					   param:json===>{}
	*/
</script>
</body>
</html>