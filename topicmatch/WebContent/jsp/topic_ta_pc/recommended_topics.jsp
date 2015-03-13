<%@page import="so.xunta.entity.User"%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	User user = (User)session.getAttribute("user");
	if(user==null){
		user = new User();
		user.setId(1L);
		user.setXunta_username("测试账号");
		user.setImageUrl(request.getContextPath()+"/jsp/topic/images/1.jpg");
		session.setAttribute("user", user);
	} 

%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<title>话题推荐-寻TA网</title>
	<link rel="stylesheet" href="css/base.css">
	<!--[if lte IE 8]>
		<style>
			/*ie8支持css3
			.header .search,.top-memory .tab-menu,.talk-list .tp,.talk-list .enter,.form .load{behavior:url(js/ie-css3.htc);}*/
		</style>
		<script src="js/html5.js"></script>
	<![endif]-->	
	<style>
		.content,.main{position: absolute;bottom: 0;left:0;right:0;}
		.content{top:120px;}
		.main{top:0;overflow-y:auto;}
	</style>
</head>
<body>
	<header class="header">
		<div class="head">
			<h1 class="logo"><a href="/">寻TA网</a></h1>
			<div class="form search">
				<input type="text" class="text">
				<input type="submit" class="iconfont submit" value="&#xe602;">
			</div>
			<nav class="nav">
				<ul class="fix">
					<li class="current"><a href="#">话题推荐</a></li>
					<li><a href="topics_memory.html">话题记忆</a></li>
					<li><a href="#">发起话题</a></li>
				</ul>
			</nav>
			<div class="user">
				<!-- ==未登录 显示内容==
				<a href="#" class="admin">登录</a>
				<a href="#">注册</a>
				-->
				<div class="user-area">
					<i class="user-pic"><img src="${sessionScope.user.imageUrl }" alt=""></i>
					<span class="grade">T17</span>
					<div class="pop">
						<i class="link"></i>
						<ul class="menu-list">
							<li class="name">${sessionScope.user.xunta_username }</li>
							<li><a href="#"><i class="iconfont">&#xe60a;</i>个人信息</a></li>
							<li><a href="#"><i class="iconfont">&#xe60c;</i>账号设置</a></li>
							<li><a href="#"><i class="iconfont">&#xe60b;</i>退出</a></li>
							<button onclick="create_one_topicMember_item()">测试</button>
						</ul>
					</div>
				</div>
				<a class="news" href="#">
					<i class="iconfont">&#xe603;</i>
					<span class="dunk">15</span>
				</a>
			</div>
			<!-- 已登录 -->
		</div>
	</header>
	<section class="content">
		<div class="main">
			<ul class="topic-list">
