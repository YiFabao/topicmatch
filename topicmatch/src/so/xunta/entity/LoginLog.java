package so.xunta.entity;

public class LoginLog {
	private Long id;
	private String userId;
	private int bind_account_step;//是否跳过,1为没跳过，0为跳过
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getBind_account_step() {
		return bind_account_step;
	}
	public void setBind_account_step(int bind_account_step) {
		this.bind_account_step = bind_account_step;
	}
	public LoginLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginLog(String userId, int bind_account_step) {
		super();
		this.userId = userId;
		this.bind_account_step = bind_account_step;
	}

	
	
}
