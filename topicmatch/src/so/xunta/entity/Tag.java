package so.xunta.entity;

import java.io.Serializable;


/**
 * 用户的个人标签
 * @author fabaoyi.yi
 * History:
 * 		2015/3/3 first release
 */
public class Tag implements Serializable{
	
	public long id;//主键id
	
	public long userId;//用户的唯一id
	
	public String tagname;//标签
	
	public String md5;

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
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
	public Tag(long userId, String tagname, String md5) {
		super();
		this.userId = userId;
		this.tagname = tagname;
		this.md5 = md5;
	}
}
