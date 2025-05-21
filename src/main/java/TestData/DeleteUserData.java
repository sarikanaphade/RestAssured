package TestData;

public class DeleteUserData {
	
	private String userid;
	private String id;
	
	public DeleteUserData(String userId, String id) {
		this.userid = userId;
		this.id = id;
	}
	
	public String getUserId() {
		return userid;
	}
	
	public String getId() {
		return id;
	}
	
	public void setUserId(String userId) {
		this.userid = userId;
	}
	
	public void setId(String id) {
		this.id = id;
	}

}
