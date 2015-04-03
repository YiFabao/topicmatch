package so.xunta.manager.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import so.xunta.entity.LoginLog;
import so.xunta.manager.UserInfoManager;
import so.xunta.utils.HibernateUtils;

public class UserInfoManagerImpl implements UserInfoManager {

	@Override
	public void addLoginLog(LoginLog loginLog) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			session.save(loginLog);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public LoginLog findLoginLogByUserId(String userId) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String hql = "from LoginLog as l where l.userId=?";
			Query query = session.createQuery(hql).setString(0, userId);
			List<LoginLog> loginLogList = query.list();
			session.getTransaction().commit();
			if(loginLogList.size()==0)
			{
				return null;
			}else{
				if(loginLogList.size()>1)
				{
					System.out.println("用户"+userId+"对应了多个log");
				}
				return loginLogList.get(0);
			}
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}
	
	public static void main(String[] args) {
		LoginLog l = new LoginLog("1",0);
		l.setUserId("1");
		UserInfoManager userInM = new UserInfoManagerImpl();
		//userInM.addLoginLog(l);
		LoginLog ll = userInM.findLoginLogByUserId("1");
		if(ll==null)
		{
			System.out.println("没有跳过");
		}else{
			if(ll.getBind_account_step()==0)
			{
				System.out.println("跳过");
			}else{
				System.out.println("没有跳过");
			}
		}
	}

}
