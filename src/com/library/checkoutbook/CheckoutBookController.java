package com.library.checkoutbook;

import java.net.URL;
import java.util.ResourceBundle;

import com.library.business.Book;
import com.library.business.BookCopy;
import com.library.business.CheckoutEntry;
import com.library.business.Library;
import com.library.business.Member;

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
import javafx.scene.paint.Color;

public class CheckoutBookController implements Initializable {
	@FXML
	private TextField txtMemberID;

	@FXML
	private Label lblMemberName;

	@FXML
	private TextField txtISBN;

	@FXML
	private Label lblBookTitle;

	@FXML
	private Label lblAvailableCopies;

	@FXML
	private TableView<CheckoutEntry> tableCheckoutEntries;

	public TableView<CheckoutEntry> getTableCheckoutEntries() {
		return tableCheckoutEntries;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableCheckoutEntries.setEditable(false);

		TableColumn<CheckoutEntry, String> bookTitleCol = new TableColumn<>(String.format("Book Title"));
		bookTitleCol.setMinWidth(400);
		bookTitleCol.setEditable(false);
		bookTitleCol.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, String>("bookTitle"));
		bookTitleCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<CheckoutEntry, String> bookISBNCol = new TableColumn<>(String.format("ISBN"));
		bookISBNCol.setMinWidth(80);
		bookISBNCol.setEditable(false);
		bookISBNCol.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, String>("bookISBN"));
		bookISBNCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<CheckoutEntry, String> bookCopyIdCol = new TableColumn<>(String.format("Copy Id"));
		bookCopyIdCol.setMinWidth(80);
		bookCopyIdCol.setEditable(false);
		bookCopyIdCol.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, String>("bookCopyId"));
		bookCopyIdCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<CheckoutEntry, String> checkoutDateCol = new TableColumn<>(String.format("Checkout Date"));
		checkoutDateCol.setMinWidth(120);
		checkoutDateCol.setEditable(false);
		checkoutDateCol.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, String>("checkoutDateString"));
		checkoutDateCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<CheckoutEntry, String> dueDateCol = new TableColumn<>(String.format("Due Date"));
		dueDateCol.setMinWidth(120);
		dueDateCol.setEditable(false);
		dueDateCol.setCellValueFactory(new PropertyValueFactory<CheckoutEntry, String>("dueDateString"));
		dueDateCol.setCellFactory(TextFieldTableCell.forTableColumn());

		tableCheckoutEntries.getColumns().addAll(bookTitleCol, bookISBNCol, bookCopyIdCol, checkoutDateCol, dueDateCol);
	}

	private Member getMember() {
		String sMemberId = txtMemberID.getText().trim();
		if (sMemberId.length() == 0) {
			Alert alert = new Alert(AlertType.ERROR, "Please input Member ID", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			lblMemberName.setText("");
			tableCheckoutEntries.getItems().clear();
			return null;
		}
		int memberId;
		try {
			memberId = Integer.parseInt(sMemberId);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, "Invalid Member ID: " + sMemberId, ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			lblMemberName.setText("");
			tableCheckoutEntries.getItems().clear();
			return null;
		}
		Member member = Library.getMember(memberId);
		if (member == null) {
			Alert alert = new Alert(AlertType.ERROR, "Cannot find Member with ID: " + memberId, ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			lblMemberName.setText("");
			tableCheckoutEntries.getItems().clear();
		} else {
			lblMemberName.setText(member.getFirstName() + " " + member.getLastName());
			tableCheckoutEntries
					.setItems(FXCollections.observableArrayList(member.getCheckoutRecord().getCheckoutEntries()));
		}

		return member;
	}

	private Book getBook() {
		String sISBN = txtISBN.getText().trim();
		if (sISBN.length() == 0) {
			Alert alert = new Alert(AlertType.ERROR, "Please input Book ISBN", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			lblBookTitle.setText("");
			lblAvailableCopies.setText("");
			return null;
		}
		Book book = Library.getBook(sISBN);
		if (book == null) {
			Alert alert = new Alert(AlertType.ERROR, "Cannot find Book with ISBN: " + sISBN, ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			lblBookTitle.setText("");
			lblAvailableCopies.setText("");
		} else {
			lblBookTitle.setText(book.getTitle());
			int count = book.countAvailableBookCopies();
			if (count == 0) {
				lblAvailableCopies.setText("No copies available");
				lblAvailableCopies.setTextFill(Color.web("#ff0000"));
			} else if (count == 1) {
				lblAvailableCopies.setText("1 available copy");
				lblAvailableCopies.setTextFill(Color.web("#2e7d32"));
			} else {
				lblAvailableCopies.setText(book.countAvailableBookCopies() + " available copies");
				lblAvailableCopies.setTextFill(Color.web("#2e7d32"));
			}
		}

		return book;
	}

	public void searchMember(ActionEvent event) {
		getMember();
	}

	public void searchBook(ActionEvent event) {
		getBook();
	}

	public void checkoutBook(ActionEvent event) {
		Member member = getMember();
		if (member == null) {
			return;
		}
		Book book = getBook();
		if (book == null) {
			return;
		}

		BookCopy bookCopy = book.getAvailableBookCopy();
		if (bookCopy == null) {
			Alert alert = new Alert(AlertType.ERROR, "No more available copy for Book: " + book.getTitle(),
					ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			return;
		}

		member.checkOut(bookCopy);

//		bookCopy.setAvailable(false);

		Library.write();

		getBook();
		getMember();
	}

}
