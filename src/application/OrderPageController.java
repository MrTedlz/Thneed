package application;
//chris
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.TextArea;

import javafx.scene.control.RadioButton;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javafx.scene.control.MenuButton;
public class OrderPageController {
	@FXML
	private RadioButton tshirt;
	@FXML
	private RadioButton sweatShirt;
	@FXML
	private RadioButton crewNeck;
	@FXML
	private RadioButton longSleeve;
	@FXML
	private RadioButton pink;
	@FXML
	private RadioButton black;
	@FXML
	private RadioButton grey;
	@FXML
	private RadioButton blue;
	@FXML
	private Button cartButton;
	@FXML
	private Button clearButton;
	@FXML
	private Button addCartButton;
	@FXML
	private Button checkoutButton;
	@FXML
	private Button addButton;
	@FXML
	private TextField quanityField;
	//combo boxes
	@FXML
	private ComboBox sizeComboBox;
	@FXML
	private ComboBox selectCustomerComboBox;
	
	@FXML 
	private ToggleGroup type;
	@FXML
	private ToggleGroup color;
	//pane
	@FXML
	private AnchorPane orderPagePane;
	
	//stage
	private Stage newCustomerStage;
	
	//the orderPageController object
	private registerNewCustomerController newCustomerController;
	private OrderHistoryController orderHistoryController;
	private LoginController loginController;
	private OrderPageController OPController;
	
//	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private ArrayList<Thneed> ordersList = new ArrayList<Thneed>();
	
	

	private boolean status = false;
	
	
	//Calling controller
	public void setCallingController(OrderHistoryController c) {
		orderHistoryController = c; 
	}
	
	public void setLoginController(LoginController lc) {
		this.loginController = lc;
	}
	
	//initialize the combo box
	@FXML
	public void initialize() {
		sizeComboBox.getItems().removeAll(sizeComboBox.getItems());
		sizeComboBox.getItems().addAll("Small", "medium", "Large");
		sizeComboBox.getSelectionModel().select("medium");
	}
	
	public void customerInitialize(ArrayList <customer> cusID) {
		for (int i = 0; i < cusID.size(); i++) {
			int customerID = cusID.get(i).getCustomerID();
			selectCustomerComboBox.getItems().add(customerID);
		}
	}
		
	public void idItems(int newID) {
		System.out.println("Items: " + selectCustomerComboBox.getItems());
		selectCustomerComboBox.getItems().add(newID);	
	}
			
	//	when user clicks the clear button it clears the current selected order information
	@FXML
	private void clearButtonClicked(ActionEvent event) {
		tshirt.setSelected(false);
		sweatShirt.setSelected(false);
		crewNeck.setSelected(false);
		longSleeve.setSelected(false);
		pink.setSelected(false);
		black.setSelected(false);
		grey.setSelected(false);
		blue.setSelected(false);
		quanityField.clear();
	}
	//	creates an order object and writes it to the text file
	@FXML
	public void checkoutButtonClicked(ActionEvent event) throws IOException {
		String size;
		int quanity;
		String colorOrder = null;
		int customerID;
		String order;
		int orderID;
		try {
			orderID = loginController.getOrderList().size() + 1;
		}
		catch(NullPointerException e) {
			orderID = 1;
		}
		
		//		Get text from fields/menubar
		customerID = Integer.parseInt(selectCustomerComboBox.getSelectionModel().getSelectedItem().toString());
		size = sizeComboBox.getSelectionModel().getSelectedItem().toString();
		quanity = Integer.parseInt(quanityField.getText());
		Date date = new Date();
		
		RadioButton typeButton = (RadioButton) type.getSelectedToggle();
		String typeOrder = typeButton.getText();
		
		RadioButton colorButton = (RadioButton) color.getSelectedToggle();
		colorOrder = colorButton.getText();
		
		//create order object, add it to the order list, write it to the text file
		Order o = new Order(orderID, customerID, quanity, size, colorOrder, typeOrder, date, status, null);
		loginController.getOrderList().add(o);
		System.out.println(o.toString());
		
		BufferedWriter bw = null;
		
		try {
			bw = new BufferedWriter(new FileWriter("Order_Info.txt", true));
			bw.write(o.toString());
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {                       // always close the file
			if (bw != null) try {
				bw.close();
		} catch (IOException ioe2) {
			    // just ignore it
			}
		} // end try/catch/finally
		
	}
	
	
	@FXML
	public void addNewCustomerButtonClicked(ActionEvent event) {
		//add code here
		if (newCustomerStage == null) { // make sure that the window only pop out one time
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/registerNewCustomer.fxml"));
			AnchorPane registerRoot;
			try {
				registerRoot = (AnchorPane) loader.load();
				Scene registerScene = new Scene(registerRoot);
				newCustomerStage = new Stage();
				newCustomerStage.setScene(registerScene);
				newCustomerController = (registerNewCustomerController) loader.getController(); // this find the controller object
				newCustomerController.setCallingController(this); //this links the controller 1 and controller 2
				newCustomerStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	


	@FXML
	public void addButtonClicked(ActionEvent event) {
		//local variable
		String size;
		int quanity;
		String typeOrder = null;
		String colorOrder = null;
		
		//get all the information
		size = sizeComboBox.getSelectionModel().getSelectedItem().toString();
		quanity = Integer.parseInt(quanityField.getText());
		
		RadioButton typeButton = (RadioButton) type.getSelectedToggle();
		typeOrder = typeButton.getText();
		
		RadioButton colorButton = (RadioButton) color.getSelectedToggle();
		colorOrder = colorButton.getText();
		
		//get 
		Thneed thneedObject = new Thneed(typeOrder, colorOrder, size, quanity);
		ordersList.add(thneedObject);
	}
	
	
	//set it back to null when the page hidden
	public void setSummaryStage() {
		newCustomerStage = null;
	}



	
}
