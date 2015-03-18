package so.xunta.topic.entity;

import java.util.List;

public class MatchTopicPeople {
	public Long userId;
	public String name;
	public String imgUrl;
	public String signature;
	public List<String> tagList;
	public int p_num;// 发起相关话题数
	public int j_num;//	参与相关话题数
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public List<String> getTagList() {
		return tagList;
	}
	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public int getJ_num() {
		return j_num;
	}
	public void setJ_num(int j_num) {
		this.j_num = j_num;
	}
	
	public String tagListToString(){
		if(tagList==null||tagList.size()==0){
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<tagList.size()-1;i++)
		{
			sb.append(tagList.get(i)+",");
		}
		sb.append(tagList.get(tagList.size()-1));
		return sb.toString();
	}
	
}
