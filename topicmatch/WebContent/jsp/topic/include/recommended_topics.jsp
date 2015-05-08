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
	//	session.setAttribute("user", user);
	} 

%>

<!DOCTYPE html>
<html lang="zh">
<head>
<style type="text/css">
/* 1秒消失提示框 */
#disppear_alert{position:absolute;top:240px;left:380px;font-size: 18px;height: 48px;text-align: center;border-radius: 10px;width: 494px;border:none;cursor: pointer;clear: both;background-color: #f3f3f3;display: block;margin-left: auto;margin-right: auto;}
</style>
</head>
	<section class="content">
		<div class="main">
			<ul class="topic-list">
				<!-- <li class="topic-item right" style="top:30px;left:200px" topicId="E1D4D92E96D74CCC80151B98AC5537D7" onclick="create_one_topic_item(this,null)">
					<div class="pic"><img src="images/2.jpg" alt="" ></div>
					<div class="info">
						<i class="iconfont man">&#xe60f;</i>fabaoyi
						<p class="area">上海市</p>
					</div>
					<div class="cont">
						春天来了
					</div>
					<i class="tri"></i>
				</li>
				<li class="topic-item right" style="top:230px;left:600px" topicId="A2B1B0F770C4E24FCB05BAADCEE47A6D" onclick="create_one_topic_item(this,null)"> 
					<div class="pic"><img src="images/delete/user-pic2.jpg" alt="" ></div>
					<div class="info">
						<i class="iconfont woman">&#xe60e;</i>candy
						<p class="area">杭州市</p>
					</div>
					<div class="cont">
						我是candy,来到寻Ta网,想<font color='red'>找到一个能聊得来的小伙伴</font>
					</div>
					<i class="tri"></i>
				</li> -->
			</ul>
			<div class="page-topic" align="center">
				<a href="#" class="iconfont prev">&#xe609;</a>
				<span class="cur">1/1</span>
				<a href="#" class="iconfont next">&#xe608;</a>
			</div>
		</div>
	</section>


