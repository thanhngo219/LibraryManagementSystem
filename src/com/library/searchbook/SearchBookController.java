package com.library.searchbook;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.library.business.Book;
import com.library.business.Library;
import com.library.business.SearchBook;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class SearchBookController implements Initializable {

	@FXML
	private TextField txtISBN;

	@FXML
	private Label lblBookTitle;

	@FXML
	private TableView<SearchBook> tableSearchBooks;

	public TableView<SearchBook> getTableSearchBooks() {
		return tableSearchBooks;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableSearchBooks.setEditable(false);

//		TableColumn<SearchBook, String> bookISBNCol = new TableColumn<>(String.format("ISBN"));
//		bookISBNCol.setMinWidth(60);
//		bookISBNCol.setEditable(false);
//		bookISBNCol.setCellValueFactory(new PropertyValueFactory<SearchBook, String>("isbn"));
//		bookISBNCol.setCellFactory(TextFieldTableCell.forTableColumn());
//
//		TableColumn<SearchBook, String> bookTitleCol = new TableColumn<>(String.format("Book Title"));
//		bookTitleCol.setMinWidth(300);
//		bookTitleCol.setEditable(false);
//		bookTitleCol.setCellValueFactory(new PropertyValueFactory<SearchBook, String>("title"));
//		bookTitleCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<SearchBook, String> bookCopyIdCol = new TableColumn<>(String.format("Copy Id"));
		bookCopyIdCol.setMinWidth(80);
		bookCopyIdCol.setEditable(false);
		bookCopyIdCol.setCellValueFactory(new PropertyValueFactory<SearchBook, String>("copyId"));
		bookCopyIdCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<SearchBook, String> memberFirstNameCol = new TableColumn<>(String.format("First Name"));
		memberFirstNameCol.setMinWidth(150);
		memberFirstNameCol.setEditable(false);
		memberFirstNameCol.setCellValueFactory(new PropertyValueFactory<SearchBook, String>("memberFirstName"));
		memberFirstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<SearchBook, String> memberLastNameCol = new TableColumn<>(String.format("Last Name"));
		memberLastNameCol.setMinWidth(150);
		memberLastNameCol.setEditable(false);
		memberLastNameCol.setCellValueFactory(new PropertyValueFactory<SearchBook, String>("memberLastName"));
		memberLastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<SearchBook, String> checkoutDateCol = new TableColumn<>(String.format("Checkout Date"));
		checkoutDateCol.setMinWidth(120);
		checkoutDateCol.setEditable(false);
		checkoutDateCol.setCellValueFactory(new PropertyValueFactory<SearchBook, String>("checkoutDateString"));
		checkoutDateCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<SearchBook, String> dueDateCol = new TableColumn<>(String.format("Due Date"));
		dueDateCol.setMinWidth(120);
		dueDateCol.setEditable(false);
		dueDateCol.setCellValueFactory(new PropertyValueFactory<SearchBook, String>("dueDateString"));
		dueDateCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<SearchBook, String> overdueCol = new TableColumn<>(String.format("Overdue?"));
		overdueCol.setMinWidth(120);
		overdueCol.setEditable(false);
		overdueCol.setCellValueFactory(new PropertyValueFactory<SearchBook, String>("OverDueString"));
		overdueCol.setCellFactory(TextFieldTableCell.forTableColumn());

		tableSearchBooks.getColumns().addAll(bookCopyIdCol, memberFirstNameCol, memberLastNameCol, checkoutDateCol,
				dueDateCol, overdueCol);
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
