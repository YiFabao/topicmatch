package so.xunta.topic.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import so.xunta.entity.Tag;
import so.xunta.entity.User;
import so.xunta.manager.TagsManager;
import so.xunta.manager.UserManager;
import so.xunta.manager.impl.TagsManagerImpl;
import so.xunta.manager.impl.UserManagerImpl;
import so.xunta.topic.entity.MatchTopicPeople;
import so.xunta.topic.entity.MessageAlert;
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
import so.xunta.topic.utils.ImportDataUtils;
import so.xunta.topic.utils.SecurityUtil;
import so.xunta.utils.DateTimeUtils;
import so.xunta.utils.Time;
import so.xunta.websocket.entity.HistoryMessage;
import weibo4j.org.json.JSONException;

/**
 * Servlet implementation class TopicService
 */
@WebServlet("/TopicService")
public class TopicService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TopicManager topicManager = new TopicManagerImpl();
	private MsgManager msgManager = new MsgManagerImpl();
	private TopicModel topicModel = new TopicModelImpl();
	so.xunta.utils.LogUtils logutil = new so.xunta.utils.LogUtils();
	//用户发起话题后查询相关用户
	private UserManager userManager = new UserManagerImpl();
	private TagsManager tagsManager = new TagsManagerImpl();

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
			logutil.traceLog(request,"发起话题");
			fqht(request,response);
			break;
		case "htss":
			//在方法内部已记录日志
			htss(request,response);
			break;
		case "joinTopic":
			//在方法内部记录日志
			joinTopic(request,response);
			break;
		case "invite":
			logutil.traceLog(request,"话题邀请");
			invite(request,response);
			break;
		case "receiveInvite":
			logutil.traceLog(request, "接受邀请");
			receiveIntive(request,response);
			break;
		case "refuseInvite":
			logutil.traceLog(request, "拒绝邀请");
			refuseInvite(request,response);
			break;
		case "clearNavBarMsgNum":
			clearNavBarMsgNum(request,response);
			break;
		case "msgalert" :
			//显示我的消息
			logutil.traceLog(request, "查看系统消息");
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
			logutil.traceLog(request, "话题 推荐");
			method_getRecommendPageData(request,response);
			break;
		case "getTopicAndTopicMembersByTopicId"://传topicId,获取Topic,List<User>用于创建对话框
			getTopicAndTopicMembersByTopicId(request,response);
			break;
		case "exit":
			logutil.traceLog(request, "退出登录");
			exit(request,response);
			break;
		case "topicMemory"://fang
			logutil.traceLog(request, "话题记忆");
			getTopicMemory(request,response);
			break;
		case "findUserByUserId":
			findUserByUserId(request,response);
			break;
		case "importData":
			ImportDataUtils.getInstance().importData(request,response);
			break;
		}
	}


	private void findUserByUserId(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		if(userId==null){
			try {
				response.getWriter().write("");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		
		User user = userManager.findUserById(Integer.parseInt(userId));
		if(user==null){
			//System.out.println("查询用户为空");
			return;
		}
		JSONObject json = new JSONObject();
		json.put("userId",user.id);
		json.put("userName", user.nickname);
		json.put("imageUrl",user.imageUrl);
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void getTopicAndTopicMembersByTopicId(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//参与话题
		String topicId = request.getParameter("topicId");
		//查询出　Topic
		Topic topic = topicManager.findTopicByTopicId(topicId);
		
		User p_user = userManager.findUserById(Integer.parseInt(topic.userId));
		//System.out.println("p_user:"+p_user.nickname);
		
		//查询出List<User>
		List<String> userIdList = topicManager.findMemberIdsByTopicId(topicId);
		if(!userIdList.contains("8")){
			userIdList.add("8");
		}
		if(!userIdList.contains("13")){
			userIdList.add("13");
		}
		
		List<Long> userIdList_l = new ArrayList<Long>();
		for(String s:userIdList)
		{
			userIdList_l.add(Long.parseLong(s));
		}
	
		List<User> userList = userManager.findUserListByUserIdList(userIdList_l);
		
		//封装成json
		JSONObject topic_json=new JSONObject();
		if(topic!=null){
			topic_json.put("topicId",topicId);
			topic_json.put("topicTitle",topic.topicName);
			topic_json.put("topicContent",topic.topicContent);
		}else{
			System.err.print("topicId==>Topic 为空");
		}
		

		JSONObject user_p = new JSONObject();
		user_p.put("userId",p_user.id);
		user_p.put("userName",p_user.nickname);
		user_p.put("imageUrl",p_user.imageUrl);
	
		
		JSONArray memberList = new JSONArray();
		if(userList !=null){
			for(User u:userList){
				JSONObject json = new JSONObject();
				json.put("userId",u.id);
				json.put("userName",u.nickname);
				json.put("imageUrl",u.imageUrl);
				memberList.add(json.toString());
			}
		}
		JSONObject all = new JSONObject();
		all.put("topic",topic_json);
		all.put("memberList",memberList);
		all.put("user_p",user_p);

		try {
			response.setContentType("text/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(all.toString());
		} catch (IOException e) {
				e.printStackTrace();
			}
	}

	//返回真实的数据
	private void method_getRecommendPageData(HttpServletRequest request, HttpServletResponse response){
		String data = request.getParameter("data");
		List<String> topicIdList = new ArrayList<String>();
		 JSONObject json=JSONObject.fromObject(data);
		 Iterator it = json.keys();
		 while(it.hasNext()){
			String topicId =  (String) it.next();
			topicIdList.add(topicId);
			//System.out.println(topicId+":"+json.getString(topicId));
		 }

		User user =(User)request.getSession().getAttribute("user");
		if(user==null){
			System.out.println("用户为空");
			return;
		}
		long uid = user.id;
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
	
		 JSONArray jsonarray = topicModel.getJSONArrayFromRecommendedTopicPublisherList(rtpl,uid);

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
			recommendedTopicPUblisherList=new ArrayList<RecommendedTopicPublisher>();
		}
		int hitsize = recommendedTopicPUblisherList.size();
		
		/**
		*徐永 18:23:55 
		*对了,想起一个事: 在推荐页面上, 如果相关的话题推荐完了, 就把其实的也推荐上去,每页十个话题, 最多推荐十页, 100个话题就行.其余的(没有命中的),就按最后回复时间排序..
		*/
		//思路：从数据库搜出所有话题按最后回复时间排序,然后取出前100-命中的话题数
		//
		List<Topic> otherTopics = topicManager.getLatestUpdateTopics(300-hitsize);//前100条最活跃的话题

		List<RecommendedTopicPublisher> other_rtbl = topicModel.getRecommendedTopicPUblisherByTopicList(otherTopics);

		recommendedTopicPUblisherList.addAll(other_rtbl);
		
		//System.out.println("推荐 列表："+recommendedTopicPUblisherList);

		if(recommendedTopicPUblisherList==null){
			try {
				response.setCharacterEncoding("utf-8");
				response.getWriter().write("没有匹配的话题");
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//System.out.println("将推荐列表封装json数据:");
		JSONArray jsonArray = new JSONArray();
		for(RecommendedTopicPublisher rtp:recommendedTopicPUblisherList){
			if(rtp==null||rtp.userId==null||rtp.topicId==null||"".equals(rtp.userId)||"".equals(rtp.topicId)){
				continue;
			}
			JSONObject obj=new JSONObject();
			//System.out.println("userId:"+rtp.getUserId()+"  ====>topicId:"+rtp.getTopicId());
			obj.put(rtp.topicId,rtp.userId);
			jsonArray.add(obj.toString());
		}
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().write(jsonArray.toString());
		} catch (IOException e) {
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
		//System.out.println("userId:"+userId);
		List<TopicRequestMsgPlusTopicDetail>  topicRequestMsgPlusTopicDetailList=msgManager.findTopicRequestMsgByUserId(userId);
		request.setAttribute("topicRequestMsgPlusTopicDetailList", topicRequestMsgPlusTopicDetailList);
		//System.out.println(topicRequestMsgPlusTopicDetailList.size());
		//System.out.println("初始化话题邀请信息");
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
		//System.out.println("初始化系统信息");
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
		//System.out.println("保存系统信息到数据库成功");
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
		//System.out.println("拒绝邀请"+"toUserId:"+toUserId+"  topicId:"+topicId);
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
		String toUserId = request.getParameter("userId");
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
		//System.out.println(topicIdArray);
		List<String> topicIdList = new ArrayList<String>();
		String[] topicIds =topicIdArray.split(",");
		for(String topicId:topicIds)
		{
			//System.out.println(topicId);
			topicIdList.add(topicId);
		}
		List<Topic> topicList = topicManager.getTopicListByTopicIdList(topicIdList);
		//System.out.println("数："+topicList.size());
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
			//System.out.println(t.topicContent);
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
		String fromUserName = userManager.findUserById(Integer.parseInt(fromUserId)).getXunta_username();
		String to_userId = request.getParameter("to_userId");
		String to_userName = request.getParameter("to_userName");
		String topicId = request.getParameter("topicId");
		
/*		System.out.println("服务端收到了客户端的话题邀请请求");
		System.out.println("用户发出邀请参与话题请求：");
		System.out.println("fromUserId:"+fromUserId);
		System.out.println("fromUserName:"+fromUserName);
		System.out.println("to_userId:"+to_userId);
		System.out.println("to_userName:"+to_userName);
		System.out.println("topicId:"+topicId);*/
		
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
			response.setHeader("Cache-Control","no-cache"); 
			response.setHeader("Pragma","no-cache"); 
			response.setDateHeader ("Expires", -1); 
			response.getWriter().write("ok");
			response.sendRedirect(request.getContextPath()+"/jsp/xunta_user/login.jsp");

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
		topicModel.joinTopic(request, response, userId, topicId);

		//System.out.println("userId:"+userId);
		//System.out.println("topicId:"+topicId);

	}

	private void htss(HttpServletRequest request, HttpServletResponse response) {
			String searchWord = request.getParameter("search_word");
			searchWord =URLDecoder.decode(searchWord);
			System.out.println("话题搜索");
			System.out.println(searchWord);
			logutil.traceLog(request, "话题搜索："+searchWord);
			//搜索话题
			//不直接从索引返回搜索的话题列表，只返回id列表
			//List<so.xunta.topic.entity.Topic> searchedtopicList=topicManager.matchMyTopic(searchWord);
			//从索引里查询topic，不过只返回toplic的id列表，因为直接从索引返回Topic对象缺少参加人数，所以还是需要从数据库查
			List<String> searchedtopicIdList=topicManager.matchMyTopicIds(searchWord);
			List<so.xunta.topic.entity.Topic> searchedtopicList=topicManager.getTopicListByTopicIdList(searchedtopicIdList);
			
			request.setAttribute("searchWord",searchWord);
			//request.setAttribute("topicList",searchedtopicList);
			
			//按<用户名,TopicList>将topic列表按用户归类，方便前端显示
			Map<String,List<so.xunta.topic.entity.Topic>> usergroupedTopicList = new HashMap<String,List<so.xunta.topic.entity.Topic>>();
			//根据topic里的uesrId获取user对象,前台可从该对象里面获取性别及城市  - fang
			Map<String,User> userIdMappingUserObj = new HashMap<String,User>();
//			for (String key : userIdMappingUserObj.keySet()) {
//				  System.out.println("userId  =  "+key+"___   性别 ： "+userIdMappingUserObj.get(key).getSex()+"__  城市 ： "+userIdMappingUserObj.get(key).getAddress());
//			}
			
			request.setAttribute("topicsHashMap",null);
			if(searchedtopicList!=null)
			{
				for(so.xunta.topic.entity.Topic t: searchedtopicList){
					String userId = t.getUserId();
					if(usergroupedTopicList.containsKey(userId))
					{
						usergroupedTopicList.get(userId).add(t);
					}
					else
					{
						List<so.xunta.topic.entity.Topic> userTopicsList = new ArrayList<so.xunta.topic.entity.Topic>();
						userTopicsList.add(t);
						usergroupedTopicList.put(userId, userTopicsList);
						
						User user = userManager.findUserById(Integer.parseInt(userId));
						userIdMappingUserObj.put(userId, user);
					}
				}
				request.setAttribute("userIdMappingUserObj",userIdMappingUserObj);
				request.setAttribute("topicsHashMap",usergroupedTopicList);
			}
			
			try {
				request.getRequestDispatcher("/jsp/topic/include/search_results.jsp").forward(request, response);
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
/*		System.out.println("用户id:"+userId);
		System.out.println("用户名:"+userName);
		System.out.println("用户LogoUrl:"+userLogoUrl);
		System.out.println("话题名称:"+topicName);
		System.out.println("话题内容:"+topicContent);*/
		
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
		//List<so.xunta.topic.entity.Topic> matchedtopicList=topicManager.matchMyTopic(topic.topicName,topic.topicContent);
		List<String> matchedtopicListIds=topicManager.matchMyTopicIds(topic.topicName,topic.topicContent);
		
		//从数据库查询前面从索引中查到的发过或参与过相关话题的用户
		List<TopicHistory> matchedTopicFromDB = topicManager.findTopicHistoryByTopicId(matchedtopicListIds);
		//要返回到前台页面的匹配的结果用户，之所以用Map不用List是为了根据userId快速查出已经添加进去的MatchTopicPeople，返回到前台时只返回values组成的List
		Map<Long,MatchTopicPeople> mtpList = new HashMap<Long,MatchTopicPeople>();
		
		if(matchedTopicFromDB!=null)
		{
			//话题id是主键，参与过该话题的用户id的list为value，这样做是为了使用findUserListByUserIdList()更快地查询所有用户
			Map<String,List<Long>> userIdsOfEachTopic = new HashMap<String,List<Long>>();
			//话题ID+Xunta+用户id作为key，P_Or_J作为value
			Map<String,String> userPJ_EachTopic = new HashMap<String,String>();
			
			/*for(TopicHistory outerth : matchedTopicFromDB)
			{
				String tid = outerth.getTopicId();
				List<Long> userIds = new ArrayList<Long>();
				for(TopicHistory th: matchedTopicFromDB)
				{
					if(th.getTopicId().equals(tid))
					{
						userPJ_EachTopic.put(tid+"=xunta="+th.getAuthorId(), String.valueOf(th.getPublish_or_join()));
						userIds.add(Long.parseLong(th.getAuthorId()));
					}
				}
				userIdsOfEachTopic.put(tid, userIds);
			}*/
			for(TopicHistory th : matchedTopicFromDB)
			{
				//不去查询统计本用户的话题信息，因为不会推荐自己给自己
				if(userId.equals(th.getAuthorId()))
					continue;
				String tid = th.getTopicId();
				if (userIdsOfEachTopic.containsKey(tid)) {
					List<Long> userIds = userIdsOfEachTopic.get(tid);
					userPJ_EachTopic.put(tid + "=xunta=" + th.getAuthorId(),
							String.valueOf(th.getPublish_or_join()));
					userIds.add(Long.parseLong(th.getAuthorId()));
				} else {
					List<Long> userIds = new ArrayList<Long>();
					userPJ_EachTopic.put(tid + "=xunta=" + th.getAuthorId(),
							String.valueOf(th.getPublish_or_join()));
					userIds.add(Long.parseLong(th.getAuthorId()));

					userIdsOfEachTopic.put(tid, userIds);
				}
			}
			
			Iterator<String> iter = userIdsOfEachTopic.keySet().iterator();
			while (iter.hasNext()) {
			    String key = iter.next();
			    List<Long> userIDs = userIdsOfEachTopic.get(key);
			    //通过List集合的方式一下查询多个用户更高效
			    List<User> relatedUsers = userManager.findUserListByUserIdList(userIDs);
			    
			    for(User u : relatedUsers)
			    {
			    	//该mtp是否添加过了，如果还没添加过，则添加
			    	if(!mtpList.containsKey(u.getId()))
			    	{
				    	MatchTopicPeople mtp = new MatchTopicPeople();
				    	mtp.setUserId(u.getId());
				    	//由于需求变了，这里换成昵称显示
				    	mtp.setName(u.getNickname());
				    	mtp.setImgUrl(u.getImageUrl());
				    	List<Tag> userTagList = tagsManager.findAllTagsByUserId(u.getId());
				    	//数据库查询到的是Tag类型的，MatchTopicPeople里只使用它的name，提取一下
						List<String> userTagName = new ArrayList<String>();
						for(Tag tag:userTagList)
						{
							userTagName.add(tag.getTagname());
						}
				    	mtp.setTagList(userTagName);
				    	//暂时使用taglist作为用户签名，后面待改
				    	mtp.setSignature(mtp.tagListToString());
				    	//将P_or_J设为1
				    	String p_or_j = userPJ_EachTopic.get(key+"=xunta="+u.getId());
				    	if(p_or_j!=null && p_or_j.equals("p"))
				    	{
				    		mtp.setP_num(1);
				    		mtp.setJ_num(0);
				    	}
				    	else
				    	{
				    		mtp.setJ_num(1);
				    		mtp.setP_num(0);
				    	}
				    	mtpList.put(u.getId(), mtp);
				    	
				    	//userHasAdd.add(u.getId());
			    	}
			    	else{//否则只是将P_OR_J加1
			    		MatchTopicPeople mtp = mtpList.get(u.getId());
			    		String p_or_j = userPJ_EachTopic.get(key+"=xunta="+u.getId());
						if(p_or_j!=null && p_or_j.equals("p"))
							mtp.setP_num(mtp.getP_num()+1);
						else
							mtp.setJ_num(mtp.getJ_num()+1);
			    	}
			    	
			    }
			}
		}
		//被注释掉的也是可行的，但是可能相对低效些
/*		boolean hasAdded = false;
		for(TopicHistory th : matchedTopicFromDB)
		{
			String currentUserId = th.getAuthorId();
			//从user表里查询user的信息
			User user = userManager.findUserById(Integer.parseInt(currentUserId));
			
			//先判断这个用户是否已经在mtpList中了，因为他可能在前面话题遍历的时候
			//已经添加进来了，如果已经进来了这里要修改的就只是他的P_num或J_num了
			int counter = 0;
			for(MatchTopicPeople p : mtpList)
			{
				if(p!=null && p.getName().equals(user.getXunta_username()))
				{
					hasAdded = true;
					char p_or_j = th.getPublish_or_join();
					if(p_or_j=='p')
						p.setP_num(p.getP_num()+1);
					else
						p.setJ_num(p.getJ_num()+1);
					break;
				}
				counter++;
			}
			if(counter==mtpList.size())
				hasAdded = false;
			if(!hasAdded)
			{
				hasAdded = false;
				MatchTopicPeople mtp = new MatchTopicPeople();
				List<Tag> userTagList = tagsManager.findAllTagsByUserId(Long.parseLong(currentUserId));
				//数据库查询到的是Tag类型的，MatchTopicPeople里只使用它的name，提取一下
				List<String> userTagName = new ArrayList<String>();
				for(Tag tag:userTagList)
				{
					userTagName.add(tag.getTagname());
				}
				mtp.setUserId(user.getId());
				mtp.setName(user.getXunta_username());
				mtp.setImgUrl(user.getImageUrl());
				mtp.setTagList(userTagName);
				//暂时用tag代替签名
				mtp.setSignature(mtp.tagListToString());
				char p_or_j = th.getPublish_or_join();
				if(p_or_j=='p')
					mtp.setP_num(1);
				else
					mtp.setJ_num(1);
				
				mtpList.add(mtp);
			}
		}*/
		if(!mtpList.isEmpty())
			request.setAttribute("matchedTopicList",new ArrayList<MatchTopicPeople>(mtpList.values()));
		else
			request.setAttribute("matchedTopicList",null);
		request.setAttribute("my_topicId",topicId);
		request.setAttribute("topicName", topicName);
		request.setAttribute("topicContent", topicContent);
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
	
	/**
	 * 获取话题历史
	 * 房
	 * */
	@SuppressWarnings({ "deprecation", "rawtypes" })
	private void getTopicMemory(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String topicType = request.getParameter("topicType");
		String topicNum = request.getParameter("topicNum");
		//System.out.println("TopicService  ==> getTopicMemory from Fang  ==>   userId : "+userId+", topicType : "+topicType+", topicNum : "+topicNum);
		List<TopicHistory> topicIdList = topicManager.findAuthorIdAndPublish_or_joinByTopicId(userId, topicType,Integer.parseInt(topicNum));
		
		if(topicIdList.size() == 0){
			//该用户在topicHistory里面没有符合topicType的记录
			//System.out.println("TopicService  ==> getTopicMemory from Fang  ==>  当前用户ID ： "+userId+",  没有符合 titleType : "+topicType+", 的记录");
			weibo4j.org.json.JSONObject jsonObject = new weibo4j.org.json.JSONObject();
			try {
				jsonObject.put("notTopic", "yes");
				response.setContentType("text/json");
				response.setCharacterEncoding("utf-8");
				response.getWriter().write(jsonObject.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			//topic history message  string : topicId
			LinkedHashMap<String,HistoryMessage> topicHistoryMessageMap = topicManager.findTopicIdByHistoryMessage(topicIdList);
			Iterator<?> iter = topicHistoryMessageMap.entrySet().iterator();
			JSONArray arrayJson = new JSONArray();
			JSONObject objectJson = new JSONObject();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String topicId = (String) entry.getKey();
				String acceppterIds=new TopicManagerImpl().findMemberIdsByTopicId(topicId).size()+"";
				String content="";
				String lastTime="";
				if(entry.getValue() != null){
					//如果该用户只发布了话题，但是没有说话， 会导致获取不到最后回复内容及最后回复时间及话题参与人数，用默认值代替
					HistoryMessage val = (HistoryMessage) entry.getValue();
					content = URLDecoder.decode(val.getContent());
					lastTime = val.getDateAndTime();
				}
				Topic topic = topicManager.findTopicIdByTopic(topicId);
				String userName = topic.getUserName();
				String topicName = topic.getTopicName();
				String createTime = topic.getCreateTime();
				String userImgUrl = new UserManagerImpl().findUserById(Integer.parseInt(topic.getUserId())).getImageUrl();
				String time_j = topicManager.findTopicIdByTopicHistory(topicId,userId);
				String month ="";
				String yyyyMMdd="";
				String HHmm="";
				try {
				//	System.out.println(time_j);
					Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time_j);
					yyyyMMdd = Time.getDate_ta_pc(date)+"  ";
					HHmm = Time.getTime_ta_pc(date);
					month = Time.getMonth(date);
//					System.out.println(userName);
//					System.out.println(userImgUrl);
//					System.out.println(topicName);
//					System.out.println(createTime);
//					System.out.println(content);
//					System.out.println(lastTime);
//					System.out.println(month);
//					System.out.println(accepters);
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("topicId", topicId);
					jsonObject.put("nickname", userName);
					jsonObject.put("userImgUrl", userImgUrl);
					jsonObject.put("topicName", topicName);
					jsonObject.put("createTime", createTime);
					jsonObject.put("content", content);
					jsonObject.put("lastTime", lastTime);
					jsonObject.put("month", month);
					jsonObject.put("accepters", acceppterIds);
					jsonObject.put("yyyyMMdd", yyyyMMdd);
					jsonObject.put("HHmm", HHmm);
//					System.out.println(jsonObject.toString());
					arrayJson.add(jsonObject);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			try {
				response.setContentType("text/json");
				response.setCharacterEncoding("utf-8");
				objectJson.put("notTopic", "no");
				objectJson.put("msg", arrayJson.toString());
				//System.out.println(objectJson.toString());
				response.getWriter().write(objectJson.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
