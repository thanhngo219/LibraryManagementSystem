package com.library.controller;

import com.library.business.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
	
	public void onLoginClick(ActionEvent event) throws Exception {
		String id = txtID.getText();
		String password = txtPassword.getText();
		
		// Implement check ID and password 
		UserDao userDao = new UserDao();
		boolean temp = userDao.login(id, password);
		if(temp) {
			Parent root = FXMLLoader.load(getClass().getResource("../gui/login.fxml"));
			Stage stage = new Stage();
			stage.setScene(root.getScene());
			stage.show();
			((Node)(event.getSource())).getScene().getWindow().hide();
		}
		System.out.println(temp);
	}
	
	
	
	
}
