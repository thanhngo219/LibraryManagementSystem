package com.library.addbookcopy;

import com.library.business.Book;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class BookCopyController {
	
	@FXML
	private Label lblIISBN;
	
	@FXML
	private TextField txtISBN;
	
	@FXML
	private Button btnLookUp;
	
	@FXML
	private Button btnAddBookCopy;
	
	private Book book;
	
	public void lookUp(ActionEvent event) {
		
		String ISBN = txtISBN.getText();
		// implement check isbn from datastore
		
		
		// return true/false
		// True; Lookup button is disable
		
		
		
		btnAddBookCopy.setDisable(false);
		
		
		// False; Show error message and User can look up again
		
	}
	
	public void addBookCopy(ActionEvent event) {
		
		// implement
	}
	
	
	
}
