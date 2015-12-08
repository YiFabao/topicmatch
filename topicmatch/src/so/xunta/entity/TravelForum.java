package so.xunta.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="test_import_data")
public class TravelForum {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String channel1;
	private String channel2;
	private String channel3;
	private String author;
	@Column(columnDefinition="text")
	private String title;
	@Column(columnDefinition="longtext")
	private String content;
	private int level;
	private Date publishtime;
	private int publishdate;
	private int catchdate;
	private int catch_onlytime;
	private String sitename;
	private Date updatetime;
	@Column(columnDefinition="text")
	private String url;
	@Column(columnDefinition="text")
	private String tp_id;
	@Column(columnDefinition="text")
	private String ch_name;
	private String md5;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChannel1() {
		return channel1;
	}
	public void setChannel1(String channel1) {
		this.channel1 = channel1;
	}
	public String getChannel2() {
		return channel2;
	}
	public void setChannel2(String channel2) {
		this.channel2 = channel2;
	}
	public String getChannel3() {
		return channel3;
	}
	public void setChannel3(String channel3) {
		this.channel3 = channel3;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Date getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}
	public int getPublishdate() {
		return publishdate;
	}
	public void setPublishdate(int publishdate) {
		this.publishdate = publishdate;
	}
	public int getCatchdate() {
		return catchdate;
	}
	public void setCatchdate(int catchdate) {
		this.catchdate = catchdate;
	}
	public int getCatch_onlytime() {
		return catch_onlytime;
	}
	public void setCatch_onlytime(int catch_onlytime) {
		this.catch_onlytime = catch_onlytime;
	}
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTp_id() {
		return tp_id;
	}
	public void setTp_id(String tp_id) {
		this.tp_id = tp_id;
	}
	public String getCh_name() {
		return ch_name;
	}
	public void setCh_name(String ch_name) {
		this.ch_name = ch_name;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	
	
}
