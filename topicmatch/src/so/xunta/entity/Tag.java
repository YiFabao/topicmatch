package so.xunta.entity;


/**
 * 用户的个人标签
 * @author fabaoyi.yi
 * History:
 * 		2015/3/3 first release
 */
public class Tag {
	
	public long id;//主键id
	
	public long userId;//用户的唯一id
	
	public String tagname;//标签

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public Tag() {
		super();
	}
	public Tag(long userId, String tagname) {
		super();
		this.userId = userId;
		this.tagname = tagname;
	}
}
