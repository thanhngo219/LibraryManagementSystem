package com.library.logincontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	
	@FXML
	private Label lblID;
	
	@FXML
	private Label lblPassword;
	
	@FXML
	private Label lblLogin;
	
	@FXML
	private TextField txtID;
	
	@FXML
	private PasswordField txtPassword;
	
	@FXML
	private Button btnSubmit;
	
	public void Login(ActionEvent event) {
		String id = txtID.getText();
		String password = txtPassword.getText();
		
		// Implement check ID and password 
		 System.out.println("Hello World!");
	}
	
	
	
	
}
