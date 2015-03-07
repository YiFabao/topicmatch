package so.xunta.manager;

import java.util.List;

import so.xunta.entity.Tag;

/**
 * 用户标签操作接口
 * @author fabao.yi
 * History:
 * 	2015/3/3 fabao.yi first release
 */
public interface TagsManager {
	
	//向数据库插入一个标签
	public void addOneTag(Tag tag);
	
	//向数据库插入多个标签
	public void addTags(List<Tag> tagList);
	
	//根据Id删除该条记录
	public void deleteOneTagByTagId(long tagId);
	
	//根据tagId_list删除多个标签
	public void deleteTags(List<Long> tagIdList);
	
	//根据userId查询该用户的所有标签
	public List<Tag> findAllTagsByUserId(long userId);
	
	//查询用户对应的标签是否为空
	public boolean checkUserTagIsEmpty(long userId);
	
}
