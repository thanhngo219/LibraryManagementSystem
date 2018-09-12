package com.library.checkoutbook;

import java.time.LocalDate;

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
		int memberId;
		try {
			memberId = Integer.parseInt(txtMemberID.getText().trim());
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR, "Invalid Member ID: " + txtMemberID.getText().trim(),
					ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			return null;
		}
		Member member = Library.getMember(memberId);
		if (member == null) {
			Alert alert = new Alert(AlertType.ERROR, "Cannot find Member with ID: " + memberId, ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
		} else {
			lblMemberName.setText(member.getFirstName() + " " + member.getLastName());
			tableCheckoutEntries
					.setItems(FXCollections.observableArrayList(member.getCheckoutRecord().getCheckoutEntries()));
		}

		return member;
	}

	private Book getBook() {
		Book book = Library.getBook(txtISBN.getText().trim());
		if (book == null) {
			Alert alert = new Alert(AlertType.ERROR, "Cannot find Book with ISBN: " + txtISBN.getText().trim(),
					ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
		} else {
			lblAvailableCopies.setText(book.countAvailableBookCopies() + " available copies");
		}

		return book;
	}

	public void searchMember(ActionEvent event) {
		getMember();
	}

	public void searchBook(ActionEvent event) {
		Book book = getBook();
		if (book != null) {
			lblBookTitle.setText(book.getTitle());
		}
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

		CheckoutEntry checkoutEntry = new CheckoutEntry(bookCopy);
		member.getCheckoutRecord().addCheckoutEntry(checkoutEntry);

//		bookCopy.setAvailable(false);

		Library.write();

		getBook();
		getMember();
	}

	public void close(ActionEvent event) {
		CheckoutBookStage.INSTANCE.hide();
	}

}
