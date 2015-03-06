package so.xunta.manager.impl;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jdt.internal.compiler.flow.FinallyFlowContext;
import org.hibernate.Query;
import org.hibernate.Session;
import so.xunta.entity.Tag;
import so.xunta.manager.TagsManager;
import so.xunta.utils.HibernateUtils;

public class TagsManagerImpl implements TagsManager{

	@Override
	public void addOneTag(Tag tag) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			session.save(tag);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
		
	}

	@Override
	public void addTags(List<Tag> tagList) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			int count = 0;
			for(Tag tag : tagList){
				session.save(tag);
				if(++count % 20 == 0){
					//当count是20的倍数时，刷新并清空session缓存，session批量写入时有一级缓存限制，防止session内存溢出
					session.flush();
					session.clear();
				}
			}
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void deleteOneTagByTagId(long tagId) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Tag where id=?").setLong(0, tagId);
			Tag tag=(Tag) query.uniqueResult();
			session.delete(tag);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void deleteTags(List<Long> tagIdList) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.openSession();
		try{
			session.beginTransaction();
			int count = 0;
			for(Long userId:tagIdList){
				Query query = session.createQuery("from Tag where id=?").setLong(0, userId);
				Tag tag = (Tag) query.uniqueResult();
				session.delete(tag);
				if(++count % 20 == 0){
					//当count是20的倍数时，刷新并清空session缓存，session有一级缓存限制，防止session内存溢出
					session.flush();
					session.clear();
				}
			}
			session.getTransaction().commit();
		}catch (RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public List<Tag> findAllTagsByUserId(long userId) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.openSession();
		Query query;
		try {
			session.beginTransaction();
			query = session.createQuery("from Tag where userId=?").setLong(0, userId);
			session.getTransaction().commit();
			return query.list();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean checkUserTagIsEmpty(long userId) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.openSession();
		Query query;
		try {
			session.beginTransaction();
			query = session.createQuery("from Tag where userId=?").setLong(0, userId);
			session.getTransaction().commit();
			int total = query.list().size();
			return total == 0 ? true:false;
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}
	
	public static void main(String[] args) {
//		TagsManager tagManager=new TagsManagerImpl();
//		Tag tag1 = new Tag(101,"足球");
//		tagManager.addOneTag(tag1);
//		Tag tag2 = new Tag(102,"篮球");
//		List<Tag> list = new ArrayList<Tag>();
//		list.add(tag1);
//		list.add(tag2);
//		tagManager.addTags(list);
//		tagManager.deleteOneTagByTagId(8);
		
//		List<Long> list = new ArrayList<Long>();
//		list.add((long) 102);
//		list.add((long) 103);
//		
//		tagManager.deleteTags(list);
		
//		List<Tag> list = tagManager.findAllTagsByUserId(101);
//		for(Tag t:list){
//			System.out.println(t.getTagname());
//		}
		
//		System.out.println(tagManager.checkUserTagIsEmpty(101));
	}

}
