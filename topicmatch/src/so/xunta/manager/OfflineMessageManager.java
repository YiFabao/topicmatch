package so.xunta.manager;

import java.util.List;

import so.xunta.websocket.entity.OfflineMessage;

public interface OfflineMessageManager {
	//根据接收人ID获取未读消息数
	public List<OfflineMessage> getOfflineunread(Long accepterId);
	//删除指定ID的离线消息
	public void deleteOfflineunread(Long accepterId);
}
