package com.library.controller;

import com.library.business.Role;
import com.library.business.User;
import com.library.business.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
		User currentUser = userDao.login(id, password);
		if(currentUser != null) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/homescreen.fxml"));
			Parent root = fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Library Management System");
			HomeController homeController = fxmlLoader.<HomeController>getController();
			switch (currentUser.getRole()) {
				case LIBRARIAN: {
					homeController.disableBtnAddMember(true);
					homeController.disableBtnEditMember(true);
					break;
				}
				case ADMIN: {
					homeController.disableBtnAddBook(true);
					homeController.disableBtnAddBookCopy(true);
					homeController.disableBtnAddCheckoutBook(true);
					break;
				}
				default:
					break;

			}
			homeController.initData(currentUser);

			stage.show();
			((Node)(event.getSource())).getScene().getWindow().hide();
		}
	}
	
	
	
	
}
