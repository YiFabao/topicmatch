package so.xunta.manager.impl;

import java.util.List;

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
		
	}

	@Override
	public void deleteOneTagByTagId(long tagId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTags(List<Long> tagIdList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Tag> findAllTagsByUserId(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkUserTagIsEmpty(long userId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static void main(String[] args) {
		TagsManager tagManager=new TagsManagerImpl();
		Tag tag=new Tag(101,"足球");
		tagManager.addOneTag(tag);
	}

}
