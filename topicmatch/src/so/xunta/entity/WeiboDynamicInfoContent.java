package so.xunta.entity;


public class WeiboDynamicInfoContent{
	
	public int id;
	public String weibo_uid;//唯一标识
	public String content;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWeibo_uid() {
		return weibo_uid;
	}
	public void setWeibo_uid(String weibo_uid) {
		this.weibo_uid = weibo_uid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public WeiboDynamicInfoContent(String weibo_uid, String content) {
		super();
		this.weibo_uid = weibo_uid;
		this.content = content;
	}
	
}
