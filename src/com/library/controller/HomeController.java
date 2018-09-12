package com.library.controller;

import com.library.business.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {
	
	@FXML
	private Button btnAddMember;
	
	@FXML
	private Button btnEditMember;
	
	@FXML
	private Button btnAddBook;
	
	@FXML
	private Button btnCheckoutBook;
	
	@FXML
	private Button btnAddBookCopy;

	private User currentUser;

	void initData(User currentUser) {
		this.currentUser = currentUser;
	}
	
	public void addMember(ActionEvent event) {
		 System.out.println("addMember!");
	}
	
	public void editMember(ActionEvent event) {
		 System.out.println("editMember!");
	}
	
	public void addBooks(ActionEvent event) {
		System.out.println("addBooks!");
	}

	public void addBook(ActionEvent event) {
		System.out.println("addBooks!");
	}

	
	public void checkoutBooks(ActionEvent event) {
		System.out.println("checkoutBooks!");
	}

	public void disableBtnAddMember(boolean value) {
		btnAddMember.setDisable(value);
	}

	public void disableBtnEditMember(boolean value) {
		btnEditMember.setDisable(value);
	}

	public void disableBtnAddBook(boolean value) {
		btnAddBook.setDisable(value);
	}

	public void disableBtnAddCheckoutBook(boolean value) {
		btnCheckoutBook.setDisable(value);
	}

	public void disableBtnAddBookCopy(boolean value) {
		btnAddBookCopy.setDisable(value);
	}
}
