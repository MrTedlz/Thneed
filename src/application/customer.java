package application;
//create customers
//Chris was here

public class customer {
//	Initialize values
	private int customerID;
	private String fullName;
	private String address;
	private String phoneNumber;
	
//	Create customer object
	public customer(int customerID, String fullName, String address, String phoneNumber) {
		super();
		this.customerID = customerID;
		this.fullName = fullName;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
//	Get Customer ID Method
	public int getCustomerID() {
		return customerID;
	}
//	Set Customer ID Method
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
//	Get Full Name Method
	public String getFullName() {
		return fullName;
	}
//	Set Full Name Method
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
//	Get Address Method
	public String getAddress() {
		return address;
	}
//	Overriding TO String Method
	@Override
	public String toString() {
		return "customer [customerID=" + customerID + ", fullName=" + fullName + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + "]";
	}
//  Set Address Method
	public void setAddress(String address) {
		this.address = address;
	}
//	Get Phone Number Method
	public String getPhoneNumber() {
		return phoneNumber;
	}
//	Set Phone Number Method
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


}