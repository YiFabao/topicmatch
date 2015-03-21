package so.xunta.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

import so.xunta.topic.entity.SystemMessageNotification;
import so.xunta.topic.entity.TopicInviteNotification;
import so.xunta.topic.model.impl.NotificationManagerImpl;
import so.xunta.utils.Time;

public class WSEvent extends MessageInbound{
	private int userId = 0;
	
	public WSEvent(int user_id){
		this.userId = user_id;
	}
	@Override
	protected void onOpen(WsOutbound outbound) {
		super.onOpen(outbound);
		System.out.println("服务端LOG : 服务端 接受到 :"+userId+"的请求连接ws, 请求成功: 状态为  "+ outbound.toString());
		System.out.println("服务端LOG : userId的连接创建时间 : "+Time.getDateAndTime(new Date()));
		WSSessionConnectControl.addUserSessionConnect(userId, outbound);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", "0");
		jsonObject.put("message", "来自服务端的ws请求成功的回执");
		WSMessageControl.messagePuth(userId, CharBuffer.wrap(jsonObject.toString()));
		
		//查看是否有话题邀请通知消息，如果有就推送给客户端
		NotificationManagerImpl notificationManagerImpl = new NotificationManagerImpl();
		List<TopicInviteNotification> topicInviteNotificationList = notificationManagerImpl.findUserIdByTopicInviteNotification(userId+"");
		if(topicInviteNotificationList.size() > 0){
			for (TopicInviteNotification notification : topicInviteNotificationList) {
				JSONObject json = new JSONObject();
				json.put("status", notification.getStatus());
				json.put("fromUserId", notification.getFromUserId());
				json.put("from_user_name", notification.getFromUserName());
				json.put("topicName", notification.getTopicName());
				json.put("time", notification.getTime());
				json.put("topicId", notification.getTopicId());
				json.put("toUserId", notification.getToUserId());
				json.put("to_user_name", notification.getToUserName());
				System.out.println(notification.getToUserId()+"  上线,获取通知消息");
				try {
					outbound.writeTextMessage(CharBuffer.wrap(json.toString()));
					System.out.println("推送离线通知消息");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//查看是否有系统通知消息，如果有就推送给客户端
		List<SystemMessageNotification> systemMessageNotification = notificationManagerImpl.findUserIdBySystemNotification(userId+"");
		if(systemMessageNotification.size() > 0){
			for (SystemMessageNotification notification : systemMessageNotification) {
				JSONObject json = new JSONObject();
				json.put("status", notification.getStatus());
				json.put("userId", notification.getUserId());
				json.put("message", notification.getMessage());
				try {
					outbound.writeTextMessage(CharBuffer.wrap(json.toString()));
					System.out.println("推送离线通知消息");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			notificationManagerImpl.deleteSystemMessageNotification(userId+"");
		}
	}
	@Override
	protected void onTextMessage(CharBuffer message) throws IOException {
		WSMessageControl.messagePuth(userId, message);
	}
	@Override
	protected void onClose(int status) {
		super.onClose(status);
		System.out.println("服务端LOG : 服务端触发Close事件,该用户:"+userId+"中断了ws连接: 状态为  "+ status);
		System.out.println("服务端LOG : userId的连接中断时间 : "+Time.getDateAndTime(new Date()));
		WSSessionConnectControl.deleteUserSessionConnect(userId);
		System.out.println(userId +" 中断连接并删除了用户管理列表中的连接");
	}
	@Override
	protected void onBinaryMessage(ByteBuffer arg0) throws IOException {
		
	}
}