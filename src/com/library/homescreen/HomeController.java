package com.library.homescreen;

import com.library.business.User;
import com.library.constant.LibraryConstant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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

	@FXML
	private Button btnSearchBook;
	
	private User currentUser;

	public void initData(User currentUser) {
		this.currentUser = currentUser;
	}

	public void btnAddMemberOnClick(ActionEvent event) throws Exception {
		navigateToNewScreen("../addmember/addMember.fxml", "Add Member");
	}

	public void btnEditMemberOnClick(ActionEvent event) throws Exception {
		navigateToNewScreen("../editMember/editMember.fxml", "Edit Member");
	}

	public void btnAddBookOnClick(ActionEvent event) throws Exception {
		navigateToNewScreen("../addbook/book.fxml", "Add Book");
	}

	public void btnAddBookCopyOnClick(ActionEvent event) throws Exception {
		navigateToNewScreen("../addbookcopy/addbookcopy.fxml", "Add Book Copy");
	}

	public void btnCheckoutBookOnClick(ActionEvent event) throws Exception {
		navigateToNewScreen("../checkoutbook/checkoutbook.fxml", "Checkout Book");
	}

	public void btnSearchBookOnClick(ActionEvent event) throws Exception {
		navigateToNewScreen("../searchbook/searchbook.fxml", "Search Overdue Book");
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

	private void navigateToNewScreen(String fxmlPath, String title) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));

		Parent root = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle(LibraryConstant.APPLICATION_TITLE + " - " + title);
		stage.setResizable(false);
		stage.show();
	}
}
