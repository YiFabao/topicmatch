/*
	 	定义成员变量
 * */
var ws; // WebSocket-Obj
var wssae = 'null'; // WebSocket state mark;
var msgArray = new Array();

document.addEventListener("plusready", PlusReady, false);

function PlusReady() {
	/*
	 * 文档加载完成后绑定事件
	 */
	document.addEventListener("pause", onAppPause, false);
	document.addEventListener("resume", onAppResume, false);
}

function createWebsocketConnect(userId) { // 提供获取websocket连接的方法
	websocketEvent(userId);
}

function onAppResume() { // 运行环境从后台切换到前台事件
	websocketEvent();
}

function onAppPause() { // 运行环境从前台切换到后台事件
	wssae = 'no';
	ws.close();
}

function websocketEvent(userId) {
	// 可以做一个连接中的效果,如果连接成功,触发onpen方法在取消连接中效果,如果10秒中没连接上会触发checkWebSocketState方法的状态,显示当前网络状态
	// ws = new
	// WebSocket('ws://121.40.61.219:8080/im_websocket/WebSocketServlet?userId='
	// + userId);
	ws = new WebSocket(
			"ws://"+document.domain+"/topicmatch/WebSocketServlet?userId=" + userId);
	checkWebSocketState();
	// 连接服务器成功触发该事件
	ws.onopen = function(event) {
		var date = new Date();
		var hours = date.getHours();
		var minutes = date.getMinutes();
		var seconds = date.getSeconds();
		var time = hours + ':' + minutes + ':' + seconds;
		console.log('测试  =====    客户端请求ws连接成功的时间 ：  ' + time);
		console.log('测试  -----    ws当前状态 ' + ws.readyState);
		var openEvent = event;
		console.log(openEvent);
		if (window.webimStateChange) {
			console.log("console    1");
			webimStateChange("ok"); // readyState
		}
		wssae = 'yes';
		setInterval(function() {
			if (wssae == 'yes') {
				heartbeat(userId); // 发送心跳包
			} else {
				return;
			}
		}, 60000);
	}
	// 连接关闭触发该事件
	ws.onclose = function(event) {
		// 将服务器中的用户管理器里删除用户
		var date = new Date();
		var hours = date.getHours();
		var minutes = date.getMinutes();
		var seconds = date.getSeconds();
		var time = hours + ':' + minutes + ':' + seconds;
		console.log('测试  -----    客户端中断了ws连接 时间 ： ' + time);
		console.log(event.code);
		if (window.navigator.onLine == true) {
			console.log('测试  -----    客户端中断了ws连接 ，网络正常');
		} else {
			console.log('测试  -----    客户端中断了ws连接 ，网络不正常');
		}
		ws.close();
		wssae = 'no';
		// fabao.yi
		if (window.webimStateChange) {
			console.log("console    2");
			webimStateChange("no"); // readyState
		}
	}
	// 客户端发生错误触发该事件
	ws.onerror = function(event) {
		var date = new Date();
		var hours = date.getHours();
		var minutes = date.getMinutes();
		var seconds = date.getSeconds();
		var time = hours + ':' + minutes + ':' + seconds;
		console
				.log('测试  -----    客户端出现了错误，执行onerror事件 此时客户端是否中断了连接？？ 查看心跳及close打印信息 发生时间 ： '
						+ time);
		var closeEvent = event;
		console.log(event.code);
	}
	// 客户端接受到消息触发该事件
	ws.onmessage = function(event) {
		console.log("消息 onmessage ：" + event.data);
		var json = JSON.parse(event.data);
		var status = json.status;
		if (status == '2') {
			var topic_id = json.topicId;
			var msg_id = json.messageId;
			for (var s = 0; s < msgArray.length; s++) {
				if (msgArray[s].indexOf(topic_id) != -1
						&& msgArray[s].indexOf(msg_id) != -1) {
					msgArray.splice(s, 1); // 移除该条消息
				}
			}
		} else if (status == "1") {
			ws.send(event.data.replace("\"status\":\"1\"", "\"status\":\"2\""));
			// 聊天信息
			/*
			 * var msg = json.msg; alert("收到服务器发来的消息 : "+msg);
			 */
			if (window.webimHandle) {
				console.log("console    3");
				webimHandle(json); // 消息处理 fabao.yi
			}
			// 广播消息在此接收
		} else if (status == "3") {
			if (window.receiveBroadcast) {
				console.log("console     4");
				receiveBroadcast(json); // 接收广播消息 fabao.yi
			}
		} else if (status == "4") {
			// 好友邀请
			console.log("4444444444444");
			console.log("topicId : "+json.topicId);
			console.log("topicName : "+json.topicName);
			console.log("time : "+json.time);
			console.log("toUserId : "+json.toUserId);
			console.log("to_user_name : "+json.to_user_name);
			console.log("fromUserId : "+json.fromUserId);
			console.log("from_user_name : "+json.from_user_name);
			
			topicMsgInform(json.from_user_name, json.topicName, json.time, json.topicId, json.toUserId, json.fromUserId, json.to_user_name);
		} else if (status == "5") {
			// 消息未读数//有消息就是{topicId:num,topicId2:num2...},没有消息就是{"status":"none"}
			if (window.unreadMessagesNum) {
				console.log("console       6");
				unreadMessagesNum(json);
			}
			// alert(json.unreadNum);
		}else if(status == "6"){
			console.log("6666666666666");
			var date = new Date();
			var hours = date.getHours();
			var minutes = date.getMinutes();
			var seconds = date.getSeconds();
			var time = hours + ':' + minutes + ':' + seconds;
			topicInformMsgFeedback(time, json.message);
		}else if(status == "7"){
			//推送离线消息到前台
			console.log("有离线消息");
			var offlineMessageJsonArray = json.offlineMessage;
			if(window.offlineMsgHandle)
			{
				offlineMsgHandle(offlineMessageJsonArray);
			}
/*			for (var o = 0; o < offlineMessageJsonArray.length; o++) {
				var offlineMessageTopicId = offlineMessageJsonArray[o].topicId;
				var offlineMessageAccepterId = offlineMessageJsonArray[o].accepterId;
				var offlineMessageUnreadNum = offlineMessageJsonArray[o].unreadNum;
				console.log('topicId : '+offlineMessageTopicId+" - accepterId : "+offlineMessageAccepterId+" - unreadNum : "+offlineMessageUnreadNum);
			}*/
			
		}else if(status=="sys_info"){
			console.log(status);
			if(window.sysMsgHandle)
			{
				window.sysMsgHandle(json.msg);
			}else{
				console.log("空");
			}
		}
	}
}
/**
 * sendMsg('topicId','msgId','user1',['user1'],'测试消息','time')
 * sendMsg('topicId','msgId','user1',['user1','2343'],'测试消息','time')
 * 
 * @param topicId
 *            话题id
 * @param msgId
 *            消息id
 * @param sender
 *            发消息者 id
 * @param accepter
 *            字符串数组,接收者id
 * @param msg
 *            消息内容
 * @param time
 *            2014-12-1 12:00:01
 */