<script>

	/**
	 * 生成一个匹配的话题发起人
	 * @author fabao.yi
	 * @param x 坐标
	 * @param y
	 * @return li
	 */
	function createItemRight(top,left,topicId,address,nickname,sex,topicname,picUrl,resMsg){
		var li=$("<li onclick=create_one_topic_item(this,null)></li>");
		li.css({
			top:top+"px",
			left:left+"px"
		});
		li.attr("topicId",topicId);
		
		//li.click(func_joinTopic);

		var img = $("<img>");
		if(!picUrl){
			img.attr("src","images/delete/user-pic2.jpg")
			.attr("alt","");
		}
		else{
			img.attr("src","<%=request.getContextPath() %>/image?picId="+picUrl)
			.attr("alt","").css("width","100%").css("height","100%");
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
		info.append(nickname);
		info.append(p_area);
		

		<%--最后回复内容--%>
		var p_resMsg = $("<p></p>").css("color","lightgray");
		if(resMsg==""){
			p_resMsg.html("(该话题下没有任何回复)");
		}else{
			p_resMsg.html("最新回复:"+resMsg);
		}
		
		
		var cont_div=$("<div></div>");
		cont_div.attr("class","cont")
				.css("display","none")
				.css("z-index",999)
				.append(topicname).append(p_resMsg);

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
	function addOneLiNode(topicId,address,nickname,sex,topicname,picUrl,resMsg){
		if(topic_li_node_array.length>20){
			return;
		}
		var coor = createSuitableCoordinates();
		if(coor==null){
			return;
		}
		//console.log("top:"+coor.top+"  left:"+coor.left);
		//createItemRight(top,left,topicId,address,xunta_username,sex,topicname,picUrl)
		var li_node=createItemRight(coor.top,coor.left,topicId,address,nickname,sex,topicname,picUrl,resMsg);
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
	var t1;
	var t2;
	function test3(){
		t1=setTimeout(show_and_hide,9);
		t2=setTimeout(test3,3000*(topic_li_node_array.length)-1000*(topic_li_node_array.length-1));
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
	var pageSum=1;//总页数
	var pageNum=20;//每页多少
	var recommendedPeopleData ={};
	var data_index_array = new Array();
	//发送请求获取后台推荐数据
	function doPost(userId){
		$.post("${pageContext.request.contextPath}/servlet/topic_service",{
			cmd:"recommendedPeople",
			userId:userId
		},function(res,status){
			
			//[obj.put(rtp.topicId,rtp.userId),...]
			if(res=="没有匹配的话题"){
				console.log("无推荐话题，请完善标签！");
				$(".topic-list").html("<h3 align='center'>悲摧了,没有与你相关的推荐,尝试多加一些与你个人爱好相关的标签吧！</h3>");
				console.log(res);
			}else{
				console.log("数组的大小:"+res.length);
				pageSum=res.length;
				if(res.length==0){
					console.log("无推荐，需无善标签信息");
					return;
				}
				console.log(res.length);
				pageSum =pageSum%pageNum==0?pageSum/pageNum:Math.floor(pageSum/pageNum)+1;//初始化总页数
				console.log("总页数："+pageSum);
				for(var i=0;i<res.length;i++)
				{
					var obj=res[i];//一条{topicId:"",userId"}
					//console.log(obj);
					for(var key in obj){
						recommendedPeopleData[key]=obj[key];//recommendedPeopleData里面存储的是<topicId,userId>
					}
				}
				console.log(recommendedPeopleData);
				for(var index in recommendedPeopleData){
					data_index_array.push(index);//数据下标 这个下标是topicId
					
				}
				$(".page-topic .cur").text(currentPage+"/"+pageSum);
				var param = getThPageData(1);//初始化第一页
				do_postForRecommendedData(param);
			}
		});
	}
	//console.log("<%=user.id%>");
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
				console.log(d);
				console.log(d.topicName);
				console.log(d.lastResMsg);
				console.log(decodeURIComponent(d.lastResMsg));
				var resMsg = d.lastResMsg==undefined?"":decodeURIComponent(d.lastResMsg);
				addOneLiNode(d.topicId,d.address,d.nickname,d.sex,d.topicName,d.userImgUrl,resMsg);
			}
			if(topic_li_node_array.length==1)
			{
				var current = topic_li_node_array[0].find("div.cont");
				console.log(current);
			}else{
				clearTimeout(t1);
				clearTimeout(t2);
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
	 
 $(function(){
	 	var wheight = $(window).height()
	 	$(".topic-list").height(wheight-120-90);
	 	
	 	//如果是从修改资料页面跳过来的，显示修改成功提示框
	 	var modifiedSuccess = "${sessionScope.modifiedSuccess}";
	 	if(modifiedSuccess&&modifiedSuccess=="true")
	 	{
	 		showSuccess('资料修改成功');
	    	$('#disppear_alert').fadeOut(1000);
	    	<%
	    	String modifiedSuccess = (String)session.getAttribute("modifiedSuccess");
	    	if(modifiedSuccess!=null&&!"".equals(modifiedSuccess.trim()))
	    		session.setAttribute("modifiedSuccess", null);
	    	%>
	 	} 
})

	//显示1秒消失提示框
	function showSuccess(text){
	        if($('#disppear_alert span').text().length>0){
	        $('#disppear_alert').empty().append('<span>'+text+'</span>');
	        $('#disppear_alert').css('display','block');
	        }else{
	        $('.main').prepend('<div id="disppear_alert"><span>'+text+'</span></div>');
	        }
	    }

 
 $(function(){
	 	/*
	 	var s =window.screen.height; 
	 	$(".topic-list").height(s-320)*/
	 	$('.send-box').live('click',function(){
	 		$('.send-box textarea').focus();
	 	})
	 })
</script>
</html>