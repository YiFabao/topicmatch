package so.xunta.topic.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import so.xunta.entity.User;
import so.xunta.manager.UserManager;
import so.xunta.manager.impl.UserManagerImpl;
import so.xunta.topic.entity.MatchedTopic;
import so.xunta.topic.entity.MessageAlert;
import so.xunta.topic.entity.RecommendedPeople;
import so.xunta.topic.entity.RecommendedTopicPublisher;
import so.xunta.topic.entity.SysMessage;
import so.xunta.topic.entity.Topic;
import so.xunta.topic.entity.TopicGroup;
import so.xunta.topic.entity.TopicHistory;
import so.xunta.topic.entity.TopicRequestMsg;
import so.xunta.topic.entity.TopicRequestMsgPlusTopicDetail;
import so.xunta.topic.model.MsgManager;
import so.xunta.topic.model.TopicManager;
import so.xunta.topic.model.TopicModel;
import so.xunta.topic.model.impl.AddTopicIndexThread;
import so.xunta.topic.model.impl.MsgManagerImpl;
import so.xunta.topic.model.impl.SaveTopicThread;
import so.xunta.topic.model.impl.TopicManagerImpl;
import so.xunta.topic.model.impl.TopicModelImpl;
import so.xunta.topic.utils.SecurityUtil;
import so.xunta.utils.DateTimeUtils;

/**
 * Servlet implementation class TopicService
 */