function sendMsg(topic_id, message_id, sender_id, nickname, message,accepter_id) {
	// 发送消息后可以做一个发送中的动画效果,发送成功后,会触发
	// onmessage中的status==2,表明发送消息成功,取消动画效果,如果在10秒钟还没有发送成功,会触发 if条件表达式,显示消息发送失败
	var msg = jsonStr(1, topic_id, message_id, sender_id, nickname,
			encodeURIComponent(message), accepter_id);
	msgArray.unshift(msg); // 将消息添加到数组,监听状态
	console.log("测试  -----    发送聊天消息时 ws的状态如下")
	console.log(ws.readyState);
	ws.send(msg);
	setTimeout(function() {
		for (var i = 0; i < msgArray.length; i++) {
			if (msgArray[i] == msg) {
				// 如果条件成立,则说明在7秒后未收到服务器的消息确认反馈,提示用户消息发送失败
				console.log("测试  -----    发送聊天消息失败");
			}
		}
	}, 5000);
}

function heartbeat(userId) {
	var date = new Date();
	var hours = date.getHours();
	var minutes = date.getMinutes();
	var seconds = date.getSeconds();
	var time = hours + ':' + minutes + ':' + seconds;
	console.log("测试  -----    发送心跳消息时 ws的状态如下");
	console.log(ws.readyState);
	ws.send('{"status" : "-1","userId" : "' + userId
			+ '","msg" : "ping","time" : "' + time + '"}');
}

// 广播该用户进入聊天窗口
function broadcast(user_id, topic_id) {
	console.log("测试  -----    发送广播消息时 ws的状态如下");
	console.log(ws.readyState);
	ws.send('{"status" : "3","userId" : "' + user_id + '","topicId":"'
			+ topic_id + '"}'); // 用户打开聊天框"",""
}

//邀请用户
function inviteUser(toUserId, topicId, fromUserId) {
	console.log(toUserId);
	// inviteIds is jsonArray
	console.log("测试  -----    发送邀请好友消息时 ws的状态如下");
	console.log(ws.readyState);
	ws.send('{"status" : "4","toUserId" : "' + toUserId + '","fromUserId" : "' + fromUserId + '","topicId" : "'+ topicId + '"}');
	console.log("发送ws话题邀请请求");
}

//邀请用户消息反馈
function inviteUserMsgFeedback(userId,message) {
	// inviteIds is jsonArray
	console.log("测试  -----    发送邀请好友消息反馈时 ws的状态如下");
	console.log("测试  -----    "+userId);
	console.log("测试  -----    "+message);
	console.log(ws.readyState);
	ws.send('{"status" : "6","message" : "' + message + '","userId" : "' + userId + '"}');
}

// 未读消息
function getUnreadMessageNum(accepter_id) {
	console.log("测试  -----    获取未读消息时 ws的状态如下");
	console.log(ws.readyState);
	ws.send('{"status" : "5","accepterId" : "' + accepter_id + '"}');
}

function jsonStr(status, topic_id, message_id, sender_id, nickname, message,
		accepter_id) {
	var jsonString = '{"status":"' + status + '","topicId":"' + topic_id
			+ '","messageId":"' + message_id + '","senderId":"' + sender_id
			+ '","nickname":" ' + nickname + '","message":"' + message
			+ '","accepterIds": [';
	for (var a = 0; a < accepter_id.length; a++) {
		if (a == accepter_id.length - 1) {
			jsonString += '"' + accepter_id[a] + '"]}';
		} else {
			jsonString += '"' + accepter_id[a] + '",';
		}
	}
	return jsonString;
}

function checkWebSocketState() {
	/*
	 * setTimeout(function() { if(wssae != "yes"){ // WebSocket创建失败 var netState =
	 * plus.networkinfo.getCurrentType(); switch (netState) { case
	 * plus.networkinfo.CONNECTION_WIFI: alert('当前网络:wifi,请联系开发人员.'); break;
	 * case plus.networkinfo.CONNECTION_CELL2G: alert('当前网络:2G,请联系开发人员.');
	 * break; case plus.networkinfo.CONNECTION_CELL3G:
	 * alert('当前网络:3G,请联系开发人员.'); break; case
	 * plus.networkinfo.CONNECTION_CELL4G: alert('当前网络:4G,请联系开发人员.'); break;
	 * default: alert('请检查网络是否连接'); break; } } }, 10000);
	 */
}