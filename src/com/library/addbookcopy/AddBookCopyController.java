package com.library.addbookcopy;

import com.library.business.Book;
import com.library.business.BookCopy;
import com.library.business.Library;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AddBookCopyController {

	@FXML
	private TextField txtISBN;

	@FXML
	private Label lblBookTitle;

	@FXML
	private Button btnAddBookCopy;

	@FXML
	private TableView<BookCopy> tableBookCopies;

	public TableView<BookCopy> getTableBookCopies() {
		return tableBookCopies;
	}

	private Book getBook() {
		String sISBN = txtISBN.getText().trim();
		if (sISBN.length() == 0) {
			Alert alert = new Alert(AlertType.ERROR, "Please input Book ISBN", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			lblBookTitle.setText("");
			tableBookCopies.getItems().clear();
			return null;
		}
		Book book = Library.getBook(sISBN);
		if (book == null) {
			Alert alert = new Alert(AlertType.ERROR, "Cannot find Book with ISBN: " + sISBN, ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			lblBookTitle.setText("");
			tableBookCopies.getItems().clear();
		} else {
			lblBookTitle.setText(book.getTitle());
			tableBookCopies.setItems(FXCollections.observableArrayList(book.getBookCopies()));
		}

		return book;
	}

	public void lookUp(ActionEvent event) {
		getBook();
	}

	public void addBookCopy(ActionEvent event) {
		Book book = getBook();
		if (book == null) {
			return;
		}

		Library.newBookCopy(book);

		Library.write();

		getBook();
	}

}