@WebServlet("/TopicService")
public class TopicService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TopicManager topicManager = new TopicManagerImpl();
	private MsgManager msgManager = new MsgManagerImpl();
	private TopicModel topicModel = new TopicModelImpl();
    public TopicService() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		//System.out.println("cmd====================================================>:"+cmd);
		if(cmd==null)return;
		switch(cmd){
		case "fqht":
			//发起话题
			fqht(request,response);
			break;
		case "htss":
			htss(request,response);
			break;
		case "joinTopic":
			joinTopic(request,response);
			break;
		case "invite":
			invite(request,response);
			break;
		case "receiveInvite":
			receiveIntive(request,response);
			break;
		case "refuseInvite":
			refuseInvite(request,response);
			break;
		case "clearNavBarMsgNum":
			clearNavBarMsgNum(request,response);
			break;
		case "msgalert" :
			//显示我的消息
			showMyMessage(request,response);
			break;
		case "initTopicRequestMsg":
			initTopicRequestMsg(request,response);//用户登录时初始化话题邀请信息
			break;
		case "initSysMsg":
			initSysMsg(request,response);//用户登录时初始化系统消息
			break;
		case "addSysMsg":
			addSysMsg(request,response);
			break;
		case "notAgreeToJoinTopic":
			notAgreeToJoinTopic(request,response);
			break;
		case "getTopicByTopicId":
			getTopicByTopicId(request,response);
			break;
		case "getTopicListByTopicIdArray":
			getTopicListByTopicIdArray(request,response);
			break;
		case "searchnicknameByUserId":
			searchnicknameByUserId(request,response);
			break;
		case "searchUnreadMsgNum":
			searchUnreadMsgNum(request,response);
			break;
		case "recommendedPeople":
			method_recommendedPeople(request,response);
			break;
		case "getRecommendPageData":
			method_getRecommendPageData(request,response);
			break;
		case "exit":
			exit(request,response);
			break;
		}
	}
	
	//返回真实的数据
	private void method_getRecommendPageData(HttpServletRequest request, HttpServletResponse response){
		String data = request.getParameter("data");
		List<String> topicIdList = new ArrayList<String>();
		 JSONObject json=JSONObject.fromObject(data);
		 Iterator it = json.keys();
		 while(it.hasNext()){
			String userId =  (String) it.next();
			topicIdList.add(json.getString(userId));
		 }
		 System.out.println("获取的topicIdList=========>:");
		 for(String s:topicIdList){
			 System.out.println(s);
		 }
		 
		 
		 List<RecommendedTopicPublisher> rtpl = topicModel.getRecommendedTopicPUblisher(topicIdList);
	
		 
		 if(rtpl==null){
			 try {
				response.getWriter().write("null");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 return;
		 }
		 System.out.println("获取到的rtpl：");
		 for(RecommendedTopicPublisher r:rtpl)
		 {
			 System.out.println("userId:"+r.userId+"  topicId:"+r.topicId);
		 }
		 
		 JSONArray jsonarray = topicModel.getJSONArrayFromRecommendedTopicPublisherList(rtpl);

		try {
			response.setContentType("json/text");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(jsonarray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//返回[{userId,topicId},{userId2,topicId2},{}....,{userIdn,topicIdn}]
	private void method_recommendedPeople(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		List<RecommendedTopicPublisher> recommendedTopicPUblisherList = topicModel.getRecommendedTopicPUblisher(userId);
		if(recommendedTopicPUblisherList==null){
			try {
				response.setCharacterEncoding("utf-8");
				response.getWriter().write("没有匹配的话题");
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JSONArray jsonArray = new JSONArray();
		for(RecommendedTopicPublisher rtp:recommendedTopicPUblisherList){
			JSONObject obj=new JSONObject();
			obj.put(rtp.getUserId(),rtp.getTopicId());
			jsonArray.add(obj);
		}
		
		//test
/*		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<109;i++){
			JSONObject obj=new JSONObject();
			obj.put(i,"akjasdlkfjkskalfaj;skfj"+i);
			jsonArray.add(obj);
		}*/
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().write(jsonArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void clearNavBarMsgNum(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		msgManager.updateSysMsgToHandledByUserId(userId);
		msgManager.updateTopicRequestMsgReaded(userId);
		
	}

	private void initTopicRequestMsg(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		System.out.println("userId:"+userId);
		List<TopicRequestMsgPlusTopicDetail>  topicRequestMsgPlusTopicDetailList=msgManager.findTopicRequestMsgByUserId(userId);
		request.setAttribute("topicRequestMsgPlusTopicDetailList", topicRequestMsgPlusTopicDetailList);
		System.out.println(topicRequestMsgPlusTopicDetailList.size());
		System.out.println("初始化话题邀请信息");
		try {
			request.getRequestDispatcher("/jsp/topic/include/topicRequest.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		};
	}

	private void initSysMsg(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		List<SysMessage> sysMsgList = msgManager.findSysMsgByUserId(userId);
		request.setAttribute("sysMsgList", sysMsgList);
		System.out.println("初始化系统信息");
		try {
			request.getRequestDispatcher("/jsp/topic/include/system_msg.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addSysMsg(HttpServletRequest request, HttpServletResponse response) {
		String fromUserId=request.getParameter("fromUserId");
		String fromUserName = request.getParameter("fromUserName");
		String toUserId= request.getParameter("toUserId");
		String toUserName = request.getParameter("toUserName");
		String sysmsg = request.getParameter("sysmsg");
		SysMessage sysMsg = new SysMessage(fromUserId, fromUserName, toUserId, toUserName, sysmsg,DateTimeUtils.getCurrentTimeStr(),0);
		msgManager.addMsg(sysMsg);
		System.out.println("保存系统信息到数据库成功");
		try {
			response.getWriter().write("ok");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void refuseInvite(HttpServletRequest request, HttpServletResponse response) {
		String toUserId = request.getParameter("toUserId");
		String topicId = request.getParameter("topicId");
		System.out.println("拒绝邀请"+"toUserId:"+toUserId+"  topicId:"+topicId);
		msgManager.updateTopicRequestMsgHandledState(toUserId, topicId,"0");
		try {
			response.getWriter().write("ok");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void receiveIntive(HttpServletRequest request, HttpServletResponse response) {
/*		cmd:"receiveInvite",
		toUserId:currentUserId,
		topicId:topicId*/
		String toUserId = request.getParameter("toUserId");
		String topicId = request.getParameter("topicId");
		System.out.println("接受邀请"+"toUserId:"+toUserId+"  topicId:"+topicId);
		msgManager.updateTopicRequestMsgHandledState(toUserId, topicId,"1");//更改邀请信息的状态
		
		//参与话题
		//查询出参与人
		UserManager userManager = new UserManagerImpl();
		User joinUser = userManager.findUserById(Integer.parseInt(toUserId));
		
		//保存参与话题历史,要检查在参与话题历史是否存在
		String currentTime = DateTimeUtils.getCurrentTimeStr();
		if(!topicManager.checkTopicIsExitInHistory(toUserId, topicId))
		{
			TopicHistory topicHistory = new TopicHistory(toUserId, topicId,currentTime ,'j');
			topicManager.addTopicHistory(topicHistory);
		}
		//保存话题组,检查是否已经存在到话题组
		if(!topicManager.checkIsTopicMember(toUserId, topicId))
		{
			TopicGroup topicMember =new TopicGroup(topicId,toUserId,joinUser.getXunta_username(),currentTime);
			topicManager.saveTopicGroup(topicMember);
		}
		try {
			response.getWriter().write("ok");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void searchUnreadMsgNum(HttpServletRequest request, HttpServletResponse response) {
		String authorId=request.getParameter("authorId");
		long num1=msgManager.findUnreadSysMsgNum(authorId);
		long num2=msgManager.findUnreadTopicRequestMsgNum(authorId);
		try {
			response.setContentType("text/json");
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("num",num1+num2);
			response.getWriter().write(jsonObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void searchnicknameByUserId(HttpServletRequest request, HttpServletResponse response) {
		//获取请求参数 userId
		String userId = request.getParameter("userId");
		String nickname = topicManager.searchNicknameByUserId(Integer.parseInt(userId));
		JSONObject jsonObj=new JSONObject();
		jsonObj.put("nickname",nickname);
		System.out.println(nickname);
		try {
			response.setContentType("text/json");
			response.getOutputStream().write(jsonObj.toString().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unused")
	private void getTopicListByTopicIdArray(HttpServletRequest request, HttpServletResponse response) {
		String topicIdArray = request.getParameter("topicIdArray");
		if(topicIdArray==null||"".equals(topicIdArray.trim())){return;}
		System.out.println(topicIdArray);
		List<String> topicIdList = new ArrayList<String>();
		String[] topicIds =topicIdArray.split(",");
		for(String topicId:topicIds)
		{
			System.out.println(topicId);
			topicIdList.add(topicId);
		}
		List<Topic> topicList = topicManager.getTopicListByTopicIdList(topicIdList);
		System.out.println("数："+topicList.size());
		JSONArray topicArray = new JSONArray();
		for(Topic t:topicList)
		{
			JSONObject topicJSONObj = new JSONObject();
			topicJSONObj.put("topicId",t.topicId);
			topicJSONObj.put("userId",t.userId);
			topicJSONObj.put("userName",t.userName);
			topicJSONObj.put("topicName",t.topicName);
			topicJSONObj.put("topicContent",t.topicContent);
			topicJSONObj.put("logo_url",t.logo_url);
			topicArray.add(topicJSONObj);
			System.out.println(t.topicContent);
		}
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().write(topicArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getTopicByTopicId(HttpServletRequest request, HttpServletResponse response) {
		String topicId = request.getParameter("topicId");
		if(topicId==null||"".equals(topicId))
		{
			return;
		}
		response.setContentType("text/json; charset=UTF-8");
		Topic topic = topicManager.findTopicByTopicId(topicId);
		if(topic ==null)
		{
			try {
				response.getWriter().write("");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
		{
			String topicName = topic.getTopicName();
			String logoUrl = topic.getLogo_url();
			JSONObject datajson=new JSONObject();
			datajson.put("topicName",topicName);
			datajson.put("logoUrl",logoUrl);
			datajson.put("topicId",topicId);
			try {
				response.getWriter().write(datajson.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//用户不同意参与话题
	private void notAgreeToJoinTopic(HttpServletRequest request, HttpServletResponse response) {
		//获取 消息的主键id
		String id_str = request.getParameter("id");
		if(id_str==null||"".equals(id_str))return;
		
		int id = Integer.parseInt(id_str);
		
		//将消息改为已处理
		topicManager.updateMessageAlertToAlreadyHandle(id);
	}

	//别人邀请我时，会显示我的消息
	private void showMyMessage(HttpServletRequest request, HttpServletResponse response) {
		User  user = (User)request.getSession().getAttribute("user");
		if(user==null){
			try {
				request.getRequestDispatcher("/jsp/xunta_user/login.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		long userId = user.id;//获取用户id
		
		List<MessageAlert> messageAlertList=topicManager.searchMyMessage(userId+"");
		topicManager.updateMessageAlertToAlreadyRead(userId+"");
		request.setAttribute("messageAlertList", messageAlertList);
		try {
			request.getRequestDispatcher("/jsp/topic/myMessage.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void invite(HttpServletRequest request, HttpServletResponse response) {
		//获取请求参数
		String fromUserId = request.getParameter("fromUserId");
		String to_userId = request.getParameter("to_userId");
		
		String fromUserName = request.getParameter("fromUserName");
		String to_userName = request.getParameter("to_userName");
		String topicId = request.getParameter("topicId");
		System.out.println("用户发出邀请参与话题请求：");
		System.out.println("fromUserId:"+fromUserId);
		System.out.println("fromUserName:"+fromUserName);
		System.out.println("to_userId:"+to_userId);
		System.out.println("to_userName:"+to_userName);
		System.out.println("topicId:"+topicId);
		
		TopicRequestMsg topicRequestMsg = new TopicRequestMsg(fromUserId, to_userId, fromUserName, to_userName, topicId, DateTimeUtils.getCurrentTimeStr(),"-1","0");
		
		try {
			//topicManager.addMessageAlert(messageAlert);
			msgManager.addTopicRequestMsg(topicRequestMsg);
			response.getWriter().write("ok");
		} catch (IOException e) {
			try {
				response.getWriter().write("failure");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	//退出登录
	private void exit(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("user");
		request.getSession().invalidate();
		System.out.println("退出登录");
		
		try {
			response.getWriter().write("ok");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 	/*System.out.println("获取cookie");
		 	Cookie[] cookies=request.getCookies();
		 	for(Cookie cookie:cookies)
		 	{
		 		System.out.println(cookie.getName()+"  "+cookie.getValue());
		 		cookie.setMaxAge(0);
		 		cookie.setPath("/");
		 		cookie.setValue("");
		 		response.addCookie(cookie);
		 	}

			System.out.println("跳转");
			try {
				response.sendRedirect(request.getContextPath()+"/jsp/xunta_user/login.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		
	}

	private void joinTopic(HttpServletRequest request, HttpServletResponse response) {
		//参与话题
		String userId = request.getParameter("userId");
		String topicId = request.getParameter("topicId");
		//调用用户参与话题的业务处理逻辑模型
		//topicModel.joinTopic(request, response, userId, topicId);
		System.out.println("userId:"+userId);
		System.out.println("topicId:"+topicId);

		
		JSONObject user_p = new JSONObject();
		user_p.put("userId",userId);
		user_p.put("userName", "易发宝");
		user_p.put("imageUrl","/images/URL");
		
		JSONObject topic_json=new JSONObject();
		topic_json.put("topicId",topicId);
		topic_json.put("topicTitle","上海哪里好玩");
		topic_json.put("topicContent","一直不知道上海哪好玩");
		
		JSONArray memberList = new JSONArray();
		JSONObject json1 = new JSONObject();
		json1.put("userId","2");
		json1.put("userName", "张三");
		json1.put("imageUrl","images/delete/user-pic2.jpg");
		JSONObject json2 = new JSONObject();
		json2.put("userId","3");
		json2.put("userName", "李四");
		json2.put("imageUrl","/images/2.jpg");
		memberList.add(json1);
		memberList.add(json2);
		
		JSONObject all = new JSONObject();
		all.put("user_p",user_p);
		all.put("topic",topic_json);
		all.put("memberList",memberList);
		System.out.println(all.toString());
		try {
			response.setContentType("text/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(all.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void htss(HttpServletRequest request, HttpServletResponse response) {
			String searchWord = request.getParameter("search_word");
			searchWord =URLDecoder.decode(searchWord);
			System.out.println("话题搜索");
			System.out.println(searchWord);
			//搜索话题
			List<so.xunta.topic.entity.Topic> searchedtopicList=topicManager.matchMyTopic(searchWord);
			request.setAttribute("searchWord",searchWord);
			request.setAttribute("topicList",searchedtopicList);
			try {
				request.getRequestDispatcher("/jsp/topic/htss.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	private void fqht(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");//用户id
		String userName = request.getParameter("userName");//用户名
		String userLogoUrl = request.getParameter("userLogoUrl");//用户logo
		String topicName = request.getParameter("topicName");//话题标题
		String topicContent = request.getParameter("topicContent");//话题内容
		System.out.println("用户id:"+userId);
		System.out.println("用户名:"+userName);
		System.out.println("用户LogoUrl:"+userLogoUrl);
		System.out.println("话题名称:"+topicName);
		System.out.println("话题内容:"+topicContent);
		
		//话题发起时间 
		String topicCreateTime = DateTimeUtils.getCurrentTimeStr();
		//话题Id 由    [用户id+话题名称+话题内容+话题创建时间]    的字符串拼接字符串生成的md5
		String topicId=SecurityUtil.strToMD5(userId+topicName+topicContent+topicCreateTime);
		//保存用户话题
		Topic topic =new Topic(topicId,userId,userName,topicName,topicContent,userLogoUrl,topicCreateTime,topicCreateTime);
		AddTopicIndexThread addTopicIndexThread=new AddTopicIndexThread(topicManager, topic);
		new Thread(addTopicIndexThread).start();
		SaveTopicThread saveTopicThread = new SaveTopicThread(topicManager, topic);
		new Thread(saveTopicThread).start();//保存话题，话题组，话题历史
		request.setAttribute("myTopic",topic);
		//匹配话题
		List<so.xunta.topic.entity.Topic> matchedtopicList=topicManager.matchMyTopic(topic.topicName,topic.topicContent);
		
		/*//按userId分组
		Map<String,List<Topic>> topicMap = new HashMap<String,List<Topic>>();
		for(Topic t:matchedtopicList)
		{
			if(topicMap.containsKey(t.userId))
			{
				topicMap.get(t.userId).add(t);
				topicMap.put(t.userId,topicMap.get(t.userId));
			}
			else
			{
				List<Topic> list=new ArrayList<Topic>();
				list.add(t);
				topicMap.put(t.userId,list);
			}
		}
		System.out.println("＝＝＝＝>"+matchedtopicList.size());
		System.out.println("topicMap.size:"+topicMap.size());
		List<MatchedTopic> mtlist = new ArrayList<MatchedTopic>();
		Iterator<Entry<String, List<Topic>>> iterator =topicMap.entrySet().iterator();
		while(iterator.hasNext())
		{
			Entry<String,List<Topic>> e = iterator.next();
			MatchedTopic mt = new MatchedTopic();
			mt.setUserId(e.getKey());
			mt.setRelativeTopicList(e.getValue());
			mt.setUserName(e.getValue().get(0).getUserName());
			mtlist.add(mt);
		}
		System.out.println("mtlist====>"+mtlist.size());
		request.setAttribute("matchedTopicList",mtlist);*/
		try {
			request.getRequestDispatcher("/jsp/topic/include/sponsored_topic.jsp").forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