<!--				<li class="topic-item right" style="top:30px;left:200px">
					<div class="pic"><img src="images/delete/user-pic2.jpg" alt=""></div>
					<div class="cont">
						今天用了迪素牛奶瓶男香，橙汁味道
					</div>
					<i class="tri"></i>
				</li>
				<li class="topic-item" style="top:100px;left:500px">
					<div class="pic"><img src="images/delete/user-pic2.jpg" alt=""></div>
				</li>
				<li class="topic-item left" style="top:230px;left:600px">
					<div class="pic"><img src="images/delete/user-pic2.jpg" alt=""></div>
					<div class="cont">
						今天用了迪素牛奶瓶男香，橙汁味道
					</div>
					<i class="tri"></i>
				</li>-->
			</ul>
			<div class="page-topic">
				<a href="#" class="iconfont prev">&#xe609;</a>
				<span class="cur">1/14</span>
				<a href="#" class="iconfont next">&#xe608;</a>
			</div>
			<!--话题聊天框-->
			<div class="topic-box">
				<div class="left">
					<ul class="rec-topic-list">
						<li class="cur">
							<span class="num">3</span>
							<p class="name" title="你多活一天你都干了些什么？">你多活一天你都干了些什么？</p>
							<a href="#" class="iconfont close">&#xe601;</a>
						</li>
						<li>
							<span class="num">3</span>
							<p class="name" title="你多活一天你都干了些什么？">你多活一天你都干了些什么？</p>
							<a href="#" class="iconfont close">&#xe601;</a>
						</li>
						<li>
							<span class="num">3</span>
							<p class="name" title="你多活一天你都干了些什么？">你多活一天你都干了些什么？</p>
							<a href="#" class="iconfont close">&#xe601;</a>
						</li>
						<li>
							<span class="num">3</span>
							<p class="name" title="你多活一天你都干了些什么？">你多活一天你都干了些什么？</p>
							<a href="#" class="iconfont close">&#xe601;</a>
						</li>
					</ul>
				</div>
				<div class="center">
					<div class="title">
						<div class="pic"><img src="images/delete/user-pic2.jpg" alt=""></div>
						<h3 title="话题：心底总会有声音不停提醒“不快乐”。">话题：心底总会有声音不停提醒“不快乐”。</h3>
						<a href="#" class="iconfont close">&#xe607;</a>
					</div>
					<div class="dec">
						<p class="txt">
							<span class="dt">话题描述：</span>
							论你怎样拥抱你想要的生活，心底总会有声音不停提醒“不快乐”你的内心似乎有个黑洞，不停向外索取，让你不停奔忙却忽略了你真正需要的东西.
						</p>
					</div>
					<div class="chat-box">
						<div class="user my">
							<p class="detail">今天用了迪素牛奶瓶男香，橙汁味道</p><div class="user-pic"><img src="images/delete/user-pic2.jpg" alt=""></div>
						</div>
						<div class="user other">
							<p class="detail">今天用了迪素牛奶瓶男香，橙汁味道</p><div class="user-pic"><img src="images/delete/user-pic2.jpg" alt=""></div>
						</div>
						<div class="system">
							-某某加入该对话组-
						</div>
					</div>
					<div class="send-box">
						<textarea name="" id=""></textarea>
						<button class="iconfont send-btn">&#xe604;</button>
					</div>
				</div>
				<div class="toggle" title="收缩">
					<i class="iconfont">&#xe605;</i>
				</div>
				<div class="right">
					<h4>参与人</h4>
					<ul class="user-list">
						<li>
							<div class="user-pic"><img src="images/delete/user-pic2.jpg" alt=""></div>
							<p class="name" title="顽皮的小废话">顽皮的小废话</p>
						</li>
						<li>
							<div class="user-pic"><img src="images/delete/user-pic2.jpg" alt=""></div>
							<p class="name" title="顽皮的小废话">顽皮的小废话</p>
						</li>
						<li>
							<div class="user-pic"><img src="images/delete/user-pic2.jpg" alt=""></div>
							<p class="name" title="顽皮的小废话">顽皮的小废话</p>
						</li>
						<li>
							<div class="user-pic"><img src="images/delete/user-pic2.jpg" alt=""></div>
							<p class="name" title="顽皮的小废话">顽皮的小废话</p>
						</li>
					</ul>
				</div>
			</div>
						<!-- 隐藏状态 -->
			<div class="mintopic-box">
				未读消息 <span class="num">23</span>
				<a href="javascript:;" class="iconfont unfold">&#xe60d;</a>
			</div>
		
		</div>
	</section>
	<div class="form confirm-box">
		<div class="cont">
			<p class="detail">XXX邀请您参与"XXX"话题</p>
			<div class="btn-area">
				<button class="btn-c">拒绝</button>
				&emsp;
				<button class="btn-c">同意</button>
			</div>
			<a href="javascript:;" class="iconfont close">&#xe601;</a>
		</div>
	</div>
