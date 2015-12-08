package so.xunta.user.listener;

import java.util.Date;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import so.xunta.entity.User;

public class MyUserloginStateListener implements HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent sbe) {
		/*System.out.println("添加一个sessionAttribute=================================================================");
		System.out.println("session创建时间:"+new Date(sbe.getSession().getCreationTime()));
		System.out.println("sessionId:"+sbe.getSession().getId());
		System.out.println("上次访问时间:"+new Date(sbe.getSession().getLastAccessedTime()));
		System.out.println("最大过期时间：:"+(sbe.getSession().getMaxInactiveInterval()/86400)+"天");
		System.out.println("xunta_user_name:"+(((User)sbe.getSession().getAttribute("user"))== null?null:((User)sbe.getSession().getAttribute("user")).xunta_username));*/
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent sbe) {
		/*System.out.println("移除一个sessionAttribute=================================================================");
		System.out.println("session创建时间:"+new Date(sbe.getSession().getCreationTime()));
		System.out.println("sessionId:"+sbe.getSession().getId());
		System.out.println("上次访问时间:"+new Date(sbe.getSession().getLastAccessedTime()));
		System.out.println("最大过期时间：:"+(sbe.getSession().getMaxInactiveInterval()/86400)+"天");
		System.out.println("xunta_user_name:"+(((User)sbe.getSession().getAttribute("user"))== null?null:((User)sbe.getSession().getAttribute("user")).xunta_username));*/
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent sbe) {
		/*System.out.println("替换一个sessionAttribute=================================================================");
		System.out.println("session创建时间:"+new Date(sbe.getSession().getCreationTime()));
		System.out.println("sessionId:"+sbe.getSession().getId());
		System.out.println("上次访问时间:"+new Date(sbe.getSession().getLastAccessedTime()));
		System.out.println("最大过期时间：:"+(sbe.getSession().getMaxInactiveInterval()/86400)+"天");
		System.out.println("xunta_user_name:"+(((User)sbe.getSession().getAttribute("user"))== null?null:((User)sbe.getSession().getAttribute("user")).xunta_username));*/
	}
}
