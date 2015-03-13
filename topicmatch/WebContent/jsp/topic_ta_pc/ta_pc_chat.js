
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
function create_one_topic_item(topicId){
	console.log("创建聊天框左侧的话题列表项");
	//初始化显示的历史消息记录数为0
	topicId_count_map[topicId]=0;
};

/**
 * 创建某个话题的参与人列表项
 * @param obj_json {topicId:, 参与人id: ,imgUrl: ,name:	,...}
 * History :
 * 		2015/3/13 fang
 */
function create_one_topicMember_item(){
	//创建聊天框右侧的一个参与人列表项
	console.log("创建聊天框右侧的一个参与人列表项");
	
	var topicMemberItem = $(".topic-box .left .rec-topic-list");
	topicMemberItem.append("<li id=\"1\" class=\"cur\"></li>");
	
	var topicMemberItem_cur = $(".rec-topic-list #1");
	
	topicMemberItem_cur.append("<span class=\"num\"></span>");
	$(".rec-topic-list #1 span").text("99");
	
	topicMemberItem_cur.append("<p class=\"name\" title=\"测试 房\"></p>");
	$(".rec-topic-list #1 p").text("测试 房");
	
	topicMemberItem_cur.append("<a href=\"#\" class=\"iconfont close\">&#xe601;</a>");
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




