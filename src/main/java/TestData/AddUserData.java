package TestData;

public class AddUserData {
	private String accountno;
	private String departmentno;
	private String salary;
	private String pincode;
	
	public AddUserData(String accountNo, String departmentNo) {
		this.setAccountNo(accountNo);
		this.setDepartmentNo(departmentNo);
		this.setSalary("200000");
		this.setPincode("123412");
	}

	public AddUserData() {
		
	}

	public String getAccountNo() {
		return accountno;
	}

	public void setAccountNo(String accountNo) {
		this.accountno = accountNo;
	}

	public String getDepartmentNo() {
		return departmentno;
	}

	public void setDepartmentNo(String departmentNo) {
		this.departmentno = departmentNo;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}
