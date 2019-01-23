package application;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{
//	Intialize Variables
	private int orderID;
	private int qty;
	private String size;
	private String color;
	private String type;
	private int customerID;
	private Date date = new Date();
	private Date projectedFill = new Date();
	private boolean status = false;

	
	//	no arg constructor
	Order(){}
	
	//	constructor for Order object
	Order(int orderID, int customerID, int qty, String size, String color, String type, Date date, boolean status, Date projectedFill){

		this.orderID = orderID;
		this.qty = qty;
		this.size = size;
		this.color = color;
		this.customerID = customerID;
		this.type = type;
		this.date = date;
		this.status = status;
		this.projectedFill = projectedFill;
	}
	
	public int compareTo(Order o) {
		return getDate().compareTo(o.getDate())*-1;
	}
	
	//	getter methods to return the order values
	public int getOrderID() {
		return orderID;
	}
	public int getqty() {
		return qty;
	}
	public String getSize() {
		return size;
	}
	public String getColor() {
		return color;
	}
	public int getCustomerID() {
		return customerID;
	}
	public Date getDate() {
		return date;
	}
	public Date getProjected() {
		return projectedFill;
	}
	public boolean getStatus() {
		return status;
	}

	
	//	setter methods to change the order details
	public void setOrderID(int newID) {
		this.orderID = newID;
	}
	public void setQty(int newQty) {
		this.qty = newQty;
	}
	public void setSize(String newSize) {
		this.size = newSize;
	}
	public void setColor(String newColor) {
		this.color = newColor;
	}
	public void setCustomerID(int newCustID) {
		this.customerID = newCustID;
	}

	public void setDate(Date newDate) {
		this.date = newDate;
	}
	public void setProjected(Date newProjected) {
		this.projectedFill = newProjected;
	}
	public void setStatus(boolean newStatus) {
		this.status = newStatus;
	}

	
	//	override toString method
	@Override
	public String toString() {
		String reply = "";

		reply += orderID + "," + qty + "," + size + "," + color + "," + customerID + "," + type + "," + date + "," + status;

		return reply;
	}

}
