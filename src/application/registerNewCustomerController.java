package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

import javafx.scene.control.RadioButton;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class registerNewCustomerController {
	//TextFields

	@FXML
	private TextField fullNameTextField;
	@FXML
	private TextField addressTextField;
	@FXML
	private TextField phoneTextField;
	//buttons
	@FXML
	private Button cancelButton;
	@FXML
	private Button registerButton;
	
	//label
	@FXML
	private Label errorLabel;

	@FXML
	private AnchorPane registerPane;

	//add a arrayList to store the employee value
	@FXML
	ArrayList<customer> customerList= new ArrayList<>();
	String[] customerInfo =  new String[4];
	
	int max = 0;
	private LoginController callingController;
	private OrderPageController orderPageController;
	private OrderHistoryController orderHistoryController;
	private Stage orderPageStage;
	
	//Calling controller
	public void setCallingController(OrderPageController c) {
		orderPageController = c; //this is a comment
	}

	//hide the window
	public void cancelButtonClick (ActionEvent event) {
		registerPane.getScene().getWindow().hide();
		orderPageController.setSummaryStage();
		
	}
	
	//Create a new customer
	public void registerButtonClick (ActionEvent event) {
		String fullName = fullNameTextField.getText();
		String address = addressTextField.getText();
		String phoneNumber = phoneTextField.getText();
		try {
			Scanner file = new Scanner(new File("Customer_Info.txt")); //use the scanner object to load and read the file 
			java.io.File write = new java.io.File("Customer_Info.txt");
			while(file.hasNextLine()) {
				String content = file.nextLine(); //nextLine will grab one line at a time
				customerInfo =  content.split(","); //split returns an array
				if (customerInfo == null) {
					customer thneedUser = new customer(0,fullName,address,phoneNumber);
				}
				else {
					if (Integer.parseInt(customerInfo[0]) > max) {
						max = Integer.parseInt(customerInfo[0]);
					}
				}
			}
			if (fullNameTextField == null || addressTextField == null || phoneTextField == null) {
				errorLabel.setText("Please enter all the info");
			}
			else {
				customer thneedUser = new customer(max + 1,fullName,address,phoneNumber);
				int id = max + 1;
				orderPageController.idItems(id);
				customerList.add(thneedUser);
			}
			try(
					//enable to append instead of replacing
					java.io.PrintWriter output = new java.io.PrintWriter(new FileOutputStream(new File("Customer_Info.txt"),true));
					){
					for (int i = 0; i < customerList.size(); i++) {
						output.append("\n" + customerList.get(i).getCustomerID() + "," + customerList.get(i).getFullName()
								+ "," + customerList.get(i).getAddress() + "," + customerList.get(i).getPhoneNumber()); // use the for loop to write the file
					}
			}
			registerPane.getScene().getWindow().hide();
			orderPageController.setSummaryStage();
			
		}catch(Exception e) {
			e.printStackTrace();
			}
		
	}
	

	public void setPageHistoryController(OrderHistoryController OHC) {
		orderHistoryController = OHC; //this is a comment
	}
	



}
	
