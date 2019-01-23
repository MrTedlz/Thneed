package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OrderHistoryController {
	@FXML
	private AnchorPane orderHistoryPane;
	@FXML
	private Label headerLabel;
	@FXML
	private ListView unfilledOrderListView;
	@FXML
	private Button viewCustomerButton;
	@FXML
	private Button placeNewOrderButton;
	@FXML
	private ListView filledOrderListView;
	@FXML
	private Label unfilledOrdersLabel;
	@FXML
	private Label filledOrdersLabel;
	@FXML
	private Button fillSelectedOrderButton;
	@FXML
	private Button saveOrdersButton;
	
	String currentSelected = null;
	//stage
	private Stage orderPageStage;
	private Stage custInfoPageStage;

	//the orderPageController object
	private OrderPageController orderPageController;
	private CustomerInfoPageController custInfoPageController;

	private ArrayList<Order> orders = new ArrayList<>();
	
	//other controller
	private LoginController callingController;
	private registerNewCustomerController RNCController;


	private String[] listViewInfoArray = new String[7];
	private String[] listViewOrderInfo = new String[8];


	//Calling controller
	public void setCallingController(LoginController c) {
		callingController = c; //this is a comment
	}
	@FXML
	public void unfilledSelect(MouseEvent event) {
	currentSelected = (String) unfilledOrderListView.getSelectionModel().getSelectedItem();
	}
	@FXML
	public void filledSelect(MouseEvent event) {
	currentSelected = (String) filledOrderListView.getSelectionModel().getSelectedItem();
	}
	//	view customer button click
	@FXML
	public void viewCustomerButtonClick(ActionEvent event) {
		// create an array list of customers and read in all customer info
		ArrayList<customer> custList = callingController.getCustList();
		System.out.println(custList.get(0).toString());
		System.out.println(filledOrderListView.getSelectionModel().getSelectedItem());
		System.out.println(unfilledOrderListView.getSelectionModel().getSelectedItem());

		listViewInfoArray = currentSelected.split(",");

		//	parse the string and loop through the customer list looking for the customer information
		int customerID = Integer.parseInt(listViewInfoArray[4]);
		
		String custName = "";
		String custPhone = "";
		String custAddress = "";

		for (int i = 0; i < custList.size(); i++) {
			if (custList.get(i).getCustomerID() == customerID) {
				
				custName = custList.get(i).getFullName();
				custAddress = custList.get(i).getAddress();
				custPhone = custList.get(i).getPhoneNumber();
			}
		}
		//	push the customer data onto the new screen
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/CustomerInfoPage.fxml"));
			AnchorPane registerRoot;
			try {
				registerRoot = (AnchorPane) loader.load();
				Scene registerScene = new Scene(registerRoot);
				custInfoPageStage = new Stage();
				custInfoPageStage.setScene(registerScene);
				custInfoPageController = (CustomerInfoPageController) loader.getController(); // this find the controller object
				custInfoPageController.setController(this); //this links the controller 1 and controller 2
				custInfoPageController.setCustIDLabel(Integer.toString(customerID));
				custInfoPageController.setCustAddressLabel(custAddress);
				custInfoPageController.setCustNameLabel(custName);
				custInfoPageController.setCustPhoneLabel(custPhone);
				custInfoPageStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}

	//	getter methods for the list views
	@FXML
	public ListView getUnfilledListView() {
		return unfilledOrderListView;
	}
	@FXML
	public ListView getFilledListView() {
		return filledOrderListView;
	}

	@FXML
	public void placeNewOrderButtonClick(ActionEvent event) {
		// TODO Autogenerated
		if (orderPageStage == null) { // make sure that the window only pop out one time
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/OrderPage.fxml"));
			AnchorPane registerRoot;
			try {
				registerRoot = (AnchorPane) loader.load();
				Scene registerScene = new Scene(registerRoot);
				orderPageStage = new Stage();
				orderPageStage.setScene(registerScene);
				orderPageController = (OrderPageController) loader.getController(); // this find the controller object
				orderPageController.setCallingController(this); //this links the controller 1 and controller 2
				orderPageController.setLoginController(this.callingController);
				//RNCController.setPageHistoryController(this);
				orderPageStage.show();
				//orderHistoryPane.getScene().getWindow().hide();// hide the current window
				ArrayList<customer> customerList = callingController.getCustList();
				orderPageController.customerInitialize(customerList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	//	function to fill orders
	@FXML
	public void fillSelectedOrderButtonClick(ActionEvent event) {
		ArrayList<Order> orderList = callingController.getOrderList();
		
		//	gets the selected item from list view and splits it on commas
		listViewOrderInfo = ((String) unfilledOrderListView.getSelectionModel().getSelectedItem()).split(",");
		
		int orderID = Integer.parseInt(listViewOrderInfo[0]);
		
		//	go through the order list and where the order ID is the same as the user selceted set status to true
		for (int i = 0; i < orderList.size(); i++) {
			if (orderList.get(i).getOrderID() == orderID) {
				
				orderList.get(i).setStatus(true);
			}
		}
		
		//	initialize varaibles and add orders to filled or unfilled lists, sort them, add them to list view
		ArrayList<Order> filledOrderList = new ArrayList<>();
		ArrayList<Order> unfilledOrderList = new ArrayList<>();
		ArrayList<String> filledInfoList = new ArrayList<>();
		ArrayList<String> unfilledInfoList = new ArrayList<>();
		
		for (int i = 0; i < orderList.size(); i++) {
			if (orderList.get(i).getStatus() == true) {
				filledOrderList.add(orderList.get(i));
				filledInfoList.add(orderList.get(i).toString());
			}
			else {
				unfilledOrderList.add(orderList.get(i));
				unfilledInfoList.add(orderList.get(i).toString());
			}
		}
		
		Collections.sort(filledInfoList);
		Collections.sort(unfilledInfoList);
		
		ObservableList<String> filledOrders = FXCollections.observableArrayList(filledInfoList);
		ObservableList<String> unfilledOrders = FXCollections.observableArrayList(unfilledInfoList);
		
		filledOrderListView.setItems(filledOrders);
		unfilledOrderListView.setItems(unfilledOrders);
	}
	
	//	function to save all orders and order information to text file
	@FXML
	public void saveOrdersButtonClick(ActionEvent event) throws IOException {
		File file = new File("Order_Info.txt");
		
		PrintWriter pw = new PrintWriter(new FileOutputStream(file));
		FileOutputStream fos = new FileOutputStream(file);
		
		for (int i = 0; i < callingController.getOrderList().size(); i++) {
			pw.write(callingController.getOrderList().get(i).toString() + "\n");
		}
		pw.close();
		fos.close();
	}
	
	//get the order page stage
	public Stage getPageStage() {
		return orderPageStage;
	}
}
