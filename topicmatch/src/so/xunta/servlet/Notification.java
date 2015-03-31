package so.xunta.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import so.xunta.topic.model.NotificationManager;
import so.xunta.topic.model.impl.NotificationManagerImpl;

/**
 * Servlet implementation class Notification
 */

public class Notification extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NotificationManager notificationManagerImpl = new NotificationManagerImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Notification() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("删除 话题邀请消息通知   话题ID  :  " + request.getParameter("topicId"));
		System.out.println("删除 话题邀请消息通知   话题ID  :  " + request.getParameter("toUserId"));
		notificationManagerImpl.deleteTopicInviteNotification(request.getParameter("topicId"),request.getParameter("toUserId"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
