
$(function(){
	//文档加载完执行

});

//全局变量
var topicId_count_map={};//{话题ID：已经显示的历史消息数,在创建一个话题列表项的时候要在该全局变量中添加初始化记录
var topicId_unreadMsgArray={};//话题topicId：未读消息数组,在聊天框隐藏状态下,保存到该数组中，显示后清空该数组
/**
 * 获取所有未消息数，从全局变量topicId_unreadMsgArray中求和
 */
function getTotalUnreadMsg(){
	var sum=0;
	for(var index in topicId_unreadMsgArray){
		sum+=topicId_unreadMsgArray[index].length;
	} 
	console.log("总未读消息："+sum);
	return sum;
}

Array.prototype.in_array = function(e)
{
	for(i=0;i<this.length;i++)
	{
	if(this[i] == e)
	return true;
	}
	return false;
}
Array.prototype.indexOf = function(val) {
	for (var i = 0; i < this.length; i++) {
	if (this[i] == val) return i;
	}
	return -1;
};
Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};

//全局变量
var topicItemArray = new Array();//用来保存已经加载过的话题列表对应的topicId

//全局变量
var chat_box_center={};//保存的是 topiId ==> 聊天框的 html
var chat_box_fold={};
var chat_box_right={};//保存的是 topicId ==> 聊天框的 html

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
 * 
 * fang
 */
function create_one_topic_item(obj,topicUnreadNum){
	console.log("创建聊天框左侧的话题列表项");
	chat_box_unfold();
	_this = $(obj);
	var topicId = _this.attr("topicId");
	
	//初始化显示的历史消息记录数为0
	topicId_count_map[topicId]=0;
	
	var topicContent = _this.find(".cont").html();
	console.log("topicContent:"+topicContent);
	console.log("topicId:"+topicId);
	var topicMemberItem = $(".topic-box .left .rec-topic-list");
	if(topicItemArray.in_array(topicId)){
		console.log("已经存在该话题");
		topicMemberItem.find("li[topicId="+topicId+"]").addClass("cur").siblings().removeClass("cur");
		changeTopicChatBox(topicId);
		return;
	}
	
	$(".topic-box .rec-topic-list").find(".cur").removeClass("cur");
	var li_node = $("<li></li>");
	li_node.attr("topicId",topicId);
	li_node.attr("class","cur");//高亮
	if(topicUnreadNum!=0&&topicUnreadNum!=null){
		var span_node = $("<span></span>");
		span_node.attr("class","num");
		span_node.text(topicUnreadNum);
		li_node.append(span_node)
	}
	var p_node = $("<p></p>");
	p_node.attr("class","name");
	p_node.attr("title",topicContent);
	p_node.text(topicContent);
	
	var a_node = $("<a>&#xe601;</a>");
	a_node.attr("href","#");
	a_node.attr("class","iconfont close");
	a_node.click(function(){
		$(this).parent().remove();//移除
		topicItemArray.remove(topicId);
		if(chat_box_center.topicId!=null){
			chat_box_center[topicId]=undefined;
		}
		if(chat_box_fold.topicId!=null){
			chat_box_fold[topicId]=undefined;
		}
		if(chat_box_right.topicId!=null){
			chat_box_right[topicId]=undefined;
		}
	});
	
	li_node.append(p_node).append(a_node);
	//li_node添加事件:点击事件
	li_node.click(function(e){
		$(this).attr("class","cur").siblings().removeClass("cur");
		//从全局变量中取出对应的html
		changeTopicChatBox(topicId);
	});
	
	topicMemberItem.append(li_node);
	topicItemArray.push(topicId);
	
	//发送请求获取对应topicId：Topic,List<User>
	$.post(contextPath+"/servlet/topic_service",{
		cmd:"joinTopic",
		userId:myselfId,
		topicId:topicId
	},function(res,status){
		//console.log(res);
		//console.log(res.user_p);
		var user_p = res.user_p;
		
		var topic = res.topic;
		var center = createChatBox_center(topic,user_p);
	
		chat_box_center[topicId]=center;//保存到全局变量
		
		var fold = createChatBox_fold(topicId);
		chat_box_fold[topicId]=fold;
		
		var memberList = res.memberList;
		console.log(memberList);
		for(var i=0;i<memberList.length;i++){
			console.log(memberList[i]);
		}
		
		var right = createChatBox_right(topicId,memberList);
		chat_box_right[topic.topicId]=right;
		console.log("打印topicId======>"+topicId);
		changeTopicChatBox(topicId);
	});
};

