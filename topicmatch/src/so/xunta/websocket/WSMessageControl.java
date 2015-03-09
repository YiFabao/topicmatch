package so.xunta.websocket;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import so.xunta.utils.Console;
import so.xunta.utils.Time;
import so.xunta.websocket.entity.HistoryMessage;
import so.xunta.websocket.utils.WebSocketUtils;

public class WSMessageControl {
	public static ArrayList<JSONObject> messageMonitorList = new ArrayList<JSONObject>();
	public static void messagePuth(int user_id , CharBuffer message){
		System.out.println("CLIENT send : " + message.toString());
		Date currentDate = new Date();
		JSONObject messageJsonObject = JSONObject.fromObject(message.toString());
		switch(Integer.parseInt(messageJsonObject.getString("status"))){
			case 0:
				//客户端连接服务端成功后告知客户端连接成功
				puth(user_id, message);
				break;
			case -1:
				//客户端每分钟发送一条指令,用来保持会话连接的有效期
				int Id = Integer.parseInt(messageJsonObject.getString("userId"));
				JSONObject msgJsonObj1 = JSONObject.fromObject(message.toString());
				msgJsonObj1.put("status", "-1");
				msgJsonObj1.put("msg", "pong");
				puth(Id, CharBuffer.wrap(msgJsonObj1.toString()));
				break;
			case 1:
				String topicId = messageJsonObject.getString("topicId");
				int senderId = Integer.parseInt(messageJsonObject.getString("senderId"));
				String nickname = messageJsonObject.getString("nickname");
				Long messageId = Long.parseLong(messageJsonObject.getString("messageId"));
				String messages = messageJsonObject.getString("message");
				String dateTime = Time.getDateAndTime(currentDate);
				int date = Time.getDate(currentDate);
				int time = Time.getTime(currentDate);
				JSONArray accepterIdArray = messageJsonObject.getJSONArray("accepterIds");
				//将消息存储到历史消息库  如果插入重复就break,还没捕捉插入冲突异常
				WebSocketUtils.addHistoryMessage(new HistoryMessage(1, topicId, messageId, senderId, accepterIdArray.toString(), messages, dateTime, nickname, date, time));
				System.out.println("存储历史消息成功");
				//响应客户端,告知消息已收到,推给客户端时修改消息状态
				puth(senderId, CharBuffer.wrap(message.toString().replaceAll("\"status\":\"1\"", "\"status\":\"2\"")));
				System.out.println("推送给客户端消息成功 -- 告知客户端消息发送成功");
				//遍历接受者,逐一发送消息
				for (int i = 0; i < accepterIdArray.size(); i++) {
					int accepterId = Integer.parseInt(accepterIdArray.get(i).toString());
					System.out.println("遍历发送者  " + accepterId);
					if(!(WSSessionConnectControl.getWindowConnect(accepterId) == null)){
						//对 在线的用户进行消息推送
						JSONObject msgJsonObj = JSONObject.fromObject(message.toString());
						msgJsonObj.put("dateTime", dateTime);
						msgJsonObj.put("date", date+"");
						msgJsonObj.put("time", time+"");
						//推送消息
						puth(accepterId, CharBuffer.wrap(msgJsonObj.toString()));
						System.out.println("推送给多个客户端发送消息 : "+ msgJsonObj.toString());
						//监听消息发送状态
						messageMonitorList.add(messageJsonObject);
						//启动监听程序,话题ID和消息ID必须保证唯一
						new messageMonitorThread(topicId, senderId, nickname, messageId, messages, dateTime, accepterId ,date, time).start();
					}else{
						//用户不在线
						WebSocketUtils.setUnreadMessageNum((long) accepterId, topicId);
						System.out.println("用户不在线,消息存储离线");
					}
				}
				break;
			case 2:
				String topicId2 = messageJsonObject.getString("topicId");
				String msgId2 = messageJsonObject.getString("messageId");
				for(int index = 0; index < messageMonitorList.size(); index++){
					String messageStr = messageMonitorList.get(index).toString();
					if(messageStr.contains(topicId2) & messageStr.contains(msgId2+"")){
						//客户端响应状态,服务器发送消息成功,从监听消息中删除
						Console.print("客户端推送消息成功    话题ID : "+topicId2+",  消息ID : "+msgId2);
						messageMonitorList.remove(index);
					}
				}
				break;
			case 3:
				//用户进入某话题时进行广播,向所有用户推送事件
				System.out.println("消息发到3");
				String userId3 = messageJsonObject.get("userId").toString();
				String topicId3 = messageJsonObject.get("topicId").toString();
				List<String> userIdArrayList = WebSocketUtils.getTopicUserIdList(topicId3);
				for (String string : userIdArrayList) {
					Integer integer = Integer.parseInt(string);
					if(!(WSSessionConnectControl.getWindowConnect(integer) == null)){
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("status", "3");
						jsonObject.put("userId", userId3);
						jsonObject.put("topicId", topicId3);
						System.out.println("用户进入话题后进行广播通知，当前通知用户ID ：  "+userId3   +"     广播的话题ID是  ：  "+ topicId3);
						puth(integer, CharBuffer.wrap(jsonObject.toString()));
					}
				}
				break;
			case 4:
				//好友邀请
				System.out.println("好友邀请  :  "+messageJsonObject.getJSONArray("inviteIds"));
				JSONArray inviteIds = messageJsonObject.getJSONArray("inviteIds");
				String inviteMsg = messageJsonObject.get("inviteMsg").toString();
				for (int i = 0; i < inviteIds.size(); i++) {
					int userId6 = Integer.parseInt(inviteIds.get(i).toString());
					if(!(WSSessionConnectControl.getWindowConnect(userId6) == null)){
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("status", "4");
						jsonObject.put("inviteMsg", inviteMsg);
						puth(userId6 , CharBuffer.wrap(jsonObject.toString()));
					}
				}
				break;
			case 5:
				System.out.println("5  " + messageJsonObject.toString());
				int accepterId5 = Integer.parseInt(messageJsonObject.get("accepterId").toString());
				List<String> topicList = WebSocketUtils.getTopicId(accepterId5);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("status", "5");
				if(topicList.size() == 0){
					//用户没参与过话题
					jsonObject.put("status", "none");
				}else{
					//用户参与过话题
					for (String topicId5 : topicList) {
						String unreadNum = WebSocketUtils.getUnreadMessageNum(topicId5, accepterId5);
						WebSocketUtils.deleteUnreadMessage(topicId5, accepterId5);
						jsonObject.put(topicId5, unreadNum);
					}
				}
				puth(accepterId5 , CharBuffer.wrap(jsonObject.toString()));
				System.out.println("执行第5分支结束");
				break;
		}
	}
	/*
	 * 推送消息方法,
	 * accepter_id : 接收人ID
	 * message : 消息实体
	 * */
	private static void puth(int accepter_id,CharBuffer message){
		try {
			System.out.println("服务器LOG ：   执行puth方法前");
			WSSessionConnectControl.getWindowConnect(accepter_id).writeTextMessage(message);
			System.out.println("服务器LOG ：   执行puth方法后");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
class messageMonitorThread extends Thread{
	private String TOPIC_ID;
	private int SENDER_ID;
	private String NICKNAME;
	private Long MESSAGE_ID;
	private String MESSAGE;
	private String DATE_AND_TIME;
	private int ACCEPTER_ID;
	private int DATE;
	private int TIME;
	public messageMonitorThread(String topic_id, int sender_id, String nickname, Long message_id, String message, String date_and_time, int accepter_id, int date, int time){ 
		this.TOPIC_ID = topic_id;
		this.SENDER_ID = sender_id;
		this.NICKNAME = nickname;
		this.MESSAGE_ID = message_id;
		this.MESSAGE = message;
		this.DATE_AND_TIME = date_and_time;
		this.ACCEPTER_ID = accepter_id;
		this.DATE = date;
		this.TIME = time;
	}
	@Override
	public void run() {
		try {
			sleep(10000);
			for(JSONObject messageJson:WSMessageControl.messageMonitorList){
				String messageStr = messageJson.toString();
				if(messageStr.contains(TOPIC_ID) & messageStr.contains(MESSAGE_ID+"")){
					//消息发送失败,判断原因,在发一次?
					Console.print("客户端推送消息失败    话题ID : "+TOPIC_ID+",  消息ID : "+MESSAGE_ID);
					WebSocketUtils.setUnreadMessageNum((long) ACCEPTER_ID, TOPIC_ID);
					break;
				}
	        }
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}