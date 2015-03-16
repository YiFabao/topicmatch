
$(function(){
	//文档加载完执行

});

//全局变量
var topicId_count_map={};//{话题ID：已经显示的历史消息数,在创建一个话题列表项的时候要在该全局变量中添加初始化记录

/**
 * 用户参与话题
 * @param topicId 话题Id
 * History :
 * 		2015/3/10 fabao.yi　first release
 */
function func_joinTopic(topicId){
	alert("用户点击喜欢的话题……");
}

/**
 * 创建一个话题列表项
 * @param topicId 话题id
 * History :
 * 		2015/3/13 10:10 fabao.yi first release
 */
function create_one_topic_item(topicId,topicContent,topicUnreadNum){
	console.log("创建聊天框左侧的话题列表项");
	//初始化显示的历史消息记录数为0
	topicId_count_map[topicId]=0;
	
	var topicMemberItem = $(".topic-box .left .rec-topic-list");
	
	var li_node = $("<li></li>");
	li_node.attr("id",topicId);
	li_node.attr("class","cur");
	
	var span_node = $("<span></span>");
	span_node.attr("class","num");
	span_node.text(topicUnreadNum);
	
	var p_node = $("<p></p>");
	p_node.attr("class","name");
	p_node.attr("title",topicContent);
	p_node.text(topicContent);
	
	var a_node = $("<a>&#xe601;</a>");
	a_node.attr("href","#");
	a_node.attr("class","iconfont close");
	
	li_node.append(span_node).append(p_node).append(a_node);
	topicMemberItem.append(li_node);
};

/**
 * 创建某个话题的参与人列表项
 * @param obj_json {topicId:, 参与人id: ,imgUrl: ,name:	,...}
 * History :
 * 		2015/3/13 fang
 */
function create_one_topicMember_item(obj_json){
	//创建聊天框右侧的一个参与人列表项
	console.log("创建聊天框右侧的一个参与人列表项");
	
	var topicUserList = $(".topic-box .right .user-list");
	
	var li_node = $("<li></li>");
	li_node.attr("id","参与人id")
	
	var div_node = $("<div></div>");
	div_node.attr("class","user-pic");
	
	var img_node = $("<img alt>");
	img_node.attr("src","images/delete/user-pic2.jpg");

	var p_node = $("<p></p>");
	p_node.attr("class","name");
	p_node.attr("title","测试-房");
	p_node.text("测试-房");
	
	div_node.append(img_node);
	li_node.append(div_node).append(p_node);
	topicUserList.append(li_node);
}

/**
 * 获取某个话题的历史消息记录
 * @param topicId　话题ID
 * @param count 全局变量,topicId已经显示的历史消息数
 * History :
 * 		2015/3/13 10:13　fabao.yi　first release,没有测试
 */

function getHistoryMessagesByTopicId(topicId,count){
	var ret_msgs = null;
	var parameters = {
		topicId: topicId,
		biginIndex: count,
		endIndex: parseInt(count) + 20
	};
	$.post("http://"+document.domain+":8080/topicmatch/TopicHistoryMessage/test", parameters, function(res, status) {
		console.log("status:" + status);
		console.log("执行获取历史消息post请求");
		if (window.historyMessageHandle) {
			historyMessageHandle(res);
			console.log("请求成功    historyMessageHandle");
		}
	});
};

/**
 * 创建消息并显示在对应topicId的对话框里
 * @param topicId 话题ID
 * @param message_obj_json　消息{messageContent: ,memberImgUrl: ,memberName: ,dateTime: }
 */
function create_and_show_one_message(topicId,message_obj_json){
	console.log("创建消息并显示在对应topicId的对话框里:"+message_obj_json.message);
}

/**
 * 当前用户在聊天编辑框内编辑内容点击发送将内容显示在聊天记录框
 * @param topicId
 * @param content
 * @param img-url
 * */
function createMessage(contentType){
	var chatBox = $(".chat-box");
	
	var p_node = $("<p></p>").attr("class","detail").text("测试");
	var img_url = $("<img alt>").attr("src","images/delete/user-pic2.jpg");
	var div_node = $("<div></div>").attr("class","user-pic");
	div_node.append(img_url);
	
	if(contentType == 0){
		//等于0表示自己的发言
		var myDiv = $("<div></div>").attr("class","user my");
		myDiv.append(p_node).append(div_node);
		chatBox.append(myDiv);
	}else{
		var otherDiv = $("<div></div>").attr("class","user other");
		otherDiv.append(p_node).append(div_node);
		chatBox.append(otherDiv);
	}
}

/**
 * 当用户进入某话题聊天框后在聊天记录框插入一条提示消息  ”-某某加入该对话组
 * */
function userEnterTopic(userName){
	var div_node = $("<div></div>").attr("class","system").text("-"+userName+"加入该对话组")
	$(".center .chat-box").append(div_node);
}
/**
 * 系统通知
 * */
function systemMsgInform(time,message){
	var ul_node = $(".tab-content .tab-panel.dn .news-list");
	var li_node = $("<li></li>").attr("class","panel");
	var p_node = $("<p></p>").attr("class","txt").text("测试：系统通知消息");
	var time = $("<time></time>").text("1111-1-1 11:11:11");
	ul_node.append(li_node.append(p_node).append(time));
}

/**
 * 话题消息通知
 * */
function topicMsgInform(time,message){
	var ul_node = $(".tab-content .tab-panel .news-list");
	
	var li_node = $("<li></li>").attr("class","panel");
	var p_node = $("<p></p>").attr("class","txt").text("测试：系统通知消息");
	var time_node = $("<time></time>").text("1111-1-1 11:11:11");
	
	var ul = $("<ul></ul>").attr("class","fix opera");
	var liYes = $("<li></li>").attr("class","btn");
	var liNo = $("<li></li>").attr("class","btn");
	var yes = $("<button></button>").attr("class","yes").text("同意");
	var no = $("<button></button>").attr("class","no").text("拒绝");
	
	ul_node.append(li_node.append(p_node).append(time_node).append(ul.append(liYes.append(yes)).append(liNo.append(no))));
	
}

/**
 * 用户点击通知消息里的同意或拒绝后元素修改
 * */
function topicMsgInformStatusChange(node,btnType){
	
	var li_node = $(node).parent().parent().parent();
	$(node).parent().parent().remove();
	
	var ul_node = $("<ul></ul>").attr("class","fix opera");
	
	var btn_vh = $("<li></li>").attr("class","btn vh");
	btn_vh.append($("<button></button>").attr("class","yes"));
	
	if(btnType == 0){
		var btn = $("<li></li>").attr("class","btn").text("已同意");
		ul_node.append(btn_vh).append(btn);
		li_node.append(ul_node);
	}else{
		var btn = $("<li></li>").attr("class","btn").text("已拒绝");
		ul_node.append(btn_vh).append(btn);
		li_node.append(ul_node);
	}
}
