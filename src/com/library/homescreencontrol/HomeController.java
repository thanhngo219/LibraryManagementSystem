package com.library.homescreencontrol;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {
	
	@FXML
	private Button btnAddMember;
	
	@FXML
	private Button btnEditMember;
	
	@FXML
	private Button btnaddBooks;
	
	@FXML
	private Button btncheckoutBooks;
	
	
	public void addMember(ActionEvent event) {
		 System.out.println("addMember!");
	}
	
	public void editMember(ActionEvent event) {
		 System.out.println("editMember!");
	}
	
	public void addBooks(ActionEvent event) {
		System.out.println("addBooks!");
	}

	
	public void checkoutBooks(ActionEvent event) {
		System.out.println("checkoutBooks!");
	}
}
