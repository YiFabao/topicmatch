package so.xunta.test;

import java.util.ArrayList;
import java.util.List;

import so.xunta.topic.entity.Topic;
import so.xunta.topic.model.TopicManager;
import so.xunta.topic.model.impl.TopicManagerImpl;


public class MyTest {
	public static void main(String[] args) {
		TopicManager topicManager = new TopicManagerImpl();
		List<String> topicnameList = new ArrayList<String>();
		
		topicnameList.add("[户外游记] 【遇见最美的自己】笨鸟也能高飞");
		topicnameList.add("【围观者说】这不是声明或解释");
		
		List<Topic> topicList = topicManager.findTopicsByTopicNameList(topicnameList);
		for(Topic t:topicList)
		{
			System.out.println(t.getTopicId());
		}
	}
}
