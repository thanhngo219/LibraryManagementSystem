package com.library.searchbook;

import java.util.List;

import com.library.business.Book;
import com.library.business.Library;
import com.library.business.SearchBook;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SearchBookController {

	@FXML
	private TextField txtISBN;

	@FXML
	private Label lblBookTitle;

	@FXML
	private TableView<SearchBook> tableSearchBooks;

	public TableView<SearchBook> getTableSearchBooks() {
		return tableSearchBooks;
	}

	public void searchBook(ActionEvent event) {
		String sISBN = txtISBN.getText().trim();
		if (sISBN.length() == 0) {
			Alert alert = new Alert(AlertType.ERROR, "Please input Book ISBN", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			lblBookTitle.setText("");
			tableSearchBooks.getItems().clear();
			return;
		}
		Book book = Library.getBook(sISBN);
		if (book == null) {
			Alert alert = new Alert(AlertType.ERROR, "Cannot find Book with ISBN: " + sISBN, ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			lblBookTitle.setText("");
			tableSearchBooks.getItems().clear();
		} else {
			lblBookTitle.setText(book.getTitle());
			List<SearchBook> list = Library.searchBook(sISBN);
			tableSearchBooks.setItems(FXCollections.observableArrayList(list));
		}
	}
}