/**
 * 创建某个话题的参与人列表项
 * @param obj_json {topicId:, 参与人id: ,imgUrl: ,name:	,...}
 * History :
 * 		2015/3/13 fang
 */
function create_one_topicMember_item(member){
	//创建聊天框右侧的一个参与人列表项
	console.log("创建聊天框右侧的一个参与人列表项");
	
	var li_node = $("<li></li>");
	li_node.attr("userId",member.userId);
	
	var div_node = $("<div></div>");
	div_node.attr("class","user-pic");
	
	var img_node = $("<img>").attr("alt","").attr("src",member.imageUrl);

	var p_node = $("<p></p>");
	p_node.addClass("name");
	p_node.attr("title",member.userName);
	p_node.html(member.userName);
	
	div_node.append(img_node);
	li_node.append(div_node).append(p_node);

	return li_node;
}

/**
 * 获取某个话题的历史消息记录
 * @param topicId　话题ID
 * @param count 全局变量,topicId已经显示的历史消息数
 * History :
 * 		2015/3/13 10:13　fang
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
 * 当用户进入某话题聊天框后在聊天记录框插入一条提示消息  ”-某某加入该对话组
 * fang
 * */
function userEnterTopic(userName){
	var div_node = $("<div></div>").attr("class","system").text("-"+userName+"加入该对话组")
	$(".center .chat-box").append(div_node);
}
/**
 * 系统通知
 * fang
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
 * fang
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
 * fang
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



/**
 * 创建话题聊天框中间部分
 * @returns dom
 * @author fabao.yi
 * 	History: 2015/3/17 first release
 */
function createChatBox_center(topicObj,userObj){
	
	var div_center = $("<div></div>").attr("class","center").attr("topicId",topicObj.topicId);
	
	var div_title = $("<div></div>").attr("class","title");
	var div_pic = $("<div></div>").attr("class","pic");
	var img = $("<img></img>").attr("src",userObj.imageUrl).attr("alt","用户图像").attr("title",userObj.userName);
	div_pic.append(img);
	
	var h3 = $("<h3></h3>").attr("title","话题："+topicObj.topicTitle).html("话题:"+topicObj.topicTitle);
	var a_node = $("<a></a>").attr("href","javascript:chat_box_close()").addClass("iconfont close").html("&#xe607;");
	div_title.append(h3);
	div_title.append(div_pic);
	div_title.append(a_node);
	
	var div_dec = $("<div></div>").attr("class","dec");
	var p_txt = $("<p></p>").addClass("txt");
	var span =$("<span></span>").addClass("dt").html("话题描述：");
	p_txt.append(span).append(topicObj.topicContent);
	div_dec.append(p_txt);
	
	var div_chat_box = $("<div></div>").attr("class","chat-box");
	//同步ajax获取历史消息
	console.log("ajax异步获取历史消息：");
	console.log("topicId:"+topicObj.topicId);
	console.log("biginIndex："+topicId_count_map[topicObj.topicId]);
	console.log("endIndex:"+(parseInt(topicId_count_map[topicObj.topicId]) + 20));
	var parameters = {
			topicId: topicObj.topicId,
			biginIndex: topicId_count_map[topicObj.topicId],//初始历史消息值
			endIndex: parseInt(topicId_count_map[topicObj.topicId]) + 20
		};
	var ret_msgs;
	$.ajax({  
	     type : "post",  
	     url : contextPath+"/TopicHistoryMessage/test",
	     data : parameters,  
	     async : false,  //同步
	     datatype:"json",
	     success : function(res){  
	    	ret_msgs=res;
	    	//更改已加载历史消息记录数
	    	console.log("获取的历史消息数为："+res.length);
	    	topicId_count_map[topicObj.topicId]=parseInt(topicId_count_map[topicObj.topicId]) + 20;
	    	console.log("异步");
	    	console.log(ret_msgs);
	     } ,
	     error:function(){
	    	 console.log("获取历史消息失败");
	     }
	});
	////////////////////////////////////////////////////
	var div_send_box = $("<div></div>").attr("class","send-box");
	var textarea_node = $("<textarea></textarea>").attr("name","").attr("id","");
	var button = $("<button></button>").attr("class","iconfont send-btn").html("&#xe604;");
	div_send_box.append(textarea_node);
	div_send_box.append(button);
	
	div_center.append(div_title);
	div_center.append(div_dec);
	div_center.append(div_chat_box);
	div_center.append(div_send_box);
	return div_center;
}