<script src="js/jquery-1.4.4.min.js"></script>
<script src="js/jquery-powerSwitch-min.js"></script>
<script src="js/jquery.easing.min.js"></script>
<script src="js/jquery.lavalamp.min.js"></script>
<script src="js/common.js"></script>
<script src="ta_pc_chat.js"></script>
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
	var html ='<li class="tp"><div class="hd"><p class="name"><a href="#" title="最近在用的香水-still">最近在用的香水-still</a></p></div><div class="bd"><a href="#" class="user-pic"><img src="images/delete/pic1.jpg" alt="齐天大圣"></a><p class="txt">很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。</p><div class="last-time">最后回复：3天前 <time>16:24:35</time></div></div><div class="operating">发起时间：<time>2015-02-02  09:12</time><a href="#" class="enter">进入</a></div></li>';
	var htmlB = '<li class="column tp"><div class="hd"><a href="#" class="user-pic"><img src="images/delete/pic1.jpg" alt="齐天大圣"></a><p class="name"><a href="#" title="最近在用的香水-still">最近在用的香水-still</a></p><a href="#" class="enter">进入</a></div><div class="bd">来自: <span class="area">希达</span><time>2015-02-16  <b class="hour">16:24:35</b></time><p class="txt">很少用香水，偶尔用下。最近在用still，很喜欢它的星钻造型和知性味道挺不错的。<br>ps：我在搜集各种香水瓶子。</p></div><div class="date"><time>2月10日-2015年</time><p class="man">234人参与</p></div></li>'
	var i=0;
	var j=0;
	function LoadPage(){
		var who;
		var loadBtn=$(".load");
		//测试加载3条
		if(i==3){
			loadBtn = $('#launch .load')
			loadBtn.addClass("disabled").attr("disabled")
			loadBtn.html("暂无更多话题内容")
			return false;
		}if(j==3){
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
				i=i+1;
				$('#launch .l,#launch .r').append(html)
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
	 * 生成一个匹配的话题发起人
	 * @author fabao.yi
	 * @param x 坐标
	 * @param y
	 * @return li
	 */
	function createItemRight(top,left,topicId,address,xunta_username,sex,topicname,picUrl){
		var li=$("<li></li>");
		li.css({
			top:top+"px",
			left:left+"px"
		});
		li.click(func_joinTopic);

		var img = $("<img>");
		if(!picUrl){
			img.attr("src","images/delete/user-pic2.jpg")
			.attr("alt","");
		}
		else{
			img.attr("src",document.domain+"/"+picUrl)
			.attr("alt","");
		}


		var pic_div=$("<div></div>")
				.attr("class","pic");
		pic_div.html(img);
		
		
		var span_sex=$("<span></span>");
		span_sex.attr("class","man");
		if(sex=="m"){
			span_sex.html("♂");
		}else if(sex=="w"){
			span_sex.html("♀");
		}
		else{
			
		}
		
		
		var p_area=$("<p></p>");
		p_area.attr("class","area").html(address);
		
		var info = $("<div></div>");
		info.attr("class","info");
		info.append(span_sex);
		info.append(xunta_username);
		info.append(p_area);
		

		var cont_div=$("<div></div>");
		cont_div.attr("class","cont")
				.css("display","none")
				.css("z-index",999)
				.text(topicname);

		var i_node=$("<i></i>");
		i_node.attr("class","tri");

		li.append(pic_div).append(info).append(cont_div).append(i_node).attr("class","topic-item right");
		var randomInt =getRandom(0,1);
/*		if(randomInt==0)
		{
			li.attr("class","topic-item right");
		}else{
			li.attr("class","topic-item left");
		}*/
/* 		console.log("top:"+(li.css("top")));
		console.log("left:"+(li.css("left"))); */

		return li;
	}

	var w_min=200,w_max=800;
	var h_min=50,h_max=500;
	var R = 86.0;//图像的圆半径
	var C = 40.0;
	var topic_li_node_array=new Array();//存放已显示的li_node

	/**
	 * 检查坐标是否合适
	 */
	function checkIsSuitable(top,left,topic_li_node_array){

		if(topic_li_node_array.length==0)
		{
			return true;
		}
		var o1=new Object();
		o1.top = top;
		o1.left = left;

		var flag =true;
		for(var i=0;i<topic_li_node_array.length;i++)
		{
			var node = topic_li_node_array[i];

			var coor2=getCoordinate(node);
			//计算圆心
			var o2 =new Object();//圆心
			o2.top=coor2.top;
			o2.left=coor2.left;

			//计算外边距

			var dd=calcDisance(o1.top, o1.left, o2.top,o2.left)-R;
			if(dd<C)
			{
				flag=false;
				break;
			}
		}
		return flag;
	}

	//计算两点之间的距离
	function calcDisance(x1,y1,x2,y2){
		dx=x1-x2;
		dy=y1-y2;
		var dd=Math.sqrt(dx*dx+dy*dy);
		return dd;
	}

	/**
	 * 获取json节点对象的位置坐标
	 * @author fabao.yi
	 * @returns {top:xxx, left:xxx}
	 *
	 */
	function getCoordinate(obj){
		if(obj)
		{
			//console.log("top:"+parseFloat(obj.css("top"))+" left:"+parseFloat(obj.css("left")));
			return {top:parseFloat(obj.css("top")),left:parseFloat(obj.css("left"))};
		}
		else{
			return {top:0,left:0};
		}
	}

	//生成一个合适的坐标
	function createSuitableCoordinates(){
		var left;
		var top;
		var count=0;
		while(count<=1000){
			left = getRandom(w_min,w_max);
			top = getRandom(h_min,h_max);
			if(checkIsSuitable(top,left,topic_li_node_array))
			{
				break;
			}
			count++;
		}
		if(count<=1000)
		{
			return {top:top,left:left};
		}
		else{
			return null;
		}
	}

	/**
	 * 在dom节点上添加一个匹配的话题节点.topic-item
	 * @author fabao.yi
	 */
	function addOneLiNode(topicId,address,xunta_username,sex,topicname,picUrl){
		if(topic_li_node_array.length>20){
			return;
		}
		var coor = createSuitableCoordinates();
		if(coor==null){
			return;
		}
		//console.log("top:"+coor.top+"  left:"+coor.left);
		//createItemRight(top,left,topicId,address,xunta_username,sex,topicname,picUrl)
		var li_node=createItemRight(coor.top,coor.left,topicId,address,xunta_username,sex,topicname,picUrl);
		li_node.mouseenter(function(){
			$(this).find("div.cont").show(200);
		});
		li_node.mouseleave(function(){
			$(this).find("div.cont").hide(200);
		});
		$("ul.topic-list").append(li_node);
		topic_li_node_array.push(li_node);
	}


	//产生两个节点
	function test(){
		var coor1=createSuitableCoordinates();
		var coor2=createSuitableCoordinates();
		var li1=createItemRight(coor1.top,coor1.left,"");
		var li2=createItemRight(coor2.top,coor2.left,"");
		$("ul.topic-list").append(li1);
		$("ul.topic-list").append(li2);
		console.log(calcDisance(coor1.top,coor1.left,coor2.top,coor2.left)-R);
		if(calcDisance(coor1.top,coor1.left,coor2.top,coor2.left)-R<2){
			console.log("重合");
		}
		else{
			console.log("没有重合");
		}

	}
	//test();
	function test2(){
		for(var i=0;i<10;i++){
			addOneLiNode();
		}
	}
	//test2();//　生成10个人

	function test3(){
		setTimeout(show_and_hide,9);
		setTimeout(test3,3000*(topic_li_node_array.length)-1000*(topic_li_node_array.length-1));
	}

	function show_and_hide(){
		
		var t=0;
		for(var i=0;i<=topic_li_node_array.length-1;i++)
		{
			var current = topic_li_node_array[i].find("div.cont");
			//console.log(current);
			setTimeout(_showc(current),t);
			setTimeout(_hidec(current),t+3000);
			t+=2000;
		}

		function showc(current){
			current.show(200);
		}
		function _showc(_current){
			return function(){
				showc(_current);
			}
		}

		function hidec(current){
			current.hide(200);
		}
		function _hidec(_current){
			return function(){
				hidec(_current);
			}
		}
	}
	
	//
	var currentPage=1;//当前第几页
	var pageSum=0;//总页数
	var pageNum=3;//每页多少
	var recommendedPeopleData ={};
	var data_index_array = new Array();
	//发送请求获取后台推荐数据
	function doPost(userId){
		$.post("${pageContext.request.contextPath}/servlet/topic_service",{
			cmd:"recommendedPeople",
			userId:userId
		},function(res,status){
			//console.log(res);
			console.log("数组的大小:"+res.length);
			pageSum=res.length;
			pageSum =Math.floor(pageSum/pageNum)+1;//初始化总页数
			console.log("总页数："+pageSum);
			for(var i=0;i<res.length;i++)
			{
				var obj=res[i];
				//console.log(obj);
				for(var key in obj){
					recommendedPeopleData[key]=obj[key];
				}
			}
			console.log(recommendedPeopleData);
			for(var index in recommendedPeopleData){
				data_index_array.push(index);//数据下标
				
			}
			$(".page-topic .cur").text(currentPage+"/"+pageSum);
			var param = getThPageData(1);//初始化第一页
			do_postForRecommendedData(param);
		});
	}
	console.log("<%=user.id%>");
	doPost("<%=user.id%>");//1为当前登录用户的id
	
	//获取某一个页数据对应的{userId:topicId,...}
	//page 从1开始的页数
	function getThPageData(page){
		console.log("当前页:"+currentPage+"  总页数:"+pageSum+"  每页多个条:"+pageNum );
		
		if(page<1){
			return null;
		}
		var ret={};
		var cur_page = currentPage;//当前第几页
		var pn = pageNum;//每一页多少数据
		//1页==> 1 -- pageNum*1
		//2页==> pageNum*(2-1)+1 --pageNum*2
		//**
		//
		//判断是否是最后一页
		if(page==pageSum){
			var from_index = pn*(page-1);//0起始的下标
			var to_index = data_index_array.length-1;
			console.log("from_index:"+from_index);
			console.log("to_index:"+to_index);
			//打印第 page 页的数据
			for(var i=from_index;i<=to_index;i++){
				console.log(data_index_array[i]+"==>"+recommendedPeopleData[data_index_array[i]]);
				ret[data_index_array[i]]=recommendedPeopleData[data_index_array[i]];
			}
		}else{
		
			var from_index = pn*(page-1);//0起始的下标
			var to_index = pn*page-1;
			console.log("from_index:"+from_index);
			console.log("to_index:"+to_index);
			//打印第 page 页的数据
			for(var i=from_index;i<=to_index;i++){
				console.log(data_index_array[i]+"==>"+recommendedPeopleData[data_index_array[i]]);
				ret[data_index_array[i]]=recommendedPeopleData[data_index_array[i]];
			}
		}

		return ret;
	}
	
	//获取某一页的数据
	function do_postForRecommendedData(param)
	{
		console.log(JSON.stringify(param));
		$.post("${pageContext.request.contextPath}/servlet/topic_service",{
			cmd:"getRecommendPageData",
			data:JSON.stringify(param)
		},function(res,status){
			console.log("获取到的服务数据："+res);
			//移除原有的数据
			$("ul.topic-list").empty();
			topic_li_node_array.length=0;
			for(var i=0;i<res.length;i++)
			{
				console.log(res[i]);
				var d=res[i];
				//将数据显示出来
				addOneLiNode(d.topicId,d.address,d.xunta_username,d.sex,d.topicName,d.picUrl);
			}
			if(topic_li_node_array.length==1)
			{
				var current = topic_li_node_array[0].find("div.cont");
				console.log(current);
			}else{
				test3();
			}
		});
	}
	
	
	//翻页
	$(".iconfont.prev").click(function(){
		if(currentPage>1){
			currentPage=currentPage-1;
		}
		else{
			console.log("已经是第一页");
			return;
		}
		console.log("上一页:"+currentPage);
		var param = getThPageData(currentPage);
		do_postForRecommendedData(param);
		$(".page-topic .cur").text(currentPage+"/"+pageSum);
	});
	
	$(".iconfont.next").click(function(){
		if(currentPage<pageSum){
			currentPage = currentPage+1;
		}else{
			console.log("已经是最后一页");
			return;
		}
		console.log("下一页"+currentPage);
		var param = getThPageData(currentPage);
		do_postForRecommendedData(param);
		$(".page-topic .cur").text(currentPage+"/"+pageSum);
	});
	
	//发送消息按钮添加点击或按回车键发送消息
	 $(document).keydown(function(event){
		    if(event.keyCode==13){
		    	create_and_show_one_message(1,{message:"测试消息"})
		    }
	 });

	/**
	 * 产生一个指定区间的随机数
	 * @author fabao.yi
	 * @param min
	 * @param max
	 * @returns {number}
	 */
	function getRandom(min,max){
		return Math.floor(Math.random()*(max-min+1)+min)
	}
</script>
</body>
</html>