package so.xunta.topic.model;

import java.util.List;
import java.util.Map;

import so.xunta.entity.User;
import so.xunta.topic.entity.MessageAlert;
import so.xunta.topic.entity.RecommendedPeople;
import so.xunta.topic.entity.RecommendedTopicPublisher;
import so.xunta.topic.entity.Topic;
import so.xunta.topic.entity.TopicGroup;
import so.xunta.topic.entity.TopicHistory;
import so.xunta.topic.entity.TopicHistoryMessage;
import so.xunta.websocket.entity.HistoryMessage;
import weibo4j.org.json.JSONArray;

public interface TopicManager {
	//创建话题索引 
	public void createTopicIndex(Topic topic);
	//获取匹配的话题,从索引里查询
	public List<Topic> matchMyTopic(String mytopic);
	public List<Topic> matchMyTopic(String topicName,String mytopic);
	//与前面一个相同，从索引里查询，不过只返回toplic的id列表,以方便后面直接使用findTopicHistoryByTopicId查询
	public List<String> matchMyTopicIds(String topicName, String topicContent);
	public List<Topic> matchMyTopicByUserId(String userId);
	public List<Topic> matchUserRelativeTopic(String userId,String topicContent);
	public List<Topic> matchUserRelativeTopic(String userId,String topicName,String topicContent);
	
	//话题推荐
	public List<Topic> recommendTopics(String userId);



	//将话题保存到数据库中
	public void saveTopic(Topic topic);
	//保存话题成员
	public void saveTopicGroup(TopicGroup topicMember);
	
	//添加消息
	public void addMessageAlert(MessageAlert messageAlert);
	//查询自己的消息
	public List<MessageAlert> searchMyMessage(String authorId);
	//查询未读消息数
	public long searchNotReadmessageNum(String authorId);
	//将未读消息更改为已读
	public void updateMessageAlertToAlreadyRead(String authorId);
	//将未读消息更改为已处理,pid为主键id
	public void updateMessageAlertToAlreadyHandle(int pid);
	//根据消息的自增id删除
	public void deleteOneMessage(int id);
	
	//添加话题历史，当用户发起话题或参与话题时记录
	public void addTopicHistory(TopicHistory topicHistory);
	//从话题历史表中通过话题id获取所有的TopicHistory
	public List<TopicHistory> findTopicHistoryByTopicId(List<String> topicIdList);
	
	//查询某用户是否是某话题id下的成员
	public boolean checkIsTopicMember(String memberId,String topicId);
	//查询话题topicId 下的联系人列表 [昵称 id]
	public List<TopicGroup> searchTopicMemberList(String topicId);
	//查询topicId对应的话题内容
	public String searchTopicContent(String topicId);
	//查询某人的最近的一条话题的话题Topic
	public Topic searchLatestTopic(String authorId);
	//搜索话题历史，从数据库中查询
	public List<Topic> searchTopicHistory(String authorId);
	//查询某条话题是否存在于作者的话题历史当中
	public boolean checkTopicIsExitInHistory(String authorId,String topicId);
	//通过userId 查询用户的昵称
	public String searchNicknameByUserId(int userId);
	//查询我的话题
	public List<Topic> searchMyTopicHistory(String userId);
	//查询我参与的话题
	public List<Topic> searhMyJoinTopic(String userId);

	
	//根据topicId 查询话题Topic
	public Topic findTopicByTopicId(String topicId);

	//根据topicId 查询出List<topicMemberId>
	public List<String> findMemberIdsByTopicId(String topicId);
	
	//根据List<topicId> 查询 List<Topic>
	public List<Topic> getTopicListByTopicIdList(List<String> topicIdList);
	
	//将话题参与人数加1
	public void addTopicJoinNumByOne(String topicId);

	//根据authorId 和  publish_or_join 获取对应的topicId   fang
	public List<TopicHistory> findAuthorIdAndPublish_or_joinByTopicId(String authorId, String publish_or_join, int titleNum);
	//根据topicId 获取 topicHistoryMessage   fang
	public Map<String,HistoryMessage> findTopicIdByHistoryMessage(List<TopicHistory> list);
	//根据topicId 获取 topic   fang
	public Topic findTopicIdByTopic(String topicId);
	//根据topicId 获取 TopicHistory   fang
	public String findTopicIdByTopicHistory(String topicId);
}
