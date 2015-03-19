package so.xunta.topic.model;

import java.util.List;
import so.xunta.topic.entity.Notification;


public interface NotificationManager {
	//存储离线通知消息
	public void addNotificationMsg(Notification notification);
	//获取离线通知消息
	public List<Notification> findUserIdByNotification(String userId);
	//删除离线通知消息
	public void deleteNotification(String userId);
}
