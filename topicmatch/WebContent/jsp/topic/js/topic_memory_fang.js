$(function() {
	// 文档加载完执行
});

/**
 * 我发起的话题 p
 */
function topic_p(request) {
	
	var exist = $(".fix #"+request.topicId);
	console.log("exist.length : "+exist.length);
	if(exist.length == 1){
		return;
	}
	
	
		var ul_l_node = $(".column.l");// 定位父级元素
		var ul_r_node = $(".column.r");// 定位父级元素
		var li_node = $("<li></li>").attr("class", "tp").attr("id",request.topicId);// 创造元素父级标签
		// 话题标题及相关参数 如 发起人 时间 等
		var div_hd_node = $("<div></div>").attr("class", "hd");
		var user_pic = $("<a></a>").attr("class", "user-pic").attr("href", "#");
		var img_node = $("<img></img>").attr("alt", request.nickname).attr(
				"src", request.userImgUrl);
		user_pic.append(img_node);
		var p_nc = $("<p></p>").attr("class", "nc").text(request.nickname);
		var p_name = $("<p></p>").attr("class", "name");
		var a_node = $("<a></a>").attr("title", request.topicName).attr("href",
				"#").text(request.topicName);
		p_name.append(a_node);
		var time_node = $("<time></time>").text(request.yyyyMMdd);
		var b_hour = $("<b></b>").attr("class", "hour").text(request.HHmm);
		time_node.append(b_hour);
		var a_enter = $("<a></a>").attr("class", "enter").attr("href", "#")
				.text("进入");

		// div整合
		div_hd_node.append(user_pic).append(p_nc).append(p_name).append(
				time_node).append(a_enter);

		// 话题描述

		var div_bd_node = $("<div></div>").attr("class", "bd");
		var p_txt = $("<p></p>").attr("class", "txt");
		var b_node = $("<b></b>").text("最后回复 : ");
		var i_ago = $("<i></i>").attr("class", "ago").text(
				"(" + request.lastTime + ")");
		p_txt.append(b_node);
		p_txt.append(request.content + "  ");
		p_txt.append(i_ago)
		div_bd_node.append(p_txt);

		if (ul_l_node.height() >= ul_r_node.height()) {
			ul_r_node.append(li_node.append(div_hd_node).append(div_bd_node));
		} else {
			ul_l_node.append(li_node.append(div_hd_node).append(div_bd_node));
		}
}

/**
 * 我参与的话题 j
 */
function topic_j(request) {
	
	var exist = $(".fix #"+request.topicId);
	console.log("exist.length : "+exist.length);
	if(exist.length == 1){
		return;
	}
	
	
	// 定义父节点
	var ul_pateUl = $("#pateUl");

		var classType = $("#pateUl li").last().attr("class");
		var li_node;
		if (classType.indexOf("even") > 0) {
			li_node = $("<li></li>").attr("class", "column tp odd").attr("id",request.topicId);
		} else {
			li_node = $("<li></li>").attr("class", "column tp even").attr("id",request.topicId);
		}
		// 定义元素父节点

		// 定义元素节点
		var div_hd = $("<div></div>").attr("class", "hd");
		var a_user_pic = $("<a></a>").attr("class", "user-pic").attr("href",
				"#");
		var img_node = $("<img></img>").attr("alt", request.nickname).attr(
				"src", request.userImgUrl);
		a_user_pic.append(img_node);
		var span_area = $("<span></span>").attr("class", "area").text(
				request.nickname);
		var p_name = $("<p></p>").attr("class", "name");
		var a_node = $("<a></a>").attr("title", request.topicName).attr("href",
				"#").text(request.topicName);
		p_name.append(a_node);
		var time_node = $("<time></time>").append(request.yyyyMMdd + "  ");
		var b_hour = $("<b></b>").attr("class", "hour").append(request.HHmm);
		time_node.append(b_hour);
		var a_enter = $("<a></a>").attr("class", "enter").attr("href", "#");
		a_enter.append("进入");

		div_hd.append(a_user_pic).append(span_area).append(p_name).append(
				time_node).append(a_enter);
		var div_bd = $("<div></div>").attr("class", "bd");
		div_bd.append("来自 : ");
		var p_txt = $("<p></p>").attr("class", "txt").text(request.content);
		div_bd.append(p_txt);
		var div_date = $("<div></div>").attr("class", "date");
		var time = $("<time></time>").text(request.month + "月");
		var p_man = $("<p></p>").attr("class", "man").append(
				request.accepters + "人参与");
		div_date.append(time).append(p_man);
		li_node.append(div_hd).append(div_bd).append(div_date);
		ul_pateUl.append(li_node);
}
/**
 * 请求话题记忆 topic_type : p 发起 topic_type ： j 参与
 */
function getTopicMemory(user_id, topic_type, topic_num) {
	var parameters = {
		"cmd" : "topicMemory",
		"topicType" : topic_type,
		"topicNum" : topic_num,
		"userId" : user_id
	};
	$.post("http://" + document.domain
			+ ":8080/topicmatch/servlet/topic_service", parameters, function(
			res, status) {
		console.log("请求话题记忆时      status   :    " + status);
		console.log("执行获取话题记忆post请求 ");
		var arrayJson = res.msg;
		console.log(arrayJson);
		for (var i = 0; i < arrayJson.length; i++) {
			console.log(res.notTopic);
			if (res.notTopic == "yes") {
				console.log("没有历史话题");
			} else {
				if (topic_type == "p") {
					console.log("p");
					topic_p(arrayJson[i]);
				} else {
					console.log("j");
					topic_j(arrayJson[i]);
				}
			}
		}
	});
};