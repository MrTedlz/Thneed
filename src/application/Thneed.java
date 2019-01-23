package application;

public class Thneed {
	//variable for the class
	private String type;
	private String color;
	private String size;
	private int quantity;
	
	

	
	
	@Override
	public String toString() {
		return "Thneed [type=" + type + ", color=" + color + ", size=" + size + ", quantity=" + quantity + "]";
	}

	//constructor
	public Thneed(String type, String color, String size, int quantity) {
		super();
		this.type = type;
		this.color = color;
		this.size = size;
		this.quantity = quantity;
	}
	
	//getter and setter method
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
