package so.xunta.topic.model;

import java.util.List;

import so.xunta.topic.entity.SystemMessageNotification;
import so.xunta.topic.entity.TopicInviteNotification;


public interface NotificationManager {
	//存储离线话题邀请通知消息
	public void addTopicNotificationMsg(TopicInviteNotification notification);
	//存储系统通知消息
	public void addSystemNotificationMsg(SystemMessageNotification notification);
	//获取离线话题邀请通知消息
	public List<TopicInviteNotification> findUserIdByTopicInviteNotification(String userId);
	//获取系统通知消息
	public List<SystemMessageNotification> findUserIdBySystemNotification(String userId);
	//删除离线通知消息
	public void deleteTopicInviteNotification(String topicId,String toUserId);
	//删除离线通知消息
	public void deleteSystemMessageNotification(String userId);
}
