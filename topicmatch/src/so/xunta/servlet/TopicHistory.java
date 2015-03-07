package so.xunta.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.hibernate.Query;
import org.hibernate.Session;

import so.xunta.topic.entity.TopicHistoryMessage;
import so.xunta.utils.HibernateUtils;

public class TopicHistory extends HttpServlet {

	private static final long serialVersionUID = 7736952712622215708L;

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println(request.getRemoteAddr()+" |"+request.getRemoteHost()+" |"+request.getRequestURI());
		String biginIndex = request.getParameter("biginIndex");
		String endIndex = request.getParameter("endIndex");
		String topic_Id = request.getParameter("topicId");
		if(!(topic_Id == null || topic_Id.equals("null"))){
			System.out.println("服务器接受到客户端请求获取话题历史消息， 话题ID ： "+topic_Id);
			response.setContentType("text/json;charset=utf-8");
			response.addHeader("Access-Control-Allow-Origin","http://localhost :8080");
			response.addHeader("Access-Control-Allow-Origin","http://121.40.61.219 :8080");
			response.setContentType("text/json");
			JSONArray jsonArray = new JSONArray();
			
			Session session = HibernateUtils.openSession();
			String hql = "from TopicHistoryMessage as hisMsg where hisMsg.topicId=? order by hisMsg.dateAndTime asc limit "+biginIndex+","+endIndex+"";
			System.out.println("获取话题历史消息的HQL语句 ：  "+hql);
			Query query = session.createQuery(hql).setString(0, topic_Id);
			List<TopicHistoryMessage> topicHistoryMessageList = query.list();
			for(TopicHistoryMessage thm:topicHistoryMessageList){
				String status = thm.getStatus()+"";
				String topicId = thm.getTopicId();
				String messageId = thm.getMessageId()+"";
				String senderId = thm.getSenderId()+"";
				String content = thm.getContent();
				String dateAndTime = thm.getDateAndTime();
				String nickname = thm.getNickname();
				String date = thm.getDate()+"";
				String time = thm.getTime()+"";
				jsonArray.add("{\"status\":\""+status+"\",\"topicId\":\""+topicId+"\",\"msgId\":\""+messageId+"\",\"sender\":\""+senderId+"\",\"message\":\""+content+"\",\"dateTime\":\""+dateAndTime+"\",\"date\":\""+date+"\",\"time\":\""+time+"\",\"nickname\":\""+nickname+"\"}");
			}
			response.getWriter().write(jsonArray.toString());
			session.close();
		}else{
			response.setContentType("text/json;charset=utf-8");
			response.addHeader("Access-Control-Allow-Origin","http://localhost :8080");
			response.addHeader("Access-Control-Allow-Origin","http://121.40.61.219 :8080");
			response.setContentType("text/json");
			response.getWriter().write("");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doGet(request, response);
	}
}