/**
 * 创建话题聊天框中间的收缩和展开按钮
 * @returns dom
 * @author fabao.yi
 * 	History: 2015/3/17 first release
 */
function createChatBox_fold(topicId){
	var div_toggle = $("<div onclick='topic_member_toggle(this)'></div>").attr("class","toggle").attr("title","展开").attr("topicId",topicId);
	var i_iconfont = $("<i></i>").attr("class","iconfont").html("&#xe605;");
	div_toggle.append(i_iconfont);
	return div_toggle;
}

/**
 * 创建话题聊天框右侧部分
 * @author fabao.yi
 * @returns dom
 * 	History: 2015/3/17 first release
 */
function createChatBox_right(topicId,memberList){

	var div_right = $("<div></div>").attr("class","right").attr("topicId",topicId);
	var h4_node = $("<h4></h4>").html("参与人");
	var ul_user_list = $("<ul></ul>").attr("class","user-list");
	for(var i=0;i<memberList.length;i++){
		var member = memberList[i];
		var member_li = create_one_topicMember_item(member);
		ul_user_list.append(member_li);
	}
	div_right.append(h4_node);
	div_right.append(ul_user_list);
	return div_right;
}

/*for(var i=0;i<3;i++){
	var center = createChatBox_center(i);
	var fold = createChatBox_fold(i);
	var right = createChatBox_right(i);
	chat_box_center[i]=center;
	chat_box_fold[i]=fold;
	chat_box_right[i]=right;
}*/

//用户点击其他话题列表 项
//1. 保存当前的话题列表项对应的右侧的话题框html结构
//2.　从dom节点删除其结构
//3. 添加用户点击的话题列表项dom节点
function changeTopicChatBox(topicId){
	//判断当前的全局变量中有没有topicId对应的存储
	if(chat_box_center[topicId]==null){
		return;
	}
	//保有存当前的话题列表项
	var center = $(".topic-box").find(".center");
	var toggle = $(".topic-box").find(".toggle");
	var right = $(".topic-box").find(".right");
	var cur_topicId = center.attr("topicId");
	chat_box_center[cur_topicId]=center;
	chat_box_fold[cur_topicId]=toggle;
	chat_box_right[cur_topicId]=right;
	//从dom节点删除
	center.remove();
	toggle.remove();
	right.remove();
	
	//加载点击的话题对应的dom
	var _center = chat_box_center[topicId];
	var _toggle = chat_box_fold[topicId];
	var _right = chat_box_right[topicId];
	$(".topic-box").append(_center);
	$(".topic-box").append(_toggle);
	$(".topic-box").append(_right);
	
	//加载全局变量中存储的未读消息到聊天框
	if(topicId_unreadMsgArray[topicId]){
		console.log("加载全局变量中存储的未读消息到聊天框……");
		var unreadMsgArray = topicId_unreadMsgArray[topicId];
		console.log(unreadMsgArray);
		for(var i=0;i<unreadMsgArray.length;i++){
			createMessage(1,unreadMsgArray[i]);//显示未读消息
		}
		//删除未读消息
		delete topicId_unreadMsgArray[topicId];
		//将未读消息提醒元素删除
		var num_node = $(".rec-topic-list").find("li[topicId="+topicId+"]").find("span.num");
		num_node.remove();
		$(".mintopic-box span.num").html(getTotalUnreadMsg());//同步总未读消息提示
	}
}

