package TestData;

public class UpdateUserData {
	
	private String accountno;
	private String departmentno;
	private String salary;
	private String pincode;
	private String userid;
	private String id;

	public UpdateUserData(String accountno,String departmentno, String salary, String pincode,String userid,String id) {
		this.accountno = accountno;
		this.departmentno = departmentno;
		this.salary = salary;
		this.pincode = pincode;
		this.userid = userid;
		this.id = id;
	}
	
	public String getAccountno() {
		return accountno;
	}
	
	public String getDepartmentno() {
		return departmentno;
	}
	
	public String getSalary() {
		return salary;
	}
	
	public String getPincode() {
		return pincode;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public String getId() {
		return id;
	}
	
	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}
	
	public void setDepartmentno(String departmentno) {
		this.departmentno = departmentno;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
}
