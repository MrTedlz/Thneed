package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class LoginController {
//	Intialize variables
//	buttons
	@FXML
	private Button singInButton;
	
	@FXML
	private Button signUpButton;
	
//	Labels
	@FXML
	private Label userNameError;
	@FXML
	private Label passwordError;

//	TextFields
	@FXML
	private TextField loginTextField;
	@FXML	
	private PasswordField passwordField;
//	pane
	@FXML
	private AnchorPane loginPane;

	

//	initializing controllers and stages
	private OrderPageController orderPageController;
	private Stage orderPageStage;
	private registerNewCustomerController registerController;
	private Stage registerStage;
	private OrderHistoryController orderHistoryPageController;
	private Stage orderHistoryStage;
	
	
	
	
	private String[] loginInfoArray = new String[2];
	private String[] orderInfoArray = new String[7];
	private String[] custInfoArray = new String[5];
//	Hashing Login Info
	private HashMap<String, String> loginHmap = new HashMap<String, String>();
	private ArrayList<Order> orderList = new ArrayList<Order>();
	

//	Initalize array lists to be pushed to the order history list view
	private ArrayList<Order> filledOrderList = new ArrayList<>();
	private ArrayList<Order> unfilledOrderList = new ArrayList<>();
	
	private ArrayList<String> filledInfoList = new ArrayList<>();
	private ArrayList<String> unfilledInfoList = new ArrayList<>();
	
	private ArrayList<customer> custList = new ArrayList<>();
	
	
	
//	Create the orderList
	public ArrayList<Order> getOrderList() {
		return orderList;
	}
//	Create the filledOrderList
	public ArrayList<Order> getFilledOrderList(){
		return filledOrderList;
	}
//	Create the unfilledOrderList
	public ArrayList<Order> getUnfilledOrderList(){
		return unfilledOrderList;
	}
	
//	Create the Customer List
	public ArrayList<customer> getCustList(){
		return custList;
	}




//	Sign in button feature the ability to look into a text file and check whether the user name and password fit
	public void signInButtonClick(ActionEvent event) throws IOException, ClassNotFoundException {
		
		
		try {
			Scanner file = new Scanner(new File("Login_info.txt")); //use the scanner object to load and read the file 

			while(file.hasNextLine()) {
				String content = file.nextLine(); //nextLine will grab one line at a time
				loginInfoArray =  content.split(","); //split returns an array
				loginHmap.put(loginInfoArray[0], loginInfoArray[1]);//put the array item in a hashmap
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		

		
		
		
		//	check and see if username and password are correct and if so load the orderHistory GUI
		if (loginHmap.keySet().contains(loginTextField.getText())) {
			if (passwordField.getText().equals(loginHmap.get(loginTextField.getText()))) {
				if (orderHistoryStage == null) { // make sure that the window only pop out one time
					
//				 	Read the order information in from the order text file
					try {
						Scanner orderFile = new Scanner(new File("Order_Info.txt")); //use the scanner object to load and read the file 

						while(orderFile.hasNextLine()) {
							String content = orderFile.nextLine(); //nextLine will grab one line at a time
							orderInfoArray =  content.split(","); //split returns an array
//							Setting the positions in the orderInfo
							int orderID = Integer.parseInt(orderInfoArray[0]);
							int custID = Integer.parseInt(orderInfoArray[4]);
							String size = orderInfoArray[2];
							String color = orderInfoArray[3];
							int qty = Integer.parseInt(orderInfoArray[1]);
							String type = orderInfoArray[5];
//							Setting the date
							SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
							Date date = dateFormat.parse((orderInfoArray[6]));
							
							boolean status = Boolean.valueOf(orderInfoArray[7]);
// 11.29bug fix							Date fillDate = dateFormat.parse((orderInfoArray[8]));
							
//							Generating the order to be read
							Order readOrder = new Order(orderID,custID,qty,size,color,type,date,status, null);
							orderList.add(readOrder); // added the read order to the filled orderList
							if (readOrder.getStatus()) {
								filledOrderList.add(readOrder);
								filledInfoList.add(readOrder.toString());
							}
							else {
								unfilledOrderList.add(readOrder);// added the read order to the unfilled OrderList
								unfilledInfoList.add(readOrder.toString());
							}
						}

					}catch(Exception e) {
						e.printStackTrace();
					}
//					Read in the customer information and recreate the customer objects
					try {
						Scanner custFile = new Scanner(new File("Customer_Info.txt")); //use the scanner object to load and read the file 

						while(custFile.hasNextLine()) {
							String content = custFile.nextLine(); //nextLine will grab one line at a time
							custInfoArray =  content.split(","); //split returns an array
							
							int custID = Integer.parseInt(custInfoArray[0]);
							String custName = custInfoArray[1];
							String custAddress = custInfoArray[2];
							String custPhone = custInfoArray[3];
							
							customer readCustomer = new customer(custID,custName,custAddress,custPhone);
							custList.add(readCustomer);
						}

					}catch(Exception e) {
						e.printStackTrace();
					}
					
					//	sort the filledInfoList
					Collections.sort(filledInfoList);
					//	Collections.reverse(filledInfoList);
					
					//	sort the unfilledInfo List
					Collections.sort(unfilledInfoList);
					//	Collections.reverse(unfilledInfoList);
					
					ObservableList<String> filledOrders = FXCollections.observableArrayList(filledInfoList);
					ObservableList<String> unfilledOrders = FXCollections.observableArrayList(unfilledInfoList);
					
					//	load the order history page and populate the list views
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/OrderHistory.fxml"));
					AnchorPane registerRoot;
					try {
						registerRoot = (AnchorPane) loader.load();
						Scene registerScene = new Scene(registerRoot);
						orderHistoryStage = new Stage();
						orderHistoryStage.setScene(registerScene);
						orderHistoryPageController = (OrderHistoryController) loader.getController(); // this find the controller object
						orderHistoryPageController.getUnfilledListView().setItems(unfilledOrders);
						orderHistoryPageController.getFilledListView().setItems(filledOrders);
						orderHistoryPageController.setCallingController(this); //this links the controller 1 and controller 2
						orderHistoryStage.show();
						loginPane.getScene().getWindow().hide();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					//	loginPane.getScene().getWindow().hide();// hide the current window
				}
			}
			else
				passwordError.setText("The password is not right, try again"); // Exception if password incorrect
		}
		else {
			userNameError.setText("Please enter a valid username");// Invalid UserName
		}
			

	}
	//set it back to null when the page hidden
	public void setSummaryStage() {
		
	}
}