function chat_box_close(){
	$('.topic-box').animate({"bottom":-620},'300');
}
function chat_box_unfold(){
	$('.topic-box').animate({"bottom":0},'300');
}
function topic_member_toggle(obj){
	var title =$(obj).attr("title");
	if(title=="展开"){
		console.log("展开");
		$(obj).attr("title","收缩");
		$(obj).find("i").html("&#xe605;");
		$('.topic-box').animate({"right":0},'slow')
	}
	else{
		console.log("收缩");
		$(obj).attr("title","展开");
		$(obj).find("i").html("&#xe606;");
		$('.topic-box').animate({"right":-82},'slow')
	}
}

var jjj={
	"user_p":{
			"userId":"1",
			"userName":"易发宝",
			"imageUrl":"/images/URL"
	},
	"topic":{
		"topicId":"T1",
		"topicTitle":"上海哪里好玩",
		"topicContent":"一直不知道上海哪好玩"
	},
	"memberList":[
		{
			"userId":"2",
			"userName":"张三",
			"imageUrl":"/images/zhang3url"
		},
		{
			"userId":"3",
			"userName":"李四",
			"imageUrl":"/images/lisiurl"
		}
	]
};

//测试var json ={userId:4,topicId:"DEC38294FCADEDFFA835C1D04D2DA2E1"};
//接收广播消息
window.receiveBroadcast = function(json)
{
	console.log("收到广播消息...");
	console.log("用户上线"+json.userId+"   "+json.topicId);
	/*//查询当前对应的话题窗口有没有打开
	if(!getDialogueByBoxId(json.topicId)){
		console.log("接收广播消息==>查询当查询当前对应的话题窗口是否已经加载==>未加载");
		return;
	}
	//如果窗口对应的话题聊天窗口存在,那么查询在该聊天窗口下有没有该联系人
	var flag=checkUserIdExistInTopicGroupList(json.userId,json.topicId);
	console.log("flag:"+flag);
	if(!flag){
		//var nickname = searchUser(json.userId);
		$.post("${pageContext.request.contextPath}/servlet/topic_service",{
			cmd:"searchnicknameByUserId",
			userId:json.userId
		},function(res,status){
			var nickname = res.nickname;
			console.log(json.userId+"对应的昵称："+nickname);
			var contact={
					topic_id:json.topicId,
					topic_memberId:json.userId,
					topic_member_name:nickname
			};
			console.log("添加联系人");
			addContactor(contact);
			//在消息框显示新用户进群
			var dialogueBox = getDialogueByBoxId(json.topicId);
			var dateTime = new Date().format("yyyy-MM-dd hh:mm:ss");
			addMsgContetntIntoDialogueBoxAboutNewUserComeIn(dialogueBox,nickname,dateTime);
			
		})
	}
	else{
		console.log("联系人在列表中已经存在");
	} */
}
/**
 * 当前用户在聊天编辑框内编辑内容点击发送将内容显示在聊天记录框
 * @param topicId
 * @param content
 * @param img-url
 * fang
 * */
