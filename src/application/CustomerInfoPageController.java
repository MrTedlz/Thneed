package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;

public class CustomerInfoPageController {
//	Define FXML Variables 
//	Pane
	@FXML
	private AnchorPane custInfoPagePane;
//	Labels
	@FXML
	private Label custNameLabel;
	@FXML
	private Label custAddressLabel;
	@FXML
	private Label custPhoneLabel;
	@FXML
	private Label custIDLabel;
//	Buttons
	@FXML
	private Button closeButton;
	
	
//	Calling OrderHistory Controller
	private OrderHistoryController orderHistoryController;
	
//	Set Controller method
	public void setController(OrderHistoryController c) {
		orderHistoryController = c;
	}
//	Set Customer Name Label Method
	public void setCustNameLabel(String custName) {
		custNameLabel.setText(custName);
	}
//	Set Customer Address Label
	public void setCustAddressLabel(String custAddress) {
		custAddressLabel.setText(custAddress);
	}
//	Set Customer Phone Label
	public void setCustPhoneLabel(String custAddress) {
		custPhoneLabel.setText(custAddress);
	}
//	Set Customer ID Label
	public void setCustIDLabel(String custID) {
		custIDLabel.setText(custID);
	}
//	Close Button Clicked Action
	@FXML
	public void closeButtonClick(ActionEvent event) {
		custInfoPagePane.getScene().getWindow().hide();
	}
}
