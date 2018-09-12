package com.library.controller;

import javax.swing.text.TabableView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SearchBookController {
	
	
	@FXML
	private Label lblISBN;

	
	@FXML
	private TextField txtISBNNumber;
	
	@FXML
	private TabableView tblCopyBook;
	
	public void onSearchClick(ActionEvent event) {
		
		String ibsn = txtISBNNumber.getText();
		
		// implement to show tableview that shows all BookCopy of book after searching for Books via ISBN
		
		
		
	}
}