function createMessage(contentType,obj_json){
	if(contentType==0){//自己发消息
		var msg = $(".topic-box .send-box").find("textarea").val();
		if(msg==null||msg.trim()==""){
			return;
		}
		var chatBox = $(".chat-box");
		var p_node = $("<p></p>").attr("class","detail").html(msg);
		var img_url = $("<img alt>").attr("src",userImageUrl);
		var div_node = $("<div></div>").attr("class","user-pic");
		div_node.append(img_url);
		//等于0表示自己的发言
		var myDiv = $("<div></div>").attr("class","user my");
		myDiv.append(p_node).append(div_node);
		chatBox.append(myDiv);
		$(".chat-box").scrollTop($(".chat-box").height()); //滚动条置底
		$(".topic-box .send-box").find("textarea").val("");
		
		//发送消息
		//发送消息需要传的参数，话题ID,消息id,发送人id,联系人id数组,消息,时间,发送人昵称
    	//1.话题id
    	//2获取联系人　id[]
    	//获取连系人的id 数组
    	var contactsIdArray = getContactsArray();
    	//3消息 
    	
    	//4发送人id
    	var fromUserId = myselfId;
    	//5发送人昵称
    	var fromUserName = myname;
    	//6 topicId
    	var topicId = $(".topic-box .center").attr("topicId");
    	//7 消息id
    	var msgId = fromUserId+""+new Date().getTime();
    	
    	console.log("话题id:"+topicId);
    	console.log("发送人id:"+fromUserId);
    	console.log("发送人昵称:"+fromUserName);
    	console.log("消息:"+msg);
    	console.log("消息id:"+msgId);
    	console.log("联系人:"+contactsIdArray);
    	sendMsg(topicId,msgId,fromUserId,fromUserName,msg.toString().trim(),contactsIdArray); 
		
	}else{
		var chatBox = $(".chat-box");
		var p_node = $("<p></p>").attr("class","detail").html(decodeURIComponent(obj_json.message));
		//根据userid从好友列表中获取图像url
		var userid = obj_json.senderId;
		var imageUrl = $(".user-list").find("li[userid="+userid+"]").find(".user-pic img").attr("src");
		console.log("imageUrl:"+imageUrl);
		//var img_url = $("<img alt>").attr("src","images/delete/user-pic2.jpg");
		var img_url = $("<img alt>").attr("src",imageUrl);
		var div_node = $("<div></div>").attr("class","user-pic");
		div_node.append(img_url);

		var otherDiv = $("<div></div>").attr("class","user other");
		otherDiv.append(p_node).append(div_node);
		chatBox.append(otherDiv);
		$(".chat-box").scrollTop($(".chat-box").height()); //滚动条置底
	}
	
}
//创建消息处理函数
window.webimHandle = function(json) {
	/**
	 * key:status=====>value:1
	 * key:topicId=====>value:DEC38294FCADEDFFA835C1D04D2DA2E1
	 * key:messageId=====>value:21422350893108 key:senderId=====>value:2
	 * key:nickname=====>value: oliver key:message=====>value:%E4%B8%8A%E6%B5%B7
	 * key:accepterIds=====>value:1,2 key:dateTime=====>value:2015-01-27
	 * 17:27:38 key:date=====>value:20150127 key:time=====>value:172738
	 */
	// 获取对应topicId 的窗口
	var msgStr = "";
	var topicId = json.topicId;
	console.log("接收到消息:" + json);
	/**
	 * 接收到消息 1.判断当前已经打开的对话框是否是消息所要显示的目的对话框 2.如果是，则直接显示
	 * 3.如果不是,将收到的消息放入到未读消息的全局变量中 ,并更新显示的未读消息数 如果存在：将未读消息数改为全局变量中的未读消息数 如果不存在：
	 * 创建话题列表项,未读消息数为全局变量中的未读消息数 创建对话框，保存到全局变量 4.创建话题列表项
	 */
	if (topicId == null || topicId == "") {
		console.log("Error:topicId为空值");
		return;
	}
	// 判断当前已经打开的对话框是否是消息所要显示的目的对话框
	var cur_topicId = $(".topic-box .center").attr("topicid");
	if (cur_topicId == topicId) {// 是当前窗口
		// 判断是否是自己发的消息
		if (json.senderId == myselfId) {
			// TODO:是自己发的消息,将消息改为发送成功
		} else {
			createMessage(1, json);
		}
	} else {// 不是当前窗口
		// 将未读消息保存到全局变量,注意同步总未读消息数
		console.log("不是当前窗口");
		if (topicId_unreadMsgArray[topicId]) {
			topicId_unreadMsgArray[topicId].push(json);
		} else {
			var arr = new Array();
			arr.push(json);
			topicId_unreadMsgArray[topicId] = arr;
		}
		$(".mintopic-box span.num").html(getTotalUnreadMsg());//同步总未读消息提示
		// 判断话题列表项是否存在
		if (topicItemArray.in_array(topicId)) {// 存在话题列表项,更新显示未读消息数
			console.log("列表项存在");
			// TODO:
			var topicUnreadNum = topicId_unreadMsgArray[topicId].length;
			// 判断是否存在<span class="num">2</span>
			// 不在就创建，存在就更新里面的text内容
			var num_node = $(".rec-topic-list li[topicId="+topicId+"]").find("span.num");
			console.log("num_length:"+num_node.length);
			if(num_node.length){
				//存在,更新未读消息数,从全局未读消息数中获取
				console.log("存在num节点元素："+num_node[0]);
				num_node.html(topicId_unreadMsgArray[topicId].length);
			}
			else{
				//不存在,创建节点元素
				console.log("不存在num节点元素,创建节点元素");
				var span_node = $("<span></span>").addClass("num").html(topicId_unreadMsgArray[topicId].length);
				console.log(span_node[0]);
				//添加
				var topic_list_node = $(".rec-topic-list li[topicId="+topicId+"]");
				topic_list_node.prepend(span_node);
			}
		} else {
			console.log("列表项不存在");
			// 创建话题列表项及创建话题对话框
			// 发送请求获取对应topicId：Topic,List<User>
			$.post(contextPath + "/servlet/topic_service", {
				cmd : "getTopicAndTopicMembersByTopicId",
				topicId : topicId
			}, function(res, status) {
				console.log(res);
				console.log(res.topic);
				console.log(res.memberList);

				var topic = res.topic;
				var memberList = res.memberList;// 话题成员
				var user_p = res.user_p;

				var center = createChatBox_center(topic, user_p);
				var fold = createChatBox_fold(topicId);
				var right = createChatBox_right(topicId, memberList);

				chat_box_center[topicId] = center;// 保存到全局变量
				chat_box_fold[topicId] = fold;
				chat_box_right[topic.topicId] = right;

				// 创建话题列表项
				var topicMemberItem = $(".topic-box .left .rec-topic-list");
				var li_node = $("<li></li>");
				li_node.attr("topicId", topicId);
				var span_node = $("<span></span>");
				span_node.attr("class", "num");
				var topicUnreadNum = topicId_unreadMsgArray[topicId].length;
				span_node.text(topicUnreadNum);
				li_node.append(span_node);

				var p_node = $("<p></p>");
				p_node.attr("class", "name");
				p_node.attr("title", topic.topicTitle);
				p_node.text(topic.topicTitle);

				var a_node = $("<a>&#xe601;</a>");
				a_node.attr("href", "#");
				a_node.attr("class", "iconfont close");
				a_node.click(function() {
					$(this).parent().remove();// 移除
					topicItemArray.remove(topicId);
					if (chat_box_center.topicId != null) {
						chat_box_center[topicId] = undefined;
					}
					if (chat_box_fold.topicId != null) {
						chat_box_fold[topicId] = undefined;
					}
					if (chat_box_right.topicId != null) {
						chat_box_right[topicId] = undefined;
					}
				});

				li_node.append(p_node).append(a_node);
				// li_node添加事件:点击事件
				li_node.click(function(e) {
					$(this).attr("class", "cur").siblings().removeClass("cur");
					// 从全局变量中取出对应的html
					changeTopicChatBox(topicId);
				});
				topicMemberItem.append(li_node);
				topicItemArray.push(topicId);
			});
		}
	}
};

//获取聊天框相应topicId 下的聊天框
function getHistoryMessage(topicId, count) {
	console.log("执行获取历史消息方法");
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

//获取联系人id数组
function getContactsArray(){
	var userIds = $(".user-list").find("li");
	var contacts=new Array();
	for(var i=0;i<userIds.length;i++)
	{
		contacts.push($(userIds[i]).attr("userid"));
	}
	return contacts;
}

//test
function testAjaxAsync(){
	var result;
	$.ajax({  
	     type : "post",  
	     url : contextPath+"/servlet/topic_service",
	     data : {cmd:"test"},  
	     async : false,  //同步
	     datatype:"json",
	     success : function(res){  
	    	result=res;
	    	console.log("异步");
	       console.log(result);
	     }  
	});
	console.log("同步");
	console.log(result);
}









