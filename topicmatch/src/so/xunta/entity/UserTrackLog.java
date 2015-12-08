package so.xunta.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_tracklog")
public class UserTrackLog {
	@Id
	@GeneratedValue
	private Long id;
	private String ip;
	private String country;
	private String action;
	private String datetime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public UserTrackLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public UserTrackLog(String ip, String country, String action, String datetime) {
		super();
		this.ip = ip;
		this.country = country;
		this.action = action;
		this.datetime = datetime;
	}

}
