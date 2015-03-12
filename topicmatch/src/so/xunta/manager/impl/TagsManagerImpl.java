package so.xunta.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.flow.FinallyFlowContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import so.xunta.entity.Tag;
import so.xunta.manager.TagsManager;
import so.xunta.utils.HibernateUtils;

// fang
public class TagsManagerImpl implements TagsManager {

	@Override
	public void addOneTag(Tag tag) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.openSession();
		try {
			tag.setId(getTagTableId());
			session.beginTransaction();
			session.save(tag);
			session.getTransaction().commit();
		} catch (ConstraintViolationException c) {
			System.out.println("编辑标签数据存储时，因数据重复，触发此异常");
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void addTags(List<Tag> tagList) {
		System.out.println("进入 addTags");
		for (Tag tag : tagList) {
			System.out.println("0");
			Session session = HibernateUtils.openSession();
			Long tagId = getTagTableId();
			Long tagUserId = tag.getUserId();
			String tagName = tag.getTagname();
			Tag t = new Tag(tagUserId, tagName);
			t.setId(tagId);
			try {
				System.out.println("1");
				session.beginTransaction();
				System.out.println("2");
				session.save(t);
				System.out.println("3");
				session.getTransaction().commit();
				System.out.println("4");
			} catch (ConstraintViolationException c) {
				System.out.println("编辑标签列表数据存储时，因数据重复，触发此异常");
			} catch (RuntimeException e) {
				session.getTransaction().rollback();
				throw e;
			} finally {
				session.close();
			}
		}
	}

	@Override
	public void deleteOneTagByTagId(long tagId) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Tag where id=?").setLong(0, tagId);
			Tag tag = (Tag) query.uniqueResult();
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
		try {
			session.beginTransaction();
			int count = 0;
			for (Long userId : tagIdList) {
				Query query = session.createQuery("from Tag where id=?").setLong(0, userId);
				Tag tag = (Tag) query.uniqueResult();
				session.delete(tag);
				if (++count % 20 == 0) {
					// 当count是20的倍数时，刷新并清空session缓存，session有一级缓存限制，防止session内存溢出
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
			return total == 0 ? true : false;
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}
	
	public Long getTagTableId(){
		Session session = HibernateUtils.openSession();
		Query query;
		try {
			session.beginTransaction();
			query = session.createQuery("select id from Tag order by id desc");
			session.getTransaction().commit();
			return (Long) query.list().get(0);
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}finally {
			session.close();
		}
	}
	

	public static void main(String[] args) {
//		 TagsManager tagManager=new TagsManagerImpl();
		// Tag tag1 = new Tag(101,"足球");
		// tagManager.addOneTag(tag1);
		// Tag tag2 = new Tag(102,"篮球");
		// List<Tag> list = new ArrayList<Tag>();
		// list.add(tag1);
		// list.add(tag2);
		// tagManager.addTags(list);
		// tagManager.deleteOneTagByTagId(8);

		// List<Long> list = new ArrayList<Long>();
		// list.add((long) 102);
		// list.add((long) 103);
		//
		// tagManager.deleteTags(list);

		// List<Tag> list = tagManager.findAllTagsByUserId(101);
		// for(Tag t:list){
		// System.out.println(t.getTagname());
		// }

		// System.out.println(tagManager.checkUserTagIsEmpty(101));
//		TagsManagerImpl a = new TagsManagerImpl();
//		System.out.println(a.getTagTableId());
	}

}
