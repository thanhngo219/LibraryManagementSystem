package com.library.addbook;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.library.business.Author;
import com.library.business.Book;
import com.library.business.BookCopy;
import com.library.business.BookType;
import com.library.business.BorrowDay;
import com.library.business.Library;
import com.library.business.User;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class BookController implements Initializable {

	@FXML
	private TextField txtTitle;

	@FXML
	private TextField txtISBNNumber;

	@FXML
	private ComboBox<Author> cbbAuthors;

	@FXML
	private ComboBox<BookType> cbbBookType;

	@FXML
	private ComboBox<BorrowDay> cbbBorrowDay;

	@FXML
	private TableView<Book> tbvBook;

	@FXML
	private Button btnAddBook;

	private User currentUser;

	public void initData(User currentUser) {
		this.currentUser = currentUser;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbbBookType.getItems().addAll(BookType.values());
		cbbBorrowDay.getItems().addAll(BorrowDay.values());
		cbbAuthors.getItems().addAll(Library.getAuthors());

		TableColumn<Book, String> bookTitleCol = new TableColumn<>(String.format("Title"));
		bookTitleCol.setMinWidth(140);
		bookTitleCol.setEditable(false);
		bookTitleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		bookTitleCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<Book, String> bookISBNCol = new TableColumn<>(String.format("ISBN"));
		bookISBNCol.setMinWidth(80);
		bookISBNCol.setEditable(false);
		bookISBNCol.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
		bookISBNCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<Book, String> borrowDayCol = new TableColumn<>(String.format("Author"));
		borrowDayCol.setMinWidth(120);
		borrowDayCol.setEditable(false);
		borrowDayCol.setCellValueFactory(new PropertyValueFactory<Book, String>("authorName"));
		borrowDayCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<Book, String> authorCol = new TableColumn<>(String.format("Borrow Day"));
		authorCol.setMinWidth(120);
		authorCol.setEditable(false);
		authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("strBorrowDay"));
		authorCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<Book, String> bookTypeCol = new TableColumn<>(String.format("Book Type"));
		bookTypeCol.setMinWidth(120);
		bookTypeCol.setEditable(false);
		bookTypeCol.setCellValueFactory(new PropertyValueFactory<Book, String>("strBookType"));
		bookTypeCol.setCellFactory(TextFieldTableCell.forTableColumn());
		tbvBook.getColumns().setAll(bookTitleCol, bookISBNCol, authorCol, bookTypeCol, borrowDayCol);

		List<Book> list = Library.getBooks();
		tbvBook.setItems(FXCollections.observableArrayList(list));
	}

	public void addBook(ActionEvent event) {
		String title = txtTitle.getText();
		String isbnNumber = txtISBNNumber.getText();

		Author author = cbbAuthors.getValue();
		BookType bookType = cbbBookType.getValue();
		BorrowDay borrowDay = cbbBorrowDay.getValue();

		if (title.isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Title cannot be empty", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			return;
		}

		if (isbnNumber.isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "ISBN cannot be empty", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			return;
		}

		if (author == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Author cannot be empty", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			return;
		}

		if (bookType == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Book Type cannot be empty", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			return;
		}

		if (borrowDay == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Borrow day cannot be empty", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			return;
		}

		Book newBook = Library.newBook(isbnNumber);
		if (newBook == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "ISBN already existed", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			return;
		}
		newBook.setTitle(title);
		newBook.getAuthors().add(author);
		newBook.setBookType(bookType);
		newBook.setBorrowDay(borrowDay);

		BookCopy newBookCopy = Library.newBookCopy(newBook);
		newBook.getBookCopies().add(newBookCopy);

		Alert alert = new Alert(Alert.AlertType.INFORMATION, "New Book is added", ButtonType.OK);
		alert.setHeaderText(null);
		alert.showAndWait();

		Library.write();
		tbvBook.setItems(FXCollections.observableArrayList(Library.getBooks()));
	}

}
