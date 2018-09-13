package com.library.checkoutbook;

import com.library.business.Book;
import com.library.business.BookCopy;
import com.library.business.CheckoutEntry;
import com.library.business.Library;
import com.library.business.Member;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class CheckoutBookController {
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

	public void close(ActionEvent event) {
		CheckoutBookStage.INSTANCE.hide();
	}

}